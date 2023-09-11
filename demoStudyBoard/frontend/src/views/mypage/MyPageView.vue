<template>
    MY PAGE
    <div>
        <p>
            이름:
            {{this.$store.state.memberName}}
        </p>
        <p>
            이메일:
            {{ this.$store.state.memberEmail }}
        </p>
    </div>
</template>
<script>
import { defineComponent, onMounted } from 'vue'

export default defineComponent({
    setup() {
        onMounted(()=>{
            console.log('마이페이지 MOUNT')
        })
        console.log('어느게더먼저')
    },
})
</script>
