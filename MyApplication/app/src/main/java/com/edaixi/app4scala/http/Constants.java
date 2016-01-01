package com.edaixi.app4scala.http;

/**
 * Created by wei-spring on 2015/8/4.
 */
public class Constants {

    /**
     * 微信支付，分享关键key,id
     **/
    public static final String WXAPP_ID = "wx98a4962c2ef2e5a6";
    public static final String WXAPP_SECRET = "9d46a45c433db35faa15ce02a6d273af";
    public static final String PARTNER_ID = "1229155401";
    public static final String PARTNER_KEY = "93070942c85088fe67bcb06fd2d462ce";
    public static final String PaySignKey = "xsdIJuZ0oB33w3OKJA1brWIDLhzxDhFIs3WfacFkmBlX1hubjKtK36DKVeBzKHiq5qmmVPewe30FZ6ih5FkoQgmMeady3MfJTIuKsz2079kAVNg4eKndyh2K65ozfvhl";
    public static final String TXAPP_ID = "1104311868";
    public static final String TXAPP_KEY = "FbNAt08Yj5fRb6jE";
    public static final String SINAWEIBO_ID = "3983016986";
    public static final String SINAWEIBO_KEY = "67eb4dd0c59d2fcdf7743b8d4a1464";

    /**
     * 全局请求接口URL常量
     **/
    //public final static String HOST = "http://open.edaixi.com";
    public final static String HOST = "http://open17.edaixi.cn:81";
    public final static String APP_IMG_BASE_URL = HOST + "/client/v3/";
    public final static String APP_API_BASE_URL = HOST + "/client/v1/";
    public final static String APP_API_BASE_URL_V4 = HOST + "/client/v4/";

    /**
     * greenDao 数据缓存一些常量
     **/
    public static final String GreenDao_OpenCitys = "green_dao_open_citys";

    /**
     * 支付获取优惠券标识
     */
    public static final int PAY_GET_COUPON = 0;


    // 获取banner地址
    public static final String GET_BANNER_LIST = APP_IMG_BASE_URL + "get_banner_list?";
    // 获取func_button_list地址
    public static final String GET_BANNER_BUTTON_LIST = APP_API_BASE_URL_V4 + "get_category_buttons?";
    //    public static final String GET_BANNER_BUTTON_LIST = APP_IMG_BASE_URL + "get_banner_button_list?";
    // 获取func_button_list地址
    public static final String GET_FUNC_BUTTON_LIST = APP_IMG_BASE_URL + "get_func_button_list?";
    // 获取启动页面广告
    public static final String GET_OPEN_ADS = APP_IMG_BASE_URL + "get_ads_list?";
    // 获取城市列表
    public final static String GET_OPEN_CITY_LIST = APP_API_BASE_URL + "cities?";
    // 获取服务范围
    public final static String GET_OPEN_CITY_AREA = APP_API_BASE_URL + "cities_options?";
    // 判断地址是否在服务范围内
    public final static String GET_CITY_AREA_AVAILABLY = APP_API_BASE_URL + "verify_address?";
    //获取用户信息
    public final static String GET_USER_CENTER_INFO = APP_API_BASE_URL + "user_center_info?";
    public final static String GET_VERIFY_CODE = APP_API_BASE_URL + "send_sms?";             //获取短信验证码
    public final static String GET_VOICE_CODE = APP_API_BASE_URL_V4 + "send_voice_sms?";     //获取语音验证码
    //登录接口
    public final static String GET_LOGIN = APP_API_BASE_URL + "bind_user?";
    //继续下单获取默认信息接口
    public final static String GET_QUICK_ORDER_INFO = APP_API_BASE_URL + "get_quick_order_info?";

    public static final String GET_CITY_DELIVERY_FEE = APP_API_BASE_URL_V4 + "create_order_page?"; //下单
    public static final String GET_TIMES = APP_API_BASE_URL_V4 + "get_service_time?"; //时间控件
    public static final String GET_QUICK_AREA = APP_API_BASE_URL + "get_can_kuaixi_list?"; //快洗支持区域
    public static final String HOTEL_SEARCH = APP_API_BASE_URL_V4 + "search_hotel?"; //酒店接口
    public static final String FEEDBACK_SUBMIT = APP_API_BASE_URL_V4 + "set_feedback?"; //意见反馈接口
    public static final String FEEDBACK_TYPES = APP_API_BASE_URL_V4 + "get_feedback_types?"; //意见反馈类型

