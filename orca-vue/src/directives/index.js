// 定义懒加载插件
import { useIntersectionObserver } from '@vueuse/core'

export const lazyPlugin = {
  install(app) {
    // 懒加载指令逻辑
    app.directive('img-lazy', {
      mounted(el, binding) {
        // el: 指令绑定的那个元素 img
        // binding: binding.value  指令等于号后面绑定的表达式的值  图片url
        // console.log(el, binding.value)
        const { stop } = useIntersectionObserver(el, ([{ isIntersecting }]) => {
          // console.log(isIntersecting)
          if (isIntersecting) {
            // 进入视口区域
            el.src = binding.value
            stop()
          }
        })
      }
    })
  }
}

export const outsidePlugin = {
  install(app) {
    app.directive('outside', {
      // 当被绑定的元素挂载到DOM中时...
      mounted(el, binding) {
        // 提供一个点击事件的处理函数
        el.clickOutsideEvent = function (event) {
          // 检查点击事件是否发生在元素外部
          if (!(el === event.target || el.contains(event.target))) {
            // 触发绑定的表达式，可以是方法或任何可执行的JavaScript表达式
            binding.value(event)
          }
        }
        // 在document上添加事件监听器
        document.addEventListener('click', el.clickOutsideEvent)
      },
      // 当指令卸载时...
      unmounted(el) {
        // 移除事件监听器，以防止内存泄漏
        document.removeEventListener('click', el.clickOutsideEvent)
      }
    })
    // 自定义指令 v-dropdown
  }
}
// isIntersecting布尔值，判断是否进入视口区域
