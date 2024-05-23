import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: () => import('@/views/Layout/index.vue')
    },
    {
      path: '/login',
      component: () => import('@/views/Login/index.vue')
    },
    {
      path: '/register',
      component: () => import('@/views/Register/index.vue')
    },
    {
      path: '/password',
      component: () => import('@/views/Password/index.vue')
    }
  ]
})

export default router
