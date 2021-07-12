package net.jinyiyun.framework.aspectj;


/**
 * 日志接口类
 * @author dongshixiao
 */
public interface InterfaceLogAspect {

    /**
     * 必须 配置织入点
     */
    void logPointCut();
}
