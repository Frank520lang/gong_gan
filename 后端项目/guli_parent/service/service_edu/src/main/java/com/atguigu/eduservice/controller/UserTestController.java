package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.UserTest;
import com.atguigu.eduservice.service.UserTestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-24
 */
@RestController
@RequestMapping("/eduservice/usertest")
@CrossOrigin
public class UserTestController {
    @Resource
    private UserTestService userTestService;
    @GetMapping("listUser/{id}")
    public R listUser(@PathVariable Integer id){

        UserTest userTest = userTestService.getById(id);
        List<UserTest> list = userTestService.list(null);
        return R.ok().data("userTest",list);
    }
}

