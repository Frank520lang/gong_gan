package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Fruit;
import com.atguigu.eduservice.entity.vo.DemoData;
import com.atguigu.eduservice.mapper.FruitMapper;
import com.atguigu.eduservice.service.FruitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-03
 */
@Service
public class FruitServiceImpl extends ServiceImpl<FruitMapper, Fruit> implements FruitService {
    Fruit fruit = new Fruit();
    @Resource
    private FruitService fruitService;
    @Override
    public void save(List<DemoData> list) {
        /*fruit.setTreeId(99);
        fruit.setSize("dadda");
        fruit.setColor("heihei");
        fruit.setSeedcase("guangguan");
        fruitService.save(fruit);*/
        for (DemoData demoData:list){
            //System.out.println(demoData);
            fruit.setTreeId(demoData.getId());
            fruit.setSize(demoData.getSize());
            fruit.setColor(demoData.getCol());
            fruit.setSeedcase(demoData.getSeed());
            fruitService.save(fruit);
            //fruit=null;
        }

        /*for (DemoData demoData:list){
            System.out.println(demoData);
        }*/
    }
}
