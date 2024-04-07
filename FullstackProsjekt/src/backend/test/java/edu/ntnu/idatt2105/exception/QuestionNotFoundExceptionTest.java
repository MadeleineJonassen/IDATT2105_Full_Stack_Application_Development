package edu.ntnu.idatt2105.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionNotFoundExceptionTest {

  @Test
  public void testExceptionMessage() {
    String message = "Question not found!";
    QuestionNotFoundException exception = new QuestionNotFoundException(message);
    assertEquals(message, exception.getMessage());
  }
}
