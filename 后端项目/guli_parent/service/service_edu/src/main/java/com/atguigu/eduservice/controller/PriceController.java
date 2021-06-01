package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Price;
import com.atguigu.eduservice.service.PriceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/eduservice/price")
@CrossOrigin
public class PriceController {

    //依赖注入
    @Resource
    PriceService priceService;

    //最新的数据库连续12条的贡柑市场价格
    //最新的12天的数据
    @GetMapping("getPrice")
    public R getPrice() {
        QueryWrapper queryWrapper = new QueryWrapper();
        //分页条件
        //当前页和每页的数据条数为参数创建分页对象
        Page page = new Page(0, 12);
        //将分页信息封装到page对象中
        priceService.page(page, queryWrapper);
        List<Price> priceData = page.getRecords();
        //创建新的集合存放price数据
        List priceList = new ArrayList();
        //创建新的集合存放day数据
        List dayList = new ArrayList();
        //增强for循环遍历数据库查出来的list集合
        for (Price map : priceData) {
            priceList.add(map.getPrice());
            dayList.add(map.getDay());
        }
        return R.ok().data("priceList", priceList).data("dayList", dayList);
    }
}

