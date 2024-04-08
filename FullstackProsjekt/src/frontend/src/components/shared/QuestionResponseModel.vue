<template>
  <div>
    <h3>{{ question }}</h3>
    <ul>
      <li v-for="(answer, index) in answers" :key="index">
        <input type="radio" :id="'answer_' + index" :value="answer" v-model="selectedAnswer">
        <label :for="'answer_' + index">{{ answer }}</label>
      </li>
    </ul>
    <button @click="checkAnswer">Check Answer</button>
    <p v-if="showResult">{{ isCorrect ? 'Correct!' : 'Incorrect!' }}</p>
  </div>
</template>

<script>
export default {
  props: {
    question: String,
    answers: Array,
    correctAnswer: String
  },
  data() {
    return {
      selectedAnswer: null,
      showResult: false,
      isCorrect: false
    };
  },
  methods: {
    checkAnswer() {
      if (this.selectedAnswer === this.correctAnswer) {
        this.isCorrect = true;
      } else {
        this.isCorrect = false;
      }
      this.showResult = true;
    }
  }
};
</script>

<style>
/* Add your CSS styles here */
</style>
