package edu.ntnu.idatt2105.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuestionAnswerTest {

    private QuestionAnswer questionAnswer;
    private Question question;

    @BeforeEach
    public void setUp() {
        // Create a sample question
        question = new Question("What is the capital of Norway?", QuestionType.MULTIPLE_CHOICE, 10, "Oslo", "Oslo*Bergen*Trondheim*Stavanger");

        // Create a sample question answer
        questionAnswer = new QuestionAnswer(question, "Oslo");
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        assertEquals(question, questionAnswer.getQuestion());
        assertEquals("Oslo", questionAnswer.getGivenAnswer());

        // Test setters
        Question newQuestion = new Question("What is the capital of Sweden?", QuestionType.MULTIPLE_CHOICE, 10, "Stockholm", "Stockholm*Oslo*Copenhagen*Helsinki");
        questionAnswer.setQuestion(newQuestion);
        assertEquals(newQuestion, questionAnswer.getQuestion());

        questionAnswer.setGivenAnswer("Stockholm");
        assertEquals("Stockholm", questionAnswer.getGivenAnswer());
    }

    @Test
    public void testIsCorrect() {
        // Test isCorrect method with correct answer
        assertTrue(questionAnswer.isCorrect());

        // Change the given answer and test again
        questionAnswer.setGivenAnswer("Bergen");
        assertFalse(questionAnswer.isCorrect());
    }

    @Test
    public void testGetQuizResult() {
        // Test getQuizResult method
        assertNull(questionAnswer.getQuizResult());

        // Set a quiz result and test again
        QuizResult quizResult = new QuizResult();
        questionAnswer.setQuizResult(quizResult);
        assertEquals(quizResult, questionAnswer.getQuizResult());
    }
}
