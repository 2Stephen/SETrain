<template>
  <div class="common-layout" style="height: 100vh;">
    <el-container style="height: 100vh;">

      <el-header
        style="background-color: rgb(172,219,252); color: rgb(51.2, 126.4, 204); display: flex; justify-content: space-between; align-items: center;">
        <div style="display: flex;justify-content: flex-start;align-items: center;">
          <div class="header-title">数据结构题库系统</div>
          <el-menu ellipsis mode="horizontal" background-color="rgb(172,219,252)" text-color="rgb(53,53,53)"
            style="width:30rem;display: flex;align-items: center;" default-active="1">
            <el-menu-item index="1" @click="clickToIndex">首页</el-menu-item>
            <el-menu-item index="2" @click="clickToHome">题库</el-menu-item>
            <el-sub-menu index="3"><template #title>帮助</template>
              <el-menu-item index="3-1">快速入门</el-menu-item>
              <el-menu-item index="3-2">常见问题</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </div>

        <div style="display: flex;justify-content: flex-end;align-items: center;">
          <!-- <el-input v-model="search" style="width: 12.5rem" placeholder="搜索题目" class="input-with-select">
            <template #append>
              <el-button type="primary" @click=""><el-icon>
                  <Search />
                </el-icon></el-button>
            </template>
          </el-input> -->
          <div v-if="isAuthenticated">
            欢迎，{{ user }}
            <el-button type="primary" plain @click="loginout" style="margin-left: 0.8rem;">退出登录</el-button>
          </div>
          <div v-if="!isAuthenticated">
            <el-button type="primary" plain @click="clickToLogin" style="margin-left: 0.8rem;">登录</el-button>
            <el-button type="primary" @click="clickToRegister" style="margin-right: 0.625rem;">注册</el-button>
          </div>
        </div>
      </el-header>

      <el-container style="background-color: rgb(172,219,252);">
        <el-aside style="display: flex;justify-content: center;width: 25rem;margin-left: 100px;">
          <div style="margin-top: 150px;text-align: left;">
            <div style="font-size: 28px;font-weight: bold;">数据结构</div>
            <div style="font-size: 40px;font-weight: bold;">免费在线刷题平台</div>
            <div style="font-size:18px;margin-bottom: 5px;margin-top: 5px;">在线练习,让考试更简单</div>
            <div style="margin-top: 20px;">
              <el-button type="primary" size="large" @click="clickToRegister">免费注册</el-button>
            </div>
          </div>
        </el-aside>

        <el-main style="display: flex;justify-content: flex-end;align-items: center;">
          <el-carousel :interval="3000" arrow="never" indicator-position="outside" style="width:800px;" height="500px">
            <el-carousel-item v-for="(image, index) in imageList" :key="index">
              <img :src="image" style="height: 500px;width:800px;">
            </el-carousel-item>
          </el-carousel>
        </el-main>
      </el-container>

      <el-footer class="foot">
        © 2024 数据结构题库系统 - 版权所有
      </el-footer>

    </el-container>
  </div>
</template>

<script>
import store from '@/store';
import { computed } from 'vue';
import { useStore } from 'vuex';
export default {
  name: 'IndexPage',
  data() {
    return {
      store : useStore(),
      isAuthenticated: computed(() => store.getters.isAuthenticated),
      user: computed(() => store.getters.getUser),

      index: 1,
      imageList: [
        'https://www.kaoshiyun.com/index/style/images/2023banner_01.svg',
        'https://www.kaoshiyun.com/index/style/images/2023banner_03.svg',
        'https://www.kaoshiyun.com/index/style/images/2023banner_04.svg',
      ]
    }
  },
  methods: {
    clickToRegister() {
      this.$router.push('/register')
    },
    clickToLogin() {
      this.$router.push('/login')
    },
    clickToIndex() {
      this.$router.push('/')
    },
    clickToHome() {
      this.$router.push('/home')
    },
    loginout(){
      this.$store.dispatch('logout');
    },

  }
}
</script>


<style scoped>
.header-title {
  font-size: 22px;
  font-weight: bold;
  margin-left: 0.625rem;
  margin-right: 3.125rem;
}

.el-menu--horizontal.el-menu {
  border-bottom: 0px;
}

.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
}

.el-menu-item {
  width: 100px;
}

.el-sub-menu {
  width: 100px;
  align-items: center;
}

.el-carousel__container {
  width: 1600px;
  height: 1000px;
}

.foot {
  background-color: rgb(172, 219, 252);
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
