<script>
import router from "@/router/index.js";
import {apiClient} from "@/api.js";
import {getIdByToken} from "@/tokenController.js";
import {categoryEnums} from "@/data/categories.js"
import {difficultyEnums} from "@/data/difficulties.js";
import Svg from "@/assets/Svg.vue";


export default {
	components: {Svg},
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
	<router-link to="/overviewQuiz" ><Svg name="go-back-icon" class="go-back-section"/></router-link>
	<div class="new-quiz-page">
		<form @submit.prevent="constructQuiz">
			<div class="newQuizDiv">
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
				<div class="space"/>

				<div>
					<button class="submit-btn">Submit</button>
				</div>

			</div>
		</form>


	</div>
	</body>

</template>
<style>
.new-quiz-page {
	margin: 5vh auto;
	max-width: 400px;
	padding: 0 20px;
}

.go-back-section{
	padding-top: 5vh;
	padding-left: 5vh;
}

input,
select {
	width: 100%;
	height: 40px;
	margin-bottom: 10px;
	background-color: #E5E5E5;
	border: 1px solid transparent;
	border-radius: 5px;
	padding: 5px;
	box-sizing: border-box;
}

select {
	appearance: none;
	background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path d="M7 10l5 5 5-5H7z"/></svg>');
	background-repeat: no-repeat;
	background-position-x: calc(100% - 8px);
	background-position-y: 50%;
	padding-right: 20px;
}

.submit-btn {
	display: block;
	margin: 0 auto;
}
</style>
