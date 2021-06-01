package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Pay;
import com.atguigu.eduservice.service.PayService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/eduservice/pay")
@CrossOrigin
public class PayController {
    //依赖注入
    @Resource
    PayService payService;

    //获取到2019年每个月的pay
    //获取到2020年每个月的pay
    @RequestMapping("getPay")
    public R getPay() {
        List<Pay> payList = payService.list(null);
        List pay = new ArrayList();
        List lastYear = new ArrayList();
        List thisYear = new ArrayList();
        for (Pay p : payList) {
            pay.add(p.getPay());
        }
        for (int j = 0; j < pay.size(); j++) {
            if (j < 12) {
                lastYear.add(pay.get(j));
            } else {
                thisYear.add(pay.get(j));
            }
        }
        return R.ok().data("lastYear", lastYear).data("thisYear", thisYear);

    }

}

