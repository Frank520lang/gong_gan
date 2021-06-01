package com.atguigu.eduservice;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.vo.DemoData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class WriteExcelTest {
    @Test
    public void simpleWrite(){
        String filePath="C:\\Users\\Administrator\\Desktop\\test\\"+System.currentTimeMillis()+".xlsx";
        EasyExcel.write(filePath, DemoData.class).sheet("模板").doWrite(data());
    }

    private List data() {
        List list = new ArrayList<DemoData>();
        DemoData demoData = new DemoData();
        demoData.setSize("large");
        demoData.setSeed("blue");
        demoData.setId(1);
        demoData.setCol("green");
        for (int i=0;i<10;i++){
            list.add(demoData);
        }
        return list;
    }
}
