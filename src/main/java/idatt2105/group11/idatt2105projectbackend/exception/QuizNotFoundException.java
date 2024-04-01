package idatt2105.group11.idatt2105projectbackend.exception;


public class QuizNotFoundException extends RuntimeException {
  public QuizNotFoundException(String message) {
    super(message);
  }
}