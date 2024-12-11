<template>
  <div class="common-layout">
    <el-container style="height: 100vh;">
      <el-header
        style="background-color: rgb(172,219,252); color: rgb(51.2, 126.4, 204); display: flex; justify-content: space-between; align-items: center;">
        <div style="display: flex;justify-content: flex-start;align-items: center;">
          <div class="header-title">数据结构题库系统</div>
          <el-menu ellipsis mode="horizontal" background-color="rgb(172,219,252)" text-color="rgb(53,53,53)"
            style="width:30rem;display: flex;align-items: center;" default-active="1">
            <el-menu-item index="1">首页</el-menu-item>
            <el-menu-item index="2">题库</el-menu-item>
            <el-sub-menu index="3"><template #title>帮助</template>
              <el-menu-item index="3-1">快速入门</el-menu-item>
              <el-menu-item index="3-2">常见问题</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </div>

        <div style="display: flex;justify-content: flex-end;align-items: center;">
          <el-input v-model="search" style="width: 12.5rem" placeholder="搜索题目" class="input-with-select">
            <template #append>
              <el-button type="primary" @click=""><el-icon>
                  <Search />
                </el-icon></el-button>
            </template>
          </el-input>
          <el-button type="primary" plain @click="clickToHome" style="margin-left: 0.8rem;">首页</el-button>
          <el-button type="primary" @click="clickToRegister" style="margin-right: 0.625rem;">注册</el-button>
        </div>
      </el-header>

      <el-container style="background-color:#409EFF;">
        <el-aside width="55rem">
          <el-carousel height="400px" :interval="3000" arrow="never" indicator-position="outside" style="margin-top: 120px;width:40rem;margin-left: 100px;;">
            <el-carousel-item v-for="(image, index) in imageList" :key="index">
              <img :src="image" style="height: 100%;width:400px;">
            </el-carousel-item>
          </el-carousel>
        </el-aside>

        <div class="login">
          <el-card style="width: 30rem">
            <div class="card-header">
              登录
            </div>

            <div>
              <el-input class="input-area" v-model="loginForm.username" style="width: 18.75rem" placeholder="请输入用户名/邮箱">
                <template #prepend>
                  <el-icon>
                    <User />
                  </el-icon>
                </template>
              </el-input>

              <el-input class="input-area" v-model="loginForm.password" style="width: 18.75rem" type="password"
                placeholder="请输入密码" show-password>
                <template #prepend>
                  <el-icon>
                    <Lock />
                  </el-icon>
                </template>
              </el-input>

              <div style="text-align:right;margin-bottom:1rem;margin-right: 5rem;">
                <el-link href="#/find" type="primary">忘记密码</el-link>
              </div>

              <el-button type="primary" style="width: 18.75rem" @click="login">登录</el-button>

              <div
                style="display: flex; align-items: center;justify-content: center;margin-top: 1rem; text-align: center;">
                <el-text>首次登录？</el-text><el-link href="#/register" type="primary">点我注册</el-link>
              </div>

            </div>

          </el-card>
        </div>
      </el-container>

    </el-container>
  </div>
</template>

<script>
import axios from 'axios';
import request from '@/api/request';
export default {
  name: 'UserLogin',
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      imageList: [
        'https://resource.zaixiankaoshi.com/pc/login-banner.png',
        'https://resource.zaixiankaoshi.com/pc/login/banner/s1.png',
        'https://resource.zaixiankaoshi.com/pc/login/banner/s3.png',
      ]
    }
  },
  methods: {
    /* eslint-disable */
    login() {
      request.post('/user/login', this.loginForm)
        .then((response) => {
          // 请求成功处理
          this.$message.success('登录成功！');
          this.$router.push('/home');
        })
        .catch((error) => {
          console.log(error);
          this.$message.error("用户名或密码错误")
        })
    },
    clickToRegister() {
      this.$router.push('/register')
    },
    clickToFind() {
      this.$router.push('/find')
    },
    clickToHome() {
      this.$router.push('/')
    },
  }
}
</script>


<style scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;

}

.card-header {
  font-size: larger;
  font-weight: bold;
  margin-top: 0.5rem;
  margin-bottom: 1rem;
}

.input-area {
  margin-bottom: .9375rem;
}

.el-link .el-icon--right.el-icon {
  vertical-align: text-bottom;
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
  width: 100px;
}

.el-sub-menu {
  width: 100px;
}
</style>
