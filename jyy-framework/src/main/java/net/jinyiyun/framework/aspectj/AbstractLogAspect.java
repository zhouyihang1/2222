package net.jinyiyun.framework.aspectj;


import net.jinyiyun.common.utils.JsonUtil;
import net.jinyiyun.common.utils.RequestUtil;
import net.jinyiyun.framework.entity.OperationLog;
import net.jinyiyun.framework.service.OperationLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author dongshixiao
 */
public abstract class AbstractLogAspect implements InterfaceLogAspect {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 接口耗时
     */
    private Long interfaceTimeConsuming;

    /**
     * 日志数据
     */
    private OperationLog log;


    /**
     * 操作类型 操作类型 0.其他 1.后台 2.app
     */
    private Integer operatorType;

    /**
     * 操作人ID
     */
    private Integer operatorId = 0;

    /**
     * 处理完请求前执行
     *
     * @param joinPoint 切点
     */
    @Before("logPointCut()")
    public void bDoBefore(JoinPoint joinPoint) throws Exception {
        // 开始执行时间戳
        interfaceTimeConsuming = System.currentTimeMillis();
        // request对象
        HttpServletRequest request = RequestUtil.getRequest();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        // 创建日志
        log = new OperationLog();
        log.setOperatorType(operatorType);
        log.setUrl(request.getRequestURL().toString());
        String methodName = joinPoint.getSignature().getName();
        log.setMethod(clazzName + " -->" + methodName);
        log.setRequestType(request.getMethod());
        log.setIp(RequestUtil.getRemoteHost(request));
        String input = JsonUtil.obj2string(joinPoint.getArgs());
        if (input != null) {
            log.setInput(input);
        } else {
            log.setInput("");
        }
        log.setErrMsg("");
        log.setHttpCode(200);
        log.setStatus(0);
        log.setMillisecond(0);
        log.setOutput("");
        log.setOperatorId(operatorId);
        log.setCreatedAt(LocalDateTime.now());
    }


    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Exception {
        log.setMillisecond(Math.toIntExact(System.currentTimeMillis() - interfaceTimeConsuming));
    }


    /**
     * 最终通知 始终会走
     */
    @After("logPointCut()")
    public void doAfter() {
        log.setMillisecond(Math.toIntExact(System.currentTimeMillis() - interfaceTimeConsuming));
        operationLogService.save(log);
    }


    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) throws Exception {
        log.setStatus(1);
        log.setErrMsg(e.getMessage());
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}
