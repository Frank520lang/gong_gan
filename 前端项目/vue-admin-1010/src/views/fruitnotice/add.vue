
<template>
  <div class="container">
    <h2>修改公告：</h2>
    <br />
    <el-form class="demo-form-inline">
      <el-form-item>
        <el-input v-model="notice.notice" :rows="25" type="textarea" style="width:1300px" />
      </el-form-item>
      <el-form-item align="center">
        <el-button :disabled="saveBtnDisabled" type="primary" @click="updateNotice">确认修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
//引入调用teacher.js文件
import notice from '@/api/fruitnotice/fruitnotice'
export default {
  //定义页面数据
  data () {
    return {
      id: 1,
      //定义一个teacher对象
      notice: {},
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
        //this.id = id
        //调用查询讲师的方法
        this.queryNotice(id)
      } else {
        //将对象清空
        this.teacher = {}
      }
    },
    //根据id查询讲师信息
    queryNotice (id) {
      //查询成功返回信息
      notice.getNoticeById(id).then(
        Response => {
          this.notice = Response.data.notice
        }
      )
    },
    //修改事务信息
    updateNotice () {
      notice.updateNoticeById(this.id, this.notice).then(
        () => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          //路由跳转
          this.$router.push('/fruitnotice/list')
        }
      )
    },
  }
}
</script>
