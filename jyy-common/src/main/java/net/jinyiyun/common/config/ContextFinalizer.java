package net.jinyiyun.common.config;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * mysql 线程销毁器
 * 为了解决内存泄露: 启动了一个名为[mysql-cj-abandoned-connection-cleanup]的线程，但未能停止它。这很可能会造成内存泄漏。线程的堆栈跟踪
 *
 * @author dongshixiao
 */
@Component
public class ContextFinalizer implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d;
        while (drivers.hasMoreElements()) {
            d = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(d);
                System.err.println("自定义 mysql 线程销毁器 Driver %s deregistered");
            } catch (SQLException sqlException) {
                System.err.printf("自定义 mysql 线程销毁器 Error deregistering driver %s%n", d);
            }
        }

        AbandonedConnectionCleanupThread.uncheckedShutdown();


    }
}
