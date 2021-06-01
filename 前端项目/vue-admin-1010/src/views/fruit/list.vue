
<template>
  <div class="app-container">
    <h2>果实信息列表:</h2>
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="fruitQuery.id" placeholder="果实id" />
      </el-form-item>

      <el-form-item>
        <el-input v-model="fruitQuery.treeId" placeholder="果树id" />
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="fruitQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="fruitQuery.end"
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
      <el-table-column label="排序" width="60" align="center">
        <template slot-scope="scope">{{ (page - 1) * limit + scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="fid" label="果实id" width="80" />

      <el-table-column prop="treeId" label="果树id" width="80" />

      <el-table-column prop="size" label="果实大小" width="240" />

      <el-table-column prop="color" label="果实颜色" width="240" />

      <el-table-column prop="seedcase" label="果皮状态" width="240" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160" />

      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <router-link :to="'/fruit/edit/'+scope.row.fid">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.fid)"
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
//引入调用fruit.js文件
import fruit from '@/api/fruit/fruit'

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
      fruitQuery: {}
    }
  },
  //页面执行之前调用
  created () {
    //页面加载之前调用methods里面的方法
    this.getList()
  },
  methods: {

    //创建一个获取当前果实信息的方法
    //调用该函数不传参，将当前页的默认值设置为1
    getList (page = 1) {
      this.page = page
      fruit.getFruitListPage(this.page, this.limit, this.fruitQuery).then(
        //请求成功，用response返回接口数据
        Response => {
          this.list = Response.data.records;
          this.total = Response.data.totals;
        }
      )
    },

    //清空列表方法
    resetData () {
      //将查询对象置空
      this.fruitQuery = {}
      //再调一次查询方法
      this.getList()
    },

    //删除果实信息通过id
    removeDataById (id) {
      //按删除按钮时弹窗确认
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(//点击确认
        () => {
          fruit.delFruitById(id).then(//删除成功
            //弹窗提醒
            this.$message({
              type: 'success',
              message: '删除成功！'
            }
            )
          )
          //重新查询数据库
          this.getList
        }
      )
    }

  }
}
</script>
