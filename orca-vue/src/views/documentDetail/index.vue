<template>
  <tinymce></tinymce>
</template>

<script setup>
import { useRoute } from 'vue-router';
const route = useRoute();
import Tinymce from '@/views/tinymce/tinymce.vue'
import { ref } from 'vue'
console.log(route.params.id)
import {useDetailStore} from '@/stores'
const detailStore = useDetailStore()
detailStore.getWordData(route.params.id)
</script>

<style lang="scss" scoped></style>
