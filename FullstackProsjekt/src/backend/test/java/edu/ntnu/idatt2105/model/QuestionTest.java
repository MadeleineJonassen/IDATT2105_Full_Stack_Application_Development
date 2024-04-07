package edu.ntnu.idatt2105.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class QuestionTest {

    private Question question;
    private Quiz quiz;

    @BeforeEach
    public void setUp() {
        // Create a sample question

        quiz = new Quiz("Test quiz", new User() , QuizCategory.CULTURE_AND_TRADITIONS, QuizDifficulty.EASY);
        question = new Question("What is the capital of Norway?", QuestionType.MULTIPLE_CHOICE, 10, "Oslo", "Oslo*Bergen*Trondheim*Stavanger");
        question.setId(1);
        question.setQuiz(quiz);
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        assertEquals("What is the capital of Norway?", question.getQuestionText());
        assertEquals(10, question.getScore());
        assertEquals(QuestionType.MULTIPLE_CHOICE, question.getType());
        assertEquals("Oslo", question.getAnswer());
        assertEquals("Oslo*Bergen*Trondheim*Stavanger", question.getOptions());

        // Test setters
        question.setQuestionText("What is the capital of Sweden?");
        assertEquals("What is the capital of Sweden?", question.getQuestionText());

        question.setScore(5);
        assertEquals(5, question.getScore());

        question.setType(QuestionType.TRUE_OR_FALSE);
        assertEquals(QuestionType.TRUE_OR_FALSE, question.getType());

        question.setAnswer("Stockholm");
        assertEquals("Stockholm", question.getAnswer());

        question.setOptions("True*False");
        assertEquals("True*False", question.getOptions());
    }

    @Test
    public void testGetOptionsList() {
        // Test options list
        assertNotNull(question.getOptionsList());
        assertEquals(4, question.getOptionsList().size());
        assertEquals("Oslo", question.getOptionsList().get(0));
        assertEquals("Bergen", question.getOptionsList().get(1));
        assertEquals("Trondheim", question.getOptionsList().get(2));
        assertEquals("Stavanger", question.getOptionsList().get(3));
    }

    @Test
    public void testGetId() {
        // Test getId method
        assertEquals(1, question.getId());
    }

    @Test
    public void testGetQuiz() {
        // Test getQuiz method
        assertEquals(quiz, question.getQuiz());
    }
}
