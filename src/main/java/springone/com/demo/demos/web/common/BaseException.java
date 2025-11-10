package springone.com.demo.demos.web.common;

import lombok.Data;

/**
 * 自定义异常类
 */
public class BaseException extends RuntimeException {
    private final Integer code;

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        this(message,400);
    }
}
