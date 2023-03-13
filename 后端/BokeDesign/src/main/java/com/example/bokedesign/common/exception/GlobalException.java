package com.example.bokedesign.common.exception;

import com.example.bokedesign.common.Result;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


//捕获全局异常
@RestControllerAdvice
public class GlobalException {
   //shiro登录异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)//shiro登录异常
    @ExceptionHandler(value = ShiroException.class)//
    public Result handle(ShiroException e){
        return  Result.fail("401",e.getMessage(),null);
    }

    //运行时异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)//返回一个状态码给前端
    @ExceptionHandler(value = RuntimeException.class)//捕获全局异常
    public Result handle(RuntimeException e)   {
        return  Result.fail(e.getMessage());
    }

    //实体校验时异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }

    //处理Assert异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e) {

        return Result.fail(e.getMessage());
    }

}
