<template>
	<body>
	<div class="overViewQuestion-page">

    <div class="headerDiv">
	    <router-link to="/" ><Svg name="go-back-icon"/></router-link>
	    <h1>Your quizzes</h1>
	    <router-link to="/create-quiz" class="add-Btn">Create new quiz</router-link>
    </div>
		<p>Play, edit or delete a quiz saved from your profile</p>

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
import Svg from "@/assets/Svg.vue";

export default {
	components: {
		Svg,
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
    this.populateQuizzes();
  },
  methods: {
    async populateQuizzes() {
      try {
        await this.setUserId();
        const response = await apiClient.get('/quiz/creator/' + this.userId);
        this.quizList = response.data;
        this.quizNo = this.quizList.length;
      } catch (error) {
        this.errorMsg = 'Error retrieving quizzes';
      }
    },
    async setUserId() {
      this.userId = await getIdByToken();
    }
	}
}
</script>

<style>
.overViewQuestion-page{
	padding: 40px;
}
.headerDiv{
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10vh;
	margin-top: 2vh;
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