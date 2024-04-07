package edu.ntnu.idatt2105.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QuizResultTest {

    private QuizResult quizResult;
    private Quiz quiz;
    private User user;

    @BeforeEach
    public void setUp() {
        // Create a sample quiz
        quiz = new Quiz("Test Quiz", null, QuizCategory.SCIENCE, QuizDifficulty.EASY);

        // Create a sample user
        user = new User("testuser", "password", null);

        // Create a sample quiz result
        LocalDateTime startedAt = LocalDateTime.now();
        quizResult = new QuizResult(quiz, user, "Completed", startedAt, startedAt.plusMinutes(10),0);
        quizResult.setId(1);
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        assertEquals(quiz, quizResult.getQuiz());
        assertEquals(user, quizResult.getUser());
        assertEquals("Completed", quizResult.getStatus());
        assertEquals(0, quizResult.getTotalScore());
        assertNotNull(quizResult.getStartedAt());
        assertNotNull(quizResult.getCompletedAt());

        // Test setters
        Quiz newQuiz = new Quiz("New Quiz", null, QuizCategory.MATH, QuizDifficulty.HARD);
        quizResult.setQuiz(newQuiz);
        assertEquals(newQuiz, quizResult.getQuiz());

        User newUser = new User("newuser", "password", null);
        quizResult.setUser(newUser);
        assertEquals(newUser, quizResult.getUser());

        quizResult.setStatus("InProgress");
        assertEquals("InProgress", quizResult.getStatus());

        quizResult.setTotalScore(15);
        assertEquals(15, quizResult.getTotalScore());

        LocalDateTime newCompletedAt = LocalDateTime.now();
        quizResult.setCompletedAt(newCompletedAt);
        assertEquals(newCompletedAt, quizResult.getCompletedAt());
    }

    @Test
    public void testIdGeneration() {
        // Test id generation
        assertNotNull(quizResult.getId());
    }
}
