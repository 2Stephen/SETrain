<template>
  <el-container style="height: 100vh;">
    <el-header
      style="background-color: rgb(172,219,252); color: rgb(51.2, 126.4, 204); display: flex; justify-content: space-between; align-items: center;">
      <div style="display: flex;justify-content: flex-start;align-items: center;">
        <div class="header-title">面试题库系统</div>
        <el-menu ellipsis mode="horizontal" background-color="rgb(172,219,252)" text-color="rgb(53,53,53)"
          style="width:30rem;display: flex;align-items: center;" default-active="3">
          <el-menu-item index="1" @click="clickToIndex" style="width:100px;">首页</el-menu-item>
          <el-menu-item index="2" @click="clickToHome" style="width:100px;">题库</el-menu-item>
          <el-menu-item index="3" @click="clickToManage" style="width:100px;">管理</el-menu-item>
          <el-sub-menu index="4" style="width:100px;">
            <template #title>帮助</template>
            <el-menu-item index="4-1">快速入门</el-menu-item>
            <el-menu-item index="4-2">常见问题</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </div>
      <div v-if="isAuthenticated">
        欢迎，{{ user }}
        <el-button type="primary" plain @click="loginout" style="margin-left: 0.8rem;">退出登录</el-button>
      </div>
      <div v-if="!isAuthenticated">
        <el-button type="primary" plain @click="clickToLogin" style="margin-left: 0.8rem;">登录</el-button>
        <el-button type="primary" @click="clickToRegister" style="margin-right: 0.625rem;">注册</el-button>
      </div>
    </el-header>

    <el-container>
      <el-aside width="220px" style="background-color: rgb(235, 245, 255);">
        <el-menu @select="handleMenuSelect" default-active="1" background-color="rgb(235, 245, 255)"
          text-color="#606266" router>
          <el-menu-item index="1">
            <el-icon><icon-menu /></el-icon>
            <span>题目管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-main>
        <router-view />
      </el-main>

    </el-container>
    <el-footer class="foot">
      © 2024 面试题库系统 - 版权所有
    </el-footer>

  </el-container>
</template>

<script>
import store from '@/store';
import { computed } from 'vue';
import { useStore } from 'vuex';
export default {
  name: "ManagePage.vue",
  data() {
    return {
      store: useStore(),
      isAuthenticated: computed(() => store.getters.isAuthenticated),
      user: computed(() => store.getters.getUser),
      search: ''
    }
  },

  mounted() {
    console.log("mounted");
    this.$router.push('/manageQuestion');
  },

  methods: {
    clickToIndex() {
      this.$router.push('/')
    },
    clickToHome() {
      this.$router.push('/home')
    },
    clickToManage() {
      this.$router.push('/manage')

    },
    clickToLogin() {
      this.$router.push('/login')
    },
    clickToRegister() {
      this.$router.push('/register')
    },
    handleMenuSelect() {
      this.$router.push('/manageQuestion')
    },
    loginout() {
      this.$store.dispatch('logout');
      this.$message.success('退出登录成功！');
      this.$router.push('/');
    },

  }
}
</script>

<style scoped>
.foot {
  background-color: rgb(235.9, 245.3, 255);
  display: flex;
  align-items: center;
  justify-content: center;
}

.el-card {
  border-radius: 10px;
}

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
  display: flex;
  text-align: left;
}

.el-sub-menu {
  width: 100px;
  align-items: center;
}
</style>