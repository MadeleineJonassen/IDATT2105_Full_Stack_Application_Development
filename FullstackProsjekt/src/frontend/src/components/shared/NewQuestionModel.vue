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
      answers: [],
      correctAnswerIndex: 0,
      type: 'MULTIPLE_CHOICE',
      score: 0,
      correctAnswer: null,
      errorMsg: ''
    }
  },
  methods: {
    //TODO: error prevention/handling
    async handleSubmit() {
      try {
        //this.findCorrectAnswer();
        //console.log(this.correctAnswer);
        await apiClient.post('/questions/save', {
          questionText: this.questionText,
          type: this.type,
          answer: this.correctAnswer.text,
          options: this.answers.map(answer => answer.text),
          score: this.score,
          quizId: this.quizId,
        })
        this.$emit('close');
      } catch (error) {
        console.log("error: " + error);
        this.errorMsg = 'Error submitting question';
      }
    },
    newAnswer() {
      this.answers.push({ text: ''});
    },
    selectOption(option) {
      this.correctAnswer = option;
      console.log(this.correctAnswer);
    }
  }
};
</script>

<template>
  <div class="modal-overlay" >
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
            <table class="table">
              <thead>
              <tr>
                <th scope="col">Answer</th>
                <th scope="col">Correct</th>
              </tr>
              </thead>
              <tbody>
                <tr v-for="(answer, index) in answers">
                  <td><input type="text" v-model="answer.text"></td>
                  <td>
                    <input type="radio" :id="index" :value="answer" v-model="correctAnswerIndex"
                           :checked="answer === correctAnswer" @change="selectOption(answer)">
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="modal-footer">
            <button class="edit-btn" @click="newAnswer">Add answer</button>
            <button class="modal-default-button" type="submit">SUBMIT</button>
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