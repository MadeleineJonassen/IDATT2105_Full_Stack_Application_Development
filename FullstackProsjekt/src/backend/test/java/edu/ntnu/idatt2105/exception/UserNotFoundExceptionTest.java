package edu.ntnu.idatt2105.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserNotFoundExceptionTest {

  @Test
  public void testExceptionMessage() {
    String message = "User not found!";
    UserNotFoundException exception = new UserNotFoundException(message);
    assertEquals(message, exception.getMessage());
  }
}
