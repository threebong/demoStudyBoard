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
                        <button type="button" @click="checkDupId()">중복ID확인</button>
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
import common from '@/common/common.js'

let isDupCheck = false;
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
    props:{
        isModalViewed: Boolean
    },
    // emit: defineEmits(['close-modal']),
    setup(props, { emit }) {// <---이렇게 해야 emit 먹힘..왜지?몰라..
        // const emit = defineEmits(['close-modal'])
        const submitForm = () => {
            // console.log(data.value)
            console.log('회원가입하기누름')
            //회원정보 vali check
            if(checkForm(data.value)){
                request.post('/api/joinMember', data.value)
                    .then((res) => {
                        console.log('갔다왔다')
                        console.log(res)
                        if(res>0){
                            alert('회원가입 완료되었습니다.')
                            emit('close-modal')
                        }else{
                            alert('회원가입이 실패했습니다.\n다시 시도해주세요.')
                        }
                    })
            }
        }

        const test = () => {
            console.log('테스트')
            request.get('/api/test',data.value)
        }


        // 입력정보 체크 TODO 이것저것검증하는거추가하기...
        const checkForm = (data) => {
            if(data.memberPw !== data.pwAgain){
                alert('비밀번호가 일치하지 않습니다.')
            }else if(common.isEmpty(data.memberId).length === 0){
                alert('ID는 필수값입니다.');
            }else if(isDupCheck === false){
                alert('아이디 중복 확인해주세요.')
            }else if(common.isEmpty(data.memberName).length === 0){
                alert('이름은 필수값입니다.')
            }else if(common.isEmpty(data.memberPw).length === 0){
                alert('비밀번호를 입력해주세요.')
            }else{
                return true
            }
            return false

        }
        // 중복 Id확인
        const checkDupId = () => {
            console.log('아이디비었나?')
            console.log(common.isEmpty(data.value.memberId).length)
            if(common.isEmpty(data.value.memberId).length === 0){
                alert('공백을 아이디로 사용할 수 없습니다.')
            }else{
                request.get('/api/checkDupId', { params: { memberId: data.value.memberId } })
                    .then(res => {
                        console.log(res)
                        if(res>0){
                            alert('중복된 ID가 있습니다.\n다른걸 사용하세요.')
                            data.value.memberId = ''
                        }else {
                            alert('사용 가능한 ID입니다.')
                            isDupCheck = true
                            console.log(isDupCheck)
                        }
                    })
            }
        }
        // 폼 초기화
        const initForm = () => {

        }

        return{
            initForm,
            checkDupId,
            submitForm,
            checkForm,
            data,
            isDupCheck,
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