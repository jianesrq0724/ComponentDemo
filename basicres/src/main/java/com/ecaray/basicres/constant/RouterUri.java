package com.ecaray.basicres.constant;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/21
 */

public class RouterUri {
//    Uri的格式:scheme://host:port/path

    public static final String SCHEME = "PDA";

    public static final String LOGIN_HOST = "login";

    public static final String APP_HOST = "app";


    /**
     * Main
     */
    public static final String MAIN_PATH = "/mainActivity";

    public static final String MAIN_URI = SCHEME + "://" + APP_HOST + MAIN_PATH;


    /**
     * 登录
     */
    public static final String LOGIN_PATH = "/loginActivity";

    public static final String LOGIN_URI = SCHEME + "://" + LOGIN_HOST + LOGIN_PATH;


    /**
     * 分享
     */
    public static final String SHARE_HOST = "share";

    public static final String SHARE_PATH = "/sysActivity";

    public static final String SHARE_URI = SCHEME + "://" + SHARE_HOST + SHARE_PATH;


}
