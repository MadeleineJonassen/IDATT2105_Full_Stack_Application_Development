<script>

import EditQuestionModel from "@/components/shared/EditQuestionModel.vue";
import {apiClient} from "@/api.js";
export default {
  components: {EditQuestionModel},
  props: {
    questionId: {
      type: Number,
      required: true,
    },
    questionText: {
      type: String,
      required: true,
    }
  },
  beforeMount() {
    this.quizId = Number(this.$route.params.quizId);
  },
  data() {
    return {
      showEditQuestion: false,
      questionNum: 0,
      question: 'question text',
      quizId: null,
    }
  },
  methods: {
    deleteQuestion() {
      try {
        apiClient.post('/questions/delete/' + this.questionId, )
      } catch (error) {
        this.errorMsg = 'Error deleting question';
      }
    },
    editQuestion() {
      this.showEditQuestion = true;
      console.log(this.showEditQuestion);
    },
    hideEditQuestion() {
      this.showEditQuestion = false;
    }
  }
}
</script>

<template>
  <div class="question-wrapper">
    <h4>{{questionId}}</h4>
    <h3>{{questionText}}</h3>
    <div class="quiz-footer">
      <button @click="editQuestion" class="edit-btn">Edit</button>
      <button @click="deleteQuestion" class="delete-btn">Delete</button>
    </div>
    <EditQuestionModel :question-id=questionId :quiz-id=quizId v-if="this.showEditQuestion" @close="hideEditQuestion"/>
  </div>
</template>

<style>
.question-wrapper {
  display: flex;
  width: 80%;
  justify-content: space-between;
}

.quiz-footer{
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  padding-left: 25px;
  padding-right: 25px;
}
</style>