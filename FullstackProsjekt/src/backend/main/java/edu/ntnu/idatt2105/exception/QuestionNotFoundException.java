package edu.ntnu.idatt2105.exception;

/**
 * Exception indicating that a question was not found.
 */
public class QuestionNotFoundException extends RuntimeException {

  /**
   * Constructor for QuestionNotFoundException.
   *
   * @param message The exception message.
   */
  public QuestionNotFoundException(String message) {
    super(message);
  }
}
