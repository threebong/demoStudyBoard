<template>
    <div>
        <div>
            <h2>로그인 하세요</h2>
            <form @submit.prevent="goLogin">
                <p>
                    <input class="w3-input" name="uid" placeholder="ID" v-model="data.user_id"><br>
                </p>
                <p>
                    <input name="password" class="w3-input" placeholder="Password" v-model="data.user_pw" type="password">
                </p>
                <p>
                    <button type="submit" class="w3-button w3-green w3-round">Login</button>
                    <button type="button" @click="openJoinMember()" class="w3-button w3-white w3-border w3-border-red w3-round-large">회원가입</button>
                </p>
            </form>
            <joinMemberView v-if="isModalViewed" @close-modal="isModalViewed=false"></joinMemberView>
        </div>
    </div>
</template>

<script type="text/javascrpt">
import { defineComponent, ref } from 'vue'
// import { useRouter } from 'vue-router'
import joinMemberView from './joinMemberView.vue'
// import request from '@/api/core/api.js'
// import common from '@/common/common.js'
// import store from '@/store/module.js'
import { useStore } from 'vuex'

const data = ref({
    user_id: '',
    user_pw: ''
})
export default defineComponent({
    name: 'loginView',
    setup() {
        // const router = useRouter();
        const store = useStore()

        const isModalViewed = ref(false)
        const openJoinMember = () => {
            console.log('회원가입누름')
            isModalViewed.value = true
            console.log(isModalViewed.value)
        }
        console.log(isModalViewed.value)

        const goLogin = () => {
            console.log('로그인누름')
            console.log(data.value)
            // request.post("/api/loginMember", data.value)
            //     .then((res) => {
            //         console.log(res)
            //         console.log(res.Authorization)
            //         console.log("로그인갔다옴")
            //     })
            // this.$store.dispatch('login')
            // const data1 = data.value
            // store.actions.login(1, JSON.stringify(data1))
            // store.actions.login(data.value)
            store.dispatch('login', data.value)
            initForm()
        }

        const initForm = () => {
            data.value.user_id = ''
            data.value.user_pw = ''
        }

        return {
            goLogin,
            openJoinMember,
            initForm,
            isModalViewed,
            data
        }
    },
    components: {
        joinMemberView
    }
})
</script>

