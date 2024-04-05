<template>
	<body>
	<div class="overViewQuestion-page">
    <div class="headerDiv">
      <div>
        <router-link to="/dashboard"> <- </router-link>
        <h1>Your quizzes</h1>
        <p>Select a quiz for your creation to either play, edit or delete</p>
      </div>
      <div>
        <button class="add-Btn">Create Quiz</button>
        <router-link to="/createQuiz"> New quiz </router-link>
      </div>
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
import QuizCard from "@/components/shared/QuizCard.vue";
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
    this.setUserId();
    this.populateQuizzes();
  },
  methods: {

    async populateQuizzes() {
      try {
        await apiClient.get('/quiz/creator/' + this.userId).then(response => {
          this.quizList = JSON.parse(JSON.stringify(response.data));
          //TODO: set max amt.
          this.quizNo = this.quizList.length;
        });
      } catch (error) {
        //TODO: proper error handling
        this.errorMsg = 'Error retrieving quizzes';
      }
    },/*
    populateQuizzes() {
      this.quizNo = this.quizList.length;
    },*/
    setUserId() {
      this.userId = getIdByToken();
    }
	},
  created() {
    this.setUserId();
    this.populateQuizzes();
  }
}
</script>

<style>
.overViewQuestion-page{
	padding: 50px;
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

#quizCard {
  width: calc(50% - 10px);
}

</style>