package net.jinyiyun.framework.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author zhouyihang
 * @TableName commodity
 */
@TableName(value ="commodity")
@Data
public class Commodity implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账户标题
     */
    @TableField(value = "account_title")
    private String accountTitle;

    /**
     * 所在区服
     */
    @TableField(value = "local_service")
    private String localService;

    /**
     * 神火等级
     */
    @TableField(value = "surefire_level")
    private String surefireLevel;

    /**
     * 战斗力
     */
    @TableField(value = "fighting_capacity")
    private String fightingCapacity;

    /**
     * 职业
     */
    @TableField(value = "professional")
    private String professional;

    /**
     * 装备属性
     */
    @TableField(value = "equip_properties")
    private String equipProperties;

    /**
     * 售卖价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 微信
     */
    @TableField(value = "wx")
    private String wx;

    /**
     * 备注信息
     */
    @TableField(value = "note")
    private String note;

    /**
     * 图片
     */
    private List<Img> imgs;

    /**
     * 发布人
     */
    @TableField(value = "user_id")
    private Integer userId;

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

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted_at",fill = FieldFill.INSERT)
    private Integer deletedAt;

    /**
     * 点击次数
     */
    @TableField(value = "click_time",fill = FieldFill.INSERT)
    private Integer clickTime;
}