import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import store from '@/store/index.js'


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/common/loginView.vue'),
    children: [
      {
        path: 'joinMember',
        component: () => import('../views/common/joinMemberView.vue')
      }
    ]
  },
  {
    path: '/board',
    name: 'board',
    beforeEnter(from, to, next){
      console.log('BEFORE ENTER ')
      console.log(store.state.token)
      if(store.state.token===null){
        alert('로그인해야만 볼 수 있습니다.\n로그인하세요.')
        next('/login')
      }else{
        next()
      }
    },
    children: [
      {
        path: 'list',
        name: 'BoardList',
        component: () => import('../views/board/BoardList.vue')
      },
      {
        path: 'detail',
        name: 'BoardDetail',
        component: () => import('../views/board/BoardDetail.vue')
      },
      {
        path: 'write',
        name: 'BoardWrite',
        component: () => import('../views/board/BoardWrite.vue')
      }
    ]
  }

  // {
  //   path: '/board/list',
  //   name: 'BoardList',
  //   component: () => import('../views/board/BoardList.vue')
  // },
  // {
  //   path: '/board/detail',
  //   name: 'BoardDetail',
  //   component: () => import('../views/board/BoardDetail.vue')
  // },
  // {
  //   path: '/board/write',
  //   name: 'BoardWrite',
  //   component: () => import('../views/board/BoardWrite.vue')
  // },

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
