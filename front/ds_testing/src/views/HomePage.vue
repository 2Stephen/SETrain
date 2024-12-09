<template>
  <el-container style="height: 100vh;">

    <el-header
      style="background-color: #409EFF; color: white; display: flex; justify-content: space-between; align-items: center;">
      <div class="header-title">数据结构题库系统</div>
      <el-header style="display: flex;justify-content: flex-end;align-items: center;">
        <el-button type="primary" plain @click="clickToHome">首页</el-button>
      </el-header>
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
export default {
  data() {
    return {
      activeMenu: "1",
      questions: [
        { id: 1, title: "顺序表的基本操作", description: "实现顺序表的插入和删除操作。" },
        { id: 2, title: "二叉树的遍历", description: "实现二叉树的前序、中序和后序遍历。" },
        { id: 3, title: "图的最短路径", description: "使用 Dijkstra 算法求解图的最短路径问题。" },
        // 更多题目...
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
    // 根据分类加载不同题目
    // loadQuestionsByCategory(index) {
    //   axios.get('http://192.168.35.214:8082/api/questions/category/${index}')
    //     .then(response => {
    //       this.questions = response.data;
    //     })
    //     .catch(error => {
    //       console.error("加载分类题目失败:", error);
    //     });
    // },


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