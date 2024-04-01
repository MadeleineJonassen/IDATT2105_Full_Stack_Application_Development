package idatt2105.group11.idatt2105projectbackend.dto;


public class TokenDTO {
  private String token;

  public TokenDTO() {
    super();
  }

  public TokenDTO(String token) {
    super();
    this.token = token;
  }

  public String getToken() {
    return token;
  }

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
