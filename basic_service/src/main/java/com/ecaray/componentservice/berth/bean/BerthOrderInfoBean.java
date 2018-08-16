package com.ecaray.componentservice.berth.bean;

import com.ecar.ecarnetwork.bean.ResBase;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述: 停车订单实体类
 * @author : Eric_Huang
 * 创建时间: 2016/5/12 10:31
 * 修改人:Eric_Huang
 * 修改时间: 2016/5/12 10:31
 */
public class BerthOrderInfoBean extends ResBase implements Serializable {

    /**
     * 正在进行中
     */
    public static final int ORDER_ING = 1;

    /**
     * 已完成
     */
    public static final int ORDER_FINISH = 2;

    /**
     * 未补缴
     */
    public static final int ORDER_NO_PAY = 3;

    @SerializedName(value = "orderId", alternate = "orderid")
    public String orderId;
    public String berthcode;
    public long billduration;

    /**
     * 计费开始时间
     */
    public long billstart;
    public long billend;
    @SerializedName(value = "carnumber", alternate = "carplate")
    public String carnumber;

    /**
     * 车辆类型 cartypename 是在按照车牌查订单时使用的； cartype 其他地方使用
     */
    @SerializedName(value = "cartype", alternate = "cartypename")
    public String cartype;

    /**
     * 车辆类型值
     */
    public int cartypevalue = 0;

    /**
     * 车辆类型 carsizename 是在多笔补缴成功之后返回的页面使用的
     */
    public String carsizename = "";

    public long duration;
    @SerializedName(value = "intime", alternate = "starttime")
    public long intime;
    public long outtime = 0;
    public String sectionname;
    public double shouldpay;
    public String warmtips;

    /**
     * 车辆属性（特殊车辆、白名单、月卡车辆、普通车辆）
     */
    public int carproperty = 0;

    /**
     * 付费方式 （株洲需求）
     */
    @SerializedName("pm")
    public int payMode;

    /**
     * 无感支付
     */
    @SerializedName("aliautopay")
    public int aliautoPay;

    /**
     * 信用代扣
     */
    @SerializedName("zmCreditPay")
    public int zmCreditPay;

    /**
     * 支付类型(新添加，接口没有，赋值)
     */
    public String mPayType = "";

    /**
     * 剩余金额(新添加，接口没有，赋值)
     */
    public String mBalance;

    /**
     * 订单状态 1-进行中 2-已完成 3-未补缴
     */
    public int orderstate;

    /**
     * 欠费笔数
     */
    public int mArrearsCount = 0;

    /**
     * 欠费金额
     */
    public double mArrearsMoney = 0;

    /*------ 合肥需求 --------*/

    /**
     * 打印的类型（合肥重印需要保存对象）
     */
    public int printType;

    /**
     * 订单号码（合肥重印需要保存对象）由于合肥需要生成一维码，之前的订单号数字太长，所以新加了一个订单号
     */
    public String mOrderCode;
    /*------ 合肥需求 --------*/

    /**
     * 渠道类型
     */
    public int channeltype;

    /*------ 株洲需求 --------*/
    /**
     * 车牌颜色
     */
    @SerializedName("carplatecolor")
    public int carplateColor;
    /**
     * 车牌颜色 对应的名字
     */
    @SerializedName("carplatecolorname")
    public String carplateColorName;
    /**
     * 路段id
     */
    @SerializedName("sectionid")
    public String sectionId;

    /*------ 株洲需求 --------*/

    /**
     * 实付金额
     */
    @SerializedName("actualpay")
    public double mActualPay = 0;

    /**
     * 预付金额
     */
    @SerializedName("prepay")
    public double mPrePay = 0;

    /**
     * 支付方式
     */
    @SerializedName("paytype")
    public int mPayChannel;

    /**
     * 退费金额
     */
    @SerializedName("refund")
    public double mRefundMoney = 0;


    public long now;

    public List<BerthOrderInfoBean> data;

    public int getChannelType() {
        return channeltype;
    }

    public int getAliautoPay() {
        return aliautoPay;
    }

