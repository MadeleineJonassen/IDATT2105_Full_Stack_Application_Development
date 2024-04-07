<script>
import Svg from "@/assets/Svg.vue";
import {setToken} from "@/tokenController.js";
import {apiClient} from "@/api.js";

export default {
	name: 'Login',
	components: {Svg},
	data() {
		return {
      username: '',
			email: '',
			password: '',
			showPassword: false,
      errorMsg: '',
		}
	},
	methods: {
		async handleSubmit() {
      try {
        await apiClient.post('/auth/login', {
          username: this.username,
          password: this.password
        }).then(response => {
					alert(this.username + " is now logged in!")
          setToken(response.data.jwt); //TODO: check token name
	        localStorage.setItem('username', this.username);
	        this.$router.push('/profile');
        });
      } catch (error) {
        //TODO: proper error handling
        this.errorMsg = 'Error logging in, try again';
      }
		},
		togglePasswordVisibility() {
			this.showPassword = !this.showPassword;
		}
	}
}
</script>

<template>
	<body>
	<div class="loginPage">
		<form @submit.prevent="handleSubmit">
			<div class="login">
				<h1 id="login">Login</h1>
				<p> Sign in to your already existing account</p>

				<div class="loginBox">
          <label>Username</label> <br>
          <input type="text" required v-model="username" placeholder="Rizz_Dragon420"/> <br>

					<label>Password</label> <br>
					<div class="password-input">
						<input :type="showPassword ? 'text' : 'password'" required v-model="password"/>
						<button type="button" class="showPasswordIcon" @click="togglePasswordVisibility">
							<Svg v-if="showPassword" :name="'hide-password-icon'"/>
							<Svg v-else :name="'show-password-icon'" />
						</button>
					</div> <br>

          <label class="error-message">{{errorMsg}}</label><br>
          <p> Don't have a account?</p><router-link to="/signup" id="signUpLink">SIGNUP!</router-link>
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
.loginPage {
	padding-top: 85px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.login {
	text-align: center;
	padding: 20px;
	border-radius: 15px;
	align-items: center;
	border-style: solid;
	margin: 20px;
	max-width: 450px;
}

.loginBox {
	padding: 40px;
}

.password-input {
	position: relative;
	display: inline-block;
	min-width: 300px;
}


.showPasswordIcon {
	position: absolute;
	top: 50%;
	right: 10px;
	transform: translateY(-50%);
	border: none;
	background: none;
	cursor: pointer;
}
#signUpLink {
	color: #CCA43B;
	padding: 10px;
	font-size: 16px;
	text-decoration: none;
}
#signUpLink:hover{
	color: #242F40;
	transition: 0.3s;
}

.submit-section {
	display: flex;
	justify-content: center;
	align-items: center;
}

@media (max-width: 700px) {


}

</style>
