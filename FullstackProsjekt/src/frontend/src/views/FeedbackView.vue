
<script>
import Svg from '../assets/Svg.vue'

export default {
	data() {
		return {
			name: this.name,
			email: this.email,
			comment: ""
		};
	},
	computed: {
		isFormFilled() {
			return this.name && this.email && this.comment;
		}
	},
	methods: {
		async handleSubmit(e) {
			e.preventDefault();
			try {
				const response = await fetch('http://localhost:3001/feedbacks', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({
						name: this.name,
						email: this.email,
						comment: this.comment
					})
				});
				if (response.ok) {
					this.status = "Success";
					localStorage.removeItem('feedbackForm');
					this.name = this.name;
					this.email = this.email;
					this.comment = "";
					alert("Thank you for your feedback!");
				} else {
					throw new Error('Failed to submit feedback');
				}
			} catch (error) {
				this.status = "Error";
				console.error('Error submitting feedback:', error);
			}
		}
	},
	mounted() {
		// Restore form data from local storage
		const formData = JSON.parse(localStorage.getItem('feedbackForm'));
		if (formData) {
			this.name = formData.name;
			this.email = formData.email;
			this.comment = formData.comment;
		}
	}
};
</script>


<template>
	<body>
	<div class="feedback-page">
	<form @submit.prevent="handleSubmit">
		<div class="feedback">
			<h1>Feedback</h1>
			<p>Is there anything you would like to tell us? Comment below!</p>
			<div class="contactBox">
				<label>Name</label>
				<input type="text" required v-model="name" ref="nameInput"/> <br>
				<label>Email</label>
				<input type="email" required v-model="email" ref="emailInput"/> <br>
				<textarea id="comment" required v-model="comment" placeholder="Comment" ref="commentInput"></textarea>
			</div>
		</div>
		<div class="submit-section">
			<input id="submit" type="submit" :class="{ 'hoverable': isFormFilled }" :disabled="!isFormFilled"/>
		</div>
	</form>
	</div>
	</body>
</template>


<style>
.feedback-page{
	padding-top: 85px;
	display: flex;
	justify-content: center;
	align-items: center;}

.feedback {
	text-align: center;
	color: #242F40;
	border-color: #363636;
	padding: 20px;
	border-radius: 15px;
	align-items: center;
	border-style: solid;
}

.contactBox {
	padding: 30px;
}

label {
	display: inline-block;
	margin-right: 10px;
	font-weight: bold;
}

input {
	padding: 5px;
	border-radius: 5px;
	border: none;
	min-width: 300px;
	background-color: #E5E5E5;
	margin: 10px;
}

input::placeholder, textarea::placeholder {
	color: #b0b0b0;
}

textarea {
	margin: 5px;
	padding: 8px;
	border-radius: 5px;
	border: none;
	min-width: 400px;
	height: 100px;
	background-color: #E5E5E5;
}

.submit-section {
	display: flex;
	justify-content: center;
	align-items: center;
}

#submit {
	min-width: 150px;
	min-height: 60px;
	font-size: 24px;
	border-radius: 6px;
	background-color: #242F40;
	color: white;
	border: none;
	cursor: pointer;
	margin-top: 20px;
}

#submit:hover:enabled {
	background-color: rgba(23, 55, 44, 0.9);
	transition: 0.5s;
}

</style>
