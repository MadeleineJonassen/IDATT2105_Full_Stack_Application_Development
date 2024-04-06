package edu.ntnu.idatt2105.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents a quiz result entity.
 */
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

  /**
   * Default constructor.
   */
  public QuizResult() {
    // JPA requires a no-arg constructor
  }

  /**
   * Constructor for initializing a quiz result.
   *
   * @param quiz       The quiz associated with the result.
   * @param user       The user who took the quiz.
   * @param status     The status of the quiz result.
   * @param startedAt  The time the quiz was started.
   * @param completedAt The time the quiz was completed.
   */
  public QuizResult(Quiz quiz, User user, String status, LocalDateTime startedAt, LocalDateTime completedAt) {
    this.quiz = quiz;
    this.user = user;
    this.status = status;
    this.startedAt = startedAt;
    this.completedAt = completedAt;
  }

  /**
   * Gets the ID of the quiz result.
   *
   * @return The ID of the quiz result.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the ID of the quiz result.
   *
   * @param id The ID of the quiz result.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the quiz associated with the result.
   *
   * @return The quiz associated with the result.
   */
  public Quiz getQuiz() {
    return quiz;
  }

  /**
   * Sets the quiz associated with the result.
   *
   * @param quiz The quiz associated with the result.
   */
  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  /**
   * Gets the user who took the quiz.
   *
   * @return The user who took the quiz.
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the user who took the quiz.
   *
   * @param user The user who took the quiz.
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Gets the score of the quiz result.
   *
   * @return The score of the quiz result.
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the score of the quiz result.
   *
   * @param score The score of the quiz result.
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Gets the status of the quiz result.
   *
   * @return The status of the quiz result.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the status of the quiz result.
   *
   * @param status The status of the quiz result.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the time the quiz was started.
   *
   * @return The time the quiz was started.
   */
  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  /**
   * Sets the time the quiz was started.
   *
   * @param startedAt The time the quiz was started.
   */
  public void setStartedAt(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  /**
   * Gets the time the quiz was completed.
   *
   * @return The time the quiz was completed.
   */
  @Nullable
  public LocalDateTime getCompletedAt() {
    return completedAt;
  }

  /**
   * Sets the time the quiz was completed.
   *
   * @param completedAt The time the quiz was completed.
   */
  public void setCompletedAt(@Nullable LocalDateTime completedAt) {
    this.completedAt = completedAt;
  }
}
