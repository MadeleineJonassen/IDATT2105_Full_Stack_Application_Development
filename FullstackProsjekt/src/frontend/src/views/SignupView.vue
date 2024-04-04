
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
        errorMsg: '', //TODO: display error to user
				showPassword: false,
			}
		},
		methods:{
			async handleSubmit() {
        //TODO: use interceptor to check matching password, send one password
        try {
          await apiClient.post('/auth/register', {
            username: this.username,
            password: this.password,
            password_confirm: this.password_confirm
          }).then(response => {
            //TODO: display successful registration to user
	          alert("User: " + this.username + " created!")
            this.$router.push('/login')
          });
        } catch (error) {
          //TODO: proper error handling
          this.errorMsg = 'Error signing up, try again';
        }
			},
			togglePasswordVisibility() {
				this.showPassword = !this.showPassword;
			},
		}
	}
</script>



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


<style>
.signupPage {
	padding-top: 85px;
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
	margin: 20px;
}

.signupBox {
	padding: 40px;
	min-width: 400px;
}

.password-input {
	position: relative;
	display: inline-block;
}

.showPasswordIcon {
	position: absolute;
	top: 50%;
	right: 5px; /* Adjust positioning as needed */
	transform: translateY(-50%);
	border: none;
	background: none;
	cursor: pointer;
}

.submit-section {
	display: flex;
	justify-content: center;
	align-items: center;
}

</style>
