import request from '@/utils/request'

export default {
  register (user) {
    return request({
      url: '/user/register',
      method: 'post',
      data: user
    })
  }
}
