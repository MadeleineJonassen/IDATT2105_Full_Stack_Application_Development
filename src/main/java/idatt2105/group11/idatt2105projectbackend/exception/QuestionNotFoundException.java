package idatt2105.group11.idatt2105projectbackend.exception;

public class QuestionNotFoundException extends RuntimeException {
  public QuestionNotFoundException(String message) {
    super(message);
  }
}