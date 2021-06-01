<template>
  <div class="app-container">
    <h1>果实信息:</h1>
    <br />
    <br />
    <br />
    <el-form label-width="120px">
      <!-- <el-form-item label="果实id：">
        <el-input v-model="fruit.fid" />
      </el-form-item>-->
      <br />
      <el-form-item label="果树id：">
        <el-input v-model="fruit.treeId" />
      </el-form-item>
      <br />
      <el-form-item label="果实大小：">
        <el-input v-model="fruit.size" />
      </el-form-item>
      <br />
      <el-form-item label="果实颜色：">
        <el-input v-model="fruit.color" />
      </el-form-item>
      <br />
      <el-form-item label="果皮状态：">
        <el-input v-model="fruit.seedcase" />
      </el-form-item>
      <br />

      <!-- 讲师头像：TODO -->
      <br />
      <el-form-item align="center">
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
//引入调用fruit.js文件
import fruitApi from '@/api/fruit/fruit'
export default {
  //定义页面数据
  data () {
    return {
      //定义一个fruit对象
      fruit: {
        //果实id
        //fid: 1,
        //果实大小
        size: '1',
        //果实颜色
        color: '1',
        //果皮状况
        seedcase: '1',
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
        const fid = this.$route.params.id
        this.fruit.fid = fid
        //调用查询果实信息的方法
        this.queryFruit(fid)
      } else {
        //将对象清空
        this.fruit = {}
      }
    },
    //根据id查询果实信息
    queryFruit (fid) {
      //查询成功返回信息
      fruitApi.getFruitById(fid).then(
        Response => {
          this.fruit = Response.data.fruit
        }
      )
    },
    //修改果实信息
    updateFruit (fid, fruit) {
      fruitApi.updateFruitById(fid, fruit).then(
        () => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          //路由跳转
          this.$router.push('/fruit/list')
        }
      )
    },
    //添加果实信息
    addFruit (fruit) {
      fruitApi.addFruit(fruit).then(
        () => {
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          //路由跳转
          this.$router.push('/fruit/list')
        }
      )
    },
    //判断是需要修改还是添加操作
    saveOrUpdate () {
      //判断路由中是否携带参数
      if (this.fruit.fid) {
        console.log("运行的1")
        this.updateFruit(this.fruit.fid, this.fruit)
      } else {
        console.log("运行的2")
        this.addFruit(this.fruit)
      }
    }
  }
}
</script>
