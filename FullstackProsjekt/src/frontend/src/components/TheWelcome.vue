<script>
export default {
	data(){
		return{
		}
	},
}
</script>


<template>
	<body>
	<section class ="header">
		<div class="text-box">
			<img id="logo" src="../components/icons/brain.png"/>
			<h1 class="heading">BrainStormer</h1>
			<p> An easy way to learn and share quizzes. <br> Make your own quiz now! </p>
			<a href="" class="hero-btn">CREATE QUIZ</a>
		</div>
	</section>

	<!----- Info ----->
	<section class="info">
		<h1>How does it work</h1>
		<p>Set a difficulty to your quizzes ..... [Add more text here]</p>

		<div class="row">
			<div class="course-col">
				<h3>Easy</h3>
				<p>The simplest of quizzes </p>
			</div>
			<div class="course-col">
				<h3>Medium</h3>
				<p>A more challenging quiz for those who want a challenge </p>
			</div>
			<div class="course-col">
				<h3>Hard</h3>
				<p>Quizes that challenge the mind to new limits... </p>
			</div>
		</div>
	</section>
	</body>
</template>




<style scoped>

/*---Top----*/
.header{
	min-height: 100vh;
	width: 100%;
	background-image: linear-gradient(rgba(4,9,30,0.7), rgba(4,9,30,0.7)),url(photos/lightning.gif);
	background-position: center;
	background-size: cover;
	position: relative;
}
#logo{
	height: 150px;
	padding: 10px;
}

/*Front page*/
.text-box{
	width: 90%;
	color: #fff;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%,-50%);
	text-align: center;
}
.text-box h1{
	font-size: 62px;
}
.text-box p{
	margin: 10px 0 40px;
	font-size: 18px;
	color: #fff;
}

.hero-btn{
	display: inline-block;
	text-decoration: none;
	color: #fff;
	border: 1px solid #fff;
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

/*Info with three levels*/
.info{
	width: 80%;
	margin: auto;
	text-align: center;
	padding-top: 100px;
}
h1{
	font-size: 36px;
	font-weight: 600;
}
p{
	color: #0f1412;
	font-size: 16px;
	font-weight: 300;
	line-height: 22px;
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
h3{
	text-align: center;
	font-weight: 600;
	margin: 10px 0;
}
.course-col:hover{
	box-shadow: 0 0 20px 0px rgba(0,0,0,0.3);
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
