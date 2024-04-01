package idatt2105.group11.idatt2105projectbackend.model;

import idatt2105.group11.idatt2105projectbackend.model.Quiz;
import idatt2105.group11.idatt2105projectbackend.model.QuizResult;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "app_user_id", unique = true)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String name;

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

  public User(String name, String password, Set<Role> authorities) {
    this.name = name;
    this.password = password;
    this.authorities = authorities;
  }

  public User(Integer id, String name, String password, Set<Role> authorities) {
    this.id = id;
    this.name = name;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return null;
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
