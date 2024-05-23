<script setup></script>

<template>
  <div class="bg">
    <div class="contain flexpf">
      <div class="top">
        <!-- 圆形 -->
        <div class="step">
          <div class="circle">1</div>
          <span>确认账号</span>
          <div class="line"></div>
        </div>

        <div class="step">
          <div class="circle">2</div>
          <span>重置密码</span>
          <div class="line"></div>
        </div>
        <div class="step">
          <div class="circle">3</div>
          <span>重置成功</span>
        </div>
      </div>
      <div class="phone">
        <el-input type="text" placeholder="请输入绑定的手机号/邮箱">
          <template #prepend>
            <span class="label">手机号/邮箱</span>
          </template>
        </el-input>
      </div>
      <el-button
        style="width: 100%; background-color: #9ad6f5; color: #fff"
        round
      >
        下一步
      </el-button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.contain {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -90%);
  //   background-color: #fff;
  width: 400px;
  height: 180px;
  .top {
    padding-left: 30px;
    text-align: center;
    height: 50px;
    // background-color: beige;
    line-height: 50px;
    display: flex;
    justify-content: space-between;
    .step {
      position: relative;
      width: 70px;
      height: 50px;
      .line {
        height: 2px;
        width: 100px;
        background-color: #c9ccd0;
        position: absolute;
        left: 55%;
        top: 15px;
      }
      .circle {
        width: 30px;
        height: 30px;
        border-radius: 50%;
        background-color: #36adeb;
        font-size: 12px;
        line-height: 30px;
        color: #fff;
      }
      span {
        position: absolute;
        top: 20px;
        left: -20%;
      }
    }
  }
}
</style>
