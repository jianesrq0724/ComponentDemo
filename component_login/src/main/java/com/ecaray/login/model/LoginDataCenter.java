package com.ecaray.login.model;

import com.ecar.ecarnetwork.http.api.ApiBox;
import com.ecaray.basicres.entity.login.LoginBean;
import com.ecaray.basicres.net.HttpParam;
import com.ecaray.basicres.net.HttpUrl;

import java.util.TreeMap;

import io.reactivex.Flowable;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/8/31 10:59
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/8/31 10:59
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class LoginDataCenter {

    public static LoginDataCenter sLoginDataCenter;
    private static LoginService sLoginService;
    private LoginDataCenter() {
    }

    public static synchronized LoginDataCenter getInstance() {
        if (sLoginDataCenter == null) {
            sLoginDataCenter = new LoginDataCenter();
        }
        if(sLoginService==null){
            sLoginService = ApiBox.getInstance().createService(LoginService.class, HttpUrl.Base_Url);
        }
        return sLoginDataCenter;
    }

    /**
     * 登录接口
     * @param account 用户名
     * @param userPw   用户密码
     * @return         Flowable
     */
    public Flowable<LoginBean> login(String account, String userPw){
        String lMETHO_LOGIN = "preLogin";
        TreeMap<String, String> lTreeMap = HttpParam.getNormalParamsMap(lMETHO_LOGIN);
        lTreeMap.put("username", account);
        lTreeMap.put("password", userPw);
        TreeMap<String , String> lEncTreeMap = HttpParam.securityKeyMethodEnc(lTreeMap);
        return sLoginService.login(lEncTreeMap);
    }

}
