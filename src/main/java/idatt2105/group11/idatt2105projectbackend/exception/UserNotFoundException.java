package idatt2105.group11.idatt2105projectbackend.exception;


public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }
}