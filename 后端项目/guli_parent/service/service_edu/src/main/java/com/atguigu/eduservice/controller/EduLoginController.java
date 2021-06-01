package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.LoginQuery;
import com.atguigu.eduservice.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/user")
//登陆注册
public class EduLoginController {
    @Resource
    UserMapper userMapper;
    @PostMapping("/login")
    public R login(@RequestBody LoginQuery loginQuery){
        String username = loginQuery.getUsername();
        String password = loginQuery.getPassword();
        //md5加密
        String mdPassword = DigestUtils.md5Hex(password);
        System.out.println(mdPassword);
        String realPassword = userMapper.getPassword(username);
        if (mdPassword.equals(realPassword)){
            return R.ok().data("token","admin");
        }
        else {
            return R.error();
        }

    }
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2802144128,2530753695&fm=26&gp=0.jpg");
    }

    @PostMapping("/register")
    public R register(@RequestBody LoginQuery loginQuery){
        //md5加密
        String mdPassword = DigestUtils.md5Hex(loginQuery.getPassword());
        loginQuery.setPassword(mdPassword);
        System.out.println(loginQuery);
        String username = loginQuery.getUsername();
        String password = loginQuery.getPassword();
        String phone = loginQuery.getPhone();
        userMapper.addUser(username,password,phone);

        return R.ok();
    }
}
