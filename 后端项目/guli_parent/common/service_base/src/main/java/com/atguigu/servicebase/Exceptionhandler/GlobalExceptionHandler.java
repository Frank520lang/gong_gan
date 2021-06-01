package com.atguigu.servicebase.Exceptionhandler;


import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

//全局异常
@ControllerAdvice
//使用logback日志插件的注解
@Slf4j
public class GlobalExceptionHandler {
    //指定什么异常出现这个方法
    @ExceptionHandler(Exception.class)
    //@requestBody注解返回数据用
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常");
    }

    //指定什么异常出现这个方法
    @ExceptionHandler(ArithmeticException.class)
    //@requestBody注解返回数据用
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了特定的异常");
    }

    //指定什么异常出现这个方法
    @ExceptionHandler(GuliException.class)
    //@requestBody注解返回数据用
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        return R.error().message(e.getMsg());
    }
}
