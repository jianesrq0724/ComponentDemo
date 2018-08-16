package com.ecaray.basicres.base;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.Nullable;

import com.ecar.ecarnetwork.http.api.ApiBox;
import com.ecar.ecarnetwork.util.file.EFileUtil;
import com.ecaray.basicres.BuildConfig;
import com.ecaray.basicres.helper.CrashHandler;
import com.ecaray.basicres.util.SPKeyUtils;
import com.ecaray.basicres.util.SPUtils;
import com.ecaray.basicres.util.Utils;
import com.ecaray.basicres.util.life.ELifeUtil;

import org.litepal.LitePal;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import bugly.ecar.com.ecarbuglylib.util.BuglyUtil;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */

public abstract class BaseApplication extends Application {

    private List<Activity> mActivityList = new LinkedList<>();
    private List<Activity> mPayActivityList = new LinkedList<>();
    protected final int mTimeOut = 30 * 1000;

    protected static BaseApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Utils.init(mApp);


        //全局异常捕捉
        if (BuildConfig.IS_CRASH) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(getApplicationContext());
        }

        //初始化数据库
        LitePal.initialize(mApp);

        //监听程序处于前后台状态
        ELifeUtil.initForegroundCallbacks(mApp);

//        SDKMangageE.init(getInstance());

        if (BuildConfig.BUILD_TYPE.contains("debug") || BuildConfig.BUILD_TYPE.contains("release")) {
            //目前控制正式环境不连接
            return;
        }


    }


    @Nullable
    public static BaseApplication getInstance() {
        return mApp;
    }

    /**
     * 添加Activity 到容器中
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            mActivityList.add(activity);
        }
    }

    /**
     * 移除
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }

    /**
     * 添加支付activity到容器
     */
    public void addPayActivity(Activity activity) {
        if (activity != null) {
            mPayActivityList.add(activity);
        }
    }

    /**
     * 结束Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
                activity.finish();
            }
        }
    }

    /**
     * 遍历所有Activity 并finish
     */
    public void exit() {
        //合肥督查版 清空上一次选择路段的id和name
        SPUtils.put(SPKeyUtils.S_SELECT_SECTION_ID, "");
        SPUtils.put(SPKeyUtils.S_SELECT_SECTION_NAME, "");
        for (Activity activity : mActivityList) {
            activity.finish();
        }
        System.exit(0);
    }

    /**
     * 遍历所有Activity 并quit
     */
    public void quit() {
        for (Activity activity : mActivityList) {
            activity.finish();
        }
    }

    /**
     * 遍历所有Activity 并quit
     */
    public void quitPayActivity() {
        for (Activity activity : mPayActivityList) {
            activity.finish();
        }
    }


    /**
     * 获取Activity 数量
     */
    public int getActivityCount() {
        if (mActivityList != null) {
            return mActivityList.size();
        }
        return 0;
    }

    /*
     * 功能:结束指定类名的Activity。 其位置栈上面的其他activity
     */
    public void finishBeforeActivity(Class<?> cls) {
        int index = -1;
        int size = mActivityList.size();
        for (int i = size - 1; i >= 0; i--) {
            if (mActivityList.get(i).getClass().equals(cls)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = size - 1; i > index; i--) {
                finishActivity(mActivityList.get(i));
            }
        }

    }

    /*
     * 功能:找到最靠近栈顶的Activity
     */
    public Activity getTopActivity(Class<?> cls) {
        int size = mActivityList.size();
        for (int i = size - 1; i >= 0; i--) {
            if (mActivityList.get(i).getClass().equals(cls)) {
                return mActivityList.get(i);
            }
        }
        return null;
    }


    /**
     * 功能:设置当前Activity（堆栈中最后一个压入的）
     */
    public Activity setCurrentActivity(Activity activity) {
        boolean isContains = mActivityList.contains(activity);
        if (isContains) {
            mActivityList.remove(activity);
        }
        mActivityList.add(activity);
        return activity;
    }


}
