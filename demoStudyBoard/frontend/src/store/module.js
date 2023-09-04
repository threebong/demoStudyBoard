import router from '../router/index.js'
import request from '@/api/core/api.js'

const state = {
    token : null,
    memberNo: null,
    memberId: null,
    memberName: null,
    memberEmail: null,
    memberPhone: null
}

const getters = {
    'token': state => state.token,
    'memberNo': state => state.memberNo,
    'memberId': state => state.memberId,
    'memberName:': state => state.memberName,
    'memberEmail': state => state.memberEmail,
    'memberPhone': state => state.memberPhone
}
//data 저장
const mutations = {
    login (state, item) {
        state.token = item['Authorization']
        // state.token = item.headers['Test']
        state.memberNo = item['memberNo']
        state.memberId = item['memberId']
        state.memberName = item['memberName']
        state.memberEmail = item['memberEmail']
        state.memberPhone = item['memberPhone']
        console.log('::::::::Mutation:::::::::')
        console.log(item['Authorization'])
        console.log(state)
        console.log(state.memberEmail)
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
          console.log(this.state.token)
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