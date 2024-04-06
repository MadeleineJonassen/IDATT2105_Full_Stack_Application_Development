<script>
import router from "@/router/index.js";
import {apiClient} from "@/api.js";

export default {
  props: {
    quizId: {
      type: Number,
      required: true,
    }
  },
  data() {
    return {
      quizTitle: null,
      quizDifficulty: null,
      quizCategory: null
    }

  },
  mounted() {
    this.getQuiz();
  },
  methods: {
    async getQuiz() {
      console.log('Fetching data for quiz: ', this.quizId);

      try {
        await apiClient.get('/quiz/quiz/' + this.quizId).then(response => {
          this.quizTitle = JSON.parse(response.data.title);
          this.category = JSON.parse(response.data.category);
          this.difficulty = JSON.parse(response.data.difficulty);
        });
      } catch (error) {
        //TODO: proper error handling
        this.errorMsg = 'Error retrieving quizzes';
      }
    },
    //link to pages, play quiz, edit, delete, with quizId
    playQuiz() {
      //create new router-method to playQuiz, using quizId
      router.push({name: 'playQuiz', params: {quizId: this.quizId}});
    },
    editQuiz() {
      //create new router-method to editQuiz, using quizId
      router.push({name: 'editQuiz', params: {quizId: this.quizId}});
    },
  }
}
</script>

<template>
  <div class="course-col">
    <div class="quiz-header">
      <h3>{{ quizTitle }}</h3>
      <!--
      <Svg :name="selectedIcon" />
      -->
    </div>
    <div class="quiz-body">
      <p>{{ quizDifficulty }}</p>
      <p>{{ quizCategory }}</p>
    </div>
    <div class="quiz-footer">
      <button @click="playQuiz" class="play-btn">Play</button>
      <button @click="editQuiz" class="edit-btn">Edit</button>
    </div>
  </div>
</template>

<style>


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

.play-btn {
	margin-right: 10px;
}
</style>