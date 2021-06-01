/**
 * Created by jiachenpan on 16/11/18.
 */
//用户名
export function isvalidUsername (str) {
  //const valid_map = ['admin', 'editor']
  //字母大小写，数字，下划线，减号，4到16位
  const valid_map = /^[a-zA-Z0-9_-]{4,16}$/
  //return valid_map.indexOf(str.trim()) >= 0
  return valid_map.test(str.trim())
}

//电话号码
export function isvalidPhone (str) {
  //11位电话号码
  const valid_map = /^1[3|4|5|7|8][0-9]{9}$/
  return valid_map.test(str.trim())
}
/* 合法uri*/
export function validateURL (textval) {
  const urlregex = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
  return urlregex.test(textval)
}

/* 小写字母*/
export function validateLowerCase (str) {
  const reg = /^[a-z]+$/
  return reg.test(str)
}

/* 大写字母*/
export function validateUpperCase (str) {
  const reg = /^[A-Z]+$/
  return reg.test(str)
}

/* 大小写字母*/
export function validatAlphabets (str) {
  const reg = /^[A-Za-z]+$/
  return reg.test(str)
}
