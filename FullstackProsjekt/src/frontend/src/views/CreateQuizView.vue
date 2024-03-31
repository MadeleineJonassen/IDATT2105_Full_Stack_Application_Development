<script setup>
import NewQuestionModel from "@/components/shared/NewQuestionModel.vue";
import {ref} from "vue";
import router from "@/router/index.js";

const createdQuestion = ref(null);
const newAnswers = ref([]);
const showNewQuestionModal = ref(false);
const selectedAnswer = ref(null);
let answerId = 1;


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
}

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

function submitQuestion() {
	if(!createdQuestion.value){
		alert('Question cannot be empty');
		return false
	}
	if(!validateAnswers()&& !answerCount()){
		alert('Fill all inputs before submitting')
		return false
	}
	//Supposed to send request to backend with a finnished Question with id and such
	router.post('/questions',{
		question:createdQuestion.value,
		answers:newAnswers.value
	})
}

</script>


<template>
	<body>
		<div class="createQuestion-page">
			<h1>Create a question to your quiz</h1>
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
						<th scope="row">{answer.id}</th>
						<td>What is Vue?</td>
						<td>
							<button class="play-btn">View</button>
							<button class="edit-btn">Edit</button>
							<button class="delete-btn"> Delete</button>
						</td>
					</tr>

					</tbody>
				</table>

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
										<th scope="row">1</th>
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

			<div>
				<button @click="createQuestion" class="create-question-btn"> Create Question </button> <br>
				<button class="save-question-btn"> SAVE QUIZ </button>
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