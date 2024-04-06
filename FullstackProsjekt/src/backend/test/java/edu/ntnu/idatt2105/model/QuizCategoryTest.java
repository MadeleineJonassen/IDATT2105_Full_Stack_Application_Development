package edu.ntnu.idatt2105.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuizCategoryTest {

    @Test
    public void testEnumValues() {
        assertEquals("BUSINESS", QuizCategory.BUSINESS.name());
        assertEquals("ART", QuizCategory.ART.name());
        assertEquals("COMPUTER_SCIENCE", QuizCategory.COMPUTER_SCIENCE.name());
        assertEquals("CULTURE_AND_TRADITIONS", QuizCategory.CULTURE_AND_TRADITIONS.name());
        assertEquals("FINANCE", QuizCategory.FINANCE.name());
        assertEquals("GENERAL_KNOWLEDGE", QuizCategory.GENERAL_KNOWLEDGE.name());
        assertEquals("GEOGRAPHY", QuizCategory.GEOGRAPHY.name());
        assertEquals("HISTORY", QuizCategory.HISTORY.name());
        assertEquals("LANGUAGES", QuizCategory.LANGUAGES.name());
        assertEquals("LAW", QuizCategory.LAW.name());
        assertEquals("MATH", QuizCategory.MATH.name());
        assertEquals("MUSIC", QuizCategory.MUSIC.name());
        assertEquals("SCIENCE", QuizCategory.SCIENCE.name());
        assertEquals("SEASONAL", QuizCategory.SEASONAL.name());
        assertEquals("SOCIAL_EMOTIONAL_LEARNING", QuizCategory.SOCIAL_EMOTIONAL_LEARNING.name());
        assertEquals("SOCIAL_STUDIES", QuizCategory.SOCIAL_STUDIES.name());
        assertEquals("TRIVIA", QuizCategory.TRIVIA.name());

        assertEquals(17, QuizCategory.values().length);
        assertEquals(QuizCategory.BUSINESS, QuizCategory.valueOf("BUSINESS"));
        assertEquals(QuizCategory.ART, QuizCategory.valueOf("ART"));
        assertEquals(QuizCategory.COMPUTER_SCIENCE, QuizCategory.valueOf("COMPUTER_SCIENCE"));
        assertEquals(QuizCategory.CULTURE_AND_TRADITIONS, QuizCategory.valueOf("CULTURE_AND_TRADITIONS"));
        assertEquals(QuizCategory.FINANCE, QuizCategory.valueOf("FINANCE"));
        assertEquals(QuizCategory.GENERAL_KNOWLEDGE, QuizCategory.valueOf("GENERAL_KNOWLEDGE"));
        assertEquals(QuizCategory.GEOGRAPHY, QuizCategory.valueOf("GEOGRAPHY"));
        assertEquals(QuizCategory.HISTORY, QuizCategory.valueOf("HISTORY"));
        assertEquals(QuizCategory.LANGUAGES, QuizCategory.valueOf("LANGUAGES"));
        assertEquals(QuizCategory.LAW, QuizCategory.valueOf("LAW"));
        assertEquals(QuizCategory.MATH, QuizCategory.valueOf("MATH"));
        assertEquals(QuizCategory.MUSIC, QuizCategory.valueOf("MUSIC"));
        assertEquals(QuizCategory.SCIENCE, QuizCategory.valueOf("SCIENCE"));
        assertEquals(QuizCategory.SEASONAL, QuizCategory.valueOf("SEASONAL"));
        assertEquals(QuizCategory.SOCIAL_EMOTIONAL_LEARNING, QuizCategory.valueOf("SOCIAL_EMOTIONAL_LEARNING"));
        assertEquals(QuizCategory.SOCIAL_STUDIES, QuizCategory.valueOf("SOCIAL_STUDIES"));
        assertEquals(QuizCategory.TRIVIA, QuizCategory.valueOf("TRIVIA"));
    }
}
