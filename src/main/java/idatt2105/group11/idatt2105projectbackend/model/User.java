package idatt2105.group11.idatt2105projectbackend.model;

import idatt2105.group11.idatt2105projectbackend.model.Quiz;
import idatt2105.group11.idatt2105projectbackend.model.QuizResult;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String password;
  private String role;

  // Quizzes created by the user
  @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Quiz> createdQuizzes = new ArrayList<>();

  // Quiz attempts by the user
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<QuizResult> quizResults = new ArrayList<>();


  public User() {
  }

  public User(String name, String password, String role) {
    this.name = name;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }


}
