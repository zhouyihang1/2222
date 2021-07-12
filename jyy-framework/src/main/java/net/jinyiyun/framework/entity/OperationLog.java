package net.jinyiyun.framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author dongshixiao
 */
@Data
@TableName(value = "operation_logs")
public class OperationLog {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    /**
     * 操作类型 0.其他 1.后台 2.app
     */
    @TableField(value = "operator_type")
    private Integer operatorType;

    /**
     * 操作人ID
     */
    @TableField(value = "operator_id")
    private Integer operatorId;

    /**
     * 请求的url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 方法名称
     */
    @TableField(value = "`method`")
    private String method;

    /**
     * 请求方式
     */
    @TableField(value = "request_type")
    private String requestType;

    /**
     * ip地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 参数
     */
    @TableField(value = "`input`")
    private String input;

    /**
     * http返回状态码
     */
    @TableField(value = "http_code")
    private Integer httpCode;

    /**
     * 是否异常 0. 正常 1.异常
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 接口运行耗时 单位:毫秒
     */
    @TableField(value = "millisecond")
    private Integer millisecond;

    /**
     * 响应
     */
    @TableField(value = "`output`")
    private String output;

    /**
     * 错误消息
     */
    @TableField(value = "err_msg")
    private String errMsg;

    @TableField(value = "created_at")
    private LocalDateTime createdAt;
}