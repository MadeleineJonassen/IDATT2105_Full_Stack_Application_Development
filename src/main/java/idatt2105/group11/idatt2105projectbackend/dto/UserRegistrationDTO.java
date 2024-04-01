package idatt2105.group11.idatt2105projectbackend.dto;

public class UserRegistrationDTO {

  private String name;
  private String password;


  public UserRegistrationDTO() {
    super();
  }

  public UserRegistrationDTO(String name, String password) {
    this.name = name;
    this.password = password;
  }

  // Getters and setters

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
            "username='" + name + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
