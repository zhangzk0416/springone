package springone.com.demo.demos.web.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoginLogAspect.class);

    @After("execution(* springone..login(..))")
    public void loginPointcut() {
        System.out.println("登录成功");
    }
}
