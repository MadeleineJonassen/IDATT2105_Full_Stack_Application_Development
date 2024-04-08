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
    await this.getUser();
    await this.setup();
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
  <div class="quiz">
    <div id="quiz-info">
      <h1 id="title">Play quiz</h1>
    </div>

    <div id="current-q" class="question">

      <h3 v-if="currentQuestion">{{currentQuestionText}}</h3>
      <ul>
        <li v-for="(option, index) in currentAnswers" :key="index">
          <label>{{ option }}</label>
          <input type="radio" :id="option" :value="option" :checked="option === selectedOption" @change="selectOption(option)">
        </li>

      </ul>
      <div v-if="showPopup" class="popup">
        <p>{{ popupMessage }}</p>
      </div>
      <button v-if="hasAnswered" @click="nextQuestion">{{buttonText}}</button>
      <button v-if="!hasAnswered" @click="submitAnswer">Submit</button>
    </div>
  </div>

<!--
	<body>

	<div class="quiz">

		<div id="info">
			<div id="score">Score: 0</div>
			<div id="ques-left">Question:1/20</div>
		</div>
		<div id="ques-view">

		</div>
		<div class="question">
			<h1>Question</h1>
		</div>

		<div class="choice">
			<div class="options"><input type="radio" name="options" value="option1" id="opt0"><label for="opt0" id="lb0">Option1</label></div>
			<div class="options"><input type="radio" name="options" value="option2" id="opt1"><label for="opt1" id="lb1">Option2</label></div>
			<div class="options"><input type="radio" name="options" value="option3" checked="checked" id="opt2"><label for="opt2" id="lb2">Option3</label></div>
			<div class="options"><input type="radio" name="options" value="option4" id="opt3"><label for="opt3" id="lb3">Option4</label></div>
		</div>

		<div class="ans-btn">
			<button type="button" class="submit-answer">Submit Answer</button>
			<a href="#display-final-score" type="button" class="view-results">View Results</a>
		</div>

	</div>

	<div class="final-result">
		<h1>The Quiz is Over</h1>
		<div class="solved-ques-no">You Solved 10 questions of {Name of quiz here}</div>
		<div class="right-wrong">3/4 were right</div>
		<div id="display-final-score">Your Final Score is: 35</div>
		<div class="remark">Remark: Satisfactory, Keep trying!</div>
		<button id="restart">Restart Quiz</button>
	</div>
	</body>
-->
</template>

<style>
.quiz{
	text-align: center;
	margin-top: 20px;
	height: 100vh;
}

#info{
	height:25px;
}

#score{
	width:50%;
	float:left;
	font-size: 25px;
}

#ques-left{
	width:50%;
	float:left;
	font-size:25px;
}

#ques-view{
	height: 35px;
	margin-top: 10px;
	padding: 2px;
}
.question{
	letter-spacing: .13em;
}

.choice{
	padding: 3%;
}

.options{
	display:block;
	font-size: 25px;
	margin-top: 30px;
	text-align: left;
}

input[type=radio] {
	border: 5px solid white;
	width: 20px;
	height: 1.3em;
	float: left;
}

.ans-btn{
	padding: 2%;
}

.submit-answer{
	border: 2px solid #CCA43B;
	padding: 15px;
	border-radius: 20px;
	transition: background-color 0.3s,border 0.2s, color 0.2s;
	margin-right: 10px;
	font-family: monospace;
}

.submit-answer:hover{
	background-color: #CCA43B;
	padding: 16px;
	color: #fff;
	cursor: pointer;
	border-radius: 20px;
}

.view-results{
	text-decoration: none;
	color: black;
	border: 2px solid #CCA43B;
	padding: 15px;
	border-radius: 20px;
	transition: background-color 0.3s,border 0.2s, color 0.2s;
}


.view-results:hover{
	background-color: #CCA43B;
	padding: 16px;
	color: #fff;
	cursor: pointer;
	border-radius: 20px;
}

/*Final Results*/
.final-result{
	text-align: center;
	padding: 10px;
	font-size: 1.5em;
	height: 100vh;
}
.solved-ques-no{
	padding: 10px;
}

.right-wrong{
	padding: 10px;
}

#display-final-score{
	padding: 5%;
}
.remark{
	padding: 5%
}
#restart{
	background-color:#CCA43B;
	margin-left: 30px;
	border: 2px solid #CCA43B;
	padding: 15px;
	border-radius: 20px;
	font-size: 80%;
	transition: background-color 0.3s,border 0.2s, color 0.2s;
}

#restart:hover{
	background-color: #CCA43B;
	color:#fff;
	cursor: pointer;
	/*    width: 120px;*/
	font-size: 90%;
}


@media only screen and (max-width: 1250px) {
	.options{
		margin-left: 8%
	}
}

@media only screen and (max-width: 850px) {
	.options{
		margin-left: 4%;
	}
}

@media only screen and (max-width: 650px) {
	.options{
		display: block;
		margin-top: 3%;

	}
}

@media only screen and (max-width: 550px) {
	.options{
		margin-top: 3%;

	}

}
</style>
