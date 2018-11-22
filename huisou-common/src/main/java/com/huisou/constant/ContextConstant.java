package com.huisou.constant;

/**
 * 用来定影全局变量的
 *
 * @author Administrator
 */
public class ContextConstant {

    //通用状态
    public static final Byte STATE_DISABLE = 10;//禁用,未通过
    public static final Byte STATE_USE = 20;//可使用,通过
    
    //上传异常,701图片为空，702图片超出大小，709图片的类型错误，704 服务器异常
    public static final String IMAGE_NULL = "701";
    public static final String SIZE_EXCEEDS = "702";
    public static final String IMAGE_TYPE = "703";
    public static final String UPLOAD_FAILURE = "704";

    //手机号已存在
    public static final String EXIST_PHONE = "102";
    //用户或角色已存在
    public static final String EXIST_NAME = "102";

    //必传参数为空
    public static final String PARAM_NULL = "401";

    //搜索条件
    public static final String SEARCH_PHONE = "phone";
    public static final String SEARCH_CUSTNAME = "contact";
    public static final String SEARCH_ITEMTYPE = "itemtype";
    public static final String SEARCH_TIEMNAME = "itemname";
    public static final String SEARCH_ALLCUST = null;

    //业务员搜索条件
    public static final String SEARCH_SALEPHONE = "salephone";
    public static final String SEARCH_SALENAME = "salename";

    //session attribute
    public static final String USER_SESSION = "user";
    public static final String CUST_SESSION = "cust";
    public static final String SALE_SESSION = "sale";
    public static final String SALE_TIMESPAN = "timespan";
    public static final String POST_SESSION = "post";

    public static final String SALE_SET_DATE = "saledate";

    //图片的类型,1身份证,2合同找,3营业执照,4发票照片  ,5代表的是doc的上传
    public static final Integer PIC_IDCARD = 1;
    public static final Integer PIC_CONTRACT = 2;
    public static final Integer PIC_LICENSE = 3;
    public static final Integer PIC_BILL = 4;
    public static final Integer doc = 5;

    // 消息类型；1-新增项目；2-需求变更；3-业务员回复；4-需业务员回复
    public static final Integer NOTICETYPE_ADDITEM = 1;
    public static final Integer NOTICETYPE_REQUIRE_CHANGE = 2;
    public static final Integer NOTICETYPE_SALE_REPLY = 3;
    public static final Integer NOTICETYPE_NEED_SALE_REPLY = 4;
    
    //消息接收人类型 （消息类型1/2/3--对应存1，表示客服接收；消息类型为4--对应存2，表示业务员接收）
    public static final Integer NOTICEACCEPTYPE_SERVICE = 1;
    public static final Integer NOTICEACCEPTYPE_SALE = 2;
    
    public static final Integer DAILI_PIC = 6;
    public static final Integer DAILI_FILE = 7;
    public static final Integer ACCIDENT_FILE = 8;
    
    //图片以及文件存储的来源
    public static final String FILES_STEM_RZ = "日志";
    public static final String FILES_STEM_SG = "事故";
    public static final String FILES_STEM_XQBG = "需求变更";


    //图片状态
    public static final Integer PIC_STATUS_EXIST = 0;
    public static final Integer PIC_STATUS_DELETE = 1;

    //token
    public static final String SESSION_TOKEN = "token";
    
    //需求变动记录表里的需求处理状态(dealstatus)，0（默认）未处理；1已处理
    public static final Integer ITEM_REQUIRE_CHANGE_UNTREATED=0;
    public static final Integer ITEM_REQUIRE_CHANGE_PROCESSED=1;

}
