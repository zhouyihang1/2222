package net.jinyiyun.app.framework.config;


import net.jinyiyun.app.framework.interceptor.AppJwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dongshixiao
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appJwt())
                // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
                // 是否有NOLogin注解 有则放行
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui.html/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/v2/**");

        // WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * app端口 jwt拦截器处理程序
     */
    @Bean
    public AppJwtInterceptor appJwt() {
        return new AppJwtInterceptor();
    }


    /**
     * 跨域设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .maxAge(36000)
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}
