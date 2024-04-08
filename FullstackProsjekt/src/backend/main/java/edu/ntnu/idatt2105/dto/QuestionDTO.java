package edu.ntnu.idatt2105.dto;

import edu.ntnu.idatt2105.model.QuestionType;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO class representing a question.
 */
public class QuestionDTO {
  private Integer id;
  private Integer quizId;
  private String questionText;
  private QuestionType type;
  private String answer;
  @Nullable
  private List<String> options;
  private int score;

  /**
   * Default constructor for QuestionDTO.
   */
  public QuestionDTO() {
  }

  /**
   * Constructor for QuestionDTO.
   *
   * @param id           The ID of the question.
   * @param questionText The text of the question.
   * @param type         The type of the question.
   * @param answer       The correct answer to the question.
   * @param options      The list of options for the question (if applicable).
   * @param score        The score assigned to the question.
   * @param quizId       The ID of the quiz the question belongs to.
   */
  public QuestionDTO(Integer id, String questionText, QuestionType type, String answer, List<String> options, int score, Integer quizId) {
    this.id = id;
    this.questionText = questionText;
    this.type = type;
    this.answer = answer;
    this.options = options;
    this.score = score;
    this.quizId = quizId;
  }


  /**
   * Get the ID of the question.
   *
   * @return The ID of the question.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Set the ID of the question.
   *
   * @param id The ID to set.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Get the text of the question.
   *
   * @return The text of the question.
   */
  public String getQuestionText() {
    return questionText;
  }

  /**
   * Set the text of the question.
   *
   * @param questionText The text to set.
   */
  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  /**
   * Get the type of the question.
   *
   * @return The type of the question.
   */
  public QuestionType getType() {
    return type;
  }

  /**
   * Set the type of the question.
   *
   * @param type The type to set.
   */
  public void setType(QuestionType type) {
    this.type = type;
  }

  /**
   * Get the correct answer to the question.
   *
   * @return The correct answer to the question.
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Set the correct answer to the question.
   *
   * @param answer The answer to set.
   */
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  /**
   * Get the list of options for the question.
   *
   * @return The list of options for the question.
   */
  public List<String> getOptions() {
    return options;
  }

  /**
   * Set the list of options for the question.
   *
   * @param options The list of options to set.
   */
  public void setOptions(List<String> options) {
    this.options = options;
  }

  /**
   * Get the score assigned to the question.
   *
   * @return The score assigned to the question.
   */
  public int getScore() {
    return score;
  }

  /**
   * Set the score assigned to the question.
   *
   * @param score The score to set.
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Get the ID of the quiz the question belongs to.
   *
   * @return The ID of the quiz.
   */
  public Integer getQuizId() {
    return quizId;
  }

  /**
   * Set the ID of the quiz the question belongs to.
   *
   * @param quizId The ID of the quiz to set.
   */
  public void setQuizId(Integer quizId) {
    this.quizId = quizId;
  }

  /**
   * Sets the options from a string containing options separated by '*' character.
   *
   * @param optionsString The string containing options.
   */
  public void setOptionsFromString(String optionsString) {
    if (optionsString != null && !optionsString.isEmpty()) {
      this.options = Arrays.stream(optionsString.split("\\*"))
              .collect(Collectors.toList());
    }
  }

  /**
   * Gets the options as a string with options separated by '*' character.
   *
   * @return The options as a string.
   */
  @Nullable
  public String getOptionsAsString() {
    return String.join("*", this.options);
  }
}
