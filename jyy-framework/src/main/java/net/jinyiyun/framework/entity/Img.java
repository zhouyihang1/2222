package net.jinyiyun.framework.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author zhouyihang
 * @TableName img
 */
@TableName(value ="img")
@Data
public class Img implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "iid", type = IdType.AUTO)
    private Integer iid;

    /**
     * 商品名称
     */
    @TableField(value = "comm_title")
    private String commTitle;

    /**
     * 商品图片地址
     */
    @TableField(value = "comm_img_url")
    private String commImgUrl;

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
}