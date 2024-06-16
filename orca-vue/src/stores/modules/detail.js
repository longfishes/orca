import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getWordDataAPI, getListAPI, updateDetailAPI } from '@/api/detail'

export const useDetailStore = defineStore(
  'tinymceDetail',
  () => {
    // 每条文章的详情数据
    const wordData = ref([])
    // 表格的列表数据
    const list = ref([])
    // tinymce传回来的最新文本内容
    const updateContent = ref('')
    const getUpdateContent = (content) => {
      updateContent.value = content
      console.log(updateContent.value)
    }
    const getList = async (data) => {
      const res = await getListAPI(data)
      list.value = res.data.data.rows
    }
    // 获取详情数据
    const getWordData = async (id) => {
      const res = await getWordDataAPI(id)
      wordData.value = res.data.data

      console.log(wordData.value)
    }
    const updateDetail = async (data) => {
      const res = await updateDetailAPI(data)
      console.log(res)
    }
    return {
      wordData,
      getWordData,
      getList,
      list,
      updateDetail,
      getUpdateContent,
      updateContent
    }
  },
  { persist: true }
)
