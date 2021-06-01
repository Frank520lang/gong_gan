import request from '@/utils/request'

export default {
  //按条件分页查询果实信息
  getTreeListPage (current, limit, treeQuery) {
    return request({
      //url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}` ,
      url: `/eduservice/tree/pageTreeCondition/${current}/${limit}`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: treeQuery
    })
  },
  //按id删除果实信息
  delTreeById (id) {
    return request({
      url: `/eduservice/tree/${id}`,
      method: 'delete',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: id
    })
  },
  //按id查询果实信息
  getTreeById (id) {
    return request({
      url: `/eduservice/tree/getTree/${id}`,
      method: 'get',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: id
    })
  },
  //修改果实信息
  updateTreeById (id, tree) {
    return request({
      url: `/eduservice/tree/updateTree/${id}`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: tree
    })
  },
  //添加果实信息
  addTree (tree) {
    return request({
      url: `/eduservice/tree/addTree`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: tree
    })
  },
}