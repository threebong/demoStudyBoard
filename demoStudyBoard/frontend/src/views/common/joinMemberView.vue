<template>
    <div class="modal">
        <div class="overlay" @click="$emit('close-modal')"></div>
        <div class="modal-card">
            <p>회원가입</p>
            <form @submit.prevent="submitForm">
                <div>
                    <div>
                        <label for="id">아이디: </label>
                        <input type="text" id="id" v-model="data.memberId"/>
                    </div>
                    <div>
                        <label for="name">이름: </label>
                        <input type="text" id="name" v-model="data.memberName"/>
                    </div>
                    <div>
                        <label for="pw">비밀번호: </label>
                        <input type="text" id="pw" v-model="data.memberPw"/>
                    </div>
                    <div>
                        <label for="pwAgain">비밀번호 확인: </label>
                        <input type="text" id="pwAgain" v-model="data.pwAgain"/>
                    </div>
                    <div>
                        <label for="email">이메일</label>
                        <input type="text" id="email" v-model="data.memberEmail"/>
                    </div>
                    <div>
                        <label for="phone">전화번호</label>
                        <input type="text" id="phone" v-model="data.memberPhone"/>
                    </div>
                    
                </div>
                <div>
                    <button type="submit" >회원 가입하기</button>
                    <button type="button" @click="$emit('close-modal')">닫기</button>
                    <button type="button" @click="test()">테스트</button>
                </div>
            </form>
        </div>
    </div>
</template>
<script>
import { defineComponent, ref } from 'vue'
import request from '@/api/core/api.js'


const data = ref({
    memberId: '',
    memberName: '',
    memberPw: '',
    pwAgain: '',
    memberEmail: '',
    memberPhone: ''
})

export default defineComponent({
    name: 'joinMemberView',
    setup() {
        const submitForm = () => {
            // console.log(data.value)
            console.log('회원가입하기누름')
            if(checkForm(data.value)) return
            console.log('회원가입하기누름통과')

            request.post('/api/joinMember', data.value)
                .then((res) => {
                    console.log('갔다왔다')
                    console.log(res.data)
                })

            console.log('비동기TEST')
        }

        const test = () => {
            console.log('테스트')
            request.get('/api/test',data.value)
        }

        
        // 입력정보 체크 TODO 이것저것검증하는거추가하기...
        const checkForm = (data) => {
            if(data.memberPw !== data.pwAgain){
                alert('비밀번호가 일치하지 않습니다.')
                return false
            }
        }

        return{
            submitForm,
            checkForm,
            data,
            test
        }
    },
    components: {

    }
})
</script>

<style>
    .modal,
    .overlay{
        width: 100%;
        height:100%;
        position: fixed;
        left:0;
        top:0;
    }
    .overlay{
        opacity: 0.5;
        background-color: black;
    }
    .modal-card{
        position:relative;
        max-width: 80%;
        margin:auto;
        margin-top:30px;
        padding:20px;
        background-color:white;
        min-height: 500px;
        z-index: 10;
        opacity:1;
    }
</style>