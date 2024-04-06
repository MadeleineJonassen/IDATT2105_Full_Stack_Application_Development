package edu.ntnu.idatt2105.dto;

public class QuestionAnswerDTO {
  private Integer id;
  private Integer questionId;
  private Integer quizResultId;
  private String givenAnswer;
  private boolean correct;

  public QuestionAnswerDTO() {
  }

  public QuestionAnswerDTO(Integer id, Integer questionId, String givenAnswer, boolean correct) {
    this.id = id;
    this.questionId = questionId;
    this.givenAnswer = givenAnswer;
    this.correct = correct;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  public String getGivenAnswer() {
    return givenAnswer;
  }

  public void setGivenAnswer(String givenAnswer) {
    this.givenAnswer = givenAnswer;
  }

  public boolean isCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }

  @Override
  public String toString() {
    return "QuestionAnswerDTO{" +
            "id=" + id +
            ", questionId=" + questionId +
            ", givenAnswer='" + givenAnswer + '\'' +
            ", correct=" + correct +
            '}';
  }
}
