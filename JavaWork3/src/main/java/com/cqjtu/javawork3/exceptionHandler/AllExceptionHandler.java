package com.cqjtu.javawork3.exceptionHandler;

import com.cqjtu.javawork3.pojo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e){
        System.out.println("异常：" + e.getMessage());
        return Result.error(e.getMessage());
    }
}
