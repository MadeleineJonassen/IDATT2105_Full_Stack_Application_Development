export default class Quiz {
    constructor(quizId, title, creatorId, category, difficulty) {
        this.quizId = quizId;
        this.title = title;
        this.creatorId = creatorId;
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
