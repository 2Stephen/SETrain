<template>

  <div style="display: flex;justify-content: flex-start;align-items: center;">
    <el-input v-model="params.title" style="width: 240px; margin-right: 20px" placeholder="请输入标题" />
    <el-select v-model="params.type" placeholder="请选择题目类型" style="width: 200px; margin-right: 20px">
      <el-option v-for="item in typeObjs" :key="item.id" :label="item.title" :value="item.id" />
    </el-select>
    <el-button type="primary" @click="addQuestion">新增</el-button>
    <el-button type="success">查询</el-button>
    <el-button type="warning">修改</el-button>
    <el-button type="danger">批量删除</el-button>
  </div>

  <el-table
      ref="multipleTableRef"
      :data="tableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
  >
    <el-table-column type="selection" :selectable="selectable" width="55" />

    <el-table-column property="id" label="ID" width="120" />
    <el-table-column property="title" label="标题" />
    <el-table-column property="content" label="简述" />
    <el-table-column property="tags" label="标签" />

    <el-table-column label="推荐答案" width="150">
      <template v-slot="scope">
        <div class="text-ellipsis" :title="scope.row.answer">
          {{ scope.row.answer }}
        </div>
      </template>
    </el-table-column>
    <el-table-column label="操作" width="300">
      <template v-slot="scope">

        <el-button type="primary" @click="editQuestion(scope.row)" >
          编辑
        </el-button>


        <el-popconfirm
            title="确定删除吗？"
            @confirm="deleteQuestion(scope.row.id)"
        >
          <template #reference>
            <el-button type="danger" style="margin-left: 20px">
              删除
            </el-button>
          </template>
        </el-popconfirm>

      </template>
    </el-table-column>
  </el-table>

  <div class="block" style="margin-top: 20px">
    <span class="demonstration"></span>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="params.pageNum"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="params.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>

  <div>
    <el-dialog
        v-model="dialogFormVisible"
        title="题目"
        :width="'50%'"
        :height="'80%'"
        :before-close="handleClose"
        center
    >
      <el-form :model="form" label-position="right" label-width="100px">
        <el-form-item label="标题">
          <el-input v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="简述">
          <el-input v-model="form.content" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="标签">
          <el-input v-model="form.tags" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="题库分类">
          <el-select v-model="form.questionBankId" clearable placeholder="请选择">
            <el-option
                v-for="item in typeObjs"
                :key="item.id"
                :label="item.title"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="推荐答案">
          <el-input
              v-model="form.answer"
              type="textarea"
              :rows="4"
              autocomplete="off">
          </el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer" style="display: flex; justify-content: flex-end;">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitAddQuestion()">确 定</el-button>
      </div>
    </el-dialog>



  </div>



</template>


<script>


import request from '@/api/request';

export default {
  name: "QuestionView.vue",
  data() {
    return {
      params: {
        title : '',
        pageNum: 1,
        pageSize: 5
      },
      tableData: [],
      typeObjs: [],
      total: 0,
      dialogFormVisible: false,
      form: {},
      user: localStorage.getItem('user')?JSON.parse(localStorage.getItem('user')):{}
    }
  },
  mounted() {
    this.findAllType();
    this.findBySearch();
  },

  methods:{

    findBySearch() {
      request.get('/question/findBySearch', {
        params: this.params
      }).then(resp => {
        if (resp.code === '0') {
          this.tableData = resp.data.list;
          this.total = resp.data.total;
          this.params.pageNum = resp.data.pageNum;
          this.params.pageSize = resp.data.pageSize;
        } else {
          this.$message({
            message: resp.msg,
            type: 'error'
          });
        }
      })
    },

    findAllType(){
      request.get('/questionBank/getAllQuestionBank').then(resp => {
        if (resp.code === '0') {
          this.typeObjs = resp.data;
        } else {
          this.$message({
            message: resp.msg,
            type: 'error'
          });
        }
      })
    },

    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize;
      this.findBySearch();
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.findBySearch();
    },

    editQuestion(obj) {
      this.form = obj;
      // 拿到questionBankId

      request.get('/questionBankQuestion/getQuestionBankIdByQuestionId/'+obj.id).then(resp => {
        if (resp.code === '0') {
          this.form.questionBankId = resp.data;
        } else {
          this.$message({
            message: resp.msg,
            type: 'error'
          });
        }
      })
      this.dialogFormVisible = true;
    },

    addQuestion() {
      this.form = {};
      this.dialogFormVisible = true;
    },


    submitAddQuestion() {
      request.post('/question/saveQuestion', this.form).then(resp => {
        if (resp.code === '0') {
          this.$message({
            message: '恭喜你，这是一条成功消息',
            type: 'success'
          });
          this.dialogFormVisible = false;
          this.findBySearch();
        } else {
          this.$message({
            message: resp.msg,
            type: 'error'
          });
          console.log(resp.msg);
        }
      })
    },

  }
}
</script>


<style scoped>

.text-ellipsis {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 150px; /* 根据需要调整宽度 */
}
</style>