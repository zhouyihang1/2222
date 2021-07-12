package net.jinyiyun.common.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 全局接口统一处理
 *
 * @author dongshixiao
 */
@Data
public class PageResult {
    /**
     * 接口调用成功或者失败
     */
    private Integer code = 0;

    /**
     * 需要传递的信息，例如错误信息
     */
    private String msg = "操作成功";

    /**
     * 需要传递的数据
     */
    private Object data;

    /**
     * 每页数据行数
     */
    private Long pageSize = 10L;

    /**
     * 总计数据条数
     */
    private Long total = 0L;

    /**
     * 总计页码
     */
    private Long totalPage = 0L;


    /**
     * mybatis-plus 页码返回处理
     */
    public PageResult(Page body) {
        // 记录数据
        data = body.getRecords();
        // 每页数
        pageSize = body.getSize();
        // 总数
        total = body.getTotal();
        // 总页码
        double v = body.getTotal() / (body.getSize() * 1.0);
        totalPage = v == 0 ? 1 : (long) Math.ceil(v);
    }
}
