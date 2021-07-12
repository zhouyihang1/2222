package net.jinyiyun.common.exception;

import lombok.Data;
import net.jinyiyun.common.enums.ResultEnum;

/**
 * 自定义异常接口返回
 * @author dongshixiao
 */
@Data
public class AlertException extends RuntimeException {

    /**
     * 我们希望定位的错误更准确，
     * 希望不同的错误可以返回不同的错误码，所以可以自定义一个Exception
     * 注意要继承自RuntimeException，底层RuntimeException继承了Exception,
     * spring框架只对抛出的异常是RuntimeException才会进行事务回滚，
     * 如果是抛出的是Exception，是不会进行事物回滚的
     */
    public AlertException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public AlertException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public AlertException(String msg) {
        super(msg);
        this.code = 1001;
    }

    public AlertException() {
        super("操作失败");
        this.code = 1001;
    }


    private Integer code;
}
