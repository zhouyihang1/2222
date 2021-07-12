package net.jinyiyun.framework.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户表
 * @author dongshixiao
 */
@Data
@TableName(value = "users")
public class User {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码，使用bcrypt加密
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 手机号码
     */
    @TableField(value = "tel")
    private String tel;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 状态：0 正常；1锁定
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 推荐人/上线ID
     */
    @TableField(value = "ref_id")
    private Integer refId;

    /**
     * 上次登录时间
     */
    @TableField(value = "last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 经度 :120.26810200
     */
    @TableField(value = "longitudes")
    private BigDecimal longitudes;

    /**
     * 纬度 :31.567526
     */
    @TableField(value = "latitudes")
    private BigDecimal latitudes;

    /**
     * 记住我令牌
     */
    @TableField(value = "remember_token")
    private String rememberToken;

    /**
     * 微信小程序openid
     */
    @TableField(value = "wx_miniprogram_openid")
    private String wxMiniprogramOpenid;

    /**
     * 微信唯一ID
     */
    @TableField(value = "wx_unionid")
    private String wxUnionid;

    /**
     * 微信App openid
     */
    @TableField(value = "wx_app_openid")
    private String wxAppOpenid;

    /**
     * 微信公众号openid
     */
    @TableField(value = "wx_offiaccount_openid")
    private String wxOffiaccountOpenid;

    /**
     * 创建时间
     */
    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}