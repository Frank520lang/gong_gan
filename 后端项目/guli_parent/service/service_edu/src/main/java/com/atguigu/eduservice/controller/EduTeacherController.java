package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-14
 */
//删除线是因为过时的注解,但不会影响使用
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    //访问地址： http://localhost:8001/eduservice/teacher/findAll
    //把service注入
    @Resource
    private EduTeacherService teacherService;

    //1 查询讲师表所有数据
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("讲师",list);
    }
    //逻辑删除讲师
    @ApiOperation(value = "删除讲师信息")
    @DeleteMapping("{id}")
    public R deleteTeacherById(@ApiParam(name = "id",value = "讲师ID",required = true)
                                   @PathVariable String id ){
        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //分页查询讲师信息
    @ApiOperation(value = "分页查询讲师信息")
    @PostMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //调用分页查询
        //将分页信息封装到pageTeacher对象中
        teacherService.page(pageTeacher,null);
        //查询到的总记录条数
        long totals = pageTeacher.getTotal();
        //查询到的数据集合
        List<EduTeacher> records = pageTeacher.getRecords();
        //将数据返回
        return R.ok().data("total",totals).data("records",records);
    }

    //条件查询带分页信息
    @ApiOperation(value = "带条件分页查询果树信息")
    //get请求只能从路径中获取参数
    //post既可以从路径中获取参数又可以从request body获取参数
    //当还需要传像treeQuery对象参数的时候考虑用post请求
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit,
                             @RequestBody TeacherQuery teacherQuery
                             ){
        //封装分页信息
        Page<EduTeacher> page = new Page<>(current,limit);
        //wrapper条件查询
        //构建条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper();
        //多条件查询
        //获取到查询对象的信息
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //根据查询对象匹配查询信息
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }

        //带条件查询
        teacherService.page(page,queryWrapper);
        long totals = page.getTotal();
        List<EduTeacher> rows = page.getRecords();
        return R.ok().data("totals",totals).data("rows",rows);
    }
    //添加讲师接口方法
    @ApiOperation(value = "添加讲师信息")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.save(eduTeacher);
        //添加成功
        if (flag){
           return R.ok();
        }
        else {
            return R.error();
        }

    }
    //按id查询讲师信息
    @ApiOperation(value = "按id查询讲师信息")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    //修改讲师信息
    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public R setTeacher(@RequestBody EduTeacher eduTeacher){
        System.out.println(eduTeacher+"运行到这里");
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

