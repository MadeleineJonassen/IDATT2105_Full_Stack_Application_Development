package edu.ntnu.idatt2105.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * Represents a user entity in the application.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "app_user_id", unique = true)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "app_user_role",
          joinColumns = {@JoinColumn(name = "app_user_id")},
          inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  private Set<Role> authorities;

  /**
   * Default constructor.
   */
  public User() {
  }

  /**
   * Constructor for initializing a user with username, password, and authorities.
   *
   * @param username    The username of the user.
   * @param password    The password of the user.
   * @param authorities The set of authorities granted to the user.
   */
  public User(String username, String password, Set<Role> authorities) {
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  /**
   * Gets the ID of the user.
   *
   * @return The ID of the user.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the ID of the user.
   *
   * @param id The ID of the user.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the username of the user.
   *
   * @return The username of the user.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username of the user.
   *
   * @param username The username of the user.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  /**
   * Gets the password of the user.
   *
   * @return The password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEnabled() {
    return true;
  }

  /**
   * Sets the password of the user.
   *
   * @param password The password of the user.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Sets the authorities of the user.
   *
   * @param authorities The set of authorities granted to the user.
   */
  public void setAuthorities(Set<Role> authorities) {
    this.authorities = authorities;
  }
}