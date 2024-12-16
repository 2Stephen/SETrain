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
        <el-input v-model="searchcontents" style="width: 12.5rem" placeholder="搜索题目" class="input-with-select">
          <template #append>
            <el-button type="primary" @click="getcontents"><el-icon>
                <Search />
              </el-icon></el-button>
          </template>
        </el-input>
        <el-button type="primary" plain @click="clickToLogin" style="margin-left: 0.8rem;">登录</el-button>
        <el-button type="primary" @click="clickToRegister" style="margin-right: 0.625rem;">注册</el-button>
      </div>
    </el-header>

    <el-container>
      <el-aside width="300px" style="background-color: rgb(235, 245, 255); overflow-y: auto;overflow-x: hidden;">
        <el-scrollbar height="38.75rem">
          <el-menu :default-active="currentQuestion.id.toString()" @select="handleQuestionSelect"
            background-color="rgb(235, 245, 255)" text-color="#606266">
            <template v-for="question in questionList" :key="question.id">
              <el-menu-item :index="question.id.toString()">
                {{ question.title }}
              </el-menu-item>
            </template>
          </el-menu>
        </el-scrollbar>
      </el-aside>

      <el-main style="padding: 20px; background-color: #f9f9f9;">
        <div v-if="show === 'questiondetail'" style="display: flex; flex-direction: column;gap: 20px;">

          <el-card shadow="hover" style="border-radius: 8px;">
            <div style="font-size: 26px; color: #409EFF; text-align:left;margin-bottom: 10px;">
              {{ currentQuestion.title }}
            </div>

            <div style="text-align: left; margin-bottom: 15px;">
              <el-tag v-for="(tag, index) in JSON.parse(currentQuestion.tags || '[]')" :key="index" type="info"
                effect="light" style="margin: 5px;">
                {{ tag }}
              </el-tag>
            </div>

            <div style="color: #606266; font-size: 16px;text-align: left;">
              <div style="color: #303133; font-size: 18px;margin-bottom: 10px;">题目内容</div>
              {{ currentQuestion.content }}
            </div>
          </el-card>

          <el-card shadow="hover" style="border-radius: 8px;">
            <div style="font-size: 20px; color: #303133; text-align: left;margin-bottom: 10px;">
              参考答案
            </div>

            <div style="color: #606266; font-size: 16px; line-height: 1.8;text-align: left;">
              <div v-for="(paragraph, index) in currentQuestion.answer.split('\n')" :key="index"
                style="margin-bottom: 10px;">{{ paragraph }}</div>
            </div>
          </el-card>

        </div>



        <div v-if="show === 'searchquestion'" >
          <h2>搜索题目列表</h2>
        <el-table :data="paginatedQuestions" style="width: 100%;">
          <el-table-column prop="title" label="题目名" />
          <el-table-column prop="tags" label="标签">
            <template #default="{ row }">
              <el-tag v-for="tag in JSON.parse(row.tags)" :key="tag" type="info" class="tag-item" style="margin-right: 10px;">{{ tag
                }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template #default="{ row }">
              <el-button link type="primary" @click="viewDetails(row.id)">
                查看详情
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination style="display: flex;justify-content: center;margin-top: 20px;"
          v-model:current-page="currentPage" v-model:page-size="pageSize" background
          layout="total, prev, pager, next, jumper" :total="questionSize"
          @current-change="getcontents" />
        </div>


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
      currentQuestionid: this.$route.params.id,
      questionList: [{ id: 1, title: '题目1' }, { id: 2, title: '题目2' }, { id: 3, title: '题目3' }],
      currentQuestion: {
        id: 1,
        title: '',
        tags: '[]',
        content: '',
        answer: '',
      },
      searchcontents: '',
      show:'questiondetail',
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页显示条数
      questionSize: 0,
      paginatedQuestions: [{id:1,title:'题目1',tags:'["tag1","tag2"]'},{id:2,title:'题目2',tags:'["tag1","tag2"]'}],

    };
  },

  created() {
    this.getcurrentquestion();
    this.getquestionList();
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
    getcurrentquestion() {
      request.get('/question/getcurrentquestion', {
        params: {
          id: this.currentQuestionid
        }
      })
        .then(response => {
          console.error(response)
          this.currentQuestion = response;
        })
        .catch(error => {
          console.error("题目加载失败!", error);
        });
    },
    getquestionList() {
      request.get('/question/getcurrenttitles', {
        params: {
          questionid: this.currentQuestionid
        }
      })
        .then(response => {
          this.questionList = response;
        })
        .catch(error => {
          console.error("题目加载失败!", error);
        });
    },
    handleQuestionSelect(index) {
      this.show = 'questiondetail';
      console.log(this.show);
      this.currentQuestionid = index;
      this.getcurrentquestion();
      
    },
    getcontents() {
      request.get('/question/searchcontents', {
        params: {
          content: this.searchcontents,
          page:this.currentPage,
          pagesize:this.pageSize,
          questionid: this.currentQuestionid,
        }
      })
        .then(response => {
          this.questionSize = response.total;
          this.paginatedQuestions = response.list;
          console.log(response.list);
          this.show = 'searchquestion';
          console.log(this.show);
        })
        .catch(error => {
          console.error("搜索题目失败!", error);
        });
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