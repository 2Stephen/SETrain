<template>
  <el-container style="height: 100vh;">

    <el-header
      style="background-color: rgb(172,219,252); color: rgb(51.2, 126.4, 204); display: flex; justify-content: space-between; align-items: center;">
      <div style="display: flex;justify-content: flex-start;align-items: center;">
        <div class="header-title">面试题库系统</div>
        <el-menu ellipsis mode="horizontal" background-color="rgb(172,219,252)" text-color="rgb(53,53,53)"
          style="width:30rem;display: flex;align-items: center;" default-active="2">
          <el-menu-item index="1" @click="clickToIndex" style="width:100px;">首页</el-menu-item>
          <el-menu-item index="2" @click="clickToHome" style="width:100px;">题库</el-menu-item>
          <el-menu-item v-if="role === 'admin'" index="3" @click="clickToManage" style="width:100px;">管理</el-menu-item>
          <el-sub-menu index="4" style="width:100px;"><template #title>帮助</template>
            <el-menu-item index="4-1">快速入门</el-menu-item>
            <el-menu-item index="4-2">常见问题</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </div>

      <div style="display: flex;justify-content: flex-end;align-items: center;">
        <el-input v-model="searchcontents" style="width: 12.5rem;margin-right: 20px;" placeholder="搜索题目"
          @keyup.enter="getallcontents">
          <template #append>
            <el-button type="primary" @click="getallcontents"><el-icon>
                <Search />
              </el-icon></el-button>
          </template>
        </el-input>
        <div v-if="isAuthenticated">
          欢迎，{{ user }}
          <el-button type="primary" plain @click="loginout" style="margin-left: 0.8rem;">退出登录</el-button>
        </div>
        <div v-if="!isAuthenticated">
          <el-button type="primary" plain @click="clickToLogin" style="margin-left: 0.8rem;">登录</el-button>
          <el-button type="primary" @click="clickToRegister" style="margin-right: 0.625rem;">注册</el-button>
        </div>
      </div>
    </el-header>

    <el-container>
      <el-aside width="220px" style="background-color: rgb(235, 245, 255);">
        <el-menu default-active="3" background-color="rgb(235, 245, 255)" text-color="#606266"
          @select="handleMenuSelect">
          <el-menu-item v-for="bank in banklist" :key="bank.id" :index="bank.id.toString()">
            {{ bank.title }}
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-main style="padding: 20px; background-color: #f9f9f9;">

        <div v-if="show === 'questionlist'">
          <h2>题目列表</h2>
          <el-table :data="paginatedQuestions" style="width: 100%;">
            <el-table-column prop="title" label="题目名" />
            <el-table-column prop="tags" label="标签">
              <template #default="{ row }">
                <el-tag v-for="tag in JSON.parse(row.tags)" :key="tag" type="info" class="tag-item"
                  style="margin-right: 10px;">{{ tag
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
            @current-change="updatePaginatedQuestions" />
        </div>

        <div v-if="show === 'searchquestion'">
          <h2>搜索题目列表</h2>
          <el-table :data="searchQuestions" style="width: 100%;">
            <el-table-column prop="question_title" label="题目名" />
            <el-table-column prop="tags" label="标签">
              <template #default="{ row }">
                <el-tag v-for="tag in JSON.parse(row.tags)" :key="tag" type="info" class="tag-item"
                  style="margin-right: 10px;">{{ tag
                  }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="question_bank_title" label="所属题库" />
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
            layout="total, prev, pager, next, jumper" :total="questionSize" @current-change="getallcontents" />
        </div>

      </el-main>
    </el-container>

    <el-footer class="foot">
      © 2024 面试题库系统 - 版权所有
    </el-footer>
  </el-container>
</template>

<script>
import request from '@/api/request';
import store from '@/store';
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  data() {
    return {
      store: useStore(),
      isAuthenticated: computed(() => store.getters.isAuthenticated),
      user: computed(() => store.getters.getUser),
      role: computed(() => store.getters.getRole),

      searchcontents: '',
      show: 'questionlist',
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页显示条数
      //banklist: [],
      banklist: [{ id: 1, title: '题库1' }, { id: 2, title: '题库2' }, { id: 3, title: '题库3' }],
      bankid: 3,
      questionSize: 0,
      //paginatedQuestions: [], // 当前分页显示的题目
      paginatedQuestions: [{ id: 1, title: '题目1', tags: '["tag1","tag2"]' }, { id: 2, title: '题目2', tags: '["tag1","tag2"]' }],
      searchQuestions: [{ id: 1, question_title: '题目1', tags: '["tag1","tag2"]', question_bank_title: '题库1' }, { id: 2, question_title: '题目2', tags: '["tag1","tag2"]', question_bank_title: '题库2' }],
    };
  },

  created() {
    this.loadBank();
    this.updatePaginatedQuestions();
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
    clickToManage() {
      if (!this.isAuthenticated) {
        this.$message.error('点击登录管理题目');
      }
      this.$router.push('/manage')
    },
    loadBank() {
      request.get('/question/getbanklist',)
        .then(response => {
          this.banklist = response;
        })
        .catch(error => {
          console.error("题库加载失败!", error);
        });
    },
    handleMenuSelect(index) {
      this.bankid = index;
      this.currentPage = 1;
      this.updatePaginatedQuestions();
    },
    // 更新分页后的题目列表
    updatePaginatedQuestions() {
      request.get('/question/paginatedquestions', {
        params: {
          index: this.bankid,
          page: this.currentPage,
          pagesize: this.pageSize
        }
      })
        .then(response => {
          this.questionSize = response.total;
          this.paginatedQuestions = response.list;
          this.show = 'questionlist';
        })
        .catch(error => {
          console.error("题目加载失败!", error);
        });
    },
    getallcontents() {
      request.get('/question/searchallcontents', {
        params: {
          content: this.searchcontents,
          page: this.currentPage,
          pagesize: this.pageSize,
        }
      })
        .then(response => {
          this.questionSize = response.total;
          this.searchQuestions = response.list;
          console.log(this.searchQuestions);
          this.show = 'searchquestion';
        })
        .catch(error => {
          console.error("搜索题目失败!", error);
        });
    },
    viewDetails(questionid) {
      if (!this.isAuthenticated) {
        this.$message.error('点击登录查看完整题目');
      }
      this.$router.push({ name: 'question', params: { id: questionid } })  // 跳转到题目详情页面
    },
    loginout() {
      this.$store.dispatch('logout');
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

.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
}

.el-menu-item {
  display: flex;
  text-align: left;
}

.el-sub-menu {
  width: 100px;
  align-items: center;
}
</style>