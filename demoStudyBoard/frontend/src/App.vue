<template>
  <nav>
    <router-link to="/">Home</router-link>
    <router-link to="/board/list">| 게시판</router-link>
    <router-link to="/about">| 공개 페이지</router-link>
    <router-link v-if="!isLogin" to="/login">| 로그인</router-link>

    <div v-if="isLogin">
      <p >{{this.$store.state.memberName}} 님 환영합니다.
      <button @click="logOut">로그아웃</button></p>
      </div>
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
import { computed, defineComponent } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  setup() {
    const store = useStore()

    const isLogin = computed(() => {
      return store.state.token === null ? false : true
    })

    const logOut = () => {
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
