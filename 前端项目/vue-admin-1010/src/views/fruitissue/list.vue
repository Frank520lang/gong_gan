
<template>
  <div class="app-container">
    <h2>事务列表：</h2>
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="issueQuery.id" placeholder="事务id" />
      </el-form-item>
      <el-form-item>
        <el-input v-model="issueQuery.userId" placeholder="果农id" />
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="issueQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="issueQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table :data="list" border fit highlight-current-row>
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">{{ (page - 1) * limit + scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="iid" label="事务id" width="70" />
      <el-table-column prop="treeId" label="果树id" width="70" />
      <el-table-column prop="userId" label="果农id" width="70" />
      <el-table-column prop="weeding" label="除草" width="160" />
      <el-table-column prop="irrigating" label="灌溉" width="160" />
      <el-table-column prop="spraying" label="打药" width="160" />
      <el-table-column prop="picking" label="采摘" width="160" />
      <!-- <el-table-column prop="intro" label="资历" /> -->

      <el-table-column prop="gmtCreate" label="添加时间" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/fruitissue/edit/'+scope.row.iid">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.iid)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
  </div>
</template>
<script>
//引入调用teacher.js文件
import issue from '@/api/fruitissue/fruitissue'

export default {
  data () {
    return {
      //存放返回的数据
      list: null,
      //限制一页显示数据的条数
      limit: 10,
      //当前页
      page: 1,
      //数据总条数
      total: 0,
      //查询条件封装对象
      issueQuery: {}
    }
  },
  created () {//页面执行之前调用
    //页面加载之前调用methods里面的方法
    this.getList()
  },
  methods: {//页面的主要方法
    //创建一个获取事务列表的方法
    //调用该函数不传参,将当前页的默认值设置为1
    getList (page = 1) {
      this.page = page
      issue.getIssueListPage(this.page, this.limit, this.issueQuery).then(
        Response => { //请求成功,用response接口返回数据
          //后端返回的数据
          this.list = Response.data.records
          //后端返回记录的总条数
          this.total = Response.data.totals
        }
      )
    },
    //清空列表方法
    resetData () {
      //将查询对象置空
      this.issueQuery = {}
      //再调一次查询方法
      this.getList()
    },
    //删除单个事务的方法
    removeDataById (id) {
      //删除时弹窗提醒
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        issue.delIssueById(id).then(//删除成功
          () => {
            //提示信息
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getList
          }
        )

      })

    }
  }
}
</script>
