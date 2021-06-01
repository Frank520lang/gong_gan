import request from '@/utils/request'
import url from 'postcss-url'

export default {
  //查询公告
  getNoticeById (id) {
    return request({
      //url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}` ,
      url: `/eduservice/notice/notice/${id}`,
      method: 'get',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把对象转换json进行传递到接口里面
      data: id
    })
  },
  //修改公告
  updateNoticeById (id, notice) {
    return request({
      url: `/eduservice/notice/updateNotice/${id}`,
      method: `post`,
      data: notice
    })

  }


}