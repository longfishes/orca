import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getWordDataAPI, getListAPI, updateDetailAPI } from '@/api/detail'

export const useDetailStore = defineStore(
  'tinymceDetail',
  () => {
    // 路由参数
    const idRouter = ref([])
    // 表格的列表数据
    const list = ref([])

    // 信号
    const flag = ref(false)

    const setFlag = (data) => {
      flag.value = data
    }
    // 右击获取该行的id
    const idRow = ref(0)
    const getIdRow = (id)=>{
      idRow.value=id
    }
   const getIdRouter = (id) => {
      idRouter.value = id
    }

    const getList = async (data) => {
      const res = await getListAPI(data)
      list.value = res.data.data.rows
    }
    return {
      
      getList,
      list,
      getIdRouter,
      idRouter,
      flag,
      setFlag,
      getIdRow,
      idRow
    }
  },
  { persist: true }
)
