package idatt2105.group11.idatt2105projectbackend.dto;

import java.util.List;

public class UserDTO extends UserRegistrationDTO {
  private Integer id;

  // Assuming you might need more fields here, such as role, etc.

  public UserDTO() {
    super();
  }

  public UserDTO(Integer id, String name, String password) {
    super(name, password);
    this.id = id;
  }

  // Getters and setters for the 'id' field
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
