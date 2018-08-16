package com.ecaray.share;


import com.ecaray.basicres.base.BaseActivity;
import com.ecaray.basicres.constant.RouterUri;
import com.luojilab.router.facade.annotation.RouteNode;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/8/14
 */
@RouteNode(path = RouterUri.SHARE_PATH, desc = "分享页面")
public class ShareActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.share_activity;
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initView() {

    }

    @Override
    public void setOnInteractListener() {
    }

    @Override
    public void initPresenter() {
    }


}
