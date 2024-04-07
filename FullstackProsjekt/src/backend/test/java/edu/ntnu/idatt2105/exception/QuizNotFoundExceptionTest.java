package edu.ntnu.idatt2105.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuizNotFoundExceptionTest {

  @Test
  public void testExceptionMessage() {
    String message = "Quiz not found!";
    QuizNotFoundException exception = new QuizNotFoundException(message);
    assertEquals(message, exception.getMessage());
  }
}
