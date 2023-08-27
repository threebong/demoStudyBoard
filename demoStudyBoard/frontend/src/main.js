import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/storage.js'
import axios from 'axios'

const app = createApp(App)

//전역변수설정
app.config.globalProperties.$store = store
app.config.globalProperties.$axios = axios

app.use(store)
    .use(router)
    .mount('#app')
