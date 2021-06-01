package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Tree;
import com.atguigu.eduservice.entity.vo.TreeQuery;
import com.atguigu.eduservice.service.TreeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/eduservice/tree")
@CrossOrigin
public class TreeController {

    @Resource
    private TreeService treeService;
    //1 查询果树表所有数据
    @GetMapping("findAll")
    public R findAllTree() {
        //调用service的方法实现查询所有的操作
        List<Tree> list = treeService.list(null);
        return R.ok().data("用户", list);
    }
    //2 逻辑删除果树
    @DeleteMapping("{tId}")
    public R deleteTeacherById(@ApiParam(name = "id",value = "讲师ID",required = true)
                               @PathVariable Integer tId ){
        boolean flag = treeService.removeById(tId);
        if (flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //3 分页查询果树信息
    @ApiOperation(value = "分页查询果树信息")
    @PostMapping("pageTree/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        //创建page对象
        Page<Tree> pageTree = new Page<>(current,limit);
        //调用分页查询
        //将分页信息封装到pageTeacher对象中
        treeService.page(pageTree,null);
        //查询到的总记录条数
        long totals = pageTree.getTotal();
        //查询到的数据集合
        List<Tree> records = pageTree.getRecords();
        //将数据返回
        return R.ok().data("total",totals).data("records",records);
    }

    //4 条件查询带分页的果树信息
    @ApiOperation(value = "带条件分页查询果树信息")
    //get请求只能从路径中获取参数
    //post既可以从路径中获取参数又可以从request body获取参数
    //当还需要传像treeQuery对象参数的时候考虑用post请求
    @PostMapping("pageTreeCondition/{current}/{limit}")
    public R pageListTree(@PathVariable long current,
                             @PathVariable long limit,
                             @RequestBody TreeQuery treeQuery
    ){
        System.out.println("运行到这里："+treeQuery);
        //封装分页信息
        Page<Tree> page = new Page<>(current,limit);
        //wrapper条件查询
        //构建条件
        QueryWrapper<Tree> queryWrapper = new QueryWrapper();
        //多条件查询
        //获取到查询对象的信息
        String season = treeQuery.getSeason();
        Integer id = treeQuery.getId();
        String begin = treeQuery.getBegin();
        String end = treeQuery.getEnd();

        //根据查询对象匹配查询信息
        if (!StringUtils.isEmpty(season)){
            queryWrapper.like("season",season);
        }
        if (!StringUtils.isEmpty(id)){
            queryWrapper.eq("t_id",id);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }

        //带条件查询
        treeService.page(page,queryWrapper);
        long totals = page.getTotal();
        List<Tree> rows = page.getRecords();
        return R.ok().data("totals",totals).data("rows",rows);
    }
    //5 添加果树接口方法
    @ApiOperation(value = "添加果树信息")
    @PostMapping("addTree")
    public R addTeacher(@RequestBody Tree tree){
        boolean flag = treeService.save(tree);
        //添加成功
        if (flag){
            return R.ok();
        }
        //添加失败
        else {
            return R.error();
        }

    }
    //6 按id查询果树信息
    @ApiOperation(value = "按id查询果树信息")
    @GetMapping("getTree/{id}")
    public R getTree(@PathVariable Integer id){
        Tree tree = treeService.getById(id);
        return R.ok().data("tree",tree);
    }
    //7 按id修改果树信息
    @ApiOperation(value = "修改果树信息")
    @PostMapping("updateTree/{id}")
    public R setTree(@PathVariable int id,
                     @RequestBody Tree tree){
        //这里RequestBody的tree接收不到前端传来的id字段，其他字段可以接收到
        //测试是否请求到这里
        //System.out.println(id+"/"+tree+"请求到这里");
        //boolean flag = treeService.updateById(tree);
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("t_id",id);
        boolean flag = treeService.update(tree,updateWrapper);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

