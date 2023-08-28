import axios from 'axios'

const request = axios.create({
    baseURL: 'http://localhost:8080'
})

//요청 타임아웃
request.defaults.timeout = 2500;

//요청 인터셉터
request.interceptors.request.use(
    config => {
        //요청 보내기 전 수행할 로직
        console.log('AXIOS!!!')
        config.headers['Access-Control-Allow-Origin']='*'
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
        return res
    },
    error => {
        console.log(error)
        return Promise.reject(error)
    }
)

//axios인스턴스 내보내기
export default request