    //获取优惠券接口
    public final static String GET_COUPON_LIST = APP_API_BASE_URL_V4 + "get_coupons?";
    //获取支付优惠券接口
    public final static String GET_ORDER_COUPON_INFO = APP_API_BASE_URL_V4 + "get_order_available_coupons_count?";
    //获取个人中心优惠券接口
    public final static String GET_MINE_COUPON_INFO = APP_API_BASE_URL_V4 + "get_coupon_description?";
    //获取余额明细
    public final static String GET_BANLANCE_LIST = APP_API_BASE_URL + "icard_details?";
    //获取常用地址列表
    public final static String GET_COMMONE_ADDRESS_LIST = APP_API_BASE_URL + "get_address_list?";
    // 创建地址
    public final static String GET_ADDRESS_ADD = APP_API_BASE_URL + "create_address?";
    // 更新地址
    public final static String GET_ADDRESS_UPDATE = APP_API_BASE_URL + "update_address?";
    //删除地址条目
    public final static String GET_ADDRESS_DELETE = APP_API_BASE_URL + "delete_address?";
    // 兑换优惠券
    public final static String GET_COUPON_BIND = APP_API_BASE_URL + "bind_coupon?";
    // 获取订单列表
    public final static String GET_ORDER_LIST = APP_API_BASE_URL + "get_order_list?";
    // 获取订单信息
    public final static String GET_ORDER_INFO = APP_API_BASE_URL + "get_order?";
    // 取消订单原因
    public final static String GET_ORDER_CANCLE = APP_API_BASE_URL + "order_cancel_reasons?";
    // 创建订单
    public final static String GET_ORDER_TRADING = APP_API_BASE_URL + "create_order?";
    // 创建订单评论
    public final static String GET_ORDER_COMMENT = APP_API_BASE_URL + "create_order_comment?";
    // 获取订单列表
    public final static String GET_COMMENT_TEXT = APP_API_BASE_URL + "comment_text_list_new?";
    public final static String GET_COMMENT_TEXT_V4 = APP_API_BASE_URL_V4 + "comment_text_list?";
    // 获取订单物流信息
    public final static String GET_ORDER_DELIVERY_INFO = APP_API_BASE_URL + "order_delivery_status_list?";
    // 取消订单
    public final static String GET_ORDER_CANCLED = APP_API_BASE_URL + "cancel_order?";
    //获取投诉订单
    public final static String GET_ORDER_COMPLAIN = APP_IMG_BASE_URL + "get_order_complaints?";
    //提交投诉订单
    public final static String GET_ORDER_COMPLAIN_SET = APP_IMG_BASE_URL + "set_order_complaints?";
    // 获取订单衣物明细
    public final static String GET_ORDER_CLOTHING_DETAIL = APP_API_BASE_URL + "order_clothing?";
    // 获取卡信息
    public final static String GET_EXTRA_ACCOUNT = APP_API_BASE_URL + "get_extra_accounts?";
    // 获取会员卡信息
    public final static String GET_USER_ICARD = APP_API_BASE_URL + "get_icard?";
    // 会员卡充值
    public final static String GET_RECHARGE_PAY = APP_API_BASE_URL + "icard_recharge?";
    // 充值卡充值?
    public final static String GET_BIND_RECHARGE = APP_API_BASE_URL + "bind_recharge?";
    // 绑定会员卡?
    public final static String GET_BIND_ENTITY_CARD = APP_API_BASE_URL + "bind_member_card?";
    // 充值页面充值信息
    public final static String GET_RECHARGE_RULE = APP_API_BASE_URL + "recharge_settings?";
    // 付款
    public final static String GET_ORDER_PAY = APP_API_BASE_URL + "pay_order2?";
    // 付款默认支付方式
    public final static String GET_ORDER_DEFAULT_PAYTYPE = APP_API_BASE_URL_V4 + "get_default_paytype?";
    //获取订单支付信息
    public final static String GET_ORDER_PAY_INFO = APP_API_BASE_URL + "order_pay_info?";
    //获取支付活动信息
    public final static String GET_PAY_ACTIVITY_INFO = APP_API_BASE_URL + "activity_promotions?";
    //邀请好友链接
    public final static String GET_INVITE_FRIENDS_INFO = APP_API_BASE_URL + "get_invite_friends_url?";
    //获取积分商城
    public static final String GET_USER_INTEGRAL_SHOP = APP_IMG_BASE_URL + "integral_mall?";
    //余额商城
    public static final String GET_USER_BALANCE_SHOP = APP_IMG_BASE_URL + "balance_mall?";
    //获取积分明细
    public static final String GET_USER_INTEGRAL_DETAIL = APP_IMG_BASE_URL + "user_credit_logs?";
    //获取登录送积分
    public static final String GET_USER_INTEGRAL_LOGIN = APP_IMG_BASE_URL + "add_point_for_login?";
    //获取评论送积分
    public static final String GET_USER_INTEGRAL_COMMENT = APP_IMG_BASE_URL + "add_point_for_comment?";
    //获取环信登录信息
    public static final String GET_USER_EASECHAT_LOGIN = APP_API_BASE_URL_V4 + "get_user_huanxin_detail?";
    //获取环信排队信息
    public static final String GET_USER_EASECHAT_QUEE = APP_API_BASE_URL_V4 + "get_user_queue_detail?";
    //获取支付送积分
    public static final String GET_USER_INTEGRAL_PAYMENT = APP_IMG_BASE_URL + "add_point_for_payment?";
    //获取非余额送积分
    public static final String GET_USER_INTEGRAL_FEI_BALANCE = APP_IMG_BASE_URL + "add_point_for_feiyuezhifu?";
    // 支付宝测试回调地址
    // public static final String ALIPAY_NOTIFY_URL = "http://payment17.edaixi.cn:81/payment/ali_app_notify";
    // 支付宝线上回调地址
    public static final String ALIPAY_NOTIFY_URL = "http://payment.edaixi.com/payment/ali_app_notify";
    // 微信测试回调地址
    //public static final String WECHAT_NOTIFY_URL = "http://payment17.edaixi.cn:81/payment/wechat_android_v4";
    // 微信新回调地址
    public static final String WECHAT_NOTIFY_URL = "http://payment.edaixi.com/payment/wechat_android_v4";
    // 百度支付测试回调地址
    //public static final String BAIDU_NOTIFY_URL = "http://payment17.edaixi.cn:81/payment/baidu_notify";
    // 百度支付回调地址
    public static final String BAIDU_NOTIFY_URL = "http://payment.edaixi.com/payment/baidu_notify";
}
