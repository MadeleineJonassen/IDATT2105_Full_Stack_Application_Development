<template>
	<body>
		<div class="feedback-page">
			<form @submit.prevent="handleSubmit">
				<div class="feedback">
					<h1>Feedback</h1>
					<p>Is there anything you would like to tell us? Comment below!</p>
					<div class="contactBox">
						<label>Name</label> <br>
						<input class="feedback-input" type="text" required v-model="name" ref="nameInput"/> <br>
						<label>Email</label> <br>
						<input class="feedback-input" type="email" required v-model="email" ref="emailInput"/>

						<br>
						<br>
						<br>
						<br>

						<label>Title</label> <br>
						<input class="feedback-input" type="text" required v-model="title" ref="title"/> <br>
						<textarea class="comment" required v-model="comment" placeholder="Tell us whats on your mind!" ref="commentInput"></textarea>
					</div>
				</div>

				<div class="submit-section">
					<input class="submit-btn" type="submit"/>
				</div>

			</form>
		</div>
	</body>
</template>


<script>
import Svg from "@/assets/Svg.vue";

export default {
	components: {Svg},
	data() {
		return {
			name: this.name,
			email: this.email,
			comment: ""
		};
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
						title: this.title,
						comment: this.comment
					})
				});
				if (response.ok) {
					this.status = "Success";
					localStorage.removeItem('feedbackForm');
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
		const formData = JSON.parse(localStorage.getItem('feedbackForm'));
		if (formData) {
			this.name = formData.name;
			this.email = formData.email;
			this.title = formData.title;
			this.comment = formData.comment;
		}
	}
};
</script>


<style>
.feedback-page{
	padding: 10vh;
	display: flex;
	justify-content: center;
	align-items: center;
}
.feedback {
	text-align: center;
	color: var(--text-dark-color);
	border-color: transparent;
	border-radius: 15px;
	align-items: center;
	border-style: solid;
}
.contactBox {
	padding: 30px;
}
.feedback-input{
	width: 50vh;
}
.comment {
	padding: 8px;
	border-radius: 5px;
	border: none;
	width: 50vh;
	height: 15vh;
	background-color: var(--field-background);
}
.submit-section {
	display: flex;
	justify-content: center;
	align-items: center;
}

	@media (max-width: 700px){
		.feedback-input,
		.comment {
			width: 90%;
		}
	}
</style>
