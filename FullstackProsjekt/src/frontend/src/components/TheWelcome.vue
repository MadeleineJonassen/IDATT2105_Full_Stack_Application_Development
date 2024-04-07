<script>
import axios from "axios";
export default {
	name: 'Home',
	async created() {
		const response = await axios.get('user');
		console.log(response)
	}
}
</script>


<template>
	<body>
	<section class ="header">
		<div class="text-box">
			<img id="logo" src="../components/icons/brain.png"/>
			<h1 class="heading">BrainStormer</h1>
			<p> Get ready to challenge your knowledge and have fun! <br> Experience a new way of learning </p>
			<router-link to="/dashboard" class="hero-btn">LOOK AT QUIZZES</router-link>
		</div>
	</section>

	<!----- Info ----->
	<section class="info">
		<h1>Ace your classes with our new learning platform</h1>
		<p>Discover deeper learning with harder difficulties</p>

		<div class="row">
			<div class="course-col">
				<h3>Easy</h3>
				<p>Suitable for beginners or those with basic knowledge on the topic.</p>
			</div>
			<div class="course-col">
				<h3>Medium</h3>
				<p>Suitable for intermediate users with some experience on the topic.</p>
			</div>
			<div class="course-col">
				<h3>Hard</h3>
				<p>Suitable for advanced users or experts on the topic. </p>
			</div>
		</div>
	</section>
	<div class="space"> </div>

	<section class="cta">
		<h1> Include and engage every student </h1>
		<router-link to="/about" class="hero-btn"> About us</router-link>
	</section>

	<footer class="footer">
		<div class="footer-content">
			<p>&copy; 2024 BrainStormer. All rights reserved.</p>
			<ul>
				<li><router-link to="/about">About us</router-link></li>
				<li><router-link to="/feedback">Contact Us</router-link></li>
			</ul>
		</div>
	</footer>

	</body>
</template>



<style scoped>
.header{
	min-height: 100vh;
	width: 100%;
	background-image: linear-gradient(rgba(4,9,30,0.7), rgba(4,9,30,0.7)),url(photos/mountain-backdrop.png);
	background-position: center;
	background-size: cover;
	position: relative;
}
#logo{
	height: 170px;
	width: 170px;
	padding: 5vh;
}

.text-box{
	width: 90%;
	color: #fff;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%,-50%);
	text-align: center;
}
.text-box h1{
	font-size: 60px;
}
.text-box p{
	margin: 10px 0 40px;
	font-size: 20px;
	color: #fff;
}

.hero-btn{
	display: inline-block;
	text-decoration: none;
	color: #fff;
	border: 1px solid #fff;
	padding: 12px 34px;
	font-size: 16px;
	background: transparent;
	position: relative;
	cursor: pointer;
}
.hero-btn:hover{
	border: 1px solid #CCA43B;
	background: #CCA43B;
	transition: 1s;
	color: #242F40;
}

/* Info section */
.info{
	width: 80%;
	margin: auto;
	text-align: center;
	padding-top: 100px;
}
h1{
	font-size: 36px;
	font-weight: 600;
}
p{
	color: #0f1412;
	font-size: 16px;
	font-weight: 300;
	line-height: 22px;
	padding: 10px;
}

h3{
	text-align: center;
	font-weight: 600;
	margin: 10px 0;
}

/* CTA */
.cta{
	margin: 100px auto;
	width: 80%;
	background-image: linear-gradient(rgba(4,9,30,0.7), rgba(4,9,30,0.7)),url(photos/office-backdrop.png);
	background-position: center;
	background-size: cover;
	border-radius: 10px;
	text-align: center;
	padding: 100px 0;
}
.cta h1{
	color: #F2F2F2;
	margin-bottom: 40px;
	padding: 0;
}

/* Footer */
.footer {
	background-color: #242F40;
	padding: 10px 0;
	text-align: center;
}
.footer-content {
	max-width: 1200px;
	margin: 0 auto;
}
.footer p {
	margin-bottom: 10px;
}
.footer ul {
	list-style: none;
	padding: 0;
}
.footer ul li {
	display: inline-block;
	margin-right: 20px;
}
.footer ul li:last-child {
	margin-right: 0;
}
.footer ul li a {
	color: #fff;
	text-decoration: none;
}
.footer ul li a:hover {
	text-decoration: underline;
}


/* Media for other devices */
@media (max-width: 700px){
	.text-box h1{
		font-size: 42px;
		transition: 1s;
	}
	.cta h1{
		font-size: 24px;
	}
}

</style>
