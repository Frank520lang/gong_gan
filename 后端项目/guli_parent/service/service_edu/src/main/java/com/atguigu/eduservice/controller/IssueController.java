package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Issue;
import com.atguigu.eduservice.entity.vo.IssueQuery;
import com.atguigu.eduservice.service.IssueService;
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
 * @since 2021-03-03
 */
@RestController
@RequestMapping("/eduservice/issue")
@CrossOrigin
public class IssueController {
    //注入issueService对象
    //@Autowired
    @Resource
    private IssueService issueService;
    //1 分页查询事务信息
    //参数为当前页和每页显示的条数
    @GetMapping("findAllIssue/{current}/{limit}")
    public R findAllIssue(@PathVariable long current,
                          @PathVariable long limit){
        //有参构造函数创建page对象
        Page <Issue>page = new Page<>(current,limit);
        //分页查询,第一个参数确定分页，第二个参数封装的操作类
        issueService.page(page,null);
        //查询到数据的集合
        List<Issue> records = page.getRecords();
        //查询到记录的条数
        long totals = page.getTotal();
        return R.ok().data("records",records).data("totals",totals);
    }
    //2 逻辑删除事务信息
    @DeleteMapping("delete/{iId}")
    public R deleteById(@PathVariable Integer iId){
        boolean flag = issueService.removeById(iId);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //3 条件查询带分页的果树信息
    @PostMapping("findByCondition/{current}/{limit}")
    public R pageListIssue(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody IssueQuery issueQuery){
        System.out.println("运行到这里："+issueQuery);
        //创建分页对象
        Page <Issue> page = new Page(current,limit);
        //获取查询对象的信息
        Integer id = issueQuery.getId();
        Integer userId = issueQuery.getUserId();
        String begin = issueQuery.getGmtCreate();
        String end = issueQuery.getGmtModified();
        //构建查询对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //匹配查询对象
        if (!StringUtils.isEmpty(id)){
            queryWrapper.eq("i_id",id);
        }if (!StringUtils.isEmpty(userId)){
            queryWrapper.eq("user_id",userId);
        }if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_modified",end);
        }
        //根据查询对象分页查询信息
        issueService.page(page,queryWrapper);
        //数据查出来符合条件记录的总条数
        long totals = page.getTotal();
        //查出来数据的集合
        List<Issue> records = page.getRecords();
        return R.ok().data("totals",totals).data("records",records);
    }
    //4 添加事务信息接口方法
    @PostMapping("addIssue")
    public R addIssue(@RequestBody Issue issue){
        boolean flag = issueService.save(issue);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //5 按id查询事务信息
    @GetMapping("getIssue/{id}")
    public R getTree(@PathVariable Integer id){
        Issue issue = issueService.getById(id);
        return R.ok().data("issue",issue);
    }
    //6 按id修改事务信息
    @PostMapping("updateIssue/{id}")
    public R setIssue(@PathVariable int id,
                     @RequestBody Issue issue){
        //这里RequestBody的tree接收不到前端传来的id字段，其他字段可以接收到
        //测试是否请求到这里
        //System.out.println(id+"/"+tree+"请求到这里");
        //boolean flag = treeService.updateById(tree);
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("i_id",id);
        boolean flag = issueService.update(issue,updateWrapper);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

