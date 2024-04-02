package edu.ntnu.idatt2105.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true)
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

  // Quizzes created by the user
  @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Quiz> createdQuizzes = new ArrayList<>();

  // Quiz attempts by the user
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<QuizResult> quizResults = new ArrayList<>();


  public User() {
    this.authorities = new HashSet<>();
  }

  public User(String username, String password, Set<Role> authorities) {
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  public User(Integer id, String username, String password, Set<Role> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Quiz> getCreatedQuizzes() {
    return createdQuizzes;
  }

  public void setCreatedQuizzes(List<Quiz> createdQuizzes) {
    this.createdQuizzes = createdQuizzes;
  }

  public List<QuizResult> getQuizResults() {
    return quizResults;
  }

  public void setQuizResults(List<QuizResult> quizResults) {
    this.quizResults = quizResults;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String name) {
    this.username = username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAuthorities(Set<Role> authorities) {
    this.authorities = authorities;
  }
}
