package edu.ntnu.idatt2105.exception;

/**
 * Exception indicating that a user was not found.
 */
public class UserNotFoundException extends RuntimeException {

  /**
   * Constructor for UserNotFoundException.
   *
   * @param message The exception message.
   */
  public UserNotFoundException(String message) {
    super(message);
  }
}
