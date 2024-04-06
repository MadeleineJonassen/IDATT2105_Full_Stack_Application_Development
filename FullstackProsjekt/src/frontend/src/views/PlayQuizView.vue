<script>
import QuizResult from "@/components/shared/PlayQuiz/QuizResult.vue";
import {getIdByToken} from "@/tokenController.js";
import {apiClient} from "@/api.js";

export default {
  data() {
    return {
      currentQuestion: null,
      quizResult: null,
      currentQuestionIndex: 0,//set to null
      userId: 0, //set to null
      quizId: 0,  //set to null
      quizTitle: 'Quiz title',
      selectedOption: '',
      questions: [],
      currentQuestionText: '',
      currentQuestionId: null,
      currentAnswers: [],
      quizResultId: 0,
      errorMsg: '',
    }
  },
  beforeMount() {
    this.setup();
    this.getQuestions();
    this.getSampleQuestions();
    this.getQuiz();
    this.getUser();
  },
  mounted() {
    this.setCurrentQuestion();

  },
  methods: {
    async setup(){
      //TODO: QuizResultService
      try {
        await apiClient.post('/results/create', {
          id: this.quizId,
          userId: this.userId
        })//TODO: set QuizResultServiceId
      } catch (error) {
        this.errorMsg = 'Error starting quiz';
      }
    },
    getUser() {
      this.userId = getIdByToken();
    },
    getQuiz() {
      try {
        apiClient.get('/quiz/quiz/' + this.quizId).then(response => {
          this.quizTitle = JSON.parse(response.data.questions);
        });
      } catch (error) {
        this.errorMsg = 'Error retrieving quiz';
      }
    },
    getQuestions(quizId) {
      //TODO: use method to fetch questions
      try {
        apiClient.get('/question/' + this.quizId).then(response => {
          this.questionIds = JSON.parse(response.data.questions);
        });
      } catch (error) {
        this.errorMsg = 'Error retrieving questions';
      }
    },
    getSampleQuestions() {
      const quest = {
        id: 0,
        questionText: "question " + -1,
        options: ["ans1","ans2", "ansThree"],
      }
      this.questions.push(quest);
      for(let i=0; i<4; i++){
        const question = {
          id: i,
          questionText: "question " + i,
          options: ["ans1","ans2"]
        }
        this.questions.push(question);
        console.log(question.id);
        console.log(this.questions.length);
      }
    },
    async nextQuestion() {
      if(!this.selectedOption) {
        this.errorMsg = 'You must select an answer';
      } else {
        this.errorMsg = '';
      }
      console.log(this.selectedOption);


      console.log(this.selectedOption);
      try {
        await apiClient.post('/save', {
          id: this.quizId,
          questionId: this.currentQuestionId,
          givenAnswer: this.selectedOption,
        })
      } catch (error) {
        this.errorMsg = 'Cannot submit question';
      }
      this.currentQuestionIndex++
      this.setCurrentQuestion();

    },
    //setting current question, updating info
    setCurrentQuestion() {
      /*if(!this.questions.empty()){
        this.currentQuestion = this.questions[this.currentQuestionIndex];
      }*/ //for some reason this check messes up the app???
      this.currentQuestion = this.questions[this.currentQuestionIndex];
      this.currentQuestionId = this.currentQuestion.id;
      this.currentQuestionText = this.currentQuestion.questionText;
      this.currentAnswers = this.currentQuestion.options;
    },
    setupMCQuestion(question) {
      this.questionTitle = question.questionText;
      this.currentAnswers = question.options;
      this.currentCorrectAnswer = question.answer;
    },
    selectOption(option) {
      this.selectedOption = option;
      console.log(this.selectedOption);
    }
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
        <!--
        <li v-for="option in answersList">
          <label>test label</label>-->
        <!--
        <input type="radio" :id="'option'" :value="option"
               :checked="option === selectedOption">-->
        <!--<label :for="'option'">option</label>-->

      </ul>


      <button @click="nextQuestion">Next Question</button>
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
