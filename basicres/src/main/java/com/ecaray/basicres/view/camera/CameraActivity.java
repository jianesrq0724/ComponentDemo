package com.ecaray.basicres.view.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.ExifInterface;
import android.media.SoundPool;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.Helper.ComRecogHelper;
import com.Helper.RecogResult;
import com.ecaray.basicres.R;
import com.ecaray.basicres.base.BaseActivity;
import com.ecaray.basicres.base.BaseApplication;
import com.ecaray.basicres.util.LogUtils;
import com.ecaray.basicres.util.PhotoUtils;
import com.ecaray.basicres.util.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 类描述: 自定义相机类
 * @author : Eric_Huang
 * 创建时间: 2016/9/23 15:53
 */
public class CameraActivity extends BaseActivity implements Camera.PreviewCallback {

    public static final String s_PHOTO_CHANNEL = "photo_channel";
    public static final String s_PHOTO_TYPE = "photo_type";

    //拍照类型
    public static final int s_APPLY_PARK = 1;       //停车申请
    public static final int s_CHECK_IN = 2;         //签入
    public static final int s_CHECK_OUT = 3;        //签出
    public static final int s_EQUIPMENT = 4;        //设备维保
    public static final int s_ILLEGAL = 5;          //违章上报
    public static final int s_BERTH_ERROR = 6;      //泊位纠错

    //默认
    public static final int s_DEFAULT = 0;
    //车牌号
    public static final int s_CARPLATE = 1;
    //泊位号
    public static final int s_BERTHCODE = 2;
    //车身
    public static final int s_CARS =3;

    private SurfaceView svCamera;

    //背景
    private RelativeLayout backgroundFl;

    //预览之前显示的Layout
    private LinearLayout llCameraOnpreview;

    //拍照按钮
    private Button btnTakePic;

    //闪关灯按钮
    private Button btnFlashMode;

    //拍照之后显示的Layout
    private LinearLayout llCameraAfterTake;

    //保存图片按钮
    private ImageButton btnSavePic;

    //取消所拍照片
    private ImageButton btnCancelPic;

    //前后置摄像头转换
    private ImageView ivCameraSwitch;

    //返回按钮
    private ImageView ivGoBack;

    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
    private Camera.Parameters mParameters;


    //0代表前置摄像头，1代表后置摄像头
    private int mCameraPosition = 1;


    //拍照图片数据
    private byte[] mPicData = null;


    //车牌号码
    private String mCarPlate;


    //是否需要识别车牌
    private boolean IsRecCarNum;


    //是否为拍照所获得图片
    private boolean IsTake;


    //判断是否能继续拍摄
    private boolean IsAction = false;

    //识别工具类
    private ComRecogHelper mRecHelper4Ecaray;

    //声音
    private SoundPool mSoundPool;
    private int mSound;

    private boolean isGetCarPlatePhoto = false;
    //图片进入渠道
    private int mPhotoChannel;
    //拍照类型
    private int mPhotoType;
    //图片保存路径
    private String mPhotoPath;
    //图片名称
    private String mPhotoName;

    //默认为不进行车牌识别，photoType为0
    public static void startCameraActivity(Activity act, int photoChannel, int requestCode){
        startCameraActivity(act, photoChannel, s_DEFAULT, requestCode);
    }

    /**
     * 开启相机
     * @param photoChannel      图片渠道
     * @param photoType         图片类型（1.车牌进行识别 2.泊位号 3.车身）
     */
    public static void startCameraActivity(Activity act, int photoChannel, int photoType, int requestCode) {
        Intent lIntent = new Intent(act, CameraActivity.class);
        lIntent.putExtra(s_PHOTO_CHANNEL, photoChannel);
        lIntent.putExtra(s_PHOTO_TYPE, photoType);
        act.startActivityForResult(lIntent, requestCode);

    }

    @Override
    public int getLayoutId() {
        return R.layout.camera_activity;
    }

