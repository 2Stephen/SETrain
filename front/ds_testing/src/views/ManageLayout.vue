<template>
  <el-container style="height: 100vh;">
    <el-header
        style="background-color: rgb(172,219,252); color: rgb(51.2, 126.4, 204); display: flex; justify-content: space-between; align-items: center;">
      <div style="display: flex;justify-content: flex-start;align-items: center;">
        <div class="header-title">数据结构题库系统</div>
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

      <div style="display: flex;justify-content: flex-end;align-items: center;">
        <el-input v-model="search" style="width: 12.5rem" placeholder="搜索题目" class="input-with-select">
          <template #append>
            <el-button type="primary" @click="">
              <el-icon>
                <Search/>
              </el-icon>
            </el-button>
          </template>
        </el-input>
        <el-button type="primary" plain @click="clickToLogin" style="margin-left: 0.8rem;">登录</el-button>
        <el-button type="primary" @click="clickToRegister" style="margin-right: 0.625rem;">注册</el-button>
      </div>
    </el-header>

    <el-container>
      <el-aside width="220px" style="background-color: rgb(235, 245, 255);">
        <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            router
        >

          <el-menu-item index="/manageQuestion">
            <el-icon><icon-menu /></el-icon>
            <span>题目管理</span>
          </el-menu-item>

          <el-menu-item index="/manageQuestionBank">
            <el-icon><setting /></el-icon>
            <span>题库管理</span>
          </el-menu-item>

          <el-menu-item index="3" disabled>
            <el-icon><document /></el-icon>
            <span>用户管理</span>
          </el-menu-item>

        </el-menu>
      </el-aside>

      <el-main>
        <router-view/>
      </el-main>

    </el-container>
    <el-footer class="foot">
      © 2024 数据结构题库系统 - 版权所有
    </el-footer>

  </el-container>
</template>

<script>
export default {
  name: "ManagePage.vue",
  data() {
    return {
      search: ''
    }
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
    // handleMenuSelect(index) {
    //   console.log(index)
    //   switch (index) {
    //     case '1':
    //       this.$router.push('/question')
    //       break
    //     case '2':
    //       this.$router.push('/questionBank')
    //       break
    //   }
    // }

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