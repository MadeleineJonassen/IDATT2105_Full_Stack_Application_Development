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
      errorMsg: '',
      //TODO: make quiz object
    };
  },
  beforeMount() {
    this.quizId = this.$route.params.quizId;
    console.log(this.quizId);
    this.getQuiz(this.quizId);
  },
  mounted() {
    //this.quizId = this.$route.params.quizId;
    //this.getQuiz(this.quizId);
  },
  methods: {
    async getQuiz(quizId) {
      console.log('Fetching data for quiz: ', quizId);
      try {
        await apiClient.get('/quiz/quiz/' + quizId).then(response => {
          console.log(response);
          this.quizTitle = response.data.title;
          //this.questions = response.data.questions;
          this.creatorId = response.data.creatorId;
          this.category = response.data.category;
          this.difficulty = response.data.difficulty;
        });
        //console.log("fetching questions");
        //get questions separately
        await apiClient.get('/questions/allQuestionsToAQuiz/' + quizId).then(response => {
          //console.log("fetched questions");
          //console.log(response.data);

          this.questions = response.data;
          console.log(this.questions[1]);
        });
      } catch (error) {
        //TODO: proper error handling
        console.log("Edit quiz error: " + error);
        this.errorMsg = 'Error retrieving quizzes';
      }
    },
    newQuestion() {
      this.showNewQuestion = true;
      console.log(this.showNewQuestion);
    },
    hideNewQuestion() {
      this.showNewQuestion = false;
    },
    deleteQuiz() {
      try {
        apiClient.post('/quiz/delete/' + this.questionId, )
      } catch (error) {
        this.errorMsg = 'Error deleting quiz';
      }
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
			<h1>Edit quiz: {{quizTitle}}</h1>
      <div class="question-div">

        <QuestionCard id="questionCard" v-for="question in questions" :key="question.id"
                      :question-id=question.id :question-text=question.questionText />

      </div>
      <NewQuestionModel v-if="showNewQuestion" @close="hideNewQuestion" :quiz-id="Number(quizId)"/>

			<div class="footer">
				<button @click="newQuestion" class="add-Btn"> Add Question </button>
        <button class="delete-btn" @click="deleteQuiz"> DELETE QUIZ </button>
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