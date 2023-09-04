import axios from 'axios'
// import { useStore } from 'vuex'
// import { computed } from 'vue'
import store from '@/store/index.js'

const request = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        Authorization: 'TEST'
    }

})


//요청 타임아웃
// request.defaults.timeout = 2500;
// const token = computed(() => state.token)

//요청 인터셉터
request.interceptors.request.use(
    config => {
        // const store = useStore()
        //요청 보내기 전 수행할 로직
        console.log('AXIOS!!!')
        const token1 = store.state.token
        // const token2 = store.state.token
        console.log('-----------------토큰넣기-----------------------')
        // console.log(token)
        console.log(token1)
        // console.log(token2)
        console.log('-----------------토큰넣기-----------------------')

        config.headers['Access-Control-Allow-Origin']='*'
        config.headers['Content-Type'] = 'application/json'

        config.headers['Authorization'] = token1
        // config.headers.Authorization = store.state.token
        // const token = computed(() => store.state.token)
        // console.log(token)
        // console.log(this.store.state.value.token)
        return config
    },
    error => {
        console.log(error)
        return Promise.reject(error);
    }
)
//응답 인터셉터
request.interceptors.response.use(
    response => {
        //응답에 대한 로직
        console.log('응답AXIOS')
        const res = response.data
        console.log(res)
        return res
    },
    error => {
        console.log(error)
        return Promise.reject(error)
    }
)

//axios인스턴스 내보내기
export default request