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
// 将路由参数传给pinia
detailStore.getIdRouter(route.params.id)
onBeforeUnmount(() => {
  console.log('Component is about to be unmounted')
 
})

</script>

<style lang="scss" scoped></style>
