import request from '@/utils/request'
export default {
  getPay () {
    return request({
      url: "/eduservice/pay/getPay",
      method: "get",
      data: ""
    })
  }
}