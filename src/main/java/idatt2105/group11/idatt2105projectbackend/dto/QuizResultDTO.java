package idatt2105.group11.idatt2105projectbackend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuizResultDTO {
  private Integer id;
  private Integer quizId;
  private Integer userId;
  private List<QuestionAnswerDTO> answers;
  private int score;
  private String status;
  private LocalDateTime startedAt;
  private LocalDateTime completedAt;


  public QuizResultDTO() {
  }

  public QuizResultDTO(Integer id, Integer quizId, Integer userId, List<QuestionAnswerDTO> answers, int score, String status, LocalDateTime startedAt, LocalDateTime completedAt) {
    this.id = id;
    this.quizId = quizId;
    this.userId = userId;
    this.answers = answers;
    this.score = score;
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

  public List<QuestionAnswerDTO> getAnswers() {
    return answers;
  }

  public void setAnswers(List<QuestionAnswerDTO> answers) {
    this.answers = answers;
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
            ", answers=" + answers +
            ", score=" + score +
            '}';
  }
}
