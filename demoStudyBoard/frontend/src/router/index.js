import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'


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
    path: '/board/list',
    name: 'BoardList',
    component: () => import('../views/board/BoardList.vue')
  }

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
