

<script>
import Svg from "@/assets/Svg.vue";
import Modal from "@/components/shared/modal/Modal.vue"
import {ref} from 'vue'


export default {
	components: {Modal, Svg},
	data() {
		return {
			showModal: ref(false),
			user: {
				username: this.username,
				avatar: '@/components/photos/developers/MadJon.png',
			},
			userQuizzes: [
				{
					id: 1,
					title: 'Math Quiz',
					description: 'Test your math skills with this quiz'
				},
				{
					id: 2,
					title: 'Science Quiz',
					description: 'Explore various science topics in this quiz'
				}
			]
		}
	},
	computed: {
		quizAttempts() {
			// Mock data for quiz attempts (replace with actual data)
			return [
				{ id: 1, quizTitle: 'Math Quiz', score: '80%', date: '2024-04-05' },
				{ id: 2, quizTitle: 'Science Quiz', score: '90%', date: '2024-04-04' }
				// Add more quiz attempt objects as needed
			];
		}
	},
	methods:{
		logout(){
			alert("Clicked")
		}
	}
};
</script>

<template>
	<body>
	<div class="profile">
		<!-- User information section -->
		<section class="user-info">
			<h2>User Profile</h2>
			<div class="user-details">
				<Svg  class="profile-pic"  name="default-avatar"/>
				<!-- <img :src="user.avatar" alt="User Avatar">  -->
				<div>
					<h3>{{ user.username }}</h3>
				</div>
			</div>
		</section>

		<!-- Quizzes created by the user -->
		<section class="user-quizzes">
			<h2>My Quizzes</h2>
			<div v-if="userQuizzes.length === 0">
				<p>No quizzes created yet.</p>
			</div>
			<div v-else>
				<div class="quiz" v-for="quiz in userQuizzes" :key="quiz.id">
					<h3>{{ quiz.title }}</h3>
					<p>{{ quiz.description }}</p>
					<!-- Add more details about each quiz as needed -->
				</div>
			</div>
		</section>

		<section class="progress-tracking">
			<h2>Progress Tracking</h2>
			<div v-if="quizAttempts.length === 0">
				<p>No quiz attempts yet.</p>
			</div>
			<div v-else>
				<div class="quiz-attempt" v-for="attempt in quizAttempts" :key="attempt.id">
					<h3>{{ attempt.quizTitle }}</h3>
					<p>Score: {{ attempt.score }}</p>
					<p>Date: {{ attempt.date }}</p>
					<!-- Add more details about each quiz attempt as needed -->
				</div>
			</div>
		</section>


		<!-- Other profile-related features -->
		<section class="profile-options">
			<h2>Profile Options</h2>
			<ul>
				<li><router-link to="/edit-profile">Edit Profile</router-link></li>
				<li><router-link to="/change-password">Change Password</router-link></li>
				<li><router-link to="/overviewQuiz">Create quiz</router-link></li>
				<li> <button class="delete-btn" @click="showModal = true">Logout</button>
					<Teleport to="body">
					<Modal :show="showModal" @close="destroyModal">

					</Modal>
				</Teleport></li>
			</ul>
		</section>

	</div>
	</body>




</template>

<style scoped>
.profile {
	max-width: 800px;
	margin: 0 auto;
	padding: 30px;
}

.profile-pic{
	height: 150px;
	width: 150px;
	margin-right: 5vh;
}

.user-info, .user-quizzes, .profile-options {
	margin-bottom: 40px;
}

.user-details {
	display: flex;
	align-items: center;
}

.user-details img {
	width: 100px;
	height: 100px;
	border-radius: 50%;
	margin-right: 20px;
}

.user-details div {
	flex: 1;
}

.user-quizzes .quiz {
	border: 1px solid #ccc;
	background-color: #d7d7d7 ;
	border-radius: 5px;
	padding: 10px;
	margin-bottom: 10px;
	cursor: pointer;
}

.profile-options ul {
	list-style: none;
	padding: 0;
}

.profile-options ul li {
	margin-bottom: 10px;
}

.profile-options ul li a {
	text-decoration: none;
	color: #CCA43B;
}

.progress-tracking {
	margin-bottom: 40px;
}

.progress-tracking .quiz-attempt {
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 10px;
	margin-bottom: 10px;
}

</style>
