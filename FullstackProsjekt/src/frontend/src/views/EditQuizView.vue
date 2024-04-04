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

export default {
  components: {NewQuestionModel, QuestionCard},
  data() {
    return {
      showNewQuestion: false,
      quizId: null,
      quizTitle: '',
      questions: [
        {id: 0, num: 1, text:'first question'},
        {id: 1, num: 2, text:'second question'},
        {id: 2, num: 3, text:'third question'}
      ],
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
      /*
      try {
        apiClient.get('/quiz/quiz/' + this.quizId).then(response => {
          this.quizTitle = JSON.parse(response.data.title);
          this.category = JSON.parse(response.data.category);
          this.difficulty = JSON.parse(response.data.difficulty);
          this.questions = JSON.parse(JSON.stringify(response.data.questions));
        });
      } catch (error) {
        //TODO: proper error handling
        this.errorMsg = 'Error retrieving quizzes';
      }*/
    },
    newQuestion() {
      this.showNewQuestion = true;
      console.log(this.showNewQuestion);
    },
    hideNewQuestion() {
      this.showNewQuestion = false;
      //TODO: update answers, +answer count
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
		<div class="createQuestion-page">
			<router-link to="/overviewQuiz"> <-  </router-link>
			<h1>Edit quiz {{quizId}}</h1>
      <p>Add questions</p>
      <div class="question-div">
        <QuestionCard question-id="questionCard" v-for="question in questions"
                      :key="question.id" :question-num="question.num" :question="question.text"/>
      </div>
      <NewQuestionModel v-if="showNewQuestion" @close="hideNewQuestion"/>
      <!--
			<div class="question-table">
				<table class="table">
					<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Question</th>
						<th scope="col">Action</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th scope="row">1</th>
						<td>What is Vue?</td>
						<td>
							<button class="play-btn">View</button>
							<button class="edit-btn">Edit</button>
							<button class="delete-btn"> Delete</button>
						</td>
					</tr>

					</tbody>
				</table>
				-->
        <!--


				<Teleport to="body">
					<NewQuestionModel :show="showNewQuestionModal" @close="destroyModal">

						<template #header>
							<h5> Add New Question</h5>
						</template>

						<template #body>
							<form>
								<div class="mb-3">
									<label for="question" class="form-label">Question</label> <br>
									<input type="text" v-model="createdQuestion" class="form-control" id="questionInput">
								</div>
								<table class="table">
									<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Answer</th>
										<th scope="col">Correct ?</th>
									</tr>
									</thead>
									<tbody>
									<tr v-for="(answer, index) in newAnswers">
										<th scope="row">{{answer.id}}</th>
										<td>
											<input type="text" v-model="answer.answer" id="questionInput">
										</td>
										<td>
											<input :checked="answer.correct_answer === 1" class="form-check-input" :value="answer.id" @change="handleRadioToggle(answer.id)"  type="radio">
										</td>
									</tr>
									</tbody>
								</table>
							</form>
						</template>

						<template #footer>
							<button @click="addNewAnswers" class="add-Btn" v-if="newAnswers.length<4" >+</button>
							<button @click="destroyModal" class="close-btn"> Close</button>
							<button v-if="newAnswers.length>=2" @click="submitQuestion" class="submit-btn">Submit</button>
						</template>

					</NewQuestionModel>
				</Teleport>
				</div>
				-->
			<div>
				<button @click="newQuestion" class="add-Btn"> Add Question </button> <br>
				<button class="save-Btn"> SAVE QUIZ </button>
			</div>
		</div>
	</body>

</template>

<style>
.createQuestion-page{
	padding: 20px;
}

input{
	height: 25px;
	width: 100%;
}

</style>