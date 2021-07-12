package net.jinyiyun.framework.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author zhouyihang
 * @TableName professional
 */
@TableName(value ="professional")
@Data
public class Professional implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职业名称
     */
    @TableField(value = "pro_name")
    private String proName;

    /**
     * 创建时间
     */
    @TableField(value = "created_at",fill= FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at",fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted_at",fill = FieldFill.INSERT)
    private Integer deletedAt;

}