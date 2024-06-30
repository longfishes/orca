<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useDetailStore } from '@/stores'
import { getListAPI, deleteAPI, renameAPI } from '@/api/detail'
const detailStore = useDetailStore()
const list = ref([])
const loading = ref(true)
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
const router = useRouter()
const handleRowClick = (row, column) => {
  // row 是被点击的行的数据
  // column 是被点击的列的信息
  // event 是原生事件对象
  console.log(row.id)
  // 假设你的详情页路由是 '/detail/:id'，并且你的行数据中包含 'id' 属性
  router.push(`/detail/${row.id}`)
}
const getList = async() => {
  // const res = await getListAPI('/')
  // list.value = res.data.data
  // loading.value = false
  const intervalId = setInterval(async () => {
    if (detailStore.flag) {
      console.log('可以了')

      const res = await getListAPI('/')
      list.value = res.data.data
      clearInterval(intervalId)
      loading.value = false
    }
  }, 2000)
}

getList()

const contextMenuVisible = ref(false)
const contextMenuTop = ref(0)
const contextMenuLeft = ref(0)
const contextMenuHeight = 500 // 假设菜单高度为200px
const contextMenuWeight = 100 // 假设菜单高度为200px
const id = ref(0)
const currentRow = ref(null)
// 处理表格右击事件
const handleTableContextmenu = (row, column, event) => {
  event.preventDefault()
  console.log()
  contextMenuVisible.value = true
  const windowHeight =
    window.innerHeight || document.documentElement.clientHeight
  contextMenuTop.value =
    event.clientY > windowHeight / 2 ? event.clientY - 432 : event.clientY
  contextMenuLeft.value = event.clientX - contextMenuWeight
  console.log(row.id)
  detailStore.getIdRow(row.id)
}
// 处理菜单项点击事件
function deleteMenuItemClick(option) {
  console.log(`选中了菜单项: ${option}`)
  const deleteWord = async () => {
    await deleteAPI(detailStore.idRow)
    getList()
    if (detailStore.idRow) ElMessage.success('文档已删除，30天内可在回收站找回')
  }

  deleteWord()
  contextMenuVisible.value = false
}
const rename = async () => {
  renameAPI({
    id
  })
}
// 监听文档点击事件以隐藏菜单（如果需要的话）
const hideMenuOnDocClick = (event) => {
  if (!event.target.closest('.context-menu')) {
    contextMenuVisible.value = false
  }
}

onMounted(() => {
  document.addEventListener('click', hideMenuOnDocClick)
})

onUnmounted(() => {
  document.removeEventListener('click', hideMenuOnDocClick)
})
</script>

<template>
  <el-tabs>
    <el-tab-pane>
      <template #label>
        <div class="later">最近</div>
      </template>

      <el-table
        :data="list"
        :row-style="{ height: '60px' }"
        @row-click="handleRowClick"
        v-loading="loading"
        @row-contextmenu="handleTableContextmenu"
        row-key="id"
      >
        <el-table-column prop="title" label="名称"> </el-table-column>
        <!-- <el-table-column prop="belong" label="归属"></el-table-column> -->

        <el-table-column prop="path" label="位置"></el-table-column>
        <el-table-column
          prop="updateTime"
          label="最后浏览时间"
        ></el-table-column>
      </el-table>
      <!-- 自定义右键菜单 -->
      <div
        v-show="contextMenuVisible"
        :style="{ top: contextMenuTop + 'px', left: contextMenuLeft + 'px' }"
        class="context-menu"
      >
        <ul class="flexpf">
          <li>新窗口打开</li>
          <li>复制链接</li>
          <li>置顶</li>
          <li>智能助手</li>
          <li @click="rename('option2')">重命名</li>
          <li>移动到</li>
          <li>创建副本</li>
          <li>下载</li>
          <li>转让所有权</li>
          <li @click="deleteMenuItemClick('option1')">删除</li>

          <!-- 其他菜单项 -->
        </ul>
      </div>
    </el-tab-pane>
    <el-tab-pane>
      <template #label>
        <div :style="{}" class="star">收藏</div>
      </template>
      star
    </el-tab-pane>
  </el-tabs>
</template>

<style lang="scss" scoped>
.el-tabs__item {
  font-size: 24px;
  background-color: aqua;
}
.star,
.later {
  font-size: 24px;
}
.el-tabs__nav-wrap .el-tabs__item.is-active {
  color: var(--el-color-primary);
  font-weight: 700;
}

.context-menu {
  position: fixed;
  z-index: 1000; /* 确保菜单显示在其他内容之上 */
  background-color: #fff;
  border: 1px solid #ccc;
  padding: 10px;
  height: 432px;
  width: 140px;
  box-shadow: 0 0 3px 3px #efefef;
}
.context-menu ul {
  height: 100%;
  list-style: none;
  padding: 0;
  margin: 0;
}
.context-menu li {
  cursor: pointer;
  padding: 5px;
}
.context-menu li:hover {
  background-color: #f0f0f0;
}
</style>
