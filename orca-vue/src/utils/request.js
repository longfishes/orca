import { useUserStore } from '@/stores'
import axios from 'axios'
import router from '@/router'
// import { ElMessage } from 'element-plus'

const baseURL = 'https://api.longfish.site'

const instance = axios.create({
  baseURL,
  timeout: 100000
})

instance.interceptors.request.use(
  (config) => {
    const userStore = useUserStore()
    if (userStore.userInfo.token) {
      config.headers.Authorization = userStore.userInfo.token
      // 请求头携带toknen,则token失效后不能访问详情页，直接退出登录
    }
    return config
  },
  (err) => Promise.reject(err)
)

instance.interceptors.response.use(
  (res) => {
    if (res.data.code === 200) {
      return res
    }
    ElMessage({ message: res.data.msg || '服务异常', type: 'error' })

    return Promise.reject(res.data)
  },
  (err) => {
    const userStore = useUserStore()

    ElMessage({
      message: err.response.data.msg || '服务异常',
      type: 'error'
    })
    console.log(err)
    // 401tokon失效处理
    // 返回登录页，清除用户信息
    if (err.response?.status === 401) {
      router.push('/login')
      userStore.removeUserInfo()
    }
    return Promise.reject(err)
  }
)

export default instance
export { baseURL }
