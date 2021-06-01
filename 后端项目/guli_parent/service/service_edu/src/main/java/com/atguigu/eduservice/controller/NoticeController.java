package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Issue;
import com.atguigu.eduservice.entity.Notice;
import com.atguigu.eduservice.service.NoticeService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-03
 */
@RestController
@RequestMapping("/eduservice/notice")
@CrossOrigin
public class NoticeController {
    //依赖注入
    @Resource
    private NoticeService noticeService;
    //查询公告
    @GetMapping("notice/{id}")
    public R notice(@PathVariable int id){
        Notice notice = noticeService.getById(id);
        return R.ok().data("notice",notice);
    }
    //修改公告
    @PostMapping("updateNotice/{id}")
    public R setNotice(@PathVariable int id,
                      @RequestBody Notice notice){
        //这里RequestBody的tree接收不到前端传来的id字段，其他字段可以接收到
        //测试是否请求到这里
        //System.out.println(id+"/"+tree+"请求到这里");
        //boolean flag = treeService.updateById(tree);
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("n_id",id);
        boolean flag = noticeService.update(notice,updateWrapper);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

