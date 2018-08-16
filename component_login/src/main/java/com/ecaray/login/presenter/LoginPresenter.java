package com.ecaray.login.presenter;

import android.app.Activity;

import com.ecaray.basicres.base.BasePresenter;
import com.ecaray.basicres.data.LoginData;
import com.ecaray.basicres.entity.login.LoginBean;
import com.ecaray.basicres.util.ObjUtils;
import com.ecaray.basicres.util.SPKeyUtils;
import com.ecaray.basicres.util.SPUtils;
import com.ecaray.login.interfaces.LoginViewI;

/**
 * ===============================================
 * 类描述:
 *
 * @author : Eric_Huang
 *         创建时间: 2016/8/30 14:34
 *         修改人:Eric_Huang
 *         修改时间: 2016/8/30 14:34
 *         修改备注:
 *         ===============================================
 */
public class LoginPresenter extends BasePresenter<LoginViewI> {

    /**
     * 单元测试 采用依赖参数 构造时 一起注入，方便mockito
     *
     * @param context 上下文
     * @param view    view
     */
    public LoginPresenter(Activity context, LoginViewI view) {
        super(context, view);
    }



    /**
     * 保存参数
     *
     * @param loginBean 登录对象
     * @param account   账号
     */
    private void saveParam(LoginBean loginBean, String account, String password) {
        ObjUtils.saveObject(ObjUtils.OBJECT_WRITE_LOGIN, loginBean);
        //重置登录信息
        LoginData.getInstance().reset();
        SPUtils.put(SPKeyUtils.s_ACCOUNT, account);
        SPUtils.put(SPKeyUtils.s_PASSWORD, password);
    }

}
