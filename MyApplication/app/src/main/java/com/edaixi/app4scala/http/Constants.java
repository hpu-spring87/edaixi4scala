package com.edaixi.app4scala.http;

/**
 * Created by wei-spring on 2015/8/4.
 */
public class Constants {
    public final static String HOST = "http://androidapi.edaixi.com";
    public final static String APP_IMG_BASE_URL = HOST + "/client/v3/";
    public final static String APP_API_BASE_URL = HOST + "/client/v1/";
    public final static String APP_API_BASE_URL_V4 = HOST + "/client/v4/";
    public final static String GET_VERIFY_CODE = APP_API_BASE_URL + "send_smsto?";
    public final static String GET_LOGIN = APP_API_BASE_URL + "bind_userto?";
    public final static String GET_COUPON_LIST = APP_API_BASE_URL_V4 + "get_coupons?";
    public final static String GET_ORDER_LIST = APP_API_BASE_URL + "get_order_list?";
    public static final String GET_USER_INTEGRAL_SHOP = APP_IMG_BASE_URL + "integral_mall?";
}
