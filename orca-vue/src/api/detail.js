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
    url: '/document/path',
   
    data
  })
}
// 修改文章
export const updateDetailAPI = ({
  id,
  title,
  cover = 'string',
  docAbstract = 'string',
  content,
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
// 新建文档
export const CreateNewAPI = (data) => {
  return request({
    url: '/document/create',
    method: 'POST',
    data
  })
}
