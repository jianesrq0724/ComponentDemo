package com.ecaray.login.applike;


import com.ecaray.basicres.constant.RouterUri;
import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * @author Eric
 * @version 1.0
 * @since 2018/3/12
 */

public class loginApplike implements IApplicationLike {

    UIRouter uiRouter = UIRouter.getInstance();

    @Override
    public void onCreate() {
        uiRouter.registerUI(RouterUri.LOGIN_HOST);
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI(RouterUri.LOGIN_HOST);
    }
}
