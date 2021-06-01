package com.atguigu.eduservice.controller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.vo.DemoData;
import com.atguigu.eduservice.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public class UploadDataListener extends AnalysisEventListener<DemoData> {
    @Autowired
    private FruitService fruitService;
    private static final int BATCH_COUNT = 5;
    List<DemoData> list = new ArrayList<DemoData>();

    /**

     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来

     *

     * @param fruitService

     */

    public UploadDataListener(FruitService fruitService) {

        this.fruitService = fruitService;

    }
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    private void saveData() {
        //将读取到的表格数据保存到数据库中
        fruitService.save(list);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //读完再调一次
        saveData();
    }
}
