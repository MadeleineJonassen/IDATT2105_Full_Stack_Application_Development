<script>
import { RouterLink, RouterView } from 'vue-router';
import router from "@/router/index.js";
import { useRouter } from 'vue-router';
export default {
  props: {
    quizId: {
      type: Number,
      required: true,
    },
    quizName: {
      type: String,
      required: true,
    },
    quizDescription: {
      type: String,
      required: true,
    }
  },
  methods: {
    //link to pages, play quiz, edit, delete, with quizId
    playQuiz() {
      //create new router-method to playQuiz, using quizId
      router.push({name: 'playQuiz', params: {quizId: this.quizId}});
    },
    editQuiz() {
      //create new router-method to editQuiz, using quizId
      this.$router.push({name: 'editQuiz', params: {quizId: this.quizId}});
    },
  }
}
</script>

<template>
  <div class="course-col">
    <div class="quiz-header">
      <h3>{{ quizName }}</h3>
      <!--
      <Svg :name="selectedIcon" />
      -->
    </div>
    <div class="quiz-body">
      <p>{{ quizDescription }}</p>
    </div>
    <div class="quiz-footer">
      <button @click="playQuiz" class="play-btn">Play</button>
      <button @click="editQuiz" class="edit-btn">Edit</button>
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

.quiz-footer{
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  padding-left: 25px;
  padding-right: 25px;
}
</style>