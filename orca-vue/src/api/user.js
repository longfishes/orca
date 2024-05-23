import request from '@/utils/request'
// 密码登录
export const lambdaLoginAPI = (data) => {
  return request({
    url: '/user/lambdaLogin',
    method: 'POST',
    data
  })
}
// 验证码登录
export const lambdaCodeLoginAPI = (data) => {
  return request({
    url: '/user/lambdaCodeLogin',
    method: 'POST',
    data
  })
}
// 注册
export const lambdaRegisterAPI = ({
  username,
  emailOrPhone,
  password,
  code
}) => {
  return request({
    url: '/user/lambdaRegister',
    method: 'POST',
    data: JSON.stringify({ username, emailOrPhone, password, code }),
    headers: {
      'content-type': 'application/json'
    }
  })
}
// 发送验证码
export const codeAPI = (username) => {
  return request({
    url: '/user/code',
    params: {
      username
    }
  })
}
