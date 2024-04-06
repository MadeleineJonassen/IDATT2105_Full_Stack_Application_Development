package edu.ntnu.idatt2105.dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuizResultDTO {
  private Integer id;
  private Integer quizId;
  private Integer userId;
  private int totalScore;
  private String status;
  private LocalDateTime startedAt;
  private LocalDateTime completedAt;


  public QuizResultDTO() {
  }

  public QuizResultDTO(Integer id, Integer quizId, Integer userId, int totalScore, String status, LocalDateTime startedAt, LocalDateTime completedAt) {
    this.id = id;
    this.quizId = quizId;
    this.userId = userId;
    this.totalScore = totalScore;
    this.status = status;
    this.startedAt = startedAt;
    this.completedAt = completedAt;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQuizId() {
    return quizId;
  }

  public void setQuizId(Integer quizId) {
    this.quizId = quizId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public int getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(int totalScore) {
    this.totalScore = totalScore;
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

  public LocalDateTime getCompletedAt() {
    return completedAt;
  }

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
