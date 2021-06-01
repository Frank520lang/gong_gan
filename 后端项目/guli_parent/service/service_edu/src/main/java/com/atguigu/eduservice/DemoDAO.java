package com.atguigu.eduservice;

import com.atguigu.eduservice.entity.vo.DemoData;

import java.util.List;

public class DemoDAO {
    public void save(List<DemoData> list) {
        for(DemoData data:list){
            System.out.println(data);
        }
    }
}
