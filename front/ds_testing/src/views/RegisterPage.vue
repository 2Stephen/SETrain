<template>
    <div class="common-layout">
        <el-container style="height: 100vh;">
            <el-header
                style="background-color: rgb(172,219,252); color: rgb(51.2, 126.4, 204); display: flex; justify-content: space-between; align-items: center;">
                <div style="display: flex;justify-content: flex-start;align-items: center;">
                    <div class="header-title">面试题库系统</div>
                    <el-menu ellipsis mode="horizontal" background-color="rgb(172,219,252)" text-color="rgb(53,53,53)"
                        style="width:30rem;display: flex;align-items: center;" default-active="1">
                        <el-menu-item index="1" @click="clickToIndex">首页</el-menu-item>
                        <el-menu-item index="2" @click="clickToHome">题库</el-menu-item>
                        <!-- <el-menu-item index="3" @click="clickToManage" style="width:100px;">管理</el-menu-item> -->
                        <el-sub-menu index="4" style="width:100px;"><template #title>帮助</template>
                            <el-menu-item index="4-1">快速入门</el-menu-item>
                            <el-menu-item index="4-2">常见问题</el-menu-item>
                        </el-sub-menu>
                    </el-menu>
                </div>

                <div style="display: flex;justify-content: flex-end;align-items: center;">
                    <el-button type="primary" plain @click="clickToIndex" style="margin-left: 0.8rem;">首页</el-button>
                    <el-button type="primary" @click="clickToLogin" style="margin-right: 0.625rem;">登录</el-button>
                </div>
            </el-header>

            <el-container style="background-color:#409EFF;">
                <el-aside width="55rem">
                    <el-carousel height="400px" :interval="3000" arrow="never" indicator-position="outside"
                        style="margin-top: 120px;width:40rem;margin-left: 100px;;">
                        <el-carousel-item v-for="(image, index) in imageList" :key="index">
                            <img :src="image" style="height: 100%;width:400px;">
                        </el-carousel-item>
                    </el-carousel>
                </el-aside>

                <div class="register">
                    <el-card style="width: 30rem">
                        <div class="card-header">
                            注册
                        </div>

                        <div>
                            <el-input class="input-area" v-model="registerForm.email" style="width: 18.75rem"
                                placeholder="请输入邮箱" :disabled="stop">
                                <template #prepend>
                                    <el-icon>
                                        <Message />
                                    </el-icon>
                                </template>
                            </el-input>

                            <div style="display: flex;justify-content: center;">
                                <el-input class="input-area" v-model="registerForm.captcha" style="width: 13rem"
                                    placeholder="请输入验证码">
                                    <template #prepend>
                                        <el-icon>
                                            <Key />
                                        </el-icon>
                                    </template>
                                </el-input>
                                &nbsp;&nbsp;
                                <el-button type="primary" plain style="width:82px;" @click="getCAPTCHA"
                                    :disabled="showTime">
                                    <div v-if="!showTime">获取验证码</div>
                                    <div v-if="showTime">{{ getTime }}秒</div>
                                </el-button>
                            </div>

                            <el-input class="input-area" v-model="registerForm.username" style="width: 18.75rem"
                                placeholder="请输入用户名">
                                <template #prepend>
                                    <el-icon>
                                        <User />
                                    </el-icon>
                                </template>
                            </el-input>

                            <el-input class="input-area" v-model="registerForm.password" style="width: 18.75rem"
                                type="password" placeholder="请输入密码" show-password>
                                <template #prepend>
                                    <el-icon>
                                        <Lock />
                                    </el-icon>
                                </template>
                            </el-input>

                            <el-input class="input-area" v-model="registerForm.checkpwd" style="width: 18.75rem"
                                type="password" placeholder="请再次输入密码" show-password>
                                <template #prepend>
                                    <el-icon>
                                        <Lock />
                                    </el-icon>
                                </template>
                            </el-input>



                            <div class="checkbox-container"
                                style="margin-bottom:1rem;display: flex; align-items: center;margin-left: 4.5rem;">
                                <el-checkbox v-model="checked" size="small"></el-checkbox> &nbsp;&nbsp;
                                <el-text size="small">我已阅读并同意</el-text><el-link href="#" type="primary"
                                    style="font-size: 12px;">《用户协议》</el-link>
                            </div>

                            <el-button type="primary" style="width: 18.75rem" @click="register"
                                :disabled="!checked">注册</el-button>

                            <div
                                style="display: flex; align-items: center;justify-content: center;margin-top: 1rem; text-align: center;">
                                <el-text>已有账号，</el-text><el-link href="#/login" type="primary">立即登录</el-link>
                            </div>

                        </div>

                    </el-card>
                </div>
            </el-container>

        </el-container>
    </div>
</template>

<script>
import request from '@/api/request';
import axios from 'axios';
export default {
    name: 'RegisterPage',
    data() {
        return {
            getTime: 60,
            showTime: false,
            stop: false,
            registerForm: {
                username: '',
                password: '',
                checkpwd: '',
                email: '',
                captcha: '',
            },
            checked: false,
            imageList: [
                'https://resource.zaixiankaoshi.com/pc/login/banner/s1.png',
                'https://resource.zaixiankaoshi.com/pc/login/banner/s3.png',
            ]
        }
    },
    methods: {
        /* eslint-disable */
        clickToLogin() {
            this.$router.push('/login')
        },
        clickToIndex() {
            this.$router.push('/')
        },
        clickToHome() {
            this.$router.push('/home')
        },
        clickToManage() {
            if (!this.isAuthenticated) {
                this.$message.error('点击登录管理题目');
            }
            this.$router.push('/manage')
        },
        getCAPTCHA() {
            const reg = /^([a-zA-Z0-9]+(\_\.[a-zA-Z0-9]+)*@[a-zA-Z0-9-]+\.[a-zA-Z]{2,4})$/;
            if (!reg.test(this.registerForm.email)) {
                this.$message.error('邮箱格式不正确！');
                return;
            }
            request.get('/user/getregisteremailcode', {
                params: {
                    email: this.registerForm.email
                }
            }).then((response) => {
                // 请求成功处理
                this.$message.success('验证码发送成功！')
                this.stop = true;
                let timer = setInterval(() => {
                    this.getTime -= 1;
                    if (this.getTime <= 0) {
                        clearInterval(timer);
                        this.showTime = false;
                        this.getTime = 60;
                    }
                }, 1000);
                this.showTime = true;
            }).catch((error) => {
                console.log(error);
                this.$message.error("邮箱已存在!")
            })
        },
        register() {
            if (!this.registerForm.username || !this.registerForm.email || !this.registerForm.password || !this.registerForm.checkpwd) {
                this.$message.error('请输入完整的邮箱、用户名及密码！');
                return;
            }
            if (this.registerForm.password.length < 6) {
                this.$message.error('密码长度不能少于6位！');
                return;
            }
            if (this.registerForm.password !== this.registerForm.checkpwd) {
                this.$message.error('两次输入的密码不一致！');
                return;
            }
            var curForm = {
                username: this.registerForm.username,
                password: this.registerForm.password,
                email: this.registerForm.email,
                captcha: this.registerForm.captcha,
            }
            // 发送注册请求
            request.post('/user/register', this.registerForm)
                .then((response) => {
                    // 请求成功处理
                    this.$message.success('注册成功！');
                    this.$router.push('/login');
                })
                .catch((error) => {
                    // 请求失败处理
                    console.log(error);
                    this.$message.error('用户名或邮箱已存在！');
                });
        }
    }
}
</script>


<style scoped>
.register {
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
