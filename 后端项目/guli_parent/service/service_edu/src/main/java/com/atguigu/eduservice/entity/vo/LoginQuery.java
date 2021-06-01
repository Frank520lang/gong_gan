package com.atguigu.eduservice.entity.vo;

import lombok.Data;

@Data
public class LoginQuery {
    String username;
    String password;
    String repassword;
    String phone;
}
