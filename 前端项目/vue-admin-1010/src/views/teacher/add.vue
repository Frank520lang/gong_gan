<template>
  <div class="app-container">
    <h2>果农表单</h2>
    <el-form label-width="120px">
      <el-form-item label="果农名字">
        <el-input v-model="teacher.name" />
      </el-form-item>
      <el-form-item label="果农排序">
        <el-input-number v-model="teacher.sort" controls-position="right" :min="0" />
      </el-form-item>
      <el-form-item label="果农资历">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <el-option :value="1" label="专业果农" />
          <el-option :value="2" label="新手果农" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="讲师资历">
        <el-input v-model="teacher.career" />
      </el-form-item>-->
      <el-form-item label="果农简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea" />
      </el-form-item>

      <!-- 讲师头像：TODO -->

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
//引入调用teacher.js文件
import teacherApi from '@/api/edu/teacher'
export default {
  //定义页面数据
  data () {
    return {
      //定义一个teacher对象
      teacher: {
        //讲师名称
        name: '',
        //讲师排序
        sort: 0,
        //讲师头衔
        level: 1,
        //讲师资历
        career: '',
        //讲师简介
        intro: '',
        //讲师头像
        avatar: ''
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
        //调用查询讲师的方法
        this.queryTeacher(id)
      } else {
        //将对象清空
        this.teacher = {}
      }
    },
    //根据id查询讲师信息
    queryTeacher (id) {
      //查询成功返回信息
      teacherApi.queryTeacherById(id).then(
        Response => {
          this.teacher = Response.data.teacher
        }
      )
    },
    //修改讲师信息
    updateTeacher (teacher) {
      teacherApi.updateTeacher(teacher).then(
        () => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          //路由跳转
          this.$router.push('/teacher/list')
        }
      )
    },
    //添加讲师信息
    addTeacher (teacher) {
      teacherApi.addTeacher(teacher).then(
        () => {
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          //路由跳转
          this.$router.push('/teacher/list')
        }
      )
    },
    //判断是需要修改还是添加操作
    saveOrUpdate () {
      if (this.teacher.id) {
        this.updateTeacher(this.teacher)
      } else {
        this.addTeacher(this.teacher)
      }
    }
  }
}
</script>
