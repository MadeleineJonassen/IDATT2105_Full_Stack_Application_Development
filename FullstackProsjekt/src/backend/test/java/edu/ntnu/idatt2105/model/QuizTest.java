package edu.ntnu.idatt2105.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QuizTest {

    private Quiz quiz;
    private User user;

    @BeforeEach
    public void setUp() {
        // Create a sample user
        user = new User("testuser", "password", null);

        // Create a sample quiz
        quiz = new Quiz("Test Quiz", user, QuizCategory.COMPUTER_SCIENCE, QuizDifficulty.EASY);
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        assertEquals("Test Quiz", quiz.getTitle());
        assertEquals(user, quiz.getCreator());
        assertEquals(QuizCategory.COMPUTER_SCIENCE, quiz.getCategory());
        assertEquals(QuizDifficulty.EASY, quiz.getDifficulty());

        // Test setters
        quiz.setTitle("Updated Quiz");
        assertEquals("Updated Quiz", quiz.getTitle());

        User newUser = new User("newuser", "password", null);
        quiz.setCreator(newUser);
        assertEquals(newUser, quiz.getCreator());

        quiz.setCategory(QuizCategory.MATH);
        assertEquals(QuizCategory.MATH, quiz.getCategory());

        quiz.setDifficulty(QuizDifficulty.HARD);
        assertEquals(QuizDifficulty.HARD, quiz.getDifficulty());
    }
}
