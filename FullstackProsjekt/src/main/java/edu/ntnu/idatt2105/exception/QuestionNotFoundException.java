package edu.ntnu.idatt2105.exception;

public class QuestionNotFoundException extends RuntimeException {
  public QuestionNotFoundException(String message) {
    super(message);
  }
}