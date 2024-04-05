<script>
import { RouterLink, RouterView } from 'vue-router';
import router from "@/router/index.js";
import { useRouter } from 'vue-router';
export default {
  props: {
    answerId: {
      type: Number,
      required: true,
    },
    answer: {
      type: String,
      required: true,
    },
    correct: {
      type: Boolean,
      required: false
    }
  },
  methods: {
    deleteAnswer() {

    }
  }
}
</script>

<template>
  <div class="question-wrapper">

    <h3>{{answer}}</h3>
    <div class="quiz-footer">
      <button @click="deleteAnswer" class="delete-btn">Delete</button>
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
  margin-bottom: 10px;
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