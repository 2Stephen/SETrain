import { createRouter, createWebHashHistory } from 'vue-router'

import IndexPage from '@/views/IndexPage.vue'
import UserLogin from '@/views/UserLogin.vue'
import HomePage from '@/views/HomePage.vue'
import FindPwd from '@/views/FindPwd.vue'
import RegisterPage from '@/views/RegisterPage.vue'
import QuestionDetail from '@/views/QuestionDetail.vue'

const routes = [
  {
    path: '/',
    name: 'index',
    component: IndexPage
  },
  {
    path: '/login',
    name: 'login',
    component: UserLogin
  },
  {
    path: '/home',
    name: 'home',
    component: HomePage
  },
  {
    path: '/find',
    name: 'find',
    component: FindPwd
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterPage
  },
  {
    path: '/question/:id',
    // path:'/question',
    name: 'QuestionDetail',
    component: QuestionDetail,
    props:true, // 将路由参数作为 props 传递给组件
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
