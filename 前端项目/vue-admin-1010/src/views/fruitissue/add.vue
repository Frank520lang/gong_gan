<template>
  <div class="app-container">
    <h2>事务信息：</h2>
    <br />
    <el-form label-width="120px">
      <el-form-item label="果树id：">
        <el-input v-model="issue.treeId" />
      </el-form-item>
      <el-form-item label="用户id:">
        <el-input v-model="issue.userId" />
      </el-form-item>
      <el-form-item label="除草：">
        <el-input v-model="issue.weeding" />
      </el-form-item>
      <el-form-item label="灌溉：">
        <el-input v-model="issue.irrigating" />
      </el-form-item>
      <el-form-item label="打药：">
        <el-input v-model="issue.spraying" />
      </el-form-item>
      <el-form-item label="采摘：">
        <el-input v-model="issue.picking" />
      </el-form-item>

      <!-- 讲师头像：TODO -->

      <el-form-item align="center">
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
//引入调用teacher.js文件
import issueApi from '@/api/fruitissue/fruitissue'
export default {
  //定义页面数据
  data () {
    return {
      //定义一个teacher对象
      issue: {
        //果树id
        treeId: '',
        //用户id
        userId: '',
        //除草
        weeding: '',
        //灌溉
        irrigating: '',
        //打药
        spraying: '',
        //采摘
        picking: ''
      },
      saveBtnDisabled: false
    }


  },
  //页面加载前调用的方法
  //组件重用时,只调用一次created方法
  created () {
    this.init()
  },
  //添加监听
  //监听路由跳转
  watch: {
    $route (to, from) {
      this.init()
    }
  },
  //定义页面方法
  methods: {
    //定义一个页面初始化的方法
    init () {
      //从路由中判断是否携带参数
      //固定写法
      if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id
        this.issue.iid = id
        //调用查询讲师的方法
        this.queryIssue(id)
      } else {
        //将对象清空
        this.teacher = {}
      }
    },
    //根据id查询讲师信息
    queryIssue (id) {
      //查询成功返回信息
      issueApi.getIssueById(id).then(
        Response => {
          this.issue = Response.data.issue
        }
      )
    },
    //修改事务信息
    updateIssue (id, issue) {
      issueApi.updateIssueById(id, issue).then(
        () => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          //路由跳转
          this.$router.push('/fruitissue/list')
        }
      )
    },
    //添加事务信息
    addIssue (issue) {
      issueApi.addIssue(issue).then(
        () => {
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          //路由跳转
          this.$router.push('/fruitissue/list')
        }
      )
    },
    //判断是需要修改还是添加操作
    saveOrUpdate () {
      if (this.issue.iid) {
        this.updateIssue(this.issue.iid, this.issue)
      } else {
        this.addIssue(this.issue)
      }
    }
  }
}
</script>
