// import { userGetInfoService } from '@/api/user'
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { lambdaLoginAPI } from '@/api/user'
import { lambdaCodeLoginAPI } from '@/api/user'

export const useUserStore = defineStore(
  'User',
  () => {
    const userInfo = ref({})
    // 密码登录
    const LoginLam = async (data) => {
      const res = await lambdaLoginAPI(data)
      userInfo.value = res.data.data
      
      console.log(userInfo.value)
    }
    // 验证码登录
    const LoginLamCode = async (data) => {
      const res = await lambdaCodeLoginAPI(data)
      userInfo.value = res.data.data

      console.log(userInfo.value)
    }
    const removeUserInfo = () => {
      userInfo.value = {}
    }
    return {
      userInfo,
      LoginLam,
      removeUserInfo,
      LoginLamCode
    }
  },
  { persist: true }
)
