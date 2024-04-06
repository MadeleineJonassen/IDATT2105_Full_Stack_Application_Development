<script>
import NewQuestionModel from "@/components/shared/NewQuestionModel.vue";
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import router from "@/router/index.js";
import {apiClient} from "@/api.js";
import QuestionCard from "@/components/shared/QuestionCard.vue";

/**
const quizId = ref(null); //TODO: set quiz id when routing here

const createdQuestion = ref(null);
const newAnswers = ref([]);
let showNewQuestionModal = ref(false);
const selectedAnswer = ref(null);
const existingQuestions = ref([]);
let answerId = 1;
const quizName = ref('');
const errorMsg = ''; //TODO: display error to user*/

//send list of question-ids?
export default {
  components: {NewQuestionModel, QuestionCard},
  data() {
    return {
      showNewQuestion: false,
      creatorId: null,
      quizId: null,
      quizTitle: '',
      questions: [], //question list is just a list of q-ids!!
      category: '',
      difficulty: '',
      errorMsg: ''
      //TODO: make quiz object
    };
  },
  mounted() {
    this.quizId = this.$route.params.quizId;
    this.getQuiz(this.quizId);
  },
  methods: {
    getQuiz(quizId) {
      console.log('Fetching data for quiz: ', quizId);
      try {
        apiClient.get('/quiz/quiz/' + this.quizId).then(response => {
          console.log(response)
          this.quizTitle = JSON.parse(response.data.title);
          this.questions = response.data.questions;
          this.creatorId = JSON.parse(response.data.creatorId);
          this.category = response.data.category;
          this.difficulty = response.data.difficulty;
        });
      } catch (error) {
        //TODO: proper error handling
        this.errorMsg = 'Error retrieving quizzes';
      }
    },
    newQuestion() {
      this.showNewQuestion = true;
      console.log(this.showNewQuestion);
    },
    hideNewQuestion() {
      this.showNewQuestion = false;
      //TODO: questions answers, +question count
    },
    deleteQuiz() {
      //API req, quizId
    }
  },
};

/**
function createQuestion() {
	showNewQuestionModal.value = true;
}
function destroyModal() {
	showNewQuestionModal.value = false;
}

function addNewAnswers() {
	const newAnswer = {
		id: answerId++,
		answer: '',
		correct_answer: 0
	}
	newAnswers.value.push(newAnswer)
}
function handleRadioToggle(Id) {
	selectedAnswer.value = Id
	newAnswers.value.forEach(answer => {
		if(answer.id === Id) {
			answer.correct_answer = 1
		} else {
			answer.correct_answer = 0
		}
	})
}*/

/**
function validateAnswers() {
	for(const answer of newAnswers.value) {
		if(answer.answer.trim()==='') {
			return false
		}
	}
	return true
}
function answerCount() {
	if(newAnswers.length < 2) {
		alert('Two answers required to submit')
	} else if(newAnswers.length === 2) {
		return true
	}
}
/*
async function getExistingQuestions() {
  try {
    const response = await apiClient.get(`/quizzes/${quizId.value}/questions`); // Fetch questions for a specific quiz
    existingQuestions.value = response.data; // Update existingQuestions with the fetched questions
  } catch (error) {
    console.error('Error fetching existing questions:', error);
    // Optionally, you can handle error response here
    alert('An error occurred while fetching existing questions');
  }
}
*/

/**
async function submitQuestion() {
  //TODO: proper error handling
	if(!createdQuestion.value){
		alert('Question cannot be empty');
		return false
	}
  if(!validateAnswers() && !answerCount()){
		alert('Fill all inputs before submitting')
		return false
	}

  try {
    await apiClient.post('questions', {
      //TODO: find quizID, send this too
      question: createdQuestion.value,
      correctAnswer: selectedAnswer.value,
      answers: newAnswers.value
    });
  } catch (error) {
    //TODO: proper error handling
    this.errorMsg = 'Error logging in';
  }
}
*/
</script>


<template>
	<body>
		<div class="newQuizDiv">
			<router-link to="/overviewQuiz"> <-  </router-link>
			<h1>Edit quiz {{quizId}}</h1>
      <div class="question-div">
        <QuestionCard  v-for="question in questions" :question-id=question.id
                      :key="question.id"/>
      </div>
      <NewQuestionModel v-if="showNewQuestion" @close="hideNewQuestion" quiz-id="this.quizId"/>

			<div class="footer">
				<button @click="newQuestion" class="add-Btn"> Add Question </button>
        <button class="delete-btn"> DELETE QUIZ </button>
				<button class="save-Btn"> SAVE QUIZ </button>
			</div>
		</div>
	</body>

</template>

<style>
.newQuizDiv{
	padding: 20px;
}

input{
	height: 25px;
	width: 100%;
}

</style>