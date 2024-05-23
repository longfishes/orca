<script setup>
import { ref } from 'vue'
import { codeAPI, lambdaRegisterAPI } from '@/api/user'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
const router = useRouter()

// 利用v-if进行登录切换，showLogin是控制显示的变量
const formModel = ref({
  username: '',
  emailOrPhone: '',
  password: '',
  code: ''
})
// 预校验
// 规则rule,表单数据绑定formModel,规则绑定prop,v-model双向绑定
const checkPhone = (rule, value, callback) => {
  const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
  const regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/

  if (!value) {
    return callback(new Error('手机号不能为空'))
  } else if (reg.test(value) || regEmail.test(value)) {
    callback()
  } else {
    callback(new Error('请输入正确的手机/邮箱格式'))
  }
}
const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    // { min: 5, max: 10, message: '用户名必须是5-10位的字符', trigger: 'blur' },
    {
      pattern: /^(?!\d+$)[a-zA-Z0-9_]{2,49}$/,
      message: '用户名由2-49位大小写字母和下划线组成，且不能为纯数字',
      trigger: 'blur'
    }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    {
      pattern: /^\S{6,15}$/,
      message: '密码必须是6-15位的非空字符',
      trigger: 'blur'
    }
  ],
  emailOrPhone: [
    { required: true, message: '请输入手机号/邮箱号', trigger: 'blur' },
    { validator: checkPhone, trigger: 'blur' }
  ],

  code: [
    { required: true, message: '请输入验证码', trigger: 'blur' },
    { min: 6, max: 6, message: '验证码必须是6位的字符', trigger: 'blur' }
  ]
}

// 发送验证码
// 计时器，两个变量：总秒数，当前秒数；点击，计时器开始，变量不等，开始倒计时，
// 到0时将插值变成重新发送
const totalSecond = ref(10)
const second = ref(10)
const timer = ref(null)

const getCode = async () => {
  console.log(formModel.value.emailOrPhone)
  console.log(111)
  if (!timer.value && second.value === totalSecond.value) {
    console.log(formModel.value.emailOrPhone)
    await codeAPI(formModel.value.emailOrPhone)
    //开启倒计时
    ElMessage.success('验证码发送成功')
    console.log(222)
    console.log(second.value)
    timer.value = setInterval(
      () => {
        second.value--
        if (second.value <= 0) {
          clearInterval(timer.value)
          timer.value = null //重置定时器 id
          second.value = totalSecond.value // 归位
        }
      },

      1000
    )
  }
}
// 点击注册
const onRegister = async () => {
  console.log(formModel.value)
  await lambdaRegisterAPI(formModel.value)
  ElMessage.success('注册成功')
  router.push('/login')
}
</script>
<template>
  <div class="logincontent">
    <section class="login-section">
      <div class="left flexCenter" style="color: #fff">
        <div class="left-contain">
          <h1>ocra</h1>
          <h2>企业级在线编辑器</h2>
          <span
            >与其说喜欢ocra，其实更喜欢ocra
            的团队。周皮是我见过的最幽默而执着的程序员，认真做产品，有耐心不功利，所以我相信
            ocra 一定会做的很好。</span
          >
        </div>
      </div>
      <div class="right">
        <div class="form-content">
          <el-form class="flexpf" :model="formModel" :rules="rules">
            <el-form-item>
              <div class="form-top">
                <span style="font-weight: 700">注册</span>
                <span
                  style="font-size: 12px; text-align: center; line-height: 37px"
                  >已有账号？<a
                    href="javascript:;"
                    @click="$router.push('login')"
                    >点此登录</a
                  >
                </span>
              </div>
            </el-form-item>

            <el-form-item prop="username">
              <el-input
                v-model="formModel.username"
                placeholder="请输入用户名"
              />
            </el-form-item>
            <el-form-item prop="emailOrPhone">
              <!-- 请输入手机号 -->
              <el-input
                v-model="formModel.emailOrPhone"
                placeholder="请输入手机号"
              />
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="formModel.password" placeholder="请输入密码" />
            </el-form-item>

            <!-- 验证码 -->
            <el-form-item prop="code">
              <el-input v-model="formModel.code" placeholder="请输入验证码">
                <template #append>
                  <span
                    class="getcode"
                    @click="getCode"
                    style="cursor: pointer"
                    >{{
                      second === totalSecond
                        ? '获取验证码'
                        : second + '秒后重新发送'
                    }}</span
                  >
                </template>
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button
                style="width: 100%; background-color: #26beff; color: #fff"
                @click="onRegister"
                >注册</el-button
              >
            </el-form-item>
          </el-form>
        </div>
      </div>
    </section>
  </div>
</template>
<style lang="scss" scoped>
// 需要分左右两边布局，则用flex为类名的盒子套住

.logincontent {
  background: url('@/assets/images/bg.jpg') no-repeat center / cover;
  height: 100%;
  width: 100%;
  /*把背景图片放大到适合元素容器的尺寸，图片比例不变*/
  background-size: cover;
  position: absolute;
  left: 0;
  top: 0;
}
.login-section {
  // background-color: aqua;
  width: 940px;
  height: 538px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -60%);
  // opacity: 0.5;
  display: flex;
  justify-content: space-between;
  .left {
    width: 50%;
    height: 100%;
    background-color: #455165;
    opacity: 0.5;
    .left-contain {
      width: 80%;
      height: 70%;
      border-bottom: 1px solid #fff;
    }
  }
  .right {
    width: 50%;
    height: 100%;
    background-color: #fff;

    display: flex;
    justify-content: center;
    align-items: center;

    .form-content {
      width: 70%;
      height: 70%;
      // background-color: black;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .el-form {
        width: 100%;
        height: 100%;
      }
      .form-top {
        width: 100%;
        display: flex;
        justify-content: space-between;
        // background-color: aquamarine;
        container-type: inline-size;
        // 设置字体大小和容器适配
        span {
          font-size: 7cqw;
        }
      }
    }
  }
}
</style>
