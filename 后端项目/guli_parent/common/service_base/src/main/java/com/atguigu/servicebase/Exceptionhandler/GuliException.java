package com.atguigu.servicebase.Exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//自定义异常类
@Data//自动生成set,get方法
@AllArgsConstructor //自动生成有参构造方法
@NoArgsConstructor //自动生成无参构造方法
public class GuliException extends RuntimeException{
    //状态码
    private Integer code;
    //异常信息
    private String msg;
}
