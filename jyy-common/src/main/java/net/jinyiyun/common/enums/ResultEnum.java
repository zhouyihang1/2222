package net.jinyiyun.common.enums;

/**
 * HTTP API 返回枚举类型
 *
 * @author dongshixiao
 */
public enum ResultEnum {
    // 操作成功 http 200
    SUCCESS(0, "操作成功"),
    // 操作失败 http 200
    FAIL(-1, "失败"),

    HTTP_BAD_REQUEST(400, "错误请求"),
    HTTP_UNAUTHORIZED(401, "未授权"),
    HTTP_FORBIDDEN(403, "禁止访问"),
    HTTP_NOT_FOUND(404, "未找到"),
    HTTP_EXCEPTION(500, "系统异常");

    /**
     * code错误码
     */
    private Integer code;

    /**
     * 前端进行页面展示的信息
     */
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
