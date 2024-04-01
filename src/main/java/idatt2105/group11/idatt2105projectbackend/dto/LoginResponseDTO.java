package idatt2105.group11.idatt2105projectbackend.dto;

import idatt2105.group11.idatt2105projectbackend.model.User;

public class LoginResponseDTO {

  private String name;
  private String password;
  private String jwt;

  public LoginResponseDTO() {
    super();
  }

  public LoginResponseDTO(User user, String jwt) {
    this.name = user.getName();
    this.password = user.getPassword();
    this.jwt = jwt;
  }

  public String getName() {
    return name;
  }

  public void setName(String username) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

  @Override
  public String toString() {
    return "LoginResponseDTO{" +
            "username='" + name + '\'' +
            ", password='" + password + '\'' +
            ", jwt='" + jwt + '\'' +
            '}';
  }
}
