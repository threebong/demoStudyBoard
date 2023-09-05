<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">게시판</router-link> |
    <router-link to="/login">로그인</router-link>

    <div v-if="isLogin==true">
      <p >{{this.$store.state.memberName}} 님 환영합니다.
      <button @click="logOut">로그아웃</button></p>
    </div>
    |||<button @click="logOut">로그아웃(TEST)</button>
  </nav>
  <router-view/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
<script>
import { defineComponent, onMounted, ref } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  setup() {
    const isLogin = ref(true)
    const store = useStore()
    onMounted(() => {
      console.log('마운트 실행')
      console.log(store.state.token)
      console.log(isLogin.value)
      if(store.state.token !== null){
        isLogin.value = true
      }else{
        isLogin.value = false
      }
    })


    const logOut = () => {
      console.log('로그인화면')
      console.log(store.state.token)
      store.dispatch('logout')
    }

    return {
      logOut,
      isLogin
    }
  },
})
</script>
