
<template>
  <div class="app-container">
    <h2>果农信息表单：</h2>
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="果农名字" />
      </el-form-item>

      <el-form-item>
        <el-select v-model="teacherQuery.level" clearable placeholder="果农资历">
          <el-option :value="1" label="专业果农" />
          <el-option :value="2" label="新手果农" />
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherQuery.end"
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

      <el-table-column prop="name" label="名字" width="80" />

      <el-table-column label="资历" width="80">
        <template slot-scope="scope">{{ scope.row.level===1?'专业果农':'新手果农' }}</template>
      </el-table-column>

      <el-table-column prop="intro" label="简介" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160" />

      <!-- <el-table-column prop="sort" label="排序" width="60" /> -->

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id)"
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
import teacher from '@/api/edu/teacher'

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
      teacherQuery: {}
    }
  },
  created () {//页面执行之前调用
    //页面加载之前调用methods里面的方法
    this.getList()
  },
  methods: {//页面的主要方法
    //创建一个获取讲师列表的方法
    //调用该函数不传参,将当前页的默认值设置为1
    getList (page = 1) {
      this.page = page
      teacher.getTeacherListPage(this.page, this.limit, this.teacherQuery).then(
        Response => { //请求成功,用response接口返回数据
          //后端返回的数据
          this.list = Response.data.rows
          //后端返回记录的总条数
          this.total = Response.data.totals
        }
      )
    },
    //清空列表方法
    resetData () {
      //将查询对象置空
      this.teacherQuery = {}
      //再调一次查询方法
      this.getList()
    },
    //删除单个讲师的方法
    removeDataById (id) {
      //删除时弹窗提醒
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        teacher.delTeacherById(id).then(//删除成功
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
