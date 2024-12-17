import { createApp } from 'vue'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElIconList from '@element-plus/icons-vue'
import App from './App.vue'
import router from './router'

const app = createApp(App).use(router).use(store)
for (const name in ElIconList) {
    app.component(name, ElIconList[name])
  }
app.use(ElementPlus).mount('#app')