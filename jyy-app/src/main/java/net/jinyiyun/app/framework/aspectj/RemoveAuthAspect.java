package net.jinyiyun.app.framework.aspectj;

import net.jinyiyun.app.framework.auth.AppAuth;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 用户清理器
 * @author dongshixiao
 */
@Aspect
@Component
public class RemoveAuthAspect {

    /**
     * 配置织入点
     */
    @Pointcut("execution(* net.jinyiyun.app.controller..*.*(..))")
    public void removePointCut() {
    }


    /**
     * 最终通知 始终会走
     */
    @After("removePointCut()")
    public void doAfter() {
        AppAuth.remove();
    }
}
