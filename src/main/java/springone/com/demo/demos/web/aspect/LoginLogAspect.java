package springone.com.demo.demos.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springone.com.demo.demos.web.POJO.VO.LoginVO;
import springone.com.demo.demos.web.POJO.entity.LoginLog;
import springone.com.demo.demos.web.common.Result;
import springone.com.demo.demos.web.dao.LogMapper;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class LoginLogAspect {

    @Autowired
    private LogMapper logMapper;

    @AfterReturning(value = "execution(* springone..login(..))", returning = "result")
    public void loginPointcut(JoinPoint joinPoint, Result<LoginVO> result) {
        log.info("登录成功");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteAddr();

        LoginVO loginVO = result.getData();
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(loginVO.getUsername());
        loginLog.setName(loginVO.getName());
        loginLog.setPhone(loginVO.getPhone());
        loginLog.setIp(ip);
        loginLog.setLoginTime(LocalDateTime.now());
        loginLog.setStatus(1);

        logMapper.insert(loginLog);
    }


}
