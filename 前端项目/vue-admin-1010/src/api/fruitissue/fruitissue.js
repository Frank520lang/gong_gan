import request from '@/utils/request'

export default {
  //按条件分页查询事务信息
  getIssueListPage (current, limit, issueQuery) {
    return request({
      //url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}` ,
      url: `/eduservice/issue/findByCondition/${current}/${limit}`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: issueQuery
    })
  },
  //按id删除果实信息
  delIssueById (id) {
    return request({
      url: `/eduservice/issue/delete/${id}`,
      method: 'delete',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: id
    })
  },
  //按id查询果实信息
  getIssueById (id) {
    return request({
      url: `/eduservice/issue/getIssue/${id}`,
      method: 'get',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: id
    })
  },
  //修改果实信息
  updateIssueById (id, issue) {
    return request({
      url: `/eduservice/issue/updateIssue/${id}`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: issue
    })
  },
  //添加果实信息
  addIssue (issue) {
    return request({
      url: `/eduservice/issue/addIssue`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: issue
    })
  },
}