export default class Question {
    constructor(questionId, quizId, score, questionText, answers) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.score = score;
        this.questionText = questionText;
        this.answers = answers;
    }
}

//method for parsing JSON to question-objects, and opposite? Perhaps better to delegate to a JSON parsing class