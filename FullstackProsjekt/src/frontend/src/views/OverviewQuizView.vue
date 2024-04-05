<template>
	<body>
	<div class="overViewQuestion-page">

    <div class="headerDiv">
      <div>
        <router-link to="/dashboard"> <- </router-link>
        <h1>Your quizzes</h1>
        <p>Select a quiz for your creation to either play, edit or delete</p>
      </div>
	      <router-link to="/createQuiz" class="add-Btn">New quiz</router-link>
      </div>

		<div class="row">
      <div class="quiz-div">
        <QuizCard id="quizCard" v-for= "quiz in quizList" :key="quiz.id" :quiz-id="quiz.id" />
      </div>
		</div>

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

	</div>
	</body>
</template>

<script>
import QuizCard from "@/components/shared/QuizCard.vue";
import {getIdByToken} from "@/tokenController.js";
import {apiClient} from "@/api.js";
import NewQuestionModel from "@/components/shared/NewQuestionModel.vue";


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
	padding: 40px;
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