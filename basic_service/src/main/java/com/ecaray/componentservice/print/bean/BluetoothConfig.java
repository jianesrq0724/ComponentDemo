package com.ecaray.componentservice.print.bean;

import java.io.Serializable;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/31
 */

public class BluetoothConfig implements Serializable {

    /**
     * 打印数据(订单信息对象)
     */
    public static final String EXTRA_DATA_ORDER_INFO = "orderInfo";

    /**
     * 打印数据(每日缴费对象)
     */
    public static final String EXTRA_DATA_CHARGE_BEAN = "chargeBean";


    public static final String EXTRA_BLUETOOTH_CONFIG = "bluetooth_config";


    /**
     * 进入类型
     * 0、设置  1、打印
     */
    public static final int ENTER_TYPE_SETTING = 0x00;
    public static final int ENTER_TYPE_PRINT = 0x01;

    /**
     * 打印类型
     * 0、申请停车打印 1、支付凭条打印 2、当日缴费打印 3、多笔订单缴费 4、单笔订单缴费 5、打印测试 6、停车订单明细打印
     */
    public static final int PRINT_TYPE_APPLY = 0x00;
    public static final int PRINT_TYPE_PAY = 0x01;
    public static final int PRINT_TYPE_DAY_CHARGE = 0x02;
    public static final int PRINT_TYPE_BATCH_PAY = 0x03;
    public static final int PRINT_TYPE_SINGLE_PAY = 0x04;
    public static final int PRINT_TYPE_TEST = 0x05;
    public static final int PRINT_TYPE_EVERY_DAY_ORDER = 0x06;


    /**
     * 进入类型 默认为打印
     */
    private int enterType = ENTER_TYPE_PRINT;

    /**
     * 打印类型 默认为测试
     */
    private int printType = PRINT_TYPE_TEST;


    public int getEnterType() {
        return enterType;
    }

    public void setEnterType(int enterType) {
        this.enterType = enterType;
    }

    public int getPrintType() {
        return printType;
    }

    public void setPrintType(int printType) {
        this.printType = printType;
    }
}
