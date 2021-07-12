package net.jinyiyun.framework.constant;

/**
 * 分页数量
 * @author dongshixiao
 */
public class Constant {
    /**
     * 每页数据行数
     */
    public static final String PAGE_SIZE = "10";
    /**
     * 分页数量参数名
     */
    public static final String PAGE_SIZE_PARAM_NAME = "pageSize";

    /**
     * 页码参数名
     */
    public static final String PAGE_PARAM_NAME = "page";



    /**
     * 手机号未数
     */
    public final static int TEL_LENGTH = 11;


    /**
     * 短信检测 超时时间   单位:分钟
     */
    public final static int TIMELINESS = 15;

    /**
     * JWT鉴权 头部的key
     */
    public final static  String JWT_TOKEN_HEAD_NAME ="Authorization";
}
