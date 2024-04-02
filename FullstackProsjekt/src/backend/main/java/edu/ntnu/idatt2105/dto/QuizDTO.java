package edu.ntnu.idatt2105.dto;


import edu.ntnu.idatt2105.model.QuizCategory;
import edu.ntnu.idatt2105.model.QuizDifficulty;

import java.util.List;

public class QuizDTO {
  private Integer id;
  private String title;
  private List<Integer> questionIds;
  private Integer creatorId;
  private QuizCategory category;
  private QuizDifficulty difficulty;

  public QuizDTO() {
  }

  public QuizDTO(Integer id, String title, List<Integer> questionIds, Integer creatorId, QuizCategory category, QuizDifficulty difficulty) {
    this.title = title;
    this.questionIds = questionIds;
    this.creatorId = creatorId;
    this.category = category;
    this.difficulty = difficulty;
  }

  // Getters and setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Iterable<Integer> getQuestionIds() {
    return questionIds;
  }

  public void setQuestionIds(List<Integer> questionIds) {
    this.questionIds = questionIds;
  }

  public Integer getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Integer creatorId) {
    this.creatorId = creatorId;
  }

  public QuizCategory getCategory() {
    return category;
  }

  public void setCategory(QuizCategory category) {
    this.category = category;
  }

  public QuizDifficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(QuizDifficulty difficulty) {
    this.difficulty = difficulty;
  }
}
