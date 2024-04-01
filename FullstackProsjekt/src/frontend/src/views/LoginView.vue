<script>
import Svg from '../assets/Svg.vue'
import axios from "axios";

export default {
	name: 'Login',
	data() {
		return {
			email: '',
			password: '',
		}
	},
	methods:{
		async handleSubmit(){
			const response = await axios.post('login', {
				email: this.email,
				password: this.password
			});
			localStorage.setItem('token', response.data.token)

		}
	}
}
</script>


<template>
	<body >

	<div class="loginPage">
    <form @submit.prevent="handleSubmit">
      <div class="login">
        <h1 id="login">Login</h1>
	      <p> Sign in to your already existing account</p>
	      <div class="loginBox">
          <label>Email</label> <br>
          <input type="email" placeholder="JohnDoe@email.com" required v-model="email" /> <br>
          <label>Password</label> <br>
          <input type="password" required v-model="password" /> <br>
          <router-link to="/signup" id="signUpLink">SIGNUP</router-link>
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
    padding: 20px;
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
    min-width: 250px;
    background-color: #E5E5E5;
    margin: 10px;
  }

  input::placeholder {
    color: #b0b0b0;
  }

  #signUpLink{
	  color: #CCA43B;
	  padding: 10px;
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
