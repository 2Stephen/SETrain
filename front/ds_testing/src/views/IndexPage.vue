<template>
  <div class="common-layout">
    <el-container>
      <el-header style="display: flex;justify-content: flex-end;align-items: center;">
        <el-button type="primary" plain @click="clickToLogin">登录</el-button>
        <el-button type="primary" @click="clickToRegister">注册</el-button>
      </el-header>

    </el-container>
  </div>
</template>

<script>
export default {
  name: 'IndexPage',
  data() {
    return {
    }
  },
  methods: {
    clickToRegister() {
      this.$router.push('/register')
    },
    clickToLogin() {
      this.$router.push('/login')
    }
  }
}
</script>


<style scoped></style>
