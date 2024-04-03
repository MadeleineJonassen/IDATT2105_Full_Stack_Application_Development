<script >
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
			<div class="course-col">
				<h3>Quiz 1</h3>
				<p>Insert photo </p>
			</div>
			<div class="course-col">
				<h3>Quiz 2</h3>
				<p>Insert photo </p>
			</div>
			<div class="course-col">
				<h3>Quiz 3</h3>
				<p>Insert photo</p>
			</div>
		</div>

		<div class="row">
			<div class="course-col">
				<h3>Quiz 4</h3>
				<p>Insert photo </p>
			</div>
			<div class="course-col">
				<h3>Quiz 5</h3>
				<p>Insert photo </p>
			</div>
			<div class="course-col">
				<h3>Quiz 6</h3>
				<p>Insert photo</p>
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