<script setup>
import { ref } from 'vue'
const tableList = ref([
  {
    type: '文档',
    belong: 'whr',
    position: '前端文件夹',
    time: '2024/06/01'
  },
  {
    type: '文档',
    belong: 'whr',
    position: '前端文件夹',
    time: '2024/06/01'
  },
  {
    type: '文档',
    belong: 'whr',
    position: '前端文件夹',
    time: '2024/06/01'
  }
])
</script>

<template>
  <el-tabs>
    <el-tab-pane>
      <template #label>
        <div class="edit">编辑过</div>
      </template>

      <el-table :data="tableList" :row-style="{ height: '60px' }">
        <el-table-column prop="type" label="类型"> </el-table-column>
        <el-table-column prop="belong" label="归属"></el-table-column>
        <el-table-column prop="position" label="位置"></el-table-column>
        <el-table-column prop="time" label="最后浏览时间"></el-table-column>
      </el-table>
    </el-tab-pane>
    <el-tab-pane>
      <template #label>
        <div :style="{}" class="look">浏览过</div>
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
.edit,
.look {
  font-size: 24px;
}
.el-tabs__nav-wrap .el-tabs__item.is-active {
  color: var(--el-color-primary);
  font-weight: 700;
}
</style>
