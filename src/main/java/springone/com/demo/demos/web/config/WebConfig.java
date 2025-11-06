package springone.com.demo.demos.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册拦截器，并配置拦截和放行的路径
//        registry.addInterceptor(jwtInterceptor)
//                .addPathPatterns("/user/**")
//                .excludePathPatterns(
//                        "/api/login"
//                );
//    }
}
