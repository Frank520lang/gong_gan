import request from '@/utils/request'

export default {
  //按条件分页查询老师信息
  getTeacherListPage (current, limit, teacherQuery) {
    return request({
      //url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}` ,
      url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: teacherQuery
    })
  },
  //按id删除讲师信息
  delTeacherById (id) {
    return request({
      //url: `/eduservice/teacher/${id}` ,
      url: `/eduservice/teacher/${id}`,
      method: 'delete',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: id
    })
  },
  //按id查询讲师信息
  queryTeacherById (id) {
    return request({
      //url: `/eduservice/teacher/getTeacher/${id}` ,
      url: `/eduservice/teacher/getTeacher/${id}`,
      method: 'get',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: id
    })
  },
  //修改讲师信息
  updateTeacher (teacher) {
    return request({
      //url: `/eduservice/teacher/updateTeacher`,
      url: `/eduservice/teacher/updateTeacher`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: teacher
    })
  },
  //添加讲师信息
  addTeacher (teacher) {
    return request({
      //url: `/eduservice/teacher/addTeacher`,
      url: `/eduservice/teacher/addTeacher`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: teacher
    })
  },
}