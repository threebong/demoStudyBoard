import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index.js'
import axios from 'axios'
import Vuex from 'vuex'
import './assets/common.css'

const app = createApp(App)

//전역변수설정
// app.config.globalProperties.$store = store
app.config.globalProperties.$axios = axios


app.use(store)
    .use(router)
    .use(Vuex)
    .mount('#app')
