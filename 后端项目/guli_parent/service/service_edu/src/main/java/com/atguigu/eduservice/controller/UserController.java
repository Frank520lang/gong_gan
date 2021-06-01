package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Issue;
import com.atguigu.eduservice.entity.User;
import com.atguigu.eduservice.entity.vo.IssueQuery;
import com.atguigu.eduservice.entity.vo.UserQuery;
import com.atguigu.eduservice.mapper.UserMapper;
import com.atguigu.eduservice.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    //分页查询所有用户信息
    @GetMapping("findAllUsers/{current}/{limit}")
    public R findAllUsers(@PathVariable long current,
                          @PathVariable long limit){
        //有参构造函数创建page对象
        //Page<User> page = new Page<>(current,limit);
        //分页查询,第一个参数确定分页，第二个参数封装的操作类
        //userService.page(page,null);
        //查询到数据的集合
        //List<User> records = page.getRecords();
        //查询到记录的条数
        //long totals = page.getTotal();
        //return R.ok().data("records",records).data("totals",totals);
        List<User> records = userMapper.listAllUsers(current,limit);
        int total = records.size();
        return R.ok().data("records",records).data("total",total);
    }
    //按条件查询用户信息
    @PostMapping("findByCondition/{current}/{limit}")
    public R pageListUser(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody UserQuery userQuery){
        System.out.println("运行到这里："+userQuery);
        //创建分页对象
        Page <User> page = new Page(current,limit);
        //获取查询对象的信息
        Integer id = userQuery.getUId();
        String name = userQuery.getName();
        String begin = userQuery.getGmtCreate();
        String end = userQuery.getGmtModified();
        //构建查询对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //匹配查询对象
        if (!StringUtils.isEmpty(id)){
            //这里“”内的是对应的数据库字段
            queryWrapper.eq("u_id",id);
        }if (!StringUtils.isEmpty(name)){
            queryWrapper.eq("name",name);
        }if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_modified",end);
        }
        //根据查询对象分页查询信息
        userService.page(page,queryWrapper);
        //数据查出来符合条件记录的总条数
        long totals = page.getTotal();
        //查出来数据的集合
        List<User> records = page.getRecords();
        return R.ok().data("totals",totals).data("records",records);
    }
    //逻辑删除用户信息
    @DeleteMapping("delete/{id}")
    public R deleteById(@PathVariable Integer id){
        boolean flag = userService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //添加用户信息
    @PostMapping("addUser")
    public R addUser(@RequestBody User user){
        boolean flag = userService.save(user);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //按id修改用户信息
    @PostMapping("updateUser/{id}")
    public R setUser(@PathVariable int id,
                      @RequestBody User user){
        //这里RequestBody的tree接收不到前端传来的id字段，其他字段可以接收到
        //测试是否请求到这里
        //System.out.println(id+"/"+tree+"请求到这里");
        //boolean flag = treeService.updateById(tree);
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("id",id);
        boolean flag = userService.update(user,updateWrapper);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