    public int getZmCreditPay() {
        return zmCreditPay;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setBerthcode(String berthcode) {
        this.berthcode = berthcode;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public void setCartypevalue(int cartypevalue) {
        this.cartypevalue = cartypevalue;
    }

    public void setCarsizename(String carsizename) {
        this.carsizename = carsizename;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public void setShouldpay(double shouldpay) {
        this.shouldpay = shouldpay;
    }

    public void setCarproperty(int carproperty) {
        this.carproperty = carproperty;
    }

    public String getBerthCode() {
        return berthcode;
    }

    public long getBillduration() {
        return billduration;
    }

    public void setBillduration(long billduration) {
        this.billduration = billduration;
    }

    public long getBillstart() {
        return billstart;
    }

    public void setBillstart(long billstart) {
        this.billstart = billstart;
    }

    public long getBillend() {
        return billend;
    }

    public String getCarNumber() {
        return carnumber;
    }

    public void setCarNumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public void setCarType(String carType) {
        cartype = carType;
    }

    public String getCarType() {
        return cartype;
    }

    public String getCarsizename() {
        return carsizename;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getIntime() {
        return intime;
    }

    public void setIntime(long intime) {
        this.intime = intime;
    }

    public long getOuttime() {
        return outtime;
    }

    public void setOuttime(long outtime) {
        this.outtime = outtime;
    }

    public String getSectionName() {
        return sectionname;
    }

    public void setSectionName(String sectionName) {
        sectionname = sectionName;
    }

    public double getShouldpay() {
        return shouldpay;
    }

    public void setShouldPay(double shouldPay) {
        shouldpay = shouldPay;
    }

    public String getWarmtips() {
        return warmtips;
    }

    public void setWarmtips(String warmtips) {
        this.warmtips = warmtips;
    }

    public String getPayType() {
        return mPayType;
    }

    public void setPayType(String payType) {
        mPayType = payType;
    }

    public String getBalance() {
        return mBalance;
    }

    public void setBalance(String balance) {
        mBalance = balance;
    }

    public int getArrearsCount() {
        return mArrearsCount;
    }

    public void setArrearsCount(int arrearsCount) {
        mArrearsCount = arrearsCount;
    }

    public double getArrearsMoney() {
        return mArrearsMoney;
    }

    public void setArrearsMoney(double arrearsMoney) {
        mArrearsMoney = arrearsMoney;
    }

    public int getPrintType() {
        return printType;
    }

    public void setPrintType(int printType) {
        this.printType = printType;
    }

    public int getCarproperty() {
        return carproperty;
    }

    public String getOrderCode() {
        return mOrderCode;
    }

    public void setOrderCode(String orderCode) {
        mOrderCode = orderCode;
    }

    public boolean isCarParking() {
        return orderstate == ORDER_ING;
    }

    public boolean isCarNoPay() {
        return orderstate == ORDER_NO_PAY;
    }

    public boolean isCarFinish() {
        return orderstate == ORDER_FINISH;
    }

    public boolean isCarArraes() {
        return orderstate == ORDER_NO_PAY;
    }

    public int getOrderState() {
        return orderstate;
    }

    public int getCarTypeValue() {
        return cartypevalue;
    }

    public int getCarplateColor() {
        return carplateColor;
    }

    public void setCarplateColor(int carplateColor) {
        this.carplateColor = carplateColor;
    }

    public String getCarplateColorName() {
        return carplateColorName;
    }

    public void setCarplateColorName(String carplateColorName) {
        this.carplateColorName = carplateColorName;
    }

    public double getPrePay() {
        return mPrePay;
    }

    public void setPrePay(double prePay) {
        mPrePay = prePay;
    }

    public double getActualPay() {
        return mActualPay;
    }

    public void setActualPay(double actualPay) {
        mActualPay = actualPay;
    }

    public double getRefundMoney() {
        return mRefundMoney;
    }

    public void setRefundMoney(double refundMoney) {
        mRefundMoney = refundMoney;
    }

    public int getPayChannel() {
        return mPayChannel;
    }

    public void setPayChannel(int payChannel) {
        mPayChannel = payChannel;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    /*------ 南昌需求 --------*/

    /**
     * 补缴金额
     */
    @SerializedName("arrears")
    private double mOverDueMoney = 0;

    /**
     * 申请时长
     */
    @SerializedName("applyduration")
    private int applyDuration;

    /**
     * 实补金额-结算(一卡通打折结算后)
     */
    @SerializedName("endarrears")
    private double endarrears;

    /**
     * 实退金额-结算(-卡通打折结算后)
     */
    @SerializedName("endrefund")
    private double endrefund;

    /**
     * 是否打折
     */
    @SerializedName("isdiscount")
    private int isdiscount;

    /**
     * 折扣率
     */
    @SerializedName("discountrate")
    private double discountrate;

    /**
     * 第二次支付方式
     */
    @SerializedName("lastpaytype")
    private int lastpaytype;

    /**
     * 实补金额
     */
    @SerializedName("repay")
    private double repay;

    /**
     * 实退金额
     */
    @SerializedName("actuarefund")
    private double actuarefund;

//    //第一次付款方式
//    @SerializedName("prepay")
//    private int perpaytype;

    /*------ 南昌需求 --------*/

    public int getLastPayType() {
        return lastpaytype;
    }

    public int getIsdiscount() {
        return isdiscount;
    }

    public double getDiscountrate() {
        return discountrate;
    }

//    public int getPerpayType() {
//        return perpaytype;
//    }

    public double getRepay() {
        return repay;
    }

    public double getActuaRefund() {
        return actuarefund;
    }

    public double getEndarrears() {
        return endarrears;
    }

    public double getEndrefund() {
        return endrefund;
    }

    public double getOverDueMoney() {
        return mOverDueMoney;
    }

    public double getReFundMoney() {
        return mRefundMoney;
    }

    public int getApplyDuration() {
        return applyDuration;
    }

    /**
     * 1、余额 2、现金 3、微信 4、支付宝 5、洪城一卡通 6、银联卡
     */
    public final static int PAY_BALANCE = 1;
    public final static int PAY_CASH = 2;
    public final static int PAY_WECHAT = 3;
    public final static int PAY_ALI = 4;
    public final static int PAY_ONE_CARD = 5;
    public final static int PAY_UNION_CARD = 6;

    /**
     * 1、预付费 2、后付费
     */
    public final static int PRE_PAY = 1;
    public final static int AFTER_PAY = 2;

    /**
     * 1、停车申请(默认) 2、欠费补缴
     */
    public final static int PARKING_DETAIL_TYPE_PRE = 1;
    public final static int PARKING_DETAIL_TYPE_NORMAL = 2;


    /*------ 上海需求 --------*/
    /**
     * 发票号
     */
    @SerializedName("invoiceno")
    public String invoiceNo;

    /**
     * 发票号
     */
    @SerializedName("waterno")
    public String waterNo;

    /**
     * 停车明细类型
     */
    @SerializedName("payproperty")
    public int parkingDetailType = PARKING_DETAIL_TYPE_PRE;
    /*------ 上海需求 --------*/

    public void setOverDueMoney(double overDueMoney) {
        mOverDueMoney = overDueMoney;
    }
}
