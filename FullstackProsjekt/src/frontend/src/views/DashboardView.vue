<template>
	<body>
	<div class="dashboard">
		<div class="top-bar">
			<router-link to="/" ><Svg name="go-back-icon" class="go-back-icon"/></router-link>

			<div class="search-container">
				<input class="searchBox" v-model="searchTerm" placeholder="Search for category...">
			</div>

			<div class="create-container">
				<router-link to="/overviewQuiz" class="add-Btn">YOUR QUIZZES</router-link>
			</div>
		</div>

		<div class="row">
			<div class="quiz-list">
				<div class="quiz-col" v-for="quiz in filteredQuizList" :key="quiz.id">
					<div class="quiz-header">
						<h3>{{ quiz.title }}</h3>
					</div>
					<div class="quiz-body">
						<p>Difficulty level: {{ quiz.difficulty }}</p>
						<p>Category: {{ quiz.category }}</p>
					</div>
					<div class="quiz-footer">
						<button @click="playQuiz(quiz.id)" class="play-btn">Play</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</body>
</template>


<script>
import { apiClient } from "@/api.js";
import router from "@/router/index.js";
import Svg from "@/assets/Svg.vue";

export default {
	components: {Svg},
	data() {
		return {
			quizList: [],
			searchTerm: ''
		};
	},
	mounted() {
		this.getQuiz();
	},
	methods: {
		async getQuiz() {
			try {
				const response = await apiClient.get('/quiz/');
				this.quizList = response.data;
			} catch (error) {
				console.error('Error retrieving quiz:', error);
			}
		},
		playQuiz(id) {
			router.push({ name: 'playQuiz', params: { quizId: id } });
		}
	},
	computed: {
		filteredQuizList() {
			return this.quizList.filter(quiz => quiz.category.toLowerCase().includes(this.searchTerm.toLowerCase()));
		}
	}
};
</script>


<style>
.dashboard{
	padding: 20px;
}
.top-bar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10vh;
	margin-top: 4vh;
}
.go-back-icon{
	margin-left: 1px;
}

.search-container {
	flex-grow: 1;
}
.create-container {
	flex-shrink: 0;
}
.searchBox {
	width: 250px;
	padding: 10px;
	margin: 0 auto;
	display: block;
	text-align: center;
}
.quiz-list {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
}

.quiz-col {
	flex-basis: 30%;
}

.quiz-col {
	flex-basis: 31%;
	background: var(--card-background);
	border-radius: 10px;
	margin-bottom: 5%;
	padding: 20px;
	box-sizing: border-box;
	transition: 0.5s;
}
.row{
	margin-top: 5%;
	display: flex;
	justify-content: space-between;
	margin-left: 5%;
	margin-right: 5%;
}

.quiz-col:hover{
	box-shadow: 0 0 20px 0px rgba(0,0,0,0.3);
}

</style>