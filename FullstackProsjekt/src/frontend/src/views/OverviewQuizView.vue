<template>
	<body>
	<div class="overViewQuestion-page">

    <div class="headerDiv">
      <div>
        <router-link to="/dashboard"> <- </router-link>
        <h1>Your quizzes</h1>
        <p>Select a quiz for your creation to either play, edit or delete</p>
      </div>
	      <router-link to="/create-quiz" class="add-Btn">New quiz</router-link>
      </div>

		<div class="row">
      <div class="quiz-div">
        <QuizCard id="quizCard" v-for= "quiz in quizList" :key="quiz.id" :quiz-id="quiz.id" />
      </div>
		</div>

	</div>
	</body>
</template>

<script>
import QuizCard from "@/components/shared/create-quiz/QuizCard.vue";
import {getIdByToken} from "@/tokenController.js";
import {apiClient} from "@/api.js";

export default {
	components: {
    QuizCard,
  },
	data() {
		return {
      userId: null,
      quizNo: 0,
      quizList: [],
		};
	},
  mounted() {
    this.populateQuizzes(); // Call populateQuizzes directly
  },
  methods: {
    async populateQuizzes() {
      try {
        await this.setUserId(); // Wait for setUserId to complete before fetching quizzes
        const response = await apiClient.get('/quiz/creator/' + this.userId);
        this.quizList = response.data;
        this.quizNo = this.quizList.length;
      } catch (error) {
        // Handle errors
        this.errorMsg = 'Error retrieving quizzes';
      }
    },
    async setUserId() {
      this.userId = await getIdByToken(); // Wait for getIdByToken() to resolve before assigning to this.userId
    }
	}
}
</script>

<style>
.overViewQuestion-page{
	padding: 40px;
}

.quiz-div {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  float: left;
  box-sizing: border-box;
}

.headerDiv {
  display: flex;
  width: 100%;
  justify-content: space-between;
}


</style>