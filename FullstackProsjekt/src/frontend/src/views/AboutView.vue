<template>
	<body>
	<div class="about-section">
		<h1>About</h1>
		<p>Welcome to our quiz web application! We provide a comprehensive and user-friendly platform for creating, managing, and taking quizzes for educational, training, or entertainment purposes.</p>
		<div class="space"> </div>
	</div>

	<div>
		<div class="columns">
			<h2>Our Features</h2>

			<div class="column">
				<ul>
					<li><strong>Quiz Creation:</strong> Create quizzes with various question types, difficulty levels, and multimedia elements.</li>
					<li><strong>Tagging and Categorization:</strong> Organize your questions with difficulty level and categories.</li>
					<li><strong>Search and Filter:</strong> Quickly find relevant questions based on categories and difficulty levels.</li>
					<li><strong>Question Management:</strong> Add, edit, delete, and organize questions within quizzes.</li>
					<li><strong>Quiz Templates:</strong> Use pre-designed or customizable templates for easy quiz creation.</li>
					<li><strong>Import and Export:</strong> Import questions from external sources and export quizzes in various formats.</li>
					<li><strong>Scoring and Feedback:</strong> Automatically score quizzes and provide immediate feedback.</li>
					<li><strong>Progress Tracking:</strong> Track your progress, view past quiz attempts, and monitor performance statistics.</li>
					<li><strong>Feedback and Support:</strong> Provide feedback, report issues, and access customer support.</li>
				</ul>
			</div>
		</div>
	</div>


	<h1 class ="our-team" style="text-align:center">Our Team</h1>
	<div class="row">
		<div class="column">
			<div class="card">
				<img id="logo" src="../components/photos/developers/TorAnt.png" />
				<div class="container">
					<h2>Torbjørn Antonsen</h2>
					<p class="title">Developer</p>
					<p>2 year Bachelor in Computer Science at NTNU Trondheim </p>
					<p>torbjorn@ntnu.no</p>
				</div>
			</div>
		</div>

		<div class="column">
			<div class="card">
				<img id="logo" src="../components/photos/developers/HeiBra.png" />
				<div class="container">
					<h2>Heine Mærde Brakstad</h2>
					<p class="title">Developer</p>
					<p>2 year Bachelor in Computer Science at NTNU Trondheim</p>
					<p>heine@ntnu.no</p>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="column">
			<div class="card">
				<img id="logo" src="../components/photos/developers/KriKol.png" />
				<div class="container">
					<h2>Kristiane Skogvang Kolshus</h2>
					<p class="title">Developer</p>
					<p>2 year Bachelor in Computer Science at NTNU Trondheim</p>
					<p>kristiane@ntnu.no</p>
				</div>
			</div>
		</div>

		<div class="column">
			<div class="card">
				<img id="logo" src="../components/photos/developers/MadJon.png" />
				<div class="container">
					<h2>Madeleine Stenberg Jonassen</h2>
					<p class="title">Developer</p>
					<p>2 year Bachelor in Computer Science at NTNU Trondheim</p>
					<p>madelesj@ntnu.no</p>
				</div>
			</div>
		</div>
	</div>

	</body>
</template>


<script>
export default {
	name: 'About',
};
</script>


<style>
.about-section {
	background-image: linear-gradient(rgba(4,9,30,0.7), rgba(4,9,30,0.7)),url("@/components/photos/wheel-backdrop.png");
	padding: 20px;
}
.columns {
	display: flex;
	justify-content: space-between;
	margin: 10vh;
}
.column ul li {
	margin-bottom: 20px;
}
.column {
	flex: 1;
	margin-right: 20px;
}
.column p{
	font-size: 14px;
}
p {
	font-size: 20px;
}
li{
	font-size: 16px;
}
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	margin: 8px auto;
	padding: 10px;
	max-width: 400px;
}
.about-section {
	padding: 10vh 10vh 10vh 10vh;
	text-align: center;
	color: var(--text-light-color);
}
.our-team{
	color: var(--sidebar-bd-color);
}
.container {
	padding: 0 16px;
	color: var(--sidebar-bd-color);
}
.title {
	color: grey;
}
#logo{
	width: 100%;
}

@media screen and (max-width: 650px) {
	.column {
		width: 100%;
		display: block;
		max-width: 100%;
	}
}
</style>
