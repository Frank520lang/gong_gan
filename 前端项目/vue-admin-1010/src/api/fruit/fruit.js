import request from '@/utils/request'

export default {
  //按条件分页查询果实信息
  getFruitListPage (current, limit, fruitQuery) {
    return request({
      //url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}` ,
      url: `/eduservice/fruit/findByCondition/${current}/${limit}`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: fruitQuery
    })
  },
  //按id删除果实信息
  delFruitById (id) {
    return request({
      url: `/eduservice/fruit/delete/${id}`,
      method: 'delete',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: id
    })
  },
  //按id查询果实信息
  getFruitById (id) {
    return request({
      url: `/eduservice/fruit/getFruit/${id}`,
      method: 'get',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: id
    })
  },
  //修改果实信息
  updateFruitById (id, fruit) {
    return request({
      url: `/eduservice/fruit/updateFruit/${id}`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: fruit
    })
  },
  //添加果实信息
  addFruit (fruit) {
    return request({
      url: `/eduservice/fruit/addFruit`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: fruit
    })
  },
  //批量添加果实信息
  uploadFruit (file) {
    return request({
      url: `/eduservice/fruit/readExcel`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: file
    })
  }
}