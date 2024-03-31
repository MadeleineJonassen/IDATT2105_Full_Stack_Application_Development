<script setup>
import NewQuestionModel from "@/components/NewQuestionModel.vue";
import {ref} from "vue";

const showNewQuestionModal=ref(false)

function createQuestion() {
	showNewQuestionModal.value = true;
}
function destroyModal(){
	showNewQuestionModal.value = false;
}

</script>


<template>
	<body>
		<div class="createQuestion-page">
			<h1>Create a question to your quiz</h1>
			<div class="question-table">
				<table class="table">
					<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Question</th>
						<th scope="col">Action</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th scope="row">1</th>
						<td>What is Vue?</td>
						<td>
							<button class="play-btn">View</button>
							<button class="edit-btn">Edit</button>
							<button class="delete-btn"> Delete</button>
						</td>
					</tr>

					</tbody>
				</table>

				<Teleport to="body">
					<NewQuestionModel :show="showNewQuestionModal" @close="destroyModal">

					</NewQuestionModel>
				</Teleport>

			</div>

			<button @click="createQuestion" class="create-question-btn"> Create Question </button> <br>
			<button class="save-question-btn"> SAVE QUIZ </button>
		</div>
	</body>

</template>

<style>
.createQuestion-page{
	padding: 20px;
}
.question-table{
	align-items: stretch;
	width: 100%;
	background-color: #b0b0b0;
}

.play-btn{
	height: 25px;
	padding: 5px;
	text-decoration: none;
	background-color: #242F40;
	border-color: transparent;
	color: #FFFFFF;
}
.edit-btn{
	height: 25px;
	padding: 5px;
	text-decoration: none;
	background-color: #CCA43B;
	border-color: transparent;
	color: #242F40;
}
.delete-btn{
	height: 25px;
	padding: 5px;
	text-decoration: none;
	background-color: #cc513b;
	border-color: transparent;
	color: #242F40;
}
.create-question-btn{
	padding: 20px;
	margin: 10px;
	background-color: #242F40;
	color: #F2F2F2;
}
.save-question-btn{
	padding: 20px;
	margin: 10px;
	background-color: #CCA43B;

}

</style>