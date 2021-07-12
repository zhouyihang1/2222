package net.jinyiyun.common.core;


import lombok.Data;
import net.jinyiyun.common.enums.ResultEnum;
import net.jinyiyun.common.exception.AlertException;

import java.util.List;

/**
 * 全局接口统一处理
 *
 * @author dongshixiao
 */
@Data
public class Result {
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


    public Result(Object body) {
        data = body;
    }

    public Result() {
    }

    public static Result error(AlertException alertException) {
        Result result = new Result();
        result.setCode(alertException.getCode());
        result.setMsg(alertException.getMessage());
        return result;
    }

    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }


    public static Result error(int i, String message) {
        Result result = new Result();
        result.setCode(i);
        result.setMsg(message);
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        ResultEnum resultEnum = ResultEnum.FAIL;
        result.setCode(resultEnum.getCode());
        result.setMsg(message);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        ResultEnum resultEnum = ResultEnum.SUCCESS;
        result.setCode(resultEnum.getCode());
        result.setMsg(result.msg);
        return result;
    }


    public static Result success(String message) {
        Result result = new Result();
        ResultEnum resultEnum = ResultEnum.SUCCESS;
        result.setCode(resultEnum.getCode());
        result.setMsg(message);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        ResultEnum resultEnum = ResultEnum.SUCCESS;
        result.setCode(resultEnum.getCode());
        result.setData(data);
        return result;
    }

}
