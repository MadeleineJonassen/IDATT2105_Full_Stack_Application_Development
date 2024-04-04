<template>
	<body>
	<div class="about-section">
		<h1>About</h1>
		<p>Welcome to our quiz web application! We provide a comprehensive and user-friendly platform for creating, managing, and taking quizzes for educational, training, or entertainment purposes.</p>
		<div class="space"> </div>
		<h2>Our Features</h2>
		<div class="columns">
			<div class="column">
				<ul>
					<li>Quiz Creation: Create quizzes with various question types, difficulty levels, and multimedia elements.</li>
					<li>Tagging and Categorization: Organize your questions with difficulty level and categories.</li>
					<li>Search and Filter: Quickly find relevant questions based on categories and difficulty levels.</li>
					<li>Question Management: Add, edit, delete, and organize questions within quizzes.</li>
				</ul>
			</div>
			<div class="column">
				<ul>
					<li>Quiz Templates: Use pre-designed or customizable templates for easy quiz creation.</li>
					<li>Import and Export: Import questions from external sources and export quizzes in various formats.</li>
					<li>Scoring and Feedback: Automatically score quizzes and provide immediate feedback.</li>
					<li>Progress Tracking: Track your progress, view past quiz attempts, and monitor performance statistics.</li>
					<li>Feedback and Support: Provide feedback, report issues, and access customer support.</li>
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
					<p><button class="button">Contact</button></p>
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
					<p><button class="button">Contact</button></p>
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
					<p><button class="button">Contact</button></p>
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
					<p><button class="button">Contact</button></p>
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
.columns {
	display: flex;
	justify-content: space-between;
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
	margin: 8px;
}

.about-section {
	padding: 10vh 10vh 10vh 10vh;
	text-align: center;
	background-color: #858A93;
	color: #242F40;
}
.our-team{
	color: #242F40;
}

.container {
	padding: 0 16px;
	color: #242F40;
}

.title {
	color: grey;
}

.button {
	border: none;
	outline: 0;
	display: inline-block;
	padding: 8px;
	color: white;
	background-color: #363636;
	text-align: center;
	cursor: pointer;
	width: 100%;
	font-family: monospace;
}

.button:hover {
	background-color: #555;
}

#logo{
	width: 100%;
}

@media screen and (max-width: 650px) {
	.column {
		width: 100%;
		display: block;
	}
}
</style>
