package net.jinyiyun.app.framework.aspectj;

import net.jinyiyun.app.framework.auth.AppAuth;
import net.jinyiyun.framework.aspectj.AbstractLogAspect;
import net.jinyiyun.framework.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * APP操作日志
 *
 * @author dongshixiao
 */
@Aspect
@Component
public class LogAspect extends AbstractLogAspect {


    /**
     * 配置织入点
     */
    @Override
    @Pointcut("execution(* net.jinyiyun.app.controller..*.*(..))")
    public void logPointCut() {
    }


    /**
     * 方法命名 String比较大小 越小越靠前执行
     */
    @Before("logPointCut()")
    public void aDoBefore(JoinPoint joinPoint) throws Exception {
        setOperatorType(2);
        User login = AppAuth.getLogin();
        if (login == null) {
            setOperatorId(0);
        } else {
            setOperatorId(login.getId());
        }
    }

}
