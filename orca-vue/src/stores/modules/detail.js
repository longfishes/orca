import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getWordDataAPI, getListAPI } from '@/api/detail'

export const useDetailStore = defineStore(
  'tinymceDetail',
  () => {
    const wordData = ref([
      {
        id: 1,
        type: '文档',
        belong: 'whr',
        position: '前端文件夹',
        time: '2024/06/01',
        content: '这是文档的内容'
      },
      {
        id: 2,
        type: '文档',
        belong: 'whr',
        position: '前端文件夹',
        time: '2024/06/01',
        content: '这是文档2的内容'
      },
      {
        id: 3,
        type: '文档',
        belong: 'whr',
        position: '前端文件夹',
        time: '2024/06/01',
        content: '这是文档3的内容'
      }
    ])
    const list = ref([])
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
    return {
      wordData,
      getWordData,
        getList,
        list
    }
  },
  { persist: true }
)