    @Override
    protected void initData() {
        mPhotoChannel = getIntent().getIntExtra(s_PHOTO_CHANNEL, 0);
        mPhotoType = getIntent().getIntExtra(s_PHOTO_TYPE, 0);
        //公司识别
        mRecHelper4Ecaray = ComRecogHelper.getDefault(BaseApplication.getInstance(), true, getString(R.string.pro_name), false);
        initSound();

        initPhotoPath();
        //是否进行识别
        IsRecCarNum = mPhotoType == s_CARPLATE;
    }

    /**
     *  初始化图片保存路径
     */
    private void initPhotoPath() {
        switch (mPhotoChannel){
            //申请停车、违章上报，
            case s_APPLY_PARK:
            case s_ILLEGAL:
                mPhotoPath = PhotoUtils.getPhotoPath(this, PhotoUtils.PHOTO_TYPE_START_PARKING);
                if(mPhotoType == s_CARPLATE){
                    //拍车牌
                    mPhotoName = PhotoUtils.getPhotoName(PhotoUtils.PHOTO_START_PARKING_CARPLATE);
                }else if(mPhotoType == s_BERTHCODE){
                    //拍泊位
                    mPhotoName = PhotoUtils.getPhotoName(PhotoUtils.PHOTO_START_PARKING_BERTHCODE);
                }else if(mPhotoType == s_CARS){
                    //拍车身
                    mPhotoName = PhotoUtils.getPhotoName(PhotoUtils.PHOTO_START_PARKING_CARS);
                }
                break;
            //签到
            case s_CHECK_IN:
                mPhotoPath = PhotoUtils.getPhotoPath(this, PhotoUtils.PHOTO_TYPE_SIGN_IN);
                mPhotoName = PhotoUtils.getPhotoName(PhotoUtils.PHOTO_TYPE_SIGN_IN);
                break;
            //签出
            case s_CHECK_OUT:
                mPhotoPath = PhotoUtils.getPhotoPath(this, PhotoUtils.PHOTO_TYPE_SIGN_OUT);
                mPhotoName = PhotoUtils.getPhotoName(PhotoUtils.PHOTO_TYPE_SIGN_OUT);
                break;
            case s_EQUIPMENT:
                mPhotoPath = PhotoUtils.getPhotoPath(this, PhotoUtils.PHOTO_TYPE_EQUIPMENT);
                mPhotoName = PhotoUtils.getPhotoName(PhotoUtils.PHOTO_TYPE_EQUIPMENT);
                break;
            case s_BERTH_ERROR:
                mPhotoPath = PhotoUtils.getPhotoPath(this, PhotoUtils.PHOTO_TYPE_BERTHCORR);
                mPhotoName = PhotoUtils.getPhotoName(PhotoUtils.PHOTO_TYPE_BERTHCORR);
                break;
            default:
                //若没有以上几种渠道进入，说明未定义，需重新定义
                ToastUtils.showShort(mContext, "未定义拍照渠道类型");
                finish();
        }
    }

    /**
     * 声明控件
     */
    private void assignViews() {
        svCamera = findViewById(R.id.sv_camera);
        backgroundFl = findViewById(R.id.background_fl);
        llCameraOnpreview = findViewById(R.id.ll_camera_onpreview);
        btnTakePic = findViewById(R.id.btn_takepic);
        btnFlashMode = findViewById(R.id.btn_flashmode);
        llCameraAfterTake = findViewById(R.id.ll_camera_aftertake);
        btnSavePic = findViewById(R.id.btn_save_pic);
        btnCancelPic = findViewById(R.id.btn_cancel_pic);
        ivCameraSwitch = findViewById(R.id.iv_camera_switch);
        ivGoBack = findViewById(R.id.iv_go_back);
    }

    private void initHolder() {
        mSurfaceHolder = svCamera.getHolder();
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceHolder.setKeepScreenOn(true);
        mSurfaceHolder.addCallback(mCallback);
    }

