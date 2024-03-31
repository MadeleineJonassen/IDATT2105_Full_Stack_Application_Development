<script>

</script>


<template>
	<body>
		<div class="overViewQuestion-page">
		<h1>Your quizzes</h1>
		<p> Select a quiz fo your creation to either play, edit or delete</p>
			<div class="question-table">
				<table class="table">
					<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Quiz</th>
						<th scope="col">Action</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th scope="row">1</th>
						<td>Vue Quiz</td>
						<td>
							<router-link to="/playQuiz" class="play-btn">Play</router-link>
							<router-link to="/createQuiz" class="edit-btn">Edit</router-link>
							<button class="delete-btn"> Delete</button>
						</td>
					</tr>
					<tr>
						<th scope="row">2</th>
						<td>What is Java?</td>
						<td>
							<router-link to="/playQuiz" class="play-btn">Play</router-link>
							<router-link to="/overviewQuiz" class="edit-btn">Edit</router-link>
							<button class="delete-btn"> Delete</button>
						</td>
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

.question-table{
	width: 450px;
	padding: 10px;
}

.play-btn{
	height: 25px;
	padding: 5px;
	text-decoration: none;
	background-color: #242F40;
	border-color: transparent;
	color: #FFFFFF;
}
.edit-btn{
	height: 25px;
	padding: 5px;
	text-decoration: none;
	background-color: #CCA43B;
	border-color: transparent;
	color: #242F40;
}
.delete-btn{
	height: 25px;
	padding: 5px;
	text-decoration: none;
	background-color: #cc513b;
	border-color: transparent;
	color: #242F40;
}
</style>