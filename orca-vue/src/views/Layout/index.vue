<template>
  <el-container class="layout">
    <el-aside width="262px">
      <el-header>
        <div class="img"></div>
        <div class="title">ORCA</div>
      </el-header>
      <el-input placeholder="搜索">
        <template #prepend>
          <el-button :icon="Search"></el-button>
        </template>
      </el-input>
      <el-menu :default-active="$route.path" router>
        <el-menu-item index="/home">
          <img
            src="@/assets/icons/home-active.png"
            style="width: 24px; height: 24px"
            v-if="$route.path === '/home'"
          />
          <img
            src="@/assets/icons/home.png"
            style="width: 24px; height: 24px"
            v-else
          />
          <span> 首页 </span>
        </el-menu-item>
        <el-menu-item index="/documents">
          <img
            src="@/assets/icons/document1-active.png"
            style="width: 24px; height: 24px"
            v-if="$route.path === '/documents'"
          />
          <img
            src="@/assets/icons/document1.png"
            style="width: 24px; height: 24px"
            v-else
          />
          <span> 我的文档 </span>
          <img
            src="@/assets/icons/bottom-arrow2.png"
            alt=""
            style="width: 24px; height: 24px; position: absolute; right: 10px"
          />
        </el-menu-item>
        <el-menu-item index="/find">
          <img
            src="@/assets/icons/find-active.png"
            style="width: 24px; height: 24px"
            v-if="$route.path === '/find'"
          />
          <img
            src="@/assets/icons/find.png"
            style="width: 24px; height: 24px"
            v-else
          />
          <span> 发现 </span>
        </el-menu-item>
        <el-menu-item class="dele">
          <img
            src="@/assets/icons/dele.png"
            style="width: 24px; height: 24px"
          />
          <span>回收站</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="flex">
        <div class="left">首页</div>
        <div class="right">
          <el-button>
            <img
              src="@/assets/icons/search.png"
              alt=""
              style="width: 36px; height: 36px"
            />
          </el-button>
          <el-button>
            <img
              src="@/assets/icons/msg.png"
              alt=""
              style="width: 36px; height: 36px"
            />
          </el-button>
          <el-button>
            <img
              src="@/assets/icons/allcheck.png"
              alt=""
              style="width: 36px; height: 36px"
            />
          </el-button>
          <el-dropdown @command="handleCommand">
            <el-avatar
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              class="headshot"
            />
            <template #dropdown>
              <el-dropdown-menu
                class="flexpf"
                style="width: 307px; align-items: center"
              >
                <div class="top">
                  <el-avatar
                    src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                  />
                </div>
                <el-dropdown-item
                  style="width: 247px; height: 42px; justify-content: center"
                  command="change"
                >
                  切换账号
                </el-dropdown-item>
                <el-dropdown-item
                  style="width: 247px; height: 42px; justify-content: center"
                  command="logout"
                >
                  退出登录
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <el-main>
        <div class="nav flex">
          <div
            class="new nav-sub flex"
            style="position: relative"
            v-outside="handleOut"
          >
            <div class="wrap flex" @click="showDrop = !showDrop">
              <img
                src="@/assets/icons/document.png"
                alt=""
                style="width: 52px; height: 52px"
              />
              <div class="info flexpf">
                <p>新建</p>
                <span>新建文档开始编辑</span>
              </div>
              <img
                src="@/assets/icons/bottom-arrow2.png"
                alt=""
                style="width: 24px; height: 24px"
              />
            </div>
            <div class="newDrop" v-if="showDrop">
              <div class="title"><strong>新建</strong></div>
              <div class="new-by">
                <div
                  class="docum flexpf"
                  style="width: 58px; height: 76px"
                  @click="$router.push('/newdocum')"
                >
                  <img
                    src="@/assets/icons/document1-active.png"
                    alt=""
                    style="width: 42px; height: 42px"
                  />
                  <span>文档</span>
                </div>
                <div class="folder flexpf" style="width: 58px; height: 76px">
                  <img
                    src="@/assets/icons/document1-active.png"
                    alt=""
                    style="width: 42px; height: 42px"
                  />
                  <span>文件夹</span>
                </div>
                <div
                  class="import flexpf"
                  style="width: 58px; height: 76px; margin-top: 5px"
                >
                  <img
                    src="@/assets/icons/import.png"
                    alt=""
                    style="width: 42px; height: 42px"
                  />
                  <span>文档</span>
                </div>
              </div>
            </div>
          </div>

          <div class="import nav-sub flex">
            <img
              src="@/assets/icons/import.png"
              alt=""
              style="width: 52px; height: 52px"
            />
            <div class="info flexpf">
              <p>导入</p>
              <span>导入文档进行操作</span>
            </div>
            <img
              src="@/assets/icons/bottom-arrow2.png"
              alt=""
              style="width: 24px; height: 24px"
            />
          </div>
          <div class="model nav-sub flex">
            <img
              src="@/assets/icons/model.png"
              alt=""
              style="width: 52px; height: 52px"
            />
            <div class="info flexpf">
              <p>模板</p>
              <span>选择模板快速创建</span>
            </div>
            <img
              src="@/assets/icons/bottom-arrow2.png"
              alt=""
              style="width: 24px; height: 24px"
            />
          </div>
        </div>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { Search } from '@element-plus/icons-vue'
