import { createPinia } from 'pinia'
import persist from 'pinia-plugin-persistedstate'
const pinia = createPinia()
pinia.use(persist)
export default pinia
// export * from './modules/count'
export * from './modules/user.js'
export * from './modules/detail.js'
