<script>

import {defineComponent} from "vue";
import Svg from "@/assets/Svg.vue";

export default defineComponent({
	components: {Svg}
})
</script>


<template>
	<body>
		<div class="overViewQuestion-page">
			<router-link to="/dashboard"> <- </router-link>
		<h1>Your quizzes</h1>
		<p> Select a quiz for your creation to either play, edit or delete</p>

		<div class="row">
			<div class="course-col">

				<div class="quiz-header">
				<h3>Quiz 1</h3>
				<Svg name="cooking-category"/>
				</div>

				<div class="quiz-body">
					<p> Info about quiz...</p>
					<p>Category:</p>
					<div id="form-box">
						<form>
							<select id="quiz-category">
								<option value="IT" selected="selected">IT</option>
								<option value="Nature">Nature</option>
								<option value="Chemistry">Chemistry</option>
								<option value="Religion">Religion</option>
								<option value="Other">Other</option>
							</select>
						</form>
					</div>
				</div>

				<div class="quiz-footer">
					<router-link to="/play-quiz" class="play-btn">Play</router-link>
					<router-link to="/createQuiz" class="edit-btn">Edit</router-link>
					<button class="delete-btn"> Delete</button>
				</div>

			</div>
		</div>
		</div>
	</body>

</template>

<style>
.overViewQuestion-page{
	padding: 20px;

}
.quiz-header{
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}
.quiz-body{
	margin-bottom: 10px;
}
#quiz-category{
	padding: 1px;
	background-color: #FFF;
	border-radius: 5px;
	font-family: monospace;
}
.quiz-footer{
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}
</style>