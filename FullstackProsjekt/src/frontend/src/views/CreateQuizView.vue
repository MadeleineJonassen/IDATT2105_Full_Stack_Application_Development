<script>

</script>


<template>
	<body>
	<h1>Create a question to your quiz</h1>
	<div class="question-table">
		<table class="table">
			<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Question</th>
				<th scope="col">Action</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th scope="row">1</th>
				<td>What is Vue?</td>
				<td>
					<button> View</button>
					<button> Edit</button>
					<button> Delete</button>
				</td>
			</tr>
			<tr>
				<th scope="row">2</th>
				<td>Is this question 2?</td>
				<td>
					<button> View</button>
					<button> Edit</button>
					<button> Delete</button>
				</td>
			</tr>
			</tbody>
		</table>
	</div>

	<button> Create Question </button> <br>
	<button> SAVE QUIZ </button>

	</body>

</template>

<style>
.question-table{
	align-items: stretch;
	width: 100%;
	background-color: #b0b0b0;

}
</style>