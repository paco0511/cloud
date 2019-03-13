package com.deepexi.extension;

import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.Iterator;

/**
 * Created by donh on 2018/11/6.
 * 全局异常统一处理
 */
@RestControllerAdvice
public class MyControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Payload errorHandler(Exception ex) {
        return new Payload(null, "500", ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ApplicationException.class)
    public Payload myErrorHandler(ApplicationException ex) {
        return new Payload(null, ex.getCode(), ex.getMessage());
    }

    /**
     * 拦截捕获 @RequestBody 参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Payload validExceptionHandler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new Payload(null, "400", message);
    }

    /**
     * 拦截捕获数据绑定时异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public Payload validExceptionHandler(BindException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new Payload(null, "400", message);
    }

    /**
     * 拦截捕获 @RequestParam 参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Payload validExceptionHandler(ConstraintViolationException ex) {
        Iterator<ConstraintViolation<?>> it = ex.getConstraintViolations().iterator();
        String message = "";
        if (it.hasNext()) {
            message = it.next().getMessageTemplate();
        }
        return new Payload(null, "400", message);
    }

    /**
     * 拦截捕获 @RequestBody required=true 绑定请求参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Payload validExceptionHandler(HttpMessageNotReadableException ex) {
        return new Payload(null, "400", "没有请求体");
    }

    /**
     * 拦截捕获绑定请求参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = UnexpectedTypeException.class)
    public Payload validExceptionHandler(UnexpectedTypeException ex) {
        return new Payload(null, "400", "参数类型不对");
    }

}