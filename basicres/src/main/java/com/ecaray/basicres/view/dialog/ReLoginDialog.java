package com.ecaray.basicres.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ecar.ecarnetwork.interfaces.security.IInvalid;
import com.ecaray.basicres.R;
import com.ecaray.basicres.constant.RouterUri;
import com.luojilab.component.componentlib.router.ui.UIRouter;


/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/9/22 11:01
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/22 11:01
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class ReLoginDialog implements IInvalid {

    public static CustomDialog sReloginDialog;


    @Override
    public void reLogin(Context context, String s) {
        showReLoginDialog(context);
    }

    /**
     * 显示异地登录弹出框
     */
    private void showReLoginDialog(Context context) {
        if (context != null) {
            if (context instanceof Activity) {
                if (sReloginDialog == null) {
                    sReloginDialog = getDialog(context, context.getString(R.string.check_user_expire),
                            context.getString(R.string.relogin), view -> {
                                sReloginDialog.dismiss();
                                //跳转到登录
                                UIRouter.getInstance().openUri(context, RouterUri.LOGIN_URI, new Bundle());
                                sReloginDialog = null;
                            });
                }
            }
        }
    }

    /**
     * 显示未签到弹出框
     *
     * @param msg     提示信息
     * @param btnText 按钮信息
     */
    public static void showReLoginDialog(Context context, String msg, String btnText) {
        if (context != null) {
            if (context instanceof Activity) {
                sReloginDialog = getDialog(context, msg, btnText, view -> {
                    sReloginDialog.dismiss();
                    UIRouter.getInstance().openUri(context, RouterUri.LOGIN_URI, new Bundle());
                    sReloginDialog = null;
                });
            }
        }
    }

    public static CustomDialog getDialog(Context context, String msg, String btnText, View.OnClickListener clickListener) {
        CustomDialog lCustomDialog = new CustomDialog(context);
        lCustomDialog.show();
        lCustomDialog.hidLeftBtn();
        lCustomDialog.setCanceledOnTouchOutside(false);
        lCustomDialog.setCancelable(false);
        lCustomDialog.setMsgText(msg);
        lCustomDialog.setRightBtnText(btnText);
        lCustomDialog.setRightBtnListener(clickListener);
        return lCustomDialog;
    }
}
