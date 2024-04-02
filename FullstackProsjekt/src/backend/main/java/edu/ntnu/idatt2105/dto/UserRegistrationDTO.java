package edu.ntnu.idatt2105.dto;

public class UserRegistrationDTO {

  private String username;
  private String password;


  public UserRegistrationDTO() {
    super();
  }

  public UserRegistrationDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // Getters and setters

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserRegistrationDTO{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
