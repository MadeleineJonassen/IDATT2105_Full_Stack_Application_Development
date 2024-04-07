

<script>
import Svg from "@/assets/Svg.vue";
import Modal from "@/components/shared/modal/Modal.vue"
import {ref} from 'vue'
import {getIdByToken} from "@/tokenController.js";
import {apiClient} from "@/api.js";


export default {
	components: {Modal, Svg},
	data() {
		return {
			userId: null,
			quizList:[],
			showModal: ref(false),
			isLoggedIn: true,
			user: {
				username: '',
			}
		};
	},
	mounted() {
		this.user.username = localStorage.getItem('username');
		this.populateQuizzes();
	},
	computed: {
		quizAttempts() {
			// Mock data for quiz attempts (replace with actual data)
			return [
				{ id: 1, quizTitle: 'Math Quiz', score: '80%', date: '2024-04-05' },
				{ id: 2, quizTitle: 'Science Quiz', score: '90%', date: '2024-04-04' }
			];
		}
	},
	methods:{
		logout(){
			this.isLoggedIn = false;
			this.$router.push('/login');
		},
		closeModal(){
			this.showModal=false;
		},
		async populateQuizzes() {
			try {
				await this.setUserId();
				const response = await apiClient.get('/quiz/creator/' + this.userId);
				this.quizList = response.data;
			} catch (error) {
				console.error('Error retrieving quizzes:', error);
			}
		},
		async setUserId() {
			this.userId = await getIdByToken();
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
			<div class="user-details" v-if="!loading">
				<Svg class="profile-pic" name="default-avatar"/>
				<div>
					<h3> Username: </h3>
					<h4>{{ user.username }} </h4>
				</div>
			</div>

		</section>

		<!-- Quizzes created by the user -->
		<section class="user-quizzes">
			<h2>My Quizzes</h2>
			<div v-if="quizList.length > 0">
				<div v-for="quiz in quizList" :key="quiz.id">
					<p>{{ quiz.name }}</p>
				</div>
			</div>
			<div v-else>
				<p>No quizzes found.</p>
				<router-link to="/create-quiz" class="add-Btn"> Create quiz now!</router-link>
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
				<li> <button class="delete-btn" @click="showModal = true">Logout</button>
					<Teleport to="body">
					<Modal :show="showModal">
						<template #header>
							<h1> You are about to sign out!</h1>
						</template>
						<template #body>
							<p>Are you sure?</p>
						</template>
						<template #footer>
							<button @click="logout" class="add-Btn"> Yes </button>
							<button @click="closeModal" class="close-btn"> No </button>
						</template>
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
.profile-options ul li a:hover{
	color: #a2822e;
	text-decoration: underline;
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