    @Override
    public void initView() {
        assignViews();
        initHolder();

        //只有进行车牌识别才显示背景
        if(mPhotoType == s_CARPLATE){
            backgroundFl.setVisibility(View.VISIBLE);
        }else{
            backgroundFl.setVisibility(View.GONE);
        }

        //签到签出显示前置摄像头
        if(mPhotoChannel == s_CHECK_IN || mPhotoChannel == s_CHECK_OUT){
            ivCameraSwitch.setVisibility(View.VISIBLE);
        }else{
            ivCameraSwitch.setVisibility(View.GONE);
        }

        svCamera.setFocusable(true);
        svCamera.setBackgroundColor(Color.TRANSPARENT);
        //显示拍照布局，隐藏保存布局
        llCameraOnpreview.setVisibility(View.VISIBLE);
        llCameraAfterTake.setVisibility(View.GONE);
        btnTakePic.setClickable(true);
        btnTakePic.setEnabled(true);
        btnSavePic.setClickable(true);
        btnSavePic.setEnabled(true);
    }

    @Override
    public void setOnInteractListener() {
        //转换前后置摄像头
        ivCameraSwitch.setOnClickListener(v -> changeCamera());

        //点击开启关闭闪光灯
        btnFlashMode.setOnClickListener(v -> openOrCloseFlash());

        //拍照
        btnTakePic.setOnClickListener(v -> {

            if (IsAction) {
                return;
            }
            takePicture();
        });

        //保存图片
        btnSavePic.setOnClickListener(v -> {
            savePic();
            btnSavePic.setClickable(false);
            btnSavePic.setEnabled(false);
        });

        //取消当前所拍照片
        btnCancelPic.setOnClickListener(v -> {
            //隐藏对应Layout
            IsAction = false;
            llCameraAfterTake.setVisibility(View.GONE);
            llCameraOnpreview.setVisibility(View.VISIBLE);
            if (mCamera != null) {
                mCamera.startPreview();
                mCamera.setPreviewCallback(CameraActivity.this);
            }
        });

        //回退按钮
        ivGoBack.setOnClickListener(v -> finish());

        //点击背景可聚焦
        backgroundFl.setOnClickListener(v -> {
            if (mCamera == null) {
                return;
            }


            mCamera.autoFocus((success, camera) -> {
                if (success) {
//                    initCamera();//实现相机的参数初始化
                }
            });
        });
    }

    @Override
    public void initPresenter() {

    }

    SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            //当surfaceView关闭时，关闭预览并释放资源
            LogUtils.i(TAG, "surfaceDestroyed");
            releaseResource();
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (null == mCamera) {
                try {
                    mCamera = Camera.open();
                    mCamera.setPreviewDisplay(mSurfaceHolder);
                    initCamera();
                    mCamera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                    ToastUtils.show(mContext, e.getMessage(), 2);
                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (holder == null) {
                return;
            }
        }
    };


    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        IsTake = true;

