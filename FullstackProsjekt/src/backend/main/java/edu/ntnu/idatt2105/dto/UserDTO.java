package edu.ntnu.idatt2105.dto;

/**
 * DTO class representing a user.
 */
public class UserDTO extends UserRegistrationDTO {
  private Integer id;

  /**
   * Default constructor for UserDTO.
   */
  public UserDTO() {
    super();
  }

  /**
   * Constructor for UserDTO.
   *
   * @param id       The ID of the user.
   * @param username The username of the user.
   * @param password The password of the user.
   */
  public UserDTO(Integer id, String username, String password) {
    super(username, password);
    this.id = id;
  }

  /**
   * Get the ID of the user.
   *
   * @return The ID of the user.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Set the ID of the user.
   *
   * @param id The ID to set.
   */
  public void setId(Integer id) {
    this.id = id;
  }
}
