<template>
  <div class="common-layout">
    <el-container style="height: 100vh;">
      <el-header style="display: flex;justify-content: flex-end;align-items: center;">
        <el-button type="primary" plain @click="clickToHome">首页</el-button>
        <el-button type="primary" @click="clickToRegister">注册</el-button>
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
      axios.post('http://192.168.35.214:8082/api/login', this.loginForm)
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
</style>