        if (IsAction) {
            mPicData = data;
            IsAction = false;
            mCamera.stopPreview();
            //如果是车牌则调用车牌识别的方法
            if (IsRecCarNum) {
                recognitionCarPlate(data, camera);
            }
        } else {
            //如果是车牌则调用车牌识别的方法
            if (IsRecCarNum) {
                recognitionCarPlate(data, camera);
            }
        }
    }

    /**
     * 车牌识别的方法
     */
    private void recognitionCarPlate(byte[] data, Camera camera) {
        useECARAYRecognition(data, camera);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initHolder();
    }

    /**
     * 亿车车牌识别
     *
     * @param data   字节数组(图片)
     * @param camera 照相机
     */
    private void useECARAYRecognition(byte[] data, Camera camera) {

        if (mRecHelper4Ecaray == null) {
            return;
        }


        mRecHelper4Ecaray.getCarnum(data, camera, new RecogResult() {
            @Override
            public void recogSuccess(String plateNum, byte[] picData) {
                mCarPlate = plateNum;
                mPicData = picData;
                if (!isGetCarPlatePhoto) {
                    savePic();
                    isGetCarPlatePhoto = true;
                }
            }

            @Override
            public void recogFail() {
            }

            @Override
            public void permitionSuccess() {

            }

            @Override
            public void permitionFail() {
                LogUtils.i(TAG, "获取权限失败");
            }
        });

    }

    /**
     * 相机参数的初始化设置
     */
    private void initCamera() {
        mParameters = mCamera.getParameters();
        mParameters.setPictureFormat(ImageFormat.JPEG);
        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        setCameraSize(mParameters);
//        mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);//1连续对焦
//        // 设置JPG照片的质量
        mParameters.setJpegQuality(80);

//        setDisplay(mParameters,mCamera);
        setCameraDisplayOrientation(findFrontFacingCameraID(), mCamera);
        mCamera.setParameters(mParameters);
        mCamera.setPreviewCallback(this);
//        camera.cancelAutoFocus();// 2如果要实现连续的自动对焦，这一句必须加上
    }

    /**
     * 拍照
     */
    private void takePicture() {
        mSoundPool.play(mSound, 1, 1, 0, 0, 1);
        IsAction = true;
        llCameraAfterTake.setVisibility(View.VISIBLE);
        llCameraOnpreview.setVisibility(View.GONE);
        ivCameraSwitch.setVisibility(View.GONE);
    }

    /**
     * 控制图像的正确显示方向
     */
    private void setCameraDisplayOrientation(int cameraId, Camera camera) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = this.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;
        } else {
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    private int findFrontFacingCameraID() {
        int cameraId = -1;
        //Search for the back facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                LogUtils.d(TAG, "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    /**
     * 实现的图像的正确显示
     */
    private void setDisplayOrientation(Camera camera, int i) {
        Method downPolymorphic;
        try {
            downPolymorphic = camera.getClass().getMethod("setDisplayOrientation", int.class);
            if (downPolymorphic != null) {
                downPolymorphic.invoke(camera, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存图片
     */
    private void savePic() {
        if (mPicData != null) {

            if (mPhotoPath == null) {
                return;
            }

            File lFile = new File(mPhotoPath);
            if (!lFile.exists()) {
                lFile.mkdirs();
            }
            final String lPicPath = mPhotoPath.concat(mPhotoName);
            Log.i(TAG, lPicPath);

            Bitmap lBitmap;
            if (IsTake) {
                lBitmap = PhotoUtils.getBitmap(mPicData, mCamera);
            } else {
                lBitmap = BitmapFactory.decodeByteArray(mPicData, 0, mPicData.length);
            }

            lBitmap = rotateBitmapByDegree(lBitmap, getBitmapDegree(mPhotoPath));
            PhotoUtils.compressAndSaveBitmap(lBitmap, lPicPath, 100);
            if (lBitmap != null) {
                lBitmap.recycle();
            }

            //保存数据，退出
            Intent lIntent = new Intent();
            lIntent.putExtra("picPath", lPicPath);
            lIntent.putExtra("carPlate", mCarPlate);
            setResult(Activity.RESULT_OK, lIntent);
            finish();

        }
    }

    private int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
                    bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     * 释放资源
     */
    public void releaseResource() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
            mSurfaceHolder.removeCallback(mCallback);
            mSurfaceHolder = null;
        }
    }

    /**
     * 初始化声音
     */
    private void initSound() {
        if (mSoundPool == null) {
            // 第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
            mSoundPool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 5);
            mSound = mSoundPool.load(Uri.parse("file:///system/media/audio/ui/camera_click.ogg").getPath(), 1);
        }
    }

    /**
     * 改变摄像头
     */
    private void changeCamera() {
        //摄像头个数
        int lCameraCount = Camera.getNumberOfCameras();
        Camera.CameraInfo lCameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < lCameraCount; i++) {
            //得到每一个摄像头的信息
            Camera.getCameraInfo(i, lCameraInfo);
            if (mCameraPosition == 1) {
                //转前置摄像头
                if (lCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    closeFlash();
                    //停掉原来摄像头的预览
                    mCamera.stopPreview();
                    mCamera.setPreviewCallback(null);
                    //释放资源
                    mCamera.release();
                    //取消原来摄像头
                    mCamera = null;
                    //打开当前选中的摄像头
                    mCamera = Camera.open(i);
                    try {
                        //通过surfaceView显示取景画面
                        mCamera.setPreviewDisplay(mSurfaceHolder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //开始预览
                    mCamera.startPreview();
                    mCamera.setDisplayOrientation(90);
                    mCameraPosition = 0;
                    mCamera.setPreviewCallback(this);
                    break;
                }
            } else {
                //转后置摄像头
                if (lCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    mCamera.stopPreview();
                    mCamera.setPreviewCallback(null);
                    mCamera.release();
                    mCamera = null;
                    mCamera = Camera.open(i);
                    try {
                        //通过surfaceView显示取景画面
                        mCamera.setPreviewDisplay(mSurfaceHolder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //开始预览
                    mCamera.startPreview();
                    mCamera.setDisplayOrientation(90);
//                    setCameraDisplayOrientation(this, lCameraInfo.facing, mCamera);
//                    setDisplay(mParameters,mCamera);
                    mCameraPosition = 1;
                    mCamera.setPreviewCallback(this);
                    break;
                }
            }
        }
    }

    /**
     * 打开或者关闭闪光灯
     */
    private void openOrCloseFlash() {
        if (mCamera != null && mParameters != null) {
            Camera.Parameters p = mCamera.getParameters();
            //获取闪光灯的状态
            String lFlashMode = p.getFlashMode();
            //闪光灯状态转换
            if (Camera.Parameters.FLASH_MODE_TORCH.equals(lFlashMode)) {
                closeFlash();
            } else if (Camera.Parameters.FLASH_MODE_OFF.equals(lFlashMode)) {
                openFlash();
            }
        }
    }

    /**
     * 关闭闪光灯
     */
    private void closeFlash() {
        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        mParameters.setExposureCompensation(-1);
        btnFlashMode.setText("打开闪光灯");
        if (mCamera != null) {
            mCamera.setParameters(mParameters);
        }

    }

    /**
     * 打开闪光灯
     */
    private void openFlash() {
        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mParameters.setExposureCompensation(0);

        btnFlashMode.setText("关闭闪光灯");
        if (mCamera != null) {
            mCamera.setParameters(mParameters);
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////设置预览区域////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean isFatty;
    private int mPreWidth;
    private int mPreHeight;

    /**
     * 设置预览扫描区域尺寸
     */
    private void setCameraSize(Camera.Parameters parameters) {
        List<Camera.Size> list = parameters.getSupportedPreviewSizes();
        Camera.Size size;

        DisplayMetrics metric = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metric);

        int width = metric.widthPixels;
        int height = metric.heightPixels;
        if (width * 3 == height * 4) {
            isFatty = true;
        }

        int length = list.size();
        int previewWidth = 480;
        int previewheight = 640;
        int second_previewWidth;
        int second_previewheight;
        if (length == 1) {
            size = list.get(0);
            previewWidth = size.width;
            previewheight = size.height;

        } else {
            for (int i = 0; i < length; i++) {
                size = list.get(i);
                if (isFatty) {
                    if (size.height <= 960 || size.width <= 1280) {

                        second_previewWidth = size.width;
                        second_previewheight = size.height;

                        previewWidth = second_previewWidth;
                        previewheight = second_previewheight;
                    }
                } else {
                    if (size.height <= 960 || size.width <= 1280) {
                        second_previewWidth = size.width;
                        second_previewheight = size.height;
                        if (previewWidth <= second_previewWidth) {
                            previewWidth = second_previewWidth;
                            previewheight = second_previewheight;
                        }
                    }
                }
            }
        }
        mPreWidth = previewWidth;
        mPreHeight = previewheight;
        parameters.setPreviewSize(mPreWidth, mPreHeight);
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseResource();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseResource();
    }

}
