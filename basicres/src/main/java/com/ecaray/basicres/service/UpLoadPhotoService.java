package com.ecaray.basicres.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ecar.ecarnetwork.base.manage.RxManage;
import com.ecar.ecarnetwork.bean.ResBase;
import com.ecar.ecarnetwork.http.exception.CommonException;
import com.ecar.ecarnetwork.http.exception.InvalidException;
import com.ecaray.basicres.base.ESubscriber;
import com.ecaray.basicres.net.PubDataCenter;
import com.ecaray.basicres.sql.UpLoadPhotoSql;
import com.ecaray.basicres.util.FileUtils;
import com.ecaray.basicres.util.LogUtils;
import com.ecaray.basicres.util.NetworkUtils;
import com.ecaray.basicres.util.PhotoUtils;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 类描述:
 * 创建人: Eric_Huang
 * 创建时间: 2017/1/6 9:49
 */
public class UpLoadPhotoService extends Service {

    private final String TAG = UpLoadPhotoService.class.getSimpleName();

    //正在上传
    private final int STATE_UPLOADING = 1;
    //没有上传
    private final int STATE_NO_UPLOAD = 2;

    private RxManage mRxManage = new RxManage();
    private NetWorkStateReceive mNetWorkStateReceive;

    //更改图片列表
    private ArrayList<UpLoadPhotoSql> mChangePhotoList = new ArrayList<>();

    //上传图片列表
    private ArrayList<UpLoadPhotoSql> mUpLoadPhotoList = new ArrayList<>();

