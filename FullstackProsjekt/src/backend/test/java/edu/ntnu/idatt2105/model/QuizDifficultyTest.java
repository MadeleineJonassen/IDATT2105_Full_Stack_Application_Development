package edu.ntnu.idatt2105.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuizDifficultyTest {

    @Test
    public void testEnumValues() {
        assertEquals("EASY", QuizDifficulty.EASY.name());
        assertEquals("MEDIUM", QuizDifficulty.MEDIUM.name());
        assertEquals("HARD", QuizDifficulty.HARD.name());

        assertEquals(3, QuizDifficulty.values().length);
        assertEquals(QuizDifficulty.EASY, QuizDifficulty.valueOf("EASY"));
        assertEquals(QuizDifficulty.MEDIUM, QuizDifficulty.valueOf("MEDIUM"));
        assertEquals(QuizDifficulty.HARD, QuizDifficulty.valueOf("HARD"));
    }
}
