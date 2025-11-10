package springone.com.demo.demos.web.interceptor;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import springone.com.demo.demos.web.common.JwtProperties;
import springone.com.demo.demos.web.utile.ThreadLocalUserId;
import springone.com.demo.demos.web.common.BaseException;
import springone.com.demo.demos.web.utile.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("进入拦截器");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = request.getHeader(jwtProperties.getTokenName());

        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecret(), token);
            log.info("用户{}发起请求",claims.get("userId"));
            ThreadLocalUserId.setCurrentId((Integer) claims.get("userId"));
            return true;
        } catch (Exception ex) {
            log.info("验证失败");
            throw new BaseException("token验证失败");
        }
    }


}
