<template>
    <div>
        <div>
            <h2>로그인 하세요</h2>
            <p>
                <input class="w3-input" name="uid" placeholder="ID" v-model="data.user_id"><br>
            </p>
            <p>
                <input name="password" class="w3-input" placeholder="Password" v-model="data.user_pw" type="password">
            </p>
            <p>
                <button type="submit" class="w3-button w3-green w3-round">Login</button>
                <button @click="openJoinMember()" class="w3-button w3-white w3-border w3-border-red w3-round-large">회원가입</button>
            </p>
            <joinMemberView v-if="isModalViewed" @close-modal="isModalViewed=false"></joinMemberView>
        </div>
    </div>
</template>

<script type="text/javascrpt">
import { defineComponent, ref } from 'vue'
// import { useRouter } from 'vue-router'
import joinMemberView from './joinMemberView.vue'

const data = ref({
    user_id: '',
    user_pw: ''
})
export default defineComponent({
    name: 'loginView',
    setup() {
        // const router = useRouter();
        const isModalViewed = ref(false)
        const openJoinMember = () => {
            console.log('회원가입누름')
            isModalViewed.value = true
            console.log(isModalViewed.value)
        }
        console.log(isModalViewed.value)

        return {
            openJoinMember,
            isModalViewed,
            data
        }
    },
    components: {
        joinMemberView
    }
})
</script>

