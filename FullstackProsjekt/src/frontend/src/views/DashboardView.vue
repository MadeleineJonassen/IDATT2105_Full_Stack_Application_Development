<script >
</script>

<template>
	<body class="dashboard">
		<h1>Dashboard</h1>
		<input class="searchBox" placeholder="Search for category..."> <br>
		<router-link to="/createQuiz" class="hero-btn">CREATE QUIZ </router-link>
		<div class="row">
			<div class="course-col">
				<h3>Quiz 1</h3>
				<img id="quizImg" src="../components/photos/background.png"/>
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
.searchBox{
	width: 250px;
	padding: 10px;
	margin-bottom: 50px;
}

.hero-btn{
	display: inline-block;
	text-decoration: none;
	color: #242F40;
	border: 1px solid #242F40;
	padding: 12px 34px;
	font-size: 16px;
	background: transparent;
	position: relative;
	cursor: pointer;
}
.hero-btn:hover{
	border: 1px solid #CCA43B;
	background: #CCA43B;
	transition: 1s;
}

.row{
	margin-top: 5%;
	display: flex;
	justify-content: space-between;
}
.course-col{
	flex-basis: 31%;
	background: #E5E5E5;
	border-radius: 10px;
	margin-bottom: 5%;
	padding: 20px;
	box-sizing: border-box;
	transition: 0.5s;
}
.course-col:hover{
	box-shadow: 0 0 20px 0px rgba(0,0,0,0.3);
}

#quizImg{
	height: 250px;
	border-radius: 5px;
}

@media (max-width: 700px){
	.text-box h1{
		font-size: 42px;
		transition: 1s;
	}
	.row{
		flex-direction: column;
	}
}
</style>