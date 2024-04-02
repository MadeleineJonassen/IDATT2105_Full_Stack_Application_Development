package edu.ntnu.idatt2105.exception;


public class QuizNotFoundException extends RuntimeException {
  public QuizNotFoundException(String message) {
    super(message);
  }
}