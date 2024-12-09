<template>
    <el-container style="height: 100vh;">
      <el-header style="background-color: #409EFF; color: white; display: flex; justify-content: space-between; align-items: center;">
        <div class="header-title">数据结构题库系统</div>
      </el-header>
  
      <el-main style="padding: 20px;">
        <el-card>
          <h3>{{ question.title }}</h3>
          <p>{{ question.description }}</p>
          <p>{{ question.detailedContent }}</p>  <!-- 题目详细内容 -->
        </el-card>
      </el-main>
  
      <el-footer class="foot">
        © 2024 数据结构题库系统 - 版权所有
      </el-footer>
    </el-container>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    props: ['id'],
    data() {
      return {
        question: {},
      };
    },
    created() {
      this.loadQuestionDetail();
    },
    methods: {
      loadQuestionDetail() {
        axios.get('http://192.168.35.214:8082/api/questions/${this.id}')
          .then(response => {
            this.question = response.data;
          })
          .catch(error => {
            console.error("加载题目详情失败:", error);
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
    background-color: rgb(235, 245, 255);
    display: flex;
    align-items: center;
    justify-content: center;
  }
  </style>
  