// import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const handleCommand = async (key) => {
  if (key === 'logout') {
    await ElMessageBox.confirm('你确认要退出吗?', '温馨提示', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    })
    router.push('/login')
  } else {
    router.push(`/user/${key}`)
  }
}
import { ref } from 'vue'
const showDrop = ref(false)
const handleOut = () => {
  showDrop.value = false
}
</script>

<style lang="scss" scoped>
.layout {
  height: 100vh;

  background-color: #fff;
  .el-aside {
    position: relative;
    padding-left: 10px;
    text-align: center;
    background-color: #f7f7f7;
    .el-header {
      width: 242px;
      margin-bottom: 10px;
      .title {
        width: 79px;
        height: 69px;
        font-weight: 700;
        font-size: 24px;
        text-align: center;
        line-height: 69px;
      }
    }
    .el-input {
      width: 242px;
      height: 38px;
      margin-right: 10px;
    }
    .el-menu {
      margin-top: 10px;
      background-color: #f7f7f7;
      width: 242px;

      border: 0;

      .el-menu-item {
        height: 56px;
        line-height: 56px;
        span {
          margin-left: 15px;
        }
      }
      .el-menu-item.is-active {
        background-color: #e9f2f5;
      }
      .dele {
        position: fixed;
        bottom: 0;
        font-size: 16px;
        width: 242px;
      }
    }
  }
  .el-container {
    padding: 0 20px 0 10px;
    .el-header {
      .left {
        width: 48px;
        height: 34px;
        font-size: 24px;
        font-weight: 700;
      }
      .el-button {
        border: 0;
        width: 36px;
        height: 36px;
      }
      .el-dropdown {
        .headshot {
          &:active,
          &:focus {
            outline: none;
          }
        }
      }
    }
    .el-main {
      .nav {
        .newDrop {
          position: absolute;
          bottom: -280px;
          left: 0;
          box-shadow: 0 0 3px 3px #efefef;
          width: 150px;
          height: 270px;
          z-index: 100;
          background-color: #fff;
          align-items: center;
          .title {
            padding-left: 15px;
            font-weight: 700;
            font-size: 18px;
          }
          .new-by {
            margin: 0 auto;
            width: 100%;
            padding-left: 20px;
            display: flex;
            // 换行
            flex-wrap: wrap;
          }
          span {
            height: 50px;
            line-height: 50px;
          }
        }
        .nav-sub {
          width: 25%;
          height: 85px;
          background-color: #efefef;
          border-radius: 12px;
          border: 1px solid #ddd;
          padding: 0 30px 0;
          line-height: 85px;
          margin-bottom: 48px;
          .info {
            width: 144px;
            height: 53px;
            p {
              display: block;
              line-height: 25px;
              height: 25px;
              margin: 0;
              font-weight: 700;
              font-size: 18px;
            }
            span {
              line-height: 25px;
              font-size: 14px;
            }
          }
        }
      }
    }
  }
}
</style>
