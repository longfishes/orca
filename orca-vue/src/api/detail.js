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
// 修改文章
export const updateDetailAPI = ({
  id,
  title = 'string',
  cover = 'string',
  docAbstract = 'string',
  content ,
  path = '/',
  isTop = false,
  status = false
}) => {
  return request({
    url: '/document/update',
    method: 'PUT',
    data: {
      id,
      title,
      cover,
      docAbstract,
      content,
      path,
      isTop,
      status
    }
  })
}
