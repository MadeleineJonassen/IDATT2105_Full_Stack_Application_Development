package edu.ntnu.idatt2105.dto;

import java.time.LocalDateTime;

/**
 * DTO class representing a quiz result.
 */
public class QuizResultDTO {
  private Integer id;
  private Integer quizId;
  private Integer userId;
  private int totalScore;
  private String status;
  private LocalDateTime startedAt;
  private LocalDateTime completedAt;

  /**
   * Default constructor for QuizResultDTO.
   */
  public QuizResultDTO() {
  }

  /**
   * Constructor for QuizResultDTO.
   *
   * @param id           The ID of the quiz result.
   * @param quizId       The ID of the quiz.
   * @param userId       The ID of the user who took the quiz.
   * @param totalScore   The total score achieved in the quiz.
   * @param status       The status of the quiz result.
   * @param startedAt    The time when the quiz was started.
   * @param completedAt  The time when the quiz was completed.
   */
  public QuizResultDTO(Integer id, Integer quizId, Integer userId, int totalScore, String status, LocalDateTime startedAt, LocalDateTime completedAt) {
    this.id = id;
    this.quizId = quizId;
    this.userId = userId;
    this.totalScore = totalScore;
    this.status = status;
    this.startedAt = startedAt;
    this.completedAt = completedAt;
  }

  /**
   * Get the ID of the quiz result.
   *
   * @return The ID of the quiz result.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Set the ID of the quiz result.
   *
   * @param id The ID to set.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Get the ID of the quiz.
   *
   * @return The ID of the quiz.
   */
  public Integer getQuizId() {
    return quizId;
  }

  /**
   * Set the ID of the quiz.
   *
   * @param quizId The ID of the quiz to set.
   */
  public void setQuizId(Integer quizId) {
    this.quizId = quizId;
  }

  /**
   * Get the ID of the user who took the quiz.
   *
   * @return The ID of the user.
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Set the ID of the user who took the quiz.
   *
   * @param userId The ID of the user to set.
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  /**
   * Get the total score achieved in the quiz.
   *
   * @return The total score achieved.
   */
  public int getTotalScore() {
    return totalScore;
  }

  /**
   * Set the total score achieved in the quiz.
   *
   * @param totalScore The total score to set.
   */
  public void setTotalScore(int totalScore) {
    this.totalScore = totalScore;
  }

  /**
   * Get the status of the quiz result.
   *
   * @return The status of the quiz result.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Set the status of the quiz result.
   *
   * @param status The status to set.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Get the time when the quiz was started.
   *
   * @return The time when the quiz was started.
   */
  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  /**
   * Set the time when the quiz was started.
   *
   * @param startedAt The time when the quiz was started.
   */
  public void setStartedAt(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  /**
   * Get the time when the quiz was completed.
   *
   * @return The time when the quiz was completed.
   */
  public LocalDateTime getCompletedAt() {
    return completedAt;
  }

  /**
   * Set the time when the quiz was completed.
   *
   * @param completedAt The time when the quiz was completed.
   */
  public void setCompletedAt(LocalDateTime completedAt) {
    this.completedAt = completedAt;
  }

  @Override
  public String toString() {
    return "QuizResultDTO{" +
            "id=" + id +
            ", quizId=" + quizId +
            ", userId=" + userId +
            ", score=" + totalScore +
            '}';
  }
}
