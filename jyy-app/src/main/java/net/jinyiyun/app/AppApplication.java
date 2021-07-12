package net.jinyiyun.app;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author dongshixiao
 */
@SpringBootApplication(scanBasePackages = {"net.jinyiyun.framework.*", "net.jinyiyun.common.*", "net.jinyiyun.app.*"})
@MapperScan(basePackages = {"net.jinyiyun.**.mapper"})
@EnableSwagger2
@EnableTransactionManagement
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
