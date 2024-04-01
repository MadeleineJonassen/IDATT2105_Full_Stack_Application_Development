<script >
</script>


<template>

	<body>

	<div class="quiz">

		<div id="info">
			<div id="score">Score: 0</div>
			<div id="ques-left">Question:1/20</div>
		</div>
		<div id="ques-view">

		</div>
		<div class="question">
			<h1>Question</h1>
		</div>

		<div class="choice">
			<div class="options"><input type="radio" name="options" value="option1" id="opt0"><label for="opt0" id="lb0">Option1</label></div>
			<div class="options"><input type="radio" name="options" value="option2" id="opt1"><label for="opt1" id="lb1">Option2</label></div>
			<div class="options"><input type="radio" name="options" value="option3" checked="checked" id="opt2"><label for="opt2" id="lb2">Option3</label></div>
			<div class="options"><input type="radio" name="options" value="option4" id="opt3"><label for="opt3" id="lb3">Option4</label></div>
		</div>

		<div class="ans-btn">
			<button type="button" class="submit-answer">Submit Answer</button>
			<a href="#display-final-score" type="button" class="view-results">View Results</a>
		</div>

	</div>

	<div class="final-result">
		<h1>The Quiz is Over</h1>
		<div class="solved-ques-no">You Solved 10 questions of {Name of quiz here}</div>
		<div class="right-wrong">3/4 were right</div>
		<div id="display-final-score">Your Final Score is: 35</div>
		<div class="remark">Remark: Satisfactory, Keep trying!</div>
		<button id="restart">Restart Quiz</button>
	</div>

	</body>

</template>

<style>
.quiz{
	text-align: center;
	margin-top: 20px;
	height: 100vh;
}

#info{
	height:25px;
}

#score{
	width:50%;
	float:left;
	font-size: 25px;
}

#ques-left{
	width:50%;
	float:left;
	font-size:25px;
}

#ques-view{
	height: 35px;
	margin-top: 10px;
	padding: 2px;
}
.question{
	letter-spacing: .13em;
}

.choice{
	padding: 3%;
}

.options{
	display:block;
	font-size: 25px;
	margin-top: 30px;
	text-align: left;
}

input[type=radio] {
	border: 5px solid white;
	width: 20px;
	height: 1.3em;
	float: left;
}

.ans-btn{
	padding: 2%;
}

.submit-answer{
	border: 2px solid #CCA43B;
	padding: 15px;
	border-radius: 20px;
	transition: background-color 0.3s,border 0.2s, color 0.2s;
	margin-right: 10px;
	font-family: monospace;
}

.submit-answer:hover{
	background-color: #CCA43B;
	padding: 16px;
	color: #fff;
	cursor: pointer;
	border-radius: 20px;
}

.view-results{
	text-decoration: none;
	color: black;
	border: 2px solid #CCA43B;
	padding: 15px;
	border-radius: 20px;
	transition: background-color 0.3s,border 0.2s, color 0.2s;
}


.view-results:hover{
	background-color: #CCA43B;
	padding: 16px;
	color: #fff;
	cursor: pointer;
	border-radius: 20px;
}

/*Final Results*/
.final-result{
	text-align: center;
	padding: 10px;
	font-size: 1.5em;
	height: 100vh;
}
.solved-ques-no{
	padding: 10px;
}

.right-wrong{
	padding: 10px;
}

#display-final-score{
	padding: 5%;
}
.remark{
	padding: 5%
}
#restart{
	background-color:#CCA43B;
	margin-left: 30px;
	border: 2px solid #CCA43B;
	padding: 15px;
	border-radius: 20px;
	font-size: 80%;
	transition: background-color 0.3s,border 0.2s, color 0.2s;
}

#restart:hover{
	background-color: #CCA43B;
	color:#fff;
	cursor: pointer;
	/*    width: 120px;*/
	font-size: 90%;
}


@media only screen and (max-width: 1250px) {
	.options{
		margin-left: 8%
	}
}

@media only screen and (max-width: 850px) {
	.options{
		margin-left: 4%;
	}
}

@media only screen and (max-width: 650px) {
	.options{
		display: block;
		margin-top: 3%;

	}
}

@media only screen and (max-width: 550px) {
	.options{
		margin-top: 3%;

	}

}
</style>
