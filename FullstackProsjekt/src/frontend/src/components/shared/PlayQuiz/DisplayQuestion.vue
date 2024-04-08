<script>
import {apiClient} from "@/api.js";

export default {
  props: {
    question: Object,
    selectedOption: String,
  },
  data() {
    return {
      questionText: null,
      options: [],
      correctAnswer: null,
    }
  },
  mounted() {
    //this.getSampleQuestion();
  },
  methods: {
    getSampleQuestion() {
      this.question = {
        id: 3,
        questionText: "question 3",
        options: ["ans1","ans2"],
        answer: "ans2"
      }
    },
    getQuestion(questionId) {
      try {
        apiClient.get('/questions/question/' + this.questionId).then(response => {
          this.question = JSON.parse(response.data);
          this.questionText = response.data.questionText;
          this.options = JSON.parse(response.data.options);
          this.correctAnswer = response.data.answer;
        });
      } catch (error) {
        //TODO: proper error handling
        this.errorMsg = 'Error retrieving question';
      }
    },
    selectOption(option) {
      this.$emit('update:selectedOption', option);
    }
  }
}
</script>

<template>
  <div class="quiz">

    <div id="info">
      <div id="score">Score: 0</div>
      <div id="ques-left">Question:1/20</div>
    </div>
    <div id="ques-view">

    </div>
    <div class="question">
      <h1>Q title</h1>
    </div>

    <ul v-if="question.options">
      <li v-for="(option) in question.options" :key="option">

        <label :for="option">option label</label>
      </li>
    </ul>


  </div>


</template>

<style scoped>
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