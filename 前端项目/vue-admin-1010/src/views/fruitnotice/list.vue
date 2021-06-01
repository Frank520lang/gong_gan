
<template>
  <div class="app-container">
    <h2>最新公告：</h2>
    <br />
    <el-form class="demo-form-inline">
      <el-form-item>
        <el-input
          v-model="notice.notice"
          :rows="25"
          type="textarea"
          style="width:1300px"
          :readonly="true"
          class="notice"
        />
      </el-form-item>

      <el-form-item align="center">
        <router-link :to="'/fruitnotice/edit/'+this.id">
          <el-button type="primary">修改公告</el-button>
        </router-link>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
//引入调用teacher.js文件
import notice from '@/api/fruitnotice/fruitnotice'

export default {
  data () {
    return {
      //查询条件封装对象
      id: 1,
      notice: {},
      saveBtnDisabled: false
    }
  },
  created () {//页面执行之前调用
    //页面加载之前调用methods里面的方法
    this.getList()
  },
  methods: {//页面的主要方法
    //创建一个获取事务列表的方法
    //调用该函数不传参,将当前页的默认值设置为1
    getList (id = 1) {
      this.id = id
      notice.getNoticeById(this.id).then(
        Response => { //请求成功,用response接口返回数据
          this.notice = Response.data.notice
        }
      )
    },
  }
}
</script>
<style>
.notice {
  color: red;
  background-color: royalblue;
}
</style>
