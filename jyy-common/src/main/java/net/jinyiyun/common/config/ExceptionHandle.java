package net.jinyiyun.common.config;


import net.jinyiyun.common.core.Result;
import net.jinyiyun.common.enums.ResultEnum;
import net.jinyiyun.common.exception.AlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * 统一异常处理
 *
 * @author dongshixiao
 */
@RestControllerAdvice
public class ExceptionHandle {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 404 错误处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public Result notFoundException(NoHandlerFoundException noHandlerFoundException) {
        return Result.error(ResultEnum.HTTP_NOT_FOUND);
    }


    /**
     * 用户自定义错误处理  允许前端弹出错误
     */
    @ExceptionHandler(value = AlertException.class)
    @ResponseBody
    public Result alertException(AlertException alertException) {
        return Result.error(alertException);
    }


    /**
     * 表单验证不通过
     */
    @ResponseBody
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public Result bindException(Exception e) {
        String errMsg = null;
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            if (bindException.hasErrors()) {
                List<ObjectError> errors = bindException.getAllErrors();
                if (!errors.isEmpty()) {
                    // 这里列出全部错误  只返回第一条错误即可
                    FieldError fieldError = (FieldError) errors.get(0);
                    errMsg = fieldError.getDefaultMessage();
                }
            }
        } else if (e instanceof MethodArgumentNotValidException) {
            // 当使用hibernate.validator包的验证时  会抛出该类型错误 区别对待
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) e;
            FieldError fieldError = validException.getBindingResult().getFieldError();
            errMsg = fieldError != null ? fieldError.getDefaultMessage() : null;
        }
        return Result.error("验证出错:" + errMsg);
    }

    /**
     * 其他错误处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception e) {
        e.printStackTrace();
        logger.error(e.getMessage());
        return Result.error(1001, e.getMessage());
    }


}
