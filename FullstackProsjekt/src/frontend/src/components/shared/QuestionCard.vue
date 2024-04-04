<script>
import { RouterLink, RouterView } from 'vue-router';
import router from "@/router/index.js";
import { useRouter } from 'vue-router';
export default {
  props: {
    questionId: {
      type: Number,
      required: true,
    },
    questionNum: {
      type: Number,
      required: false,
    },
    question: {
      type: String,
      required: false,
    },
  },
  methods: {
    //link to pages, play quiz, edit, delete, with quizId
    viewQuestion() {
      //create new router-method to playQuiz, using quizId
      //router.push({name: 'playQuiz', params: {questionId: this.questionId}});
    },
    editQuestion() {
      //create new router-method to editQuiz, using quizId
      //this.$router.push({name: 'editQuiz', params: {questionId: this.questionId}});
    },
    deleteQuestion() {

    }
  }
}
</script>

<template>
  <div class="question-wrapper">
    <h4>{{questionNum}}</h4>
    <h3>{{question}}</h3>
    <div class="quiz-footer">
      <button @click="viewQuestion" class="play-btn">Play</button>
      <button @click="editQuestion" class="edit-btn">Edit</button>
      <button @click="deleteQuestion" class="delete-btn">Edit</button>
    </div>
  </div>
</template>

<style>
.overViewQuestion-page{
  padding: 50px;
}

.quiz-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.quiz-body{
  margin-bottom: 40px;
}

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