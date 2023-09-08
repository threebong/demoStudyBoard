<template>
    <loginView></loginView>
</template>
<script>
import { defineComponent } from 'vue'
import loginView from '@/common/loginView.vue'
// import joinMemberView from '@/common/joinMemberView.vue'

export default defineComponent({
    name: 'logoinMain',
    setup() {

    },
    components: {
        loginView
        // joinMemberView
    }
})
</script>
