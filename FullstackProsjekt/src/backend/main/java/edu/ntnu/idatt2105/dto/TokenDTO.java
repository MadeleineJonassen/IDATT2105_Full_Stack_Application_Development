package edu.ntnu.idatt2105.dto;

/**
 * DTO class representing a token.
 */
public class TokenDTO {
  private String token;

  /**
   * Default constructor for TokenDTO.
   */
  public TokenDTO() {
    super();
  }

  /**
   * Constructor for TokenDTO.
   *
   * @param token The token string.
   */
  public TokenDTO(String token) {
    super();
    this.token = token;
  }

  /**
   * Get the token string.
   *
   * @return The token string.
   */
  public String getToken() {
    return token;
  }

  /**
   * Set the token string.
   *
   * @param token The token string to set.
   */
  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public String toString() {
    return "TokenDTO{" +
            "token='" + token + '\'' +
            '}';
  }
}
