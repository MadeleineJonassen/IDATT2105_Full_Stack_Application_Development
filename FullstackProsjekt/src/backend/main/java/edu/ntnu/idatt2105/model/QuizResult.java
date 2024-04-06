package edu.ntnu.idatt2105.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_results")
public class QuizResult {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "quiz_id", nullable = false)
  private Quiz quiz;

  @Column(nullable = false)
  private int score;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private LocalDateTime startedAt;

  @Column
  @Nullable
  private LocalDateTime completedAt;

  public QuizResult() {
    // JPA requires a no-arg constructor
  }

  // Constructor for initializing with a quiz and optionally with answers.
  public QuizResult(Quiz quiz, User user, String status, LocalDateTime startedAt, LocalDateTime completedAt) {
    this.quiz = quiz;
    this.user = user;
    this.status = status;
    this.startedAt = startedAt;
    this.completedAt = completedAt;
  }

  // Getter and Setter methods
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  @Nullable
  public LocalDateTime getCompletedAt() {
    return completedAt;
  }

  public void setCompletedAt(@Nullable LocalDateTime completedAt) {
    this.completedAt = completedAt;
  }
}
