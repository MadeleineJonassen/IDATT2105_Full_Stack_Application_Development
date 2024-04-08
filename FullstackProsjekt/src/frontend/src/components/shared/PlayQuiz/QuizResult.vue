<script>
export default {
  name: "QuizResult"
}
</script>

<template>
  <div class="final-result">
    <h1>The Quiz is Over</h1>
    <div class="solved-ques-no">You Solved 10 questions of {Name of quiz here}</div>
    <div class="right-wrong">3/4 were right</div>
    <div id="display-final-score">Your Final Score is: 35</div>
    <div class="remark">Remark: Satisfactory, Keep trying!</div>
    <button id="restart">Restart Quiz</button>
  </div>
</template>

<style scoped>
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