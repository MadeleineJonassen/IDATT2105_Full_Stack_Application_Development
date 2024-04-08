<script>
import QuizResult from "@/components/shared/PlayQuiz/QuizResult.vue";
import {getIdByToken} from "@/tokenController.js";
import {apiClient} from "@/api.js";
import router from "@/router/index.js";

export default {
  data() {
    return {
      showPopup: false,
      popupMessage: '',
      currentQuestion: null,
      quizResult: null,
      currentQuestionIndex: 0,//set to null
      userId: 0, //set to null
      quizId: 0,  //set to null
      quizTitle: 'Quiz title',
      selectedOption: null,
      questions: [],
      currentQuestionText: '',
      currentQuestionId: null,
      currentAnswers: [],
      quizResultId: 0,
      errorMsg: '',
      totalScore: 0,
      currentScore: 0,
      hasAnswered: false,
      buttonText: "Next Question"
    }
  },
  async mounted() {
    // await this.getUser();
    // await this.setup();
    this.quizId = this.$route.params.quizId;
    this.getQuiz();
    await this.getQuestions();
  },
  methods: {
    async setup(){
      //TODO: QuizResultService
      this.quizId = this.$route.params.quizId;
      const data = {
        quizId: this.quizId,
        userId: this.userId
      }
      console.log(data)
      try {
        await apiClient.post('/results/create', data).then(response => {
          this.quizResultId = response.data.id
          console.log(response.data)
        })//TODO: set QuizResultServiceId
      } catch (error) {
        this.errorMsg = 'Error starting quiz';
      }
    },
    async getUser() {
      this.userId = await getIdByToken();
    },
    getQuiz() {
      try {
        apiClient.get('/quiz/quiz/' + this.quizId).then(response => {
          this.quizTitle = response.data.title;
        });
      } catch (error) {
        this.errorMsg = 'Error retrieving quiz';
      }
    },
    getQuestions() {
      //TODO: use method to fetch questions
      try {
        apiClient.get('/questions/allQuestionsToAQuiz/' + this.quizId).then(response => {
          this.questions = response.data;
          for (const question of this.questions) {
            this.totalScore += question.score
          }
          console.log(this.totalScore)
          this.setCurrentQuestion();
        });
      } catch (error) {
        this.errorMsg = 'Error retrieving questions';
      }
    },
    submitAnswer() {
      if (this.selectedOption === null) {
        alert("You must select an option")
        return;
      }
      if (this.currentQuestionIndex < this.questions.length - 1) {
        this.hasAnswered = true;
        if (this.selectedOption === this.currentQuestion.answer) {
          this.popupMessage = 'Correct!';
          this.currentScore += this.currentQuestion.score;
        } else {
          this.popupMessage = 'Incorrect!';
        }
        this.showPopup = true;
      } else {
        // This is the last question
        this.hasAnswered = true;
        if (this.selectedOption === this.currentQuestion.answer) {
          this.popupMessage = 'Correct!';
          this.currentScore += this.currentQuestion.score;
        } else {
          this.popupMessage = 'Incorrect!';
        }
        this.showPopup = true;
        this.buttonText = "Finish";
        this.popupMessage += "\nYour final score is: " + this.currentScore + " / " + this.totalScore
      }
    },

    async nextQuestion() {
      this.showPopup = false;
      this.hasAnswered = false;
      this.selectedOption = null;
      this.currentQuestionIndex++;
      this.setCurrentQuestion();
    },
    //setting current question, updating info
    setCurrentQuestion() {
      if (this.currentQuestionIndex < this.questions.length) {
        this.currentQuestion = this.questions[this.currentQuestionIndex];
        this.currentQuestionId = this.currentQuestion.id;
        this.currentQuestionText = this.currentQuestion.questionText;
        this.currentAnswers = this.currentQuestion.options;
      } else {
        router.push({name: 'dashboard'})
      }

    },
    selectOption(option) {
      this.selectedOption = option;
    },
  }
}
</script>


<template>
	<body>
  <div class="quiz">
    <div id="quiz-info">
      <h1 id="title">Play quiz</h1>
    </div>

    <div id="current-q" class="question">

      <h3 v-if="currentQuestion">{{currentQuestionText}}</h3>

      <div class="options">
        <div class="option" :class="{ 'selected': selectedOption === option }" @click="selectOption(option)" v-for="(option, index) in currentAnswers" :key="index">
          <label>{{ option }}</label>
        </div>
      </div>
      <div v-if="showPopup" class="popup">
        <p>{{ popupMessage }}</p>
      </div>
      <button class="play-quiz-btn" v-if="hasAnswered" @click="nextQuestion">{{buttonText}}</button>
      <button class="play-quiz-btn" v-if="!hasAnswered" @click="submitAnswer">Submit</button>
    </div>
  </div>
	</body>
</template>

<style>
.quiz {
	text-align: center;
	margin-top: 40px;
	margin-left: 20vh;
	margin-right: 20vh;
}

#current-q {
	padding: 20px;
	background-color: var(--play-card-background);
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1#title {
	margin-bottom: 20px;
	font-size: 18px;
	color: var(--text-dark-color);
}

.question {
	font-size: 32px;
}

.options {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}

.option {
	font-size: 20px;
	font-weight: bold;
	width: 100%;
	height: 60px;
	border: 2px solid #ccc;
	border-radius: 10px;
	margin: 10px;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
	transition: all 0.3s ease;
}

.option:hover {
	background-color: var(--play-btn-hover);
}

.selected {
	background-color: var(--safe-color);
	color: var(--text-light-color);
}

.popup {
	background-color: rgba(0, 0, 0, 0.8);
	color: var(--text-light-color);
	padding: 2vh 8vh;
	border-radius: 5px;
	position: fixed;
	left: 52.5%;
	bottom: 50%;
	transform: translateX(-50%);
	transition: 0.5s ease;

}

.play-quiz-btn {
	background-color: var(--option-color);
	color: var(--text-light-color);
	font-size: 18px;
	padding: 10px 20px;
	margin-top: 5vh;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.play-quiz-btn:hover {
	background-color: var(--option-hover);
}

@media (max-width: 700px){
	.quiz{
		margin-left: 2vh;
		margin-right: 2vh;
	}
	.popup{
		padding: 1vh 4vh;
		left: 57%;
		font-size: 25px;
	}
	#current-q{
		padding: 5px;
	}
}
@media (max-width: 1000px) {
	.popup{
		left: 54%;
	}
}
</style>
