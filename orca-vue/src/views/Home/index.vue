<script setup>
import { ref } from 'vue'
import { useDetailStore } from '@/stores'
import { getListAPI } from '@/api/detail'
const detailStore = useDetailStore()
const list = ref([])

import { useRouter } from 'vue-router'
const router = useRouter()
const handleRowClick = (row, column) => {
  // row 是被点击的行的数据
  // column 是被点击的列的信息
  // event 是原生事件对象
  console.log(row.id)
  // 假设你的详情页路由是 '/detail/:id'，并且你的行数据中包含 'id' 属性
  router.push(`/detail/${row.id}`)
}
const getList = () => {
  const intervalId = setInterval(async () => {
    if (detailStore.flag) {
      console.log('可以了')
      const res = await getListAPI('/')
      list.value = res.data.data
      clearInterval(intervalId)
    }
  }, 1000)
}
getList()
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
      >
        <el-table-column prop="title" label="名称"> </el-table-column>
        <el-table-column prop="belong" label="归属"></el-table-column>
        <el-table-column prop="path" label="位置"></el-table-column>
        <el-table-column
          prop="updateTime"
          label="最后浏览时间"
        ></el-table-column>
      </el-table>
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
</style>
