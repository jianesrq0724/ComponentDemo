package com.ecaray.pda_xj.application;


import com.ecaray.basicres.base.BaseApplication;
import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        UIRouter.getInstance().registerUI("app");

    }
}
