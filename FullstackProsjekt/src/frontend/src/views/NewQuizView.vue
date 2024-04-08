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
      questions: [],
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
        await apiClient.post('quiz/create', {
          title: this.quizTitle,
          questionIds: this.questions,
          creatorId: this.creatorId,
          category: this.selectedCategory,
          difficulty: this.selectedDifficulty
        }).then(response => {
          this.quizId = JSON.parse(response.data.id);
          router.push({name: 'editQuiz', params: {quizId: this.quizId}});
        })
      } catch(error){
        this.errorMsg = 'Cannot construct quiz';
      }
    },
    getUser() {
      this.creatorId = getIdByToken();
    }
  },
}
</script>

<template>
	<body>
	<div class="new-quiz-page">
		  <form @submit.prevent="constructQuiz">
					<router-link to="/overviewQuiz"> <-  </router-link>
					<h1>New quiz</h1>
		      <div>
		        <h2>Title</h2>
		        <input>
		      </div>
		      <div>
		        <h2>Category</h2>
		        <form>
		          <select v-model="selectedCategory">
		            <option v-for="category in categories" :key="category.id" :value="category">{{categories.category}}</option>
		          </select>
		        </form>
		      </div>
		      <div>
		        <h2>Difficulty</h2>
		        <form>
		          <select v-model="selectedDifficulty">
		            <option v-for="difficulty in difficulties" :key="difficulty.id" :value="difficulty">{{difficulties.difficulty}}</option>
		          </select>
		        </form>
		      </div>

		      <div class="footer">
		        <router-link to="/overviewQuiz" class="delete-btn"> Cancel  </router-link>
			      <button class="submit-btn" type="submit"> Submit</button>
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

.footer{
	margin: 10vh;
	display: flex;
	justify-content: center;
	align-items: center;

}

</style>