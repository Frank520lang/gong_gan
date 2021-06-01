package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Fruit;
import com.atguigu.eduservice.entity.vo.DemoData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-03
 */
public interface FruitService extends IService<Fruit> {

    void save(List<DemoData> list);
}
