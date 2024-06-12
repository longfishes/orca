import request from '@/utils/request'
// 获取文章详情
export const getWordDataAPI = (id) => {
  return request({
    url: `/document/${id}`
  })
}
// 获取文章表格
export const getListAPI = (data) => {
    return request({
        url: '/document/list',
        method: 'POST',
        data
    })
}
