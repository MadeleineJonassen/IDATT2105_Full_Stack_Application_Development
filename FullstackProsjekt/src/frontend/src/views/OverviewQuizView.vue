<script>
  import {apiClient} from "@/api.js";
  import {ref} from "vue";
  getQuizzes();

  const quizzes = ref([]);

  async function getQuizzes() {
    //TODO: try/catch
    const response = await apiClient.get('/quizzes/${quizId.value}');
    quizzes.value = response.data; //TODO: create parsing method
  }
</script>

<template>
	<body>
		<div class="overViewQuestion-page">
			<router-link to="/dashboard"> <- </router-link>
		<h1>Your quizzes</h1>
		<p> Select a quiz fo your creation to either play, edit or delete</p>
			<div class="table">
				<table class="table">
					<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Quiz</th>
						<th scope="col">Action</th>
						<th scope="col">Category </th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th scope="row">1</th>
						<td>Vue Quiz</td>
						<td>
							<router-link to="/play-quiz" class="play-btn">Play</router-link>
							<router-link to="/createQuiz" class="edit-btn">Edit</router-link>
							<button class="delete-btn"> Delete</button>
						</td>
						<th>
							<div id="form-box">
								<form>
									<select id="language">
										<option value="IT" selected="selected">IT</option>
										<option value="Nature">Nature</option>
										<option value="Chemistry">Chemistry</option>
										<option value="Religion">Religion</option>
										<option value="Other">Other</option>
									</select>
								</form>
							</div>
						</th>
					</tr>
					<tr>
						<th scope="row">2</th>
						<td>What is Java?</td>
						<td>
							<router-link to="/play-quiz" class="play-btn">Play</router-link>
							<router-link to="/overviewQuiz" class="edit-btn">Edit</router-link>
							<button class="delete-btn"> Delete</button>
						</td>
						<th>
							<div id="form-box">
								<form>
									<select id="language">
										<option value="IT" selected="selected">IT</option>
										<option value="Nature">Nature</option>
										<option value="Chemistry">Chemistry</option>
										<option value="Religion">Religion</option>
										<option value="Other">Other</option>
									</select>
								</form>
							</div>
						</th>
					</tr>
					</tbody>
				</table>
			</div>
		</div>

	</body>

</template>

<style>
.overViewQuestion-page{
	padding: 20px;
}
</style>