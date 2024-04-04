package edu.ntnu.idatt2105.dto;

import edu.ntnu.idatt2105.model.QuestionType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDTO {
  private Integer id;
  private String questionText;
  private QuestionType type;
  private String answer;
  private List<String> options;
  private int score;


  public QuestionDTO() {
  }

  public QuestionDTO(Integer id, String questionText, QuestionType type, String answer, List<String> options, int score) {
    this.id = id;
    this.questionText = questionText;
    this.type = type;
    this.answer = answer;
    this.options = options;
    this.score = score;
  }

  // Getters og setters
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

  public QuestionType getType() {
    return type;
  }

  public void setType(QuestionType type) {
    this.type = type;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setOptionsFromString(String optionsString) {
    if (optionsString != null && !optionsString.isEmpty()) {
      this.options = Arrays.stream(optionsString.split("\\*"))
              .collect(Collectors.toList());
    }
  }

  public String getOptionsAsString() {
    return String.join("*", this.options);
  }
}
