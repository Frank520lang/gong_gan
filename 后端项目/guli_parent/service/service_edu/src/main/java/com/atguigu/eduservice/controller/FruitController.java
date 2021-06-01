package com.atguigu.eduservice.controller;


import com.alibaba.excel.EasyExcel;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Fruit;
import com.atguigu.eduservice.entity.vo.DemoData;
import com.atguigu.eduservice.entity.vo.Down;
import com.atguigu.eduservice.entity.vo.FruitQuery;
import com.atguigu.eduservice.mapper.FruitMapper;
import com.atguigu.eduservice.service.FruitService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-03
 */
@RestController
@RequestMapping("/eduservice/fruit")
@CrossOrigin
public class FruitController {
    //依赖注入
    @Resource
    private FruitService fruitService;
    @Resource
    private FruitMapper fruitMapper;
    //1 分页查询果实信息
    //参数为当前页和每页显示的条数
    @GetMapping("findAllFruit/{current}/{limit}")
    public R findAllFruit(@PathVariable long current,
                          @PathVariable long limit){
        //有参构造函数创建page对象
        Page<Fruit> page = new Page<>(current,limit);
        //分页查询,第一个参数确定分页，第二个参数封装的操作类
        fruitService.page(page,null);
        //查询到数据的集合
        List<Fruit> records = page.getRecords();
        //查询到记录的条数
        long totals = page.getTotal();
        return R.ok().data("records",records).data("totals",totals);
    }
    //2 逻辑删除事务信息
    @DeleteMapping("delete/{fId}")
    public R deleteById(@PathVariable Integer fId){
        boolean flag = fruitService.removeById(fId);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //3 条件查询带分页的果实信息
    @PostMapping("findByCondition/{current}/{limit}")
    public R pageListFruit(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody FruitQuery fruitQuery){
        //System.out.println("运行到这里："+issueQuery);
        //创建分页对象
        Page <Fruit> page = new Page(current,limit);
        //获取查询对象的信息
        Integer id = fruitQuery.getId();
        Integer treeId = fruitQuery.getTreeId();
        //String start = fruitQuery.getGmtCreate();
        String start=fruitQuery.getBegin();
        String end = fruitQuery.getEnd();
        //构建查询对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //匹配查询对象
        if (!StringUtils.isEmpty(id)){
            queryWrapper.eq("f_id",id);
        }if (!StringUtils.isEmpty(treeId)){
            queryWrapper.eq("tree_id",treeId);
        }if (!StringUtils.isEmpty(start)){
            queryWrapper.ge("gmt_create",start);
        }if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_modified",end);
        }
        //根据查询对象分页查询信息
        fruitService.page(page,queryWrapper);
        //数据查出来符合条件记录的总条数
        long totals = page.getTotal();
        //查出来数据的集合
        List<Fruit> records = page.getRecords();
        return R.ok().data("totals",totals).data("records",records);
    }
    //4 添加果实信息接口方法
    @PostMapping("addFruit")
    public R addFruit(@RequestBody Fruit fruit){
        boolean flag = fruitService.save(fruit);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //5 按id查询事务信息
    @GetMapping("getFruit/{id}")
    public R getFruit(@PathVariable Integer id){
        Fruit fruit = fruitService.getById(id);
        return R.ok().data("fruit",fruit);
    }
    //6 按id修改果实信息
    @PostMapping("updateFruit/{id}")
    public R setFruit(@PathVariable int id,
                      @RequestBody Fruit fruit){
        //这里RequestBody的tree接收不到前端传来的id字段，其他字段可以接收到
        //测试是否请求到这里
        //System.out.println(id+"/"+tree+"请求到这里");
        //boolean flag = treeService.updateById(tree);
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("f_id",id);
        boolean flag = fruitService.update(fruit,updateWrapper);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //批量添加果实信息
    @PostMapping("readExcel")
    public R readExcel(@RequestBody MultipartFile file) throws IOException {
        //System.out.println("我是沙雕");
        //System.out.println("执行到这里"+file.getName()+" / "+file.getSize()+"/"+file.isEmpty());
        //读取Excel文件
        EasyExcel.read(file.getInputStream(), DemoData.class, new UploadDataListener(fruitService)).sheet().doRead();
        return R.ok();
    }

    //文件下载
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("果实信息列表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Down.class).sheet("果实信息列表").doWrite(data());
        //return R.ok();
    }
    //往Excel表写数据的方法
    private List data() {
        List list = new ArrayList();
//        DemoData demoData = new DemoData();
//        demoData.setCol("green");
//        demoData.setId(1);
//        demoData.setSeed("blue");
//        demoData.setSize("large");
//        for (int i=0;i<10;i++){
//            list.add(demoData);
//        }
        list=fruitService.list(null);
        return list;
    }
}

