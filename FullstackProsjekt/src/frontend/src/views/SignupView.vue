
<script>
	import Svg from '../assets/Svg.vue'
  import {apiClient} from "@/api.js";
	export default {
		name: 'Register',
		data(){
			return{
				first_name:'',
				last_name:'',
				email:'',
				password:'',
				password_confirm:'',
        errorMsg: '', //TODO: display error to user
			}
		},
		methods:{
			async handleSubmit(e) {
        //TODO: use interceptor to check matching password, send one password
        try {
          await apiClient.post('/api/auth/register', {
            first_name: this.first_name,
            last_name: this.last_name,
            email: this.email,
            password: this.password
            //password_confirm: this.password_confirm
          }).then(response => {
            this.$router.push('/login')
          });
        } catch (error) {
          //TODO: proper error handling
          this.errorMsg = 'Error signing up';
        }
			}
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
					<label>First Name</label> <br>
					<input type="text" required v-model="first_name" placeholder="John"/> <br>
					<label>Last Name</label> <br>
					<input type="text" required v-model="last_name" placeholder="Doe"/> <br>
					<label>Email</label> <br>
					<input type="email" required v-model="email" placeholder="JohnDoe@email.com"/> <br>
					<label>Password</label> <br>
					<input type="text" required v-model="password" /> <br>
					<label>Confirm Password</label> <br>
					<input type="text" required v-model="password_confirm" /> <br>
				</div>
			</div>
			<div class="submit-section">
				<input id="submit" type="submit"/>
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
	padding: 30px;
}

label {
	display: inline-block;
	font-weight: bold;
}
input {
	padding: 5px;
	border-radius: 5px;
	border: none;
	min-width: 250px;
	background-color: #E5E5E5;
	margin: 10px;
}
input::placeholder {
	color: #b0b0b0;
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
