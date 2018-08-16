package com.ecaray.login;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.ecaray.basicres.base.BaseActivity;
import com.ecaray.basicres.constant.RouterUri;
import com.ecaray.basicres.util.DialogUtils;
import com.ecaray.basicres.util.SPKeyUtils;
import com.ecaray.basicres.util.SPUtils;
import com.ecaray.basicres.view.dialog.CustomDialog;
import com.ecaray.login.interfaces.LoginViewI;
import com.ecaray.login.presenter.LoginPresenter;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.router.facade.annotation.RouteNode;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */
@RouteNode(path = RouterUri.LOGIN_PATH, desc = "登录页面")
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginViewI {

    private EditText mLoginUserNameEt;
    private EditText mLoginUserPwEt;
    private TextView mLoginTv;
    private TextView mAppVersionTv;

    //账户id & 密码
    private String mAccount;
    private CustomDialog mCustomDialog;

    private void assignViews() {
        mLoginUserNameEt = findViewById(R.id.login_user_name_et);
        mLoginUserPwEt = findViewById(R.id.login_user_pw_et);
        mLoginTv = findViewById(R.id.login_tv);
        mAppVersionTv = findViewById(R.id.app_version_tv);
    }


    @Override
    public int getLayoutId() {

        return R.layout.login_activity;
    }

    @Override
    protected void initData() {
        mAccount = (String) SPUtils.get(SPKeyUtils.s_ACCOUNT, "");
    }

    @Override
    public void initView() {
        assignViews();
        mAppVersionTv.setText(getString(R.string.login_version_name, BuildConfig.VERSION_NAME));
        //判断账号是否为空
        if (!TextUtils.isEmpty(mAccount)) {
            mLoginUserNameEt.setText(mAccount);
            mLoginUserNameEt.setSelection(mLoginUserNameEt.getText().toString().length());
        }

    }

    @Override
    public void setOnInteractListener() {
        //登录按钮
        mLoginTv.setOnClickListener(view -> {
            UIRouter.getInstance().openUri(mContext, RouterUri.SHARE_URI, new Bundle());
        });

    }

    @Override
    public void initPresenter() {
        mPresenter = new LoginPresenter(mContext, this);
    }


    @Override
    public void onBackPressed() {
        DialogUtils.showExitSystem(mContext);
    }

}
