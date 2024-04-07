package edu.ntnu.idatt2105.dto;

import edu.ntnu.idatt2105.model.User;

/**
 * DTO class representing the response after a successful login.
 */
public class LoginResponseDTO {

  private String username;
  private String password; // Password should not be included in the response for security reasons.
  private String jwt;

  /**
   * Default constructor for LoginResponseDTO.
   */
  public LoginResponseDTO() {
    super();
  }

  /**
   * Constructor for LoginResponseDTO.
   *
   * @param user The user object.
   * @param jwt  The JWT token generated for the user.
   */
  public LoginResponseDTO(User user, String jwt) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.jwt = jwt;
  }

  /**
   * Get the username.
   *
   * @return The username.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Set the username.
   *
   * @param username The username.
   */
  public void setUsername(String username) {
    this.username = this.username;
  }

  /**
   * Get the password.
   *
   * @return The password. Note: Password should not be included in the response for security reasons.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the password.
   *
   * @param password The password.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Get the JWT token.
   *
   * @return The JWT token.
   */
  public String getJwt() {
    return jwt;
  }

  /**
   * Set the JWT token.
   *
   * @param jwt The JWT token.
   */
  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

  @Override
  public String toString() {
    return "LoginResponseDTO{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' + // Password should not be included in the response for security reasons.
            ", jwt='" + jwt + '\'' +
            '}';
  }
}
