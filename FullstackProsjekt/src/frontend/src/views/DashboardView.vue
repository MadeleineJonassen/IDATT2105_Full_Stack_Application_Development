<script >
</script>

<template>
	<body class="dashboard">
		<h1>Dashboard</h1>
		<input class="searchBox" placeholder="Search">

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
	padding: 10px;
}
.searchBox{
	padding: 10px;
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
</style>