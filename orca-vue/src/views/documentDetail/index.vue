<template>
   <tinymce :message="parentMessage"></tinymce>
</template>

<script setup>
    import Tinymce from '@/views/tinymce/tinymce.vue'
    import { ref } from 'vue'
    const parentMessage = ref('')
    parentMessage.value = '这是父组件传过来的值'
    console.log(parentMessage.value)
</script>

<style lang="scss" scoped>

</style>