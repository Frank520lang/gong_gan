<template>
  <div class="app-container">
    <h2>果树信息：</h2>
    <br />
    <el-form label-width="120px">
      <!-- <el-form-item label="果树id:">
        <el-input v-model="tree.tid" />
      </el-form-item>-->
      <el-form-item label="生长期:
      ">
        <el-input v-model="tree.season" />
      </el-form-item>
      <el-form-item label="树根:">
        <el-input v-model="tree.root" />
      </el-form-item>
      <el-form-item label="茎:">
        <el-input v-model="tree.steam" />
      </el-form-item>
      <el-form-item label="树叶:">
        <el-input v-model="tree.leaf" />
      </el-form-item>
      <el-form-item label="树干:">
        <el-input v-model="tree.trunk" />
      </el-form-item>
      <el-form-item label="花:">
        <el-input v-model="tree.flower" />
      </el-form-item>
      <el-form-item label="果实">
        <el-input v-model="tree.fruit" />
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
import treeApi from '@/api/fruittree/fruittree'
export default {
  //定义页面数据
  data () {
    return {
      //定义一个tree对象
      tree: {
        //果树id
        tId: '',
        //生长期
        season: '',
        //树根
        root: '',
        //树茎
        steam: '',
        //树叶
        leaf: '',
        //树干
        trunk: '',
        //花
        flower: '',
        //果实
        fruit: '',

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
        this.tree.tid = id;
        //调用查询果树信息的方法
        this.queryTree(id)
      } else {
        //将对象清空
        this.tree = {}
      }
    },
    //根据id查询果实信息
    queryTree (id) {
      //查询成功返回信息
      treeApi.getTreeById(id).then(
        Response => {
          this.tree = Response.data.tree
        }
      )
    },
    //修改果树信息
    updateTree (id, tree) {
      treeApi.updateTreeById(id, tree).then(
        () => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          //路由跳转
          this.$router.push('/fruittree/list')
        }
      )
    },
    //添加果树信息
    addTree (tree) {
      treeApi.addTree(tree).then(
        () => {
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          //路由跳转
          this.$router.push('/fruittree/list')
        }
      )
    },
    //判断是需要修改还是添加操作
    saveOrUpdate () {
      if (this.tree.tid) {
        console.log("运行到1")
        this.updateTree(this.tree.tid, this.tree)
      } else {
        console.log("运行到2")
        this.addTree(this.tree)
      }
    }
  }
}
</script>
