package com.ecaray.login.model;


import com.ecaray.basicres.entity.login.LoginBean;

import java.util.TreeMap;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/8/31 10:42
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/8/31 10:42
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public interface LoginService{

    String lastPath = "data";

    @GET(lastPath)
    Flowable<LoginBean> login(@QueryMap TreeMap<String, String> map);

}
