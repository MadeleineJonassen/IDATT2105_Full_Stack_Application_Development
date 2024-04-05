export default class Quiz {
    constructor(quizId, title, creatorId, questions, category, difficulty) {
        this.quizId = quizId;
        this.title = title;
        this.creatorId = creatorId;
        this.questions = questions;
        this.category = category;
        this.difficulty = difficulty;
    }




}

/**
 QuizDTO:
 Integer id;
 String title;
 List<Integer> questionIds;
 Integer creatorId;
 QuizCategory category;
 QuizDifficulty difficulty;
 */
