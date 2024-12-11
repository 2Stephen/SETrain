<template>
  <el-container style="height: 100vh;">

    <el-header
      style="background-color: rgb(172,219,252); color: rgb(51.2, 126.4, 204); display: flex; justify-content: space-between; align-items: center;">
      <div style="display: flex;justify-content: flex-start;align-items: center;">
        <div class="header-title">数据结构题库系统</div>
        <el-menu ellipsis mode="horizontal" background-color="rgb(172,219,252)" text-color="rgb(53,53,53)"
          style="width:30rem;display: flex;align-items: center;" default-active="2">
          <el-menu-item index="1" @click="clickToIndex" style="width:100px;">首页</el-menu-item>
          <el-menu-item index="2" @click="clickToHome" style="width:100px;">题库</el-menu-item>
          <el-sub-menu index="3" style="width:100px;"><template #title>帮助</template>
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
        <el-button type="primary" plain @click="clickToLogin" style="margin-left: 0.8rem;">登录</el-button>
        <el-button type="primary" @click="clickToRegister" style="margin-right: 0.625rem;">注册</el-button>
      </div>
    </el-header>

    <el-container>
      <el-aside width="220px" style="background-color: rgb(235, 245, 255); overflow-y: auto;overflow-x: hidden;">
        <el-scrollbar height="38.75rem">
          <el-menu :default-active="currentQuestion.id" @select="handleQuestionSelect"
            background-color="rgb(235, 245, 255)" text-color="#606266" router>
            <template v-for="question in questionList" :key="question.id">
              <el-menu-item :index="question.id">
                {{ question.title }}
              </el-menu-item>
            </template>
          </el-menu>
        </el-scrollbar>
      </el-aside>

      <el-main style="padding: 20px; background-color: #f9f9f9;">
        <div>
          <!-- 题目标题 -->
          <h2 style="margin-bottom: 10px;">{{ currentQuestion.title }}</h2>

          <!-- 标签展示 -->
          <div style="margin-bottom: 20px;">
            <el-tag v-for="tag in currentQuestion.tags" :key="tag" type="info" style="margin-right: 10px;">
              {{ tag }}
            </el-tag>
          </div>

          <!-- 题目内容 -->
          <div style="margin-bottom: 30px;">
            <h3>题目内容</h3>
            <p>{{ currentQuestion.content }}</p>
          </div>

          <!-- 答案部分 -->
          <div>
            <h3>答案</h3>
            <p>{{ currentQuestion.answer }}</p>
          </div>
        </div>


      </el-main>
    </el-container>

    <el-footer class="foot">
      © 2024 数据结构题库系统 - 版权所有
    </el-footer>

  </el-container>
</template>

<script>
// import request from '@/api/request';

export default {
  data() {
    return {

      questionList: [
        { id: "1", title: "什么是 Java 中的不可变类?", tags: ["Java", "基础"], content: "不可变类是...", answer: "不可变类的例子有 String 等。" },
        { id: "2", title: "Java 中的序列化是什么?", tags: ["Java", "序列化"], content: "序列化是...", answer: "序列化通过实现 Serializable 接口。" },
        { id: "3", title: "Java 中多态的作用?", tags: ["Java", "面向对象"], content: "多态是...", answer: "多态的主要作用是..." },
        { id: "4", title: "什么是 Java 中的不可变类?", tags: ["Java", "基础"], content: "不可变类是...", answer: "不可变类的例子有 String 等。" },
        { id: "5", title: "Java 中的序列化是什么?", tags: ["Java", "序列化"], content: "序列化是...", answer: "序列化通过实现 Serializable 接口。" },
        { id: "6", title: "Java 中多态的作用?", tags: ["Java", "面向对象"], content: "多态是...", answer: "多态的主要作用是..." },
        { id: "7", title: "什么是 Java 中的不可变类?", tags: ["Java", "基础"], content: "不可变类是...", answer: "不可变类的例子有 String 等。" },
        { id: "8", title: "Java 中的序列化是什么?", tags: ["Java", "序列化"], content: "序列化是...", answer: "序列化通过实现 Serializable 接口。" },
        { id: "9", title: "Java 中多态的作用?", tags: ["Java", "面向对象"], content: "多态是...", answer: "多态的主要作用是..." },
        { id: "10", title: "什么是 Java 中的不可变类?", tags: ["Java", "基础"], content: "不可变类是...", answer: "不可变类的例子有 String 等。" },
        { id: "11", title: "Java 中的序列化是什么?", tags: ["Java", "序列化"], content: "序列化是...", answer: "序列化通过实现 Serializable 接口。" },
        { id: "12", title: "Java 中多态的作用?", tags: ["Java", "面向对象"], content: "多态是...", answer: "多态的主要作用是..." },
        { id: "13", title: "什么是 Java 中的不可变类?", tags: ["Java", "基础"], content: "不可变类是...", answer: "不可变类的例子有 String 等。" },
        { id: "14", title: "Java 中的序列化是什么?", tags: ["Java", "序列化"], content: "序列化是...", answer: "序列化通过实现 Serializable 接口。" },
        { id: "15", title: "Java 中多态的作用?", tags: ["Java", "面向对象"], content: "多态是...", answer: "多态的主要作用是..." },
        { id: "16", title: "什么是 Java 中的不可变类?", tags: ["Java", "基础"], content: "不可变类是...", answer: "不可变类的例子有 String 等。" },
        { id: "17", title: "Java 中的序列化是什么?", tags: ["Java", "序列化"], content: "序列化是...", answer: "序列化通过实现 Serializable 接口。" },
        { id: "18", title: "Java 中多态的作用?", tags: ["Java", "面向对象"], content: "多态是...", answer: "多态的主要作用是..." },
        { id: "19", title: "什么是 Java 中的不可变类?", tags: ["Java", "基础"], content: "不可变类是...", answer: "不可变类的例子有 String 等。" },
        { id: "20", title: "Java 中的序列化是什么?", tags: ["Java", "序列化"], content: "序列化是...", answer: "序列化通过实现 Serializable 接口。" },
        { id: "21", title: "Java 中多态的作用?", tags: ["Java", "面向对象"], content: "多态是...", answer: "多态的主要作用是..." },
      ],
      currentQuestion: {},


    };
  },

  created() {
    // 默认加载第一个问题
    this.currentQuestion = this.questionList[0];
  },

  methods: {
    clickToIndex() {
      this.$router.push('/')
    },
    clickToHome() {
      this.$router.push('/home')
    },
    clickToRegister() {
      this.$router.push('/register')
    },
    clickToLogin() {
      this.$router.push('/login')
    },
    handleQuestionSelect(id) {
      // 根据 ID 查找选中的问题
      this.currentQuestion = this.questionList.find((question) => question.id === id);
    },


  },
};
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

.el-menu-item {
  display: flex;
  text-align: left;
}

.el-sub-menu {
  width: 100px;
  align-items: center;
}

.el-aside {
  border-right: 1px solid #dcdcdc;
  scrollbar-width: thin;
  /* Firefox */
}

.el-aside::-webkit-scrollbar {
  width: 8px;
  /* Chrome, Safari */
}

.el-aside::-webkit-scrollbar-thumb {
  background-color: #c0c4cc;
  border-radius: 4px;
}

.el-aside::-webkit-scrollbar-track {
  background-color: #f5f5f5;
}
</style>