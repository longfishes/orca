import { createApp } from 'vue'
import pinia from './stores/index'
import App from './App.vue'
import router from './router'
import '@/styles/common.scss'
import { lazyPlugin } from '@/directives/index'
// import { componentPlugin } from '@/components/index.js'
const app = createApp(App)
app.use(router)
app.use(pinia)
app.use(lazyPlugin)
// app.use(componentPlugin)
app.mount('#app')
