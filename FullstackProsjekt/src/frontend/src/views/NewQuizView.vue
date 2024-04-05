<script>
import NewQuestionModel from "@/components/shared/NewQuestionModel.vue";
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import router from "@/router/index.js";
import {apiClient} from "@/api.js";
import {getIdByToken} from "@/tokenController.js";
import {categoryEnums} from "@/data/categories.js"
import {difficultyEnums} from "@/data/difficulties.js";

//like editquiz, but w/o questions, redirect to edit when quiz is constructed

export default {
  data() {
    return {
      showNewQuestion: false,
      creatorId: null,
      quiz: null,
      quizId: null,
      quizTitle: '',
      category: '',
      difficulty: '',
      errorMsg: '',
      selectedCategory: null,
      categories: categoryEnums,
      selectedDifficulty: null,
      difficulties: difficultyEnums,
      //TODO: make quiz object
    };
  },
  mounted() {
    this.getUser();
  },
  methods: {
    async constructQuiz() {
      try {
        const post = {
          title: this.quizTitle,
          creatorId: this.creatorId,
          category: this.selectedCategory,
          difficulty: this.selectedDifficulty
        }
        await apiClient.post('quiz/create', post).then(response => {
          this.quizId = JSON.parse(response.data.id);
          router.push({name: 'editQuiz', params: {quizId: this.quizId}});
        })
      } catch(error){
        this.errorMsg = 'Cannot construct quiz';
      }
    },
    async getUser() {
      this.creatorId = await getIdByToken();
    }

  },
}
</script>

<template>
	<body>
  <form @submit.prevent="constructQuiz">
		<div class="newQuizDiv">
			<router-link to="/overviewQuiz"> <-  </router-link>
			<h1>New quiz</h1>
      <div>
        <h2>Title</h2>
        <input type="text" required v-model="quizTitle" placeholder="Insert title here..."/> <br>
      </div>
      <div>
        <h2>Category</h2>
        <form>
          <select v-model="selectedCategory">
            <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
          </select>
        </form>
      </div>
      <div>
        <h2>Difficulty</h2>
        <form>
          <select v-model="selectedDifficulty">
            <option v-for="difficulty in difficulties" :key="difficulty" :value="difficulty">{{ difficulty}}</option>
          </select>
        </form>
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
      <div class="footer">
        <router-link to="/overviewQuiz"> Cancel  </router-link>
        <div class="submit-section">
          <input id="submit" type="submit"/>
        </div>
      </div>
    </div>
  </form>
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

.submit-section {
  display: flex;
  justify-content: center;
  align-items: center;
}

#submit {
  min-width: 150px;
  min-height: 60px;
  font-size: 24px;
  border-radius: 6px;
  background-color: #242F40;
  color: white;
  border: none;
  cursor: pointer;
  margin-top: 20px;
}

</style>