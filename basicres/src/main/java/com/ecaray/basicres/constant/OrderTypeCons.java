package com.ecaray.basicres.constant;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/23
 */

public class OrderTypeCons {

    public static final String EXTRA_ORDER_TYPE = "orderType";

    /**
     * 正常缴费
     */
    public static final int S_NORMAL_PAY = 1;

    /**
     * 单笔欠费补缴
     */
    public static final int S_ARREARS_PAY = 2;

    /**
     * 多笔补缴
     */
    public static final int S_ARREARS_BATCH_PAY = 3;
}
