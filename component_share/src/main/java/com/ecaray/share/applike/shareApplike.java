package com.ecaray.share.applike;


import com.ecaray.basicres.constant.RouterUri;
import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * @author carl
 * @version 1.0
 * @since 2018/8/14
 */

public class shareApplike implements IApplicationLike {

    UIRouter uiRouter = UIRouter.getInstance();

    @Override
    public void onCreate() {
        uiRouter.registerUI(RouterUri.SHARE_HOST);
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI(RouterUri.SHARE_HOST);
    }
}
