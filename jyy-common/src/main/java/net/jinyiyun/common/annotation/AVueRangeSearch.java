package net.jinyiyun.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 未登录注解
 * 仅支持字段注解
 *
 * @author dongshixiao
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AVueRangeSearch {
    String relationField();
}
