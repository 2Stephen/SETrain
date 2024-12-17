import { createRouter, createWebHashHistory } from 'vue-router'
import store from '../store'

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
    name: 'question',
    component: QuestionDetail,
    props:true, // 将路由参数作为 props 传递给组件
    meta: {
      requireAuth: true
    }
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if(to.meta.requireAuth && !store.getters.isAuthenticated){
    // next('/login'); //未登录，重定向到登录页
  }else{
    next(); //放行
  }
})

export default router
