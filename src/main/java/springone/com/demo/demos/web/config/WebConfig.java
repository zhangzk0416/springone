package springone.com.demo.demos.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springone.com.demo.demos.web.interceptor.JwtInterceptor;

/**
 * 拦截器配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器，并配置拦截和放行的路径
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns(
                        "/user/login"
                );
    }
}
