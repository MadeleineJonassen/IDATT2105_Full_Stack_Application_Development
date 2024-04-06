package edu.ntnu.idatt2105.dto;

/**
 * DTO class representing a question-answer pair.
 */
public class QuestionAnswerDTO {
  private Integer id;
  private Integer questionId;
  private Integer quizResultId;
  private String givenAnswer;
  private boolean correct;

  /**
   * Default constructor for QuestionAnswerDTO.
   */
  public QuestionAnswerDTO() {
  }

  /**
   * Constructor for QuestionAnswerDTO.
   *
   * @param id          The ID of the question-answer pair.
   * @param questionId  The ID of the question.
   * @param givenAnswer The answer provided by the user.
   * @param correct     Whether the answer is correct or not.
   */
  public QuestionAnswerDTO(Integer id, Integer questionId, String givenAnswer, boolean correct) {
    this.id = id;
    this.questionId = questionId;
    this.givenAnswer = givenAnswer;
    this.correct = correct;
  }

  /**
   * Get the ID of the question-answer pair.
   *
   * @return The ID.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Set the ID of the question-answer pair.
   *
   * @param id The ID to set.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Get the ID of the question.
   *
   * @return The question ID.
   */
  public Integer getQuestionId() {
    return questionId;
  }

  /**
   * Set the ID of the question.
   *
   * @param questionId The question ID to set.
   */
  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  /**
   * Get the answer provided by the user.
   *
   * @return The given answer.
   */
  public String getGivenAnswer() {
    return givenAnswer;
  }

  /**
   * Set the answer provided by the user.
   *
   * @param givenAnswer The given answer to set.
   */
  public void setGivenAnswer(String givenAnswer) {
    this.givenAnswer = givenAnswer;
  }

  /**
   * Check if the answer is correct.
   *
   * @return True if the answer is correct, false otherwise.
   */
  public boolean isCorrect() {
    return correct;
  }

  /**
   * Set whether the answer is correct or not.
   *
   * @param correct True if the answer is correct, false otherwise.
   */
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
