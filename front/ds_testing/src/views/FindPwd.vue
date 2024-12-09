<template>
    <div class="common-layout">
        <el-container style="height: 100vh;">
            <el-header style="display: flex;justify-content: flex-end;align-items: center;">
                <el-button type="primary" plain @click="clickToLogin">登录</el-button>
                <el-button type="primary" @click="clickToRegister">注册</el-button>
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

                <div class="find">
                    <el-card style="width: 30rem;">
                        <div class="card-header">
                            找回密码
                        </div>

                        <div>
                            <el-input class="input-area" v-model="findForm.email" style="width: 18.75rem"
                                placeholder="请输入邮箱">
                                <template #prepend>
                                    <el-icon>
                                        <Message />
                                    </el-icon>
                                </template>
                            </el-input>

                            <div style="display: flex;justify-content: center;">
                                <el-input class="input-area" v-model="findForm.captcha" style="width: 13rem"
                                    placeholder="请输入验证码">
                                    <template #prepend>
                                        <el-icon>
                                            <Key />
                                        </el-icon>
                                    </template>
                                </el-input>
                                &nbsp;&nbsp;
                                <el-button type="primary" plain style="width:82px;">获取验证码</el-button>
                            </div>


                            <el-input class="input-area" v-model="findForm.password" style="width: 18.75rem"
                                type="password" placeholder="请输入新密码" show-password>
                                <template #prepend>
                                    <el-icon>
                                        <Lock />
                                    </el-icon>
                                </template>
                            </el-input>

                            <el-input class="input-area" v-model="findForm.checkpwd" style="width: 18.75rem"
                                type="password" placeholder="请再次输入密码" show-password>
                                <template #prepend>
                                    <el-icon>
                                        <Lock />
                                    </el-icon>
                                </template>
                            </el-input>

                            <el-button type="primary"
                                style="width: 18.75rem;margin-bottom: 0.625rem;margin-top: 0.5rem;"
                                @click="find">邮箱验证</el-button>

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
    name: 'FindPwd',
    data() {
        return {
            findForm: {
                email: '',
                captcha: '',
                password: '',
                checkpwd: ''
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
        clickToLogin() {
            this.$router.push('/login')
        },
        clickToRegister() {
            this.$router.push('/register')
        },
        find() {
            if (!this.findForm.email || !this.findForm.captcha || !this.findForm.password || !this.findForm.checkpwd) {
                this.$message.error('请输入完整的邮箱、验证码及密码！');
                return;
            }
            const reg = /^([a-zA-Z0-9]+(\.[a-zA-Z0-9]+)*@[a-zA-Z0-9-]+\.[a-zA-Z]{2,4})$/;
            if (!reg.test(this.findForm.email)) {
                this.$message.error('邮箱格式不正确！');
                return;
            }
            if (this.findForm.password.length < 6) {
                this.$message.error('密码长度不能少于6位！');
                return;
            }
            if (this.findForm.password !== this.findForm.checkpwd) {
                this.$message.error('两次输入的密码不一致！');
                return;
            }
            // 发送注册请求
            axios.post('http://192.168.35.214:8082/api/register', this.registerForm)
                .then((response) => {
                    // 请求成功处理
                    this.$message.success('验证成功！');
                    this.$router.push('/home');
                })
                .catch((error) => {
                    // 请求失败处理
                    console.error(error);
                    this.$message.error('邮箱或验证码错误！');
                });

        },
    }
}
</script>


<style scoped>
.find {
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
