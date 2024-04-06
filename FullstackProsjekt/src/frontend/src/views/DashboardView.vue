<script >
	import { apiClient } from "@/api.js";
	import router from "@/router/index.js";
	import Svg from "@/assets/Svg.vue";
	import { categoryEnums } from "@/data/categories.js";

	export default {
	components: { Svg },
	props: {
	quizId: {
	type: Number,
	required: true,
},
},
	data() {
	return {
    quizList: []
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

    console.log(this.quizList[0].title)
  } catch (error) {
    // TODO: Proper error handling
    console.error('Error retrieving quiz:', error);
  }
},
	getIcon(category) {
	// Check if the category exists in the enum
	if (categoryEnums.includes(category)) {
	// Retrieve the icon name from the mapping
	return categoryIcons[category] || categoryIcons.Default;
} else {
	// If category not found, return the default icon
	return categoryIcons.Default;
}
},
	playQuiz() {
	router.push({ name: 'playQuiz', params: { quizId: this.quizId } });
},
},
};
</script>


<template>
	<body class="dashboard">
	<div class="top-bar">

		<div class="search-container">
			<input class="searchBox" placeholder="Search for category...">
		</div> <br>
		<div class="create-container">
			<router-link to="/overviewQuiz" class="create-btn">YOUR QUIZES</router-link>
		</div>

	</div>
		<div class="row">
      <div class="quiz-list">
        <div class="quiz-col" v-for="quiz in quizList" :key="quiz.quizId">
          <div class="quiz-header">
            <h3>{{ quiz.title }}</h3>
          </div>
          <div class="quiz-body">
            <p>Difficulty level: {{ quiz.difficulty }}</p>
            <p>Category: {{ quiz.category }}</p>
          </div>
          <div class="quiz-footer">
            <button @click="playQuiz(quiz.quizId)" class="play-btn">Play</button>
          </div>
        </div>
      </div>
		</div>

	</body>
</template>


<style>
.dashboard{
	padding: 20px;
}
.top-bar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}
.search-container {
	flex-grow: 1; /* Grow to take available space */
	margin-right: 10px; /* Adjust margin between search box and button */
}

.create-container {
	flex-shrink: 0; /* Do not shrink */
}
.searchBox{
	width: 250px;
	padding: 10px;
	margin: 0 auto; /* Center horizontally */
	display: block;
	text-align: center;
}

.quiz-col{
	flex-basis: 31%;
	background: #d7d7d7;
	border-radius: 10px;
	margin-bottom: 5%;
	padding: 20px;
	box-sizing: border-box;
	transition: 0.5s;}

.create-btn{
	text-decoration: none;
	color: #E5E5E5;
	padding: 12px 34px;
	font-size: 16px;
	cursor: pointer;
	margin-bottom: 60px;
	background-color: #242F40;
}
.create-btn:hover{
	border: 1px solid #CCA43B;
	color: #242F40;
	background: #CCA43B;
	transition: 1s;
}

</style>