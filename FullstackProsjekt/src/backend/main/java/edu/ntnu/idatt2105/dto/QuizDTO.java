package edu.ntnu.idatt2105.dto;

import edu.ntnu.idatt2105.model.QuizCategory;
import edu.ntnu.idatt2105.model.QuizDifficulty;

/**
 * DTO class representing a quiz.
 */
public class QuizDTO {
  private Integer id;
  private String title;
  private Integer creatorId;
  private QuizCategory category;
  private QuizDifficulty difficulty;

  /**
   * Default constructor for QuizDTO.
   */
  public QuizDTO() {
  }

  /**
   * Constructor for QuizDTO.
   *
   * @param title      The title of the quiz.
   * @param creatorId  The ID of the creator of the quiz.
   * @param category   The category of the quiz.
   * @param difficulty The difficulty of the quiz.
   */
  public QuizDTO(String title, Integer creatorId, QuizCategory category, QuizDifficulty difficulty) {
    this.title = title;
    this.creatorId = creatorId;
    this.category = category;
    this.difficulty = difficulty;
  }

  /**
   * Get the ID of the quiz.
   *
   * @return The ID of the quiz.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Set the ID of the quiz.
   *
   * @param id The ID to set.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Get the title of the quiz.
   *
   * @return The title of the quiz.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Set the title of the quiz.
   *
   * @param title The title to set.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Get the ID of the creator of the quiz.
   *
   * @return The ID of the creator of the quiz.
   */
  public Integer getCreatorId() {
    return creatorId;
  }

  /**
   * Set the ID of the creator of the quiz.
   *
   * @param creatorId The ID of the creator to set.
   */
  public void setCreatorId(Integer creatorId) {
    this.creatorId = creatorId;
  }

  /**
   * Get the category of the quiz.
   *
   * @return The category of the quiz.
   */
  public QuizCategory getCategory() {
    return category;
  }

  /**
   * Set the category of the quiz.
   *
   * @param category The category to set.
   */
  public void setCategory(QuizCategory category) {
    this.category = category;
  }

  /**
   * Get the difficulty of the quiz.
   *
   * @return The difficulty of the quiz.
   */
  public QuizDifficulty getDifficulty() {
    return difficulty;
  }

  /**
   * Set the difficulty of the quiz.
   *
   * @param difficulty The difficulty to set.
   */
  public void setDifficulty(QuizDifficulty difficulty) {
    this.difficulty = difficulty;
  }
}
