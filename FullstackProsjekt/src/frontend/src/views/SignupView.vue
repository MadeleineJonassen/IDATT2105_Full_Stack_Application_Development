<template>
	<body>
	<div class="signupPage">
		<form  @submit.prevent="handleSubmit">
			<div class="signup">
				<h1 id="signup">Signup</h1>
				<p> Create an account to get started!</p>
				<div class="signupBox">
          <label>Username</label> <br>
          <input type="text" required v-model="username" placeholder="Insert username here..."/> <br>

					<label>Password</label> <br>
					<div class="password-input">
						<input :type="showPassword ? 'text' : 'password'" required v-model="password"/>
						<button type="button" class="showPasswordIcon" @click="togglePasswordVisibility">
							<Svg v-if="showPassword" :name="'hide-password-icon'"/>
							<Svg v-else :name="'show-password-icon'" />
						</button>
					</div> <br>

					<label>Confirm Password</label> <br>
					<div class="password-input">
						<input :type="showPassword ? 'text' : 'password'" required v-model="password_confirm"/>
						<button type="button" class="showPasswordIcon" @click="togglePasswordVisibility">
							<Svg v-if="showPassword" :name="'hide-password-icon'"/>
							<Svg v-else :name="'show-password-icon'" />
						</button>
					</div> <br>

					<label class="error-message">{{errorMsg}}</label><br>

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
import Svg from '../assets/Svg.vue'
import {apiClient} from "@/api.js";
export default {
	name: 'Register',
	components: {Svg},
	data(){
		return{
			username: '',
			password:'',
			password_confirm:'',
			errorMsg: '',
			showPassword: false,
		}
	},
	methods:{
		async handleSubmit() {
			try {
				await apiClient.post('/auth/register', {
					username: this.username,
					password: this.password,
				}).then(response => {
					alert("User: " + this.username + " created!")
					this.$router.push('/login')
				});
			} catch (error) {
				this.errorMsg = 'Error signing up, try again';
			}
		},
		togglePasswordVisibility() {
			this.showPassword = !this.showPassword;
		},
	}
}
</script>


<style>
.signupPage {
	padding-top: 10vh;
	display: flex;
	justify-content: center;
	align-items: center;
}
.signup {
	text-align: center;
	padding: 20px;
	border-radius: 15px;
	align-items: center;
	border-style: solid;
}
.signupBox {
	padding: 20px;
}
.password-input {
	position: relative;
	display: inline-block;
	min-width: 300px;
}
.showPasswordIcon {
	position: absolute;
	top: 50%;
	right: 0;
	transform: translateY(-50%);
	border: none;
	background: none;
	cursor: pointer;
}
.submit-section{
	margin-top: 5vh;
}
</style>
