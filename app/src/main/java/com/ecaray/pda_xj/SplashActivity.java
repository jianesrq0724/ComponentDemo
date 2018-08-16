package com.ecaray.pda_xj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ecaray.basicres.constant.RouterUri;
import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //跳转到登录
        UIRouter.getInstance().openUri(SplashActivity.this, RouterUri.LOGIN_URI, new Bundle());

        //跳转main
//        UIRouter.getInstance().openUri(SplashActivity.this, RouterUri.MAIN_URI, new Bundle());

        finish();
    }
}
