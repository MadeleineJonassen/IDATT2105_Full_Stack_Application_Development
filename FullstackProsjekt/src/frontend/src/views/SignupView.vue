
<script>
</script>



<template>
	<form  @submit.prevent="handleSubmit">
		<div class="signup">
			<h1 id="signup">Signup</h1>
			<div class="signupBox">
				<label>First Name</label>
				<input type="text" required v-model="fName" ref="fNameInput"/> <br>
				<label>Last Name</label>
				<input type="text" required v-model="lName" ref="lNameInput"/> <br>
				<label> Email </label>
				<input type="email" required v-model="email" ref="emailInput"/> <br>
				<label>Password</label>
				<input type="password" required v-model="password" ref="passwordInput"/> <br>
			</div>
		</div>
		<div class="submit-section">
			<input id="submit" type="submit"/>
		</div>
	</form>
</template>

<style>
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

input::placeholder, textarea::placeholder {
	color: #242F40;
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
