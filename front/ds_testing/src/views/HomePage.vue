<template>
  <el-container style="height: 100vh;">

    <el-header
      style="background-color: rgb(172,219,252); color: rgb(51.2, 126.4, 204); display: flex; justify-content: space-between; align-items: center;">
      <div style="display: flex;justify-content: flex-start;align-items: center;">
        <div class="header-title">数据结构题库系统</div>
        <el-menu ellipsis mode="horizontal" background-color="rgb(172,219,252)" text-color="rgb(53,53,53)"
          style="width:30rem;display: flex;align-items: center;" default-active="1">
          <el-menu-item index="1" @click="click">首页</el-menu-item>
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
      </div>
    </el-header>

    <el-container>
      <el-aside width="220px" style="background-color: rgb(235, 245, 255);">
        <el-menu :default-active="activeMenu" @select="handleMenuSelect" background-color="rgb(235, 245, 255)"
          text-color="#606266">
          <el-menu-item index="1">线性表</el-menu-item>
          <el-menu-item index="2">栈与队列</el-menu-item>
          <el-menu-item index="3">串</el-menu-item>
          <el-menu-item index="4">树</el-menu-item>
          <el-menu-item index="5">图</el-menu-item>
          <el-menu-item index="6">查找算法</el-menu-item>
          <el-menu-item index="7">排序算法</el-menu-item>
        </el-menu>
      </el-aside>

      <el-main>
        <el-row :gutter="20">
          <el-col :span="8" v-for="question in questions" :key="question.id">
            <el-card :body-style="{ padding: '20px' }" style="height: 250px;">
              <h4>{{ question.title }}</h4>
              <p>{{ question.description }}</p>
              <el-button type="primary" size="small" @click="viewDetails(question.id)">查看详情</el-button>
            </el-card>
          </el-col>
        </el-row>
      </el-main>
    </el-container>

    <el-footer class="foot">
      © 2024 数据结构题库系统 - 版权所有
    </el-footer>
  </el-container>
</template>

<script>
import request from '@/api/request';

export default {
  data() {
    return {
      activeMenu: "1",
      questions: [
      ],
    };
  },
  // created() {
  //   this.loadQuestionsByCategory(index); // 加载默认题目数据
  // },
  methods: {
    clickToHome() {
      this.$router.push('/')
    },
    handleMenuSelect(index) {
      console.log("选择的分类索引：", index);
      this.loadQuestionsByCategory(index);  // 加载选择分类的题目
    },
    viewDetails(questionId) {
      console.log("查看题目详情，题目 ID:", questionId);
      this.$router.push('/question/${questionId}'); // 跳转到题目详情页面
    },
    //根据分类加载不同题目
    loadQuestionsByCategory(index) {
      request.get('/question/bankindex', {
        params: {
          index: index
        }
      })
        .then(response => {
          this.questions = response.data;
        })
        .catch(error => {
          console.error("加载当前题库中的题目失败:", error);
        });
    },


  },
};
</script>

<style scoped>
.header-title {
  font-size: 20px;
  font-weight: bold;
}

.foot {
  background-color: rgb(235.9, 245.3, 255);
  display: flex;
  align-items: center;
  justify-content: center;
}

.el-card {
  border-radius: 10px;
}
</style>