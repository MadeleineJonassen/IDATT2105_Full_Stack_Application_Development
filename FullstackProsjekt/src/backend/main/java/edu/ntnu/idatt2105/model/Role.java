package edu.ntnu.idatt2105.model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.springframework.security.core.GrantedAuthority;

/**
 * Represents a role entity in the application.
 */
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "role_id")
  private Integer roleId;

  @Column(nullable = false)
  private String authority;

  /**
   * Default constructor.
   */
  public Role() {
    super();
  }

  /**
   * Constructor for initializing a role with authority.
   *
   * @param authority The authority of the role.
   */
  public Role(String authority) {
    this.authority = authority;
  }

  /**
   * Constructor for initializing a role with ID and authority.
   *
   * @param roleId    The ID of the role.
   * @param authority The authority of the role.
   */
  public Role(Integer roleId, String authority) {
    this.roleId = roleId;
    this.authority = authority;
  }

  /**
   * Gets the ID of the role.
   *
   * @return The ID of the role.
   */
  public Integer getRoleId() {
    return roleId;
  }

  /**
   * Sets the ID of the role.
   *
   * @param roleId The ID of the role.
   */
  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getAuthority() {
    return this.authority;
  }

  /**
   * Sets the authority of the role.
   *
   * @param authority The authority of the role.
   */
  public void setAuthority(String authority) {
    this.authority = authority;
  }

  /**
   * Returns a string representation of the role.
   *
   * @return A string representation of the role.
   */
  @Override
  public String toString() {
    return "Role{" +
            "roleId=" + roleId +
            ", authority='" + authority + '\'' +
            '}';
  }
}
