package edu.ntnu.idatt2105.exception;

/**
 * Exception indicating that a quiz was not found.
 */
public class QuizNotFoundException extends RuntimeException {

  /**
   * Constructor for QuizNotFoundException.
   *
   * @param message The exception message.
   */
  public QuizNotFoundException(String message) {
    super(message);
  }
}
