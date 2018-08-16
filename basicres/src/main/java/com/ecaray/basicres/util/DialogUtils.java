package com.ecaray.basicres.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.ecaray.basicres.R;
import com.ecaray.basicres.base.BaseApplication;


/**
 * 类描述: dialog 工具
 * 创建人: Eric_Huang
 * 创建时间: 2016/9/29 10:27
 * 修改人:Eric_Huang
 * 修改时间: 2016/9/29 10:27
 */
public class DialogUtils {

    /**
     * 自定义dialog
     */
    public static Dialog showAskDialog(Context context, String title, String message,
                                       DialogInterface.OnClickListener ok, DialogInterface.OnClickListener cancel) {

        return showAskDialog(context, title, message, context.getString(R.string.confirm),
                context.getString(R.string.cancel), ok, cancel);
    }


    public static Dialog showAskDialog(Context context, String text, String okText,
                                       DialogInterface.OnClickListener ok, String cancelText,
                                       DialogInterface.OnClickListener cancel) {
        if (TextUtils.isEmpty(okText)) {
            return showAskDialog(context, cancelText, ok, cancel);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            return builder.setIcon(R.mipmap.park_icon).setTitle(R.string.app_name).setMessage(text).setPositiveButton(okText, ok)
                    .setNegativeButton(cancelText, cancel)
                    .setCancelable(false)
                    .show();
        }
    }

    public static Dialog showAskDialog(Context context, String title, String message, String posText, String negText,
                                       DialogInterface.OnClickListener ok, DialogInterface.OnClickListener cancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setTitle(title).setMessage(message).setPositiveButton(posText, ok)
                .setNegativeButton(negText, cancel)
                .setCancelable(false)
                .show();
    }

    public static AlertDialog showAskDialog(Context context, String message, String posText, String negText,
                                            DialogInterface.OnClickListener ok, DialogInterface.OnClickListener cancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setMessage(message).setPositiveButton(posText, ok)
                .setNegativeButton(negText, cancel)
                .setCancelable(false)
                .show();
    }

    public static Dialog showAskDialog(Context context, String message, DialogInterface.OnClickListener ok, DialogInterface.OnClickListener cancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setIcon(R.mipmap.park_icon).setTitle(R.string.app_name).setMessage(message)
                .setPositiveButton(context.getString(R.string.confirm), ok)
                .setNegativeButton(context.getString(R.string.cancel), cancel)
                .setCancelable(false)
                .show();
    }


    /**
     * 退出系统弹出框
     */
    public static void showExitSystem(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.park_icon)
                .setTitle(R.string.app_name)
                .setMessage(R.string.exit_app).setPositiveButton(R.string.confirm, (dialog, which) -> {
            //退出应用
            BaseApplication.getInstance().exit();
        })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

}
