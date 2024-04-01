package idatt2105.group11.idatt2105projectbackend.dto;

import java.util.List;

public class QuestionDTO {
  private Integer id;
  private String questionText;
  private int score;
  private List<String> answerOptions;
  private int correctAnswerIndex;


  public QuestionDTO() {
  }

  public QuestionDTO(Integer id, String questionText, int score) {
    this.id = id;
    this.questionText = questionText;
    this.score = score;
  }

  // Getters and setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getQuestionText() {
    return questionText;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public List<String> getAnswerOptions() {
    return answerOptions;
  }

  public void setAnswerOptions(List<String> answerOptions) {
    this.answerOptions = answerOptions;
  }

  public int getCorrectAnswerIndex() {
    return correctAnswerIndex;
  }

  public void setCorrectAnswerIndex(int correctAnswerIndex) {
    this.correctAnswerIndex = correctAnswerIndex;
  }


}
