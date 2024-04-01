package edu.ntnu.idatt2105.exception;


public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }
}