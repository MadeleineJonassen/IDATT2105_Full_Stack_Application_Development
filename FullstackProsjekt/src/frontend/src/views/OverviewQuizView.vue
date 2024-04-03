<template>
	<body>
	<div class="overViewQuestion-page">
		<router-link to="/dashboard"> <- </router-link>
		<h1>Your quizzes</h1>
		<p>Select a quiz for your creation to either play, edit or delete</p>

		<div class="row">
			<div class="course-col">

				<div class="quiz-header">
					<h3>Quiz 1</h3>
					<Svg :name="selectedIcon" />
				</div>

				<div class="quiz-body">
					<p>Info about quiz...</p>
					<p>Category:</p>
					<div id="form-box">
						<form>
							<select id="quiz-category-1" @change="changeCategory">
								<option value="Animal">Animals</option>
								<option value="Athletic">Athletic/Sport</option>
								<option value="Computer" selected="selected">Computer Science</option>
								<option value="Drama">Drama/Movie</option>
								<option value="Music">Music</option>
								<option value="Religion">Religion</option>
								<option value="Science">Science</option>
								<option value="Society">Society</option>
								<option value="Other">Other</option>
							</select>
						</form>
					</div>
				</div>

				<div class="quiz-footer">
					<router-link to="/play-quiz" class="play-btn">Play</router-link>
					<router-link to="/createQuiz" class="edit-btn">Edit</router-link>
					<button class="delete-btn">Delete</button>
				</div>
			</div>

			<div>
				<button class="add-Btn">Create Quiz</button>
			</div>

		</div>
	</div>
	</body>
</template>

<script>
import { defineComponent } from "vue";
import Svg from "@/assets/Svg.vue";
import {apiClient} from "@/api.js";
import {ref} from "vue";
getQuizzes();

const quizzes = ref([]);

async function getQuizzes() {
  //TODO: try/catch
  const response = await apiClient.get('/quizzes/${quizId.value}');
  quizzes.value = response.data; //TODO: create parsing method
}

export default defineComponent({
	components: { Svg },
	data() {
		return {
			selectedCategory: 'Computer',
			categoryIcons: {
				'Animal': 'animal-category',
				'Athletic': 'athletic-category',
				'Computer': 'computer-category',
				'Drama': 'drama-category',
				'Music': 'music-category',
				'Religion': 'religion-category',
				'Science': 'science-category',
				'Society': 'society-category',
				'Other': 'other-category'
			}
		};
	},
	computed: {
		selectedIcon() {
			return this.categoryIcons[this.selectedCategory];
		}
	},
	methods: {
		changeCategory(event) {
			this.selectedCategory = event.target.value;
		}
	}
});
</script>

<style>
.overViewQuestion-page{
	padding: 50px;
}

.quiz-header{
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}
.quiz-body{
	margin-bottom: 40px;
}
#quiz-category{
	padding: 1px;
	background-color: #FFF;
	border-radius: 5px;
	font-family: monospace;
}
.quiz-footer{
	display: flex;
	justify-content: space-evenly;
	align-items: center;
	padding-left: 25px;
	padding-right: 25px;
}
</style>