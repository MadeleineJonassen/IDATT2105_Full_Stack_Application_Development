<script>
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
	<div class="new-quiz-page">
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
			</div>
		</form>
	</div>
	</body>

</template>
<style>
.new-quiz-page{
	margin:20vh;
}

input{
	height: 25px;
	width: 100%;
}
select{
	min-width: 100%;
	height: 25px;
	background-color: #E5E5E5;
	border-color: transparent;
	border-radius: 5px;
}

</style>
