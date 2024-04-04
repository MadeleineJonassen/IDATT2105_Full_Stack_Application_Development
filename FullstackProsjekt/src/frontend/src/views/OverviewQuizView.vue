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
      </div>
    </div>


		<div class="row">
      <div class="quiz-div">
        <QuizCard id="quizCard" v-for= "quiz in quizList" :key="quiz.id" :quizDescription="quiz.description" :quizName="quiz.name" :quiz-id="quiz.id" />
      </div>
		</div>
	</div>
	</body>
</template>

<script>
import QuizCard from "@/components/shared/QuizCard.vue";
import {getIdByToken} from "@/tokenController.js";

export default {
	components: {
    QuizCard,
  },
	data() {
		return {
      userName: '',
      quizNo: 0,
      quizList: [
      { name: 'Quiz 1', description: 'Description of Quiz 1', id: 1 },
      { name: 'Quiz 2', description: 'Description of Quiz 2', id: 2 },
      { name: 'Quiz 3', description: 'Description of Quiz 3', id: 3 },
      { name: 'Quiz 4', description: 'Description of Quiz 4', id: 3 },
      { name: 'Quiz 5', description: 'Description of Quiz 5', id: 3 }
    ], //TODO: replace with request-method when ready, using quiz-objects
		};
	},
	methods: {
    /*
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
    },*/
    populateQuizzes() {
      this.quizNo = this.quizList.length;
    },
    newQuiz() {
      //link to new quiz page
    },
    setUserId() {
      this.userName = getIdByToken();
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