    private int mState = 0;
    private Thread mThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(mChangePhotoList.size() == 0){
            //列表为空，则从数据库获取
            getDataFromSql();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 从数据库中获取没有添加水印图片数据
     */
    public synchronized void getDataFromSql() {
        if(UpLoadPhotoSql.findNoAddWaterMarkData().size() > 0){
            if(mChangePhotoList.size() == 0){
                mChangePhotoList.addAll(UpLoadPhotoSql.findNoAddWaterMarkData());
                addWaterMark();
            }
        }else{
            LogUtils.i(TAG, "数据库中没有未添加水印数据");
            //判断当前状态，如果不是正在上传，则启动图片上传
            if(mState != STATE_UPLOADING){
                add2UpLoadPhotoList();
            }
        }
    }

    /**
     * 添加水印
     */
    private void addWaterMark() {
        if(mChangePhotoList.size() > 0){
            //循环遍历
            while (mChangePhotoList.size() > 0){
                UpLoadPhotoSql lPhoto = mChangePhotoList.remove(0);
                if(lPhoto != null){

                    //图片不存在则删除数据库中信息
                    File file = new File(lPhoto.getPath());
                    if (!file.exists()) {
                        Log.i(TAG, "图片不存在");
                        UpLoadPhotoSql.delete4SqlWithPath(lPhoto.getPath());
                        continue;
                    }

                    //如果添加水印成功，则更改数据库中的水印标识为true
                    if(PhotoUtils.isAddWaterMarkSuccess(UpLoadPhotoService.this, lPhoto)){
                        UpLoadPhotoSql.changeData2Sql(lPhoto.getOrderId(), true);
                    }
                }
            }

        }else{
            //列表为空，重新从数据库中获取图片
            getDataFromSql();
        }

        //判断当前状态，如果不是正在上传，则启动图片上传
        if(mState != STATE_UPLOADING){
            add2UpLoadPhotoList();
        }
    }

    /**
     * 添加到上传图片列表
     */
    private synchronized void add2UpLoadPhotoList() {
        if(UpLoadPhotoSql.findHadAddWaterMarkData().size() > 0){
            if(mUpLoadPhotoList.size() == 0){
                mUpLoadPhotoList.addAll(UpLoadPhotoSql.findHadAddWaterMarkData());
                dealData();
                setState(STATE_UPLOADING);
            }
        }else{
            Log.i(TAG, "没有已加水印的图片的图片");
            setState(STATE_NO_UPLOAD);
        }
    }

    /**
     * 处理上传图片列表数据
     */
    private void dealData(){
        if(NetworkUtils.isNetworkAvailable()){
            if(mUpLoadPhotoList.size() > 0){
                UpLoadPhotoSql lUpLoadPhotoSql = mUpLoadPhotoList.remove(0);
                upLoadPhoto(lUpLoadPhotoSql);
            }else{
                add2UpLoadPhotoList();
            }
        }
    }

    private void upLoadPhoto(UpLoadPhotoSql lUpLoadPhotoSql){

        mRxManage.clear();

        File file = new File(lUpLoadPhotoSql.getPath());
        if (!file.exists()) {
            Log.i(TAG, "图片不存在");
            UpLoadPhotoSql.delete4SqlWithPath(lUpLoadPhotoSql.getPath());
            dealData();
            return;
        }

        ESubscriber<ResBase> lESubscriber = new ESubscriber<ResBase>(this) {
            @Override
            protected void onUserSuccess(ResBase resBase) {
                LogUtils.i("上传图片成功");
                FileUtils.deleteFiles(lUpLoadPhotoSql.getPath());
                UpLoadPhotoSql.delete4SqlWithPath(lUpLoadPhotoSql.getPath());
                dealData();
            }

            @Override
            protected void onCheckNgisFailed(Context context, InvalidException commonException) {

                //上传图片不需要验证，所以会在验证失败中返回
//                if(commonException.getResObj() != null && commonException.getResObj().state == 1){
//                    LogUtils.i("上传图片成功");
//                    FileUtils.deleteFiles(lUpLoadPhotoSql.getPath());
//                    UpLoadPhotoSql.delete4SqlWithOrderId(lUpLoadPhotoSql.getOrderId(), lUpLoadPhotoSql.getType());
//                    dealData();
//                } else {
//                    LogUtils.i("上传图片失败");
//                    dealData();
//                }

                if(commonException != null && commonException.getResObj() != null && "232".equals(commonException.getResObj().code)){
                    LogUtils.i("上传图片成功");
                    FileUtils.deleteFiles(lUpLoadPhotoSql.getPath());
                    UpLoadPhotoSql.delete4SqlWithPath(lUpLoadPhotoSql.getPath());
                    dealData();
                }else{
                    delayDealData();
                }

            }

            @Override
            protected void onUnifiedError(CommonException ex) {
                delayDealData();
            }

            @Override
            protected void onUserError(CommonException ex) {
                delayDealData();
            }
        };

        Disposable lSubscription = PubDataCenter.getInstance().upLoadPic(lUpLoadPhotoSql.getOrderId(), lUpLoadPhotoSql.getType(),
                lUpLoadPhotoSql.getFileName(), file)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(lESubscriber);

        mRxManage.add(lSubscription);
    }

    /**
     * 延迟处理数据
     */
    private void delayDealData() {
        LogUtils.i("上传图片失败");
        mThread = new Thread(() -> {
            try {
                Thread.sleep(15000);
                dealData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        mThread.start();
    }

    private void setState(int state){
        mState = state;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxManage.clear();
        unregisterReceiver(mNetWorkStateReceive);
    }


    /**
     * 注册断网广播接收者
     */
    private void initReceiver() {
        mNetWorkStateReceive = new NetWorkStateReceive();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetWorkStateReceive, filter);
    }

    private class NetWorkStateReceive extends BroadcastReceiver {

        //判断网络
        private final int CONNECT_STATE = 1;
        private final int DISCONNECT_STATE = 0;
        int mConnectState = DISCONNECT_STATE;

        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (gprs.isConnected() || wifi.isConnected()) {
                if(mConnectState != CONNECT_STATE){
                    LogUtils.i(TAG, "NetWorkStateReceive");
                    //监听到网络好了则继续执行上传操作
                    dealData();
                    mConnectState = CONNECT_STATE;
                }
            }else{
                mConnectState = DISCONNECT_STATE;
            }
        }
    }

}
