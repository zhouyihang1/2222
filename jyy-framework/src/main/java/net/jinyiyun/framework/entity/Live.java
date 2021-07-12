package net.jinyiyun.framework.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author zhouyihang
 * @TableName live
 */
@TableName(value ="live")
@Data
public class Live implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 直播标题
     */
    @TableField(value = "live_title")
    private String liveTitle;

    /**
     * 直播链接
     */
    @TableField(value = "live_link")
    private String liveLink;

    /**
     * 直播图片
     */
    @TableField(value = "live_img")
    private String liveImg;

    /**
     * 创建时间
     */
    @TableField(value = "created_at",fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @TableField(value = "update_at",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted_at",fill = FieldFill.INSERT)
    private Integer deletedAt;
}