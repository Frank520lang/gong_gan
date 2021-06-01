import request from '@/utils/request'
export default {
  getOther () {
    return request(
      {
        url: '/eduservice/temperature/other',
        mrthod: 'get',
        data: ''
      }
    )
  }
}