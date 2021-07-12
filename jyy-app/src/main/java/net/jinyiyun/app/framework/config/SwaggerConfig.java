package net.jinyiyun.app.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;

/**
 * @author zhouyihang
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){

        //配置环境
        Profiles profiles = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否启动swagger
                .enable(flag)
                //可配置多个组，需要写多个Docket
                .groupName("test")
                .select()
                //配置扫描包
                .apis(RequestHandlerSelectors.basePackage("net.jinyiyun.app.controller"))
                //过滤扫描包
                //.paths(PathSelectors.ant("net.jinyiyun.app.controller"))
                .build();
    }

    /**
     * 配置swagger-ui/index. html信息
     * @return
     */
    public ApiInfo apiInfo(){
        Contact contact = new Contact("周一航","http://www.baidu.com","2943947830@qq.com");
        return new ApiInfo(
                "魔域"
                , "接口测试"
                , "v1.0"
                , "http://www.baidu.com"
                , contact
                , "Apache 2.0"
                , "http://www.apache.org/licenses/LICENSE-2.0"
                , new ArrayList()
        );
    }
}
