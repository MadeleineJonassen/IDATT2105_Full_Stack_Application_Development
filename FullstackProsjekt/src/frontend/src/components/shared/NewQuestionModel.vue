<script>
import {apiClient} from "@/api.js";
export default {
  props: {
    quizId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      questionText: '',
      answers: [{ text: '', correct: false }],
      correctAnswerIndex: 0,
      type: 'MC',
      score: 0,
      correctAnswer: null,
      errorMsg: ''
    }
  },
  methods: {
    //TODO: error prevention/handling
    async handleSubmit() {
      try {
        this.findCorrectAnswer();
        await apiClient.post('/questions', {
          quizId: this.quizId,
          questionText: this.questionText,
          type: this.type,
          answer: this.correctAnswer,
          options: this.answers.map(answer => answer.text), //just the strings!
          score: this.score
          //add questionId to questions in editQuiz!

        })
      } catch (error) {
        this.errorMsg = 'Error submitting question';
      }
    },
    closeModal() {
      this.$emit('close');
    },
    newAnswer() {
      this.answers.push({ text: '', correct: false });
    },
    findCorrectAnswer(){
      if (this.correctAnswerIndex !== null && this.answers[this.correctAnswerIndex]) {
        this.correctAnswer = this.answers[this.correctAnswerIndex].text;
      }
    }
  }

};
</script>

<template>
  <div class="modal-overlay" @click="closeModal">
    <div @click.stop class="modal-mask">
      <div class="modal-container">
        <form @submit.prevent="handleSubmit">
          <div class="question-title">
            <h3>Question:</h3>
            <input v-model="questionText" placeholder="Type your question here">

            <label>Score:</label>
            <input type="number" id="scoreInput" v-model="score">
          </div>
          <div class="modal-body">
            <!--
            <AnswerCard answer-id="answerCard" v-for="answer in answers"
                        :key="answer.id" :answerId="answer.id" :answer="answer.answer" :correct="answer.correct"/>
            -->
            <table class="table">
              <thead>
              <tr>
                <th scope="col">Answer</th>
                <th scope="col">Correct ?</th>
              </tr>
              </thead>
              <tbody>
                <tr v-for="(answer, index) in answers">
                  <td><input type="text" v-model="answer.text"></td>
                  <td>
                    <input type="radio" :id="'correctAnswer_' + index" :value="index" v-model="correctAnswerIndex">
                    <label :for="'correctAnswer_' + index">Correct</label>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="modal-footer">
            <button class="edit-btn" @click="newAnswer">Add answer</button>
            <button class="modal-default-button" @click="$emit('close')">SUBMIT</button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!--

	<Transition name="modal">
		<div v-if="show" class="modal-mask">
			<div class="modal-container">
				<div class="modal-header">
					<slot name="header">default header</slot>
				</div>

				<div class="modal-body">
					<slot name="body">default body</slot>
				</div>

				<div class="modal-footer">
					<slot name="footer">
						default footer
						<button
								class="modal-default-button"
								@click="$emit('close')"
						>OK</button>
					</slot>
				</div>
			</div>
		</div>
	</Transition>
	-->
</template>

<style>
.modal-mask {
	position: fixed;
	z-index: 9998;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	display: flex;
	transition: opacity 0.3s ease;
}

.modal-container {
	width: 500px;
	margin: auto;
	padding: 20px 30px;
	background-color: #fff;
	border-radius: 2px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
	transition: all 0.3s ease;
}

.question-title {
  display: flex;
  justify-content: space-between;
}




.modal-header h5 {
	margin-top: 0;
	color: #363636;
}

.modal-body {
	margin: 20px 0;
}

.modal-default-button {
	float: right;
}


.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
	-webkit-transform: scale(1.1);
	transform: scale(1.1);
}
</style>