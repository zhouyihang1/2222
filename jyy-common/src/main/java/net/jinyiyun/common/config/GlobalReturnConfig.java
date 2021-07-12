package net.jinyiyun.common.config;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.jinyiyun.common.core.PageResult;
import net.jinyiyun.common.core.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局返回值统一封装
 *
 * @author dongshixiao
 */
@Configuration
public class GlobalReturnConfig {

    /**
     * 异常状态码 起始值和结束值
     */
    final static Integer START_CODE = 400;
    final static Integer END_CODE = 600;

    @RestControllerAdvice
    static class ResultResponseAdvice implements ResponseBodyAdvice<Object> {
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            // 如果本身就是Result 就不需要再包装
            if (body instanceof Result) {
                Integer code = ((Result) body).getCode();
                if (code >= START_CODE && code <= END_CODE) {
                    serverHttpResponse.setStatusCode(HttpStatus.valueOf(code));
                }
                return body;
            }

            // 如果是Page类型对象 则返回分页数据
            if (body instanceof Page) {
                return new PageResult((Page) body);
            }

//            System.out.println("body类型: " + body.getClass().getName());

            return body;
//            return new Result(body);
        }
    }
}
