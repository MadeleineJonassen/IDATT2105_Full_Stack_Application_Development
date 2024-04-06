
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
						<input type="email" required v-model="email" ref="emailInput"/>

						<br>
						<br>
						<br>
						<br>

						<label>Title</label>
						<input type="text" required v-model="title" ref="title"/> <br>
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


<style>
.feedback-page{
	padding: 10vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

.feedback {
	text-align: center;
	color: #242F40;
	border-color: transparent;
	padding: 20px;
	border-radius: 15px;
	align-items: center;
	border-style: solid;
}

.contactBox {
	padding: 30px;
}


.comment {
	padding: 8px;
	border-radius: 5px;
	border: none;
	min-width: 40vh;
	height: 100px;
	background-color: #E5E5E5;
}


.submit-section {
	display: flex;
	justify-content: center;
	align-items: center;
}

</style>
