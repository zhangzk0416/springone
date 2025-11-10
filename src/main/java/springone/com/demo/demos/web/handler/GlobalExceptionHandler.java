package springone.com.demo.demos.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springone.com.demo.demos.web.common.Result;
import springone.com.demo.demos.web.common.BaseException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Result handlerBaseException(BaseException e){
        log.info("出现异常");
        return Result.fail(401,e.getMessage());
    }
}
