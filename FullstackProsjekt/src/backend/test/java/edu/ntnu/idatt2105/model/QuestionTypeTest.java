package edu.ntnu.idatt2105.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTypeTest {

    @Test
    public void testEnumValues() {
        assertEquals("STRING", QuestionType.STRING.name());
        assertEquals("TRUE_OR_FALSE", QuestionType.TRUE_OR_FALSE.name());
        assertEquals("MULTIPLE_CHOICE", QuestionType.MULTIPLE_CHOICE.name());

        assertEquals(3, QuestionType.values().length);
        assertEquals(QuestionType.STRING, QuestionType.valueOf("STRING"));
        assertEquals(QuestionType.TRUE_OR_FALSE, QuestionType.valueOf("TRUE_OR_FALSE"));
        assertEquals(QuestionType.MULTIPLE_CHOICE, QuestionType.valueOf("MULTIPLE_CHOICE"));
    }
}
