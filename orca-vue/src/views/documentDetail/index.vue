<template>
  <tinymce></tinymce>
</template>

<script setup>
import { useRoute } from 'vue-router'
const route = useRoute()
import Tinymce from '@/views/tinymce/tinymce.vue'
import { ref } from 'vue'
console.log(route.params.id)
import { useDetailStore } from '@/stores'
import { onBeforeUnmount } from 'vue'

const detailStore = useDetailStore()
detailStore.getWordData(route.params.id)
onBeforeUnmount(() => {
  console.log('Component is about to be unmounted')
  // 清理操作，如取消订阅、移除事件监听器等
})
detailStore.updateDetail({
  id: route.params.id,
  content: detailStore.updateContent
})
</script>

<style lang="scss" scoped></style>
