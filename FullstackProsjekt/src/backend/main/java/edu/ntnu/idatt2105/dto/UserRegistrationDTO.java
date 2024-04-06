package edu.ntnu.idatt2105.dto;

/**
 * DTO class representing user registration information.
 */
public class UserRegistrationDTO {

  private String username;
  private String password;

  /**
   * Default constructor for UserRegistrationDTO.
   */
  public UserRegistrationDTO() {
    super();
  }

  /**
   * Constructor for UserRegistrationDTO.
   *
   * @param username The username for registration.
   * @param password The password for registration.
   */
  public UserRegistrationDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * Get the username for registration.
   *
   * @return The username for registration.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Set the username for registration.
   *
   * @param username The username to set.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Get the password for registration.
   *
   * @return The password for registration.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the password for registration.
   *
   * @param password The password to set.
   */
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
