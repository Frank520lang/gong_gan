package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Temperature;
import com.atguigu.eduservice.service.TemperatureService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/eduservice/temperature")
@CrossOrigin
public class TemperatureController {
    //依赖注入
    @Resource
    TemperatureService temperatureService;

    //12个月月平均的蒸发量，降水量，平均温度
    @GetMapping("other")
    public R getOther() {
        Page page = new Page(0, 12);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("year", "2019");
        temperatureService.page(page, queryWrapper);
        List<Temperature> other = page.getRecords();
        List awayList = new ArrayList();
        List downList = new ArrayList();
        List temperatureList = new ArrayList();
        for (Temperature temperature : other) {
            awayList.add(temperature.getAway());
            downList.add(temperature.getDown());
            temperatureList.add(temperature.getTemperature());
        }
        return R.ok().data("awayList", awayList).data("downList", downList).data("temperatureList", temperatureList);
    }

}

