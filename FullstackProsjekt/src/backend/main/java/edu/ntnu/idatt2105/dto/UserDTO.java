package edu.ntnu.idatt2105.dto;

import java.util.List;

public class UserDTO extends UserRegistrationDTO {
  private Integer id;

  // Assuming you might need more fields here, such as role, etc.

  public UserDTO() {
    super();
  }

  public UserDTO(Integer id, String username, String password) {
    super(username, password);
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
