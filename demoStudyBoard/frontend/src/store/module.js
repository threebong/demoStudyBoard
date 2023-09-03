import router from '../router/index.js'
import request from '@/api/core/api.js'

const state = {
    token : null,
    id: null,
    name: null,
    email: null,
    phone: null
}

const getters = {
    'token': state => state.token,
    'id': state => state.id,
    'name:': state => state.name,
    'email': state => state.email,
    'phone': state => state.phone
}
//data 저장
const mutations = {
    login (state, item) {
        // state.token = item.headers['Authorization']
        // state.token = item.headers['Test']
        console.log(item)
        state.id = item.data['id']
        state.name = item.data['name']
        state.email = item.data['email']
        state.phone = item.data['phone']
    },
    logout (state) {
        state.token = null
        state.id = null
        state.name = null
        state.email = null
        state.phone = null
    }
}

const actions = {
    login ( {commit}, data ) {//{id, password}
        // const params = {
        //     "email": id,
        //     "password": password
        // }
        console.log("----[module.js]----")
        console.log(data)
        request.post("/api/loginMember", data 
            //} JSON.stringify(params), {
        //   headers: { 'content-type': 'application/json' }}
        ).then(res => {
          console.log(res)
          commit('login', res)
          console.log(':::::::::::::VUEX 의 로그인:::::::::::: ')
          router.push("/")
        }).catch(e => {
          console.log(e)
          console.log(':::::VUEX 로그인실패:::::::::::')
        })
  
    },
    logout ({commit}) {
        commit('logout')
    } 
}

export default {
    state,
    getters,
    mutations,
    actions
}