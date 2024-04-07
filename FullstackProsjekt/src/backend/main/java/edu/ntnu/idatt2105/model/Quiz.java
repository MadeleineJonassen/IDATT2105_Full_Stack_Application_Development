package edu.ntnu.idatt2105.model;

import jakarta.persistence.*;

/**
 * Entity class representing a quiz.
 */
@Entity
@Table(name = "quiz")
public class Quiz {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id")
  private User creator;

  @Enumerated(EnumType.STRING)
  private QuizCategory category;

  @Enumerated(EnumType.STRING)
  private QuizDifficulty difficulty;

  /**
   * Default constructor for Quiz.
   */
  public Quiz() {
  }

  /**
   * Constructor for Quiz.
   *
   * @param title     The title of the quiz.
   * @param creator   The creator of the quiz.
   * @param category  The category of the quiz.
   * @param difficulty The difficulty of the quiz.
   */
  public Quiz(String title, User creator, QuizCategory category, QuizDifficulty difficulty) {
    this.title = title;
    this.creator = creator;
    this.category = category;
    this.difficulty = difficulty;
  }

  /**
   * Gets the ID of the quiz.
   *
   * @return The ID of the quiz.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the ID of the quiz.
   *
   * @param id The ID of the quiz.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the title of the quiz.
   *
   * @return The title of the quiz.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the quiz.
   *
   * @param title The title of the quiz.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets the creator of the quiz.
   *
   * @return The creator of the quiz.
   */
  public User getCreator() {
    return creator;
  }

  /**
   * Sets the creator of the quiz.
   *
   * @param creator The creator of the quiz.
   */
  public void setCreator(User creator) {
    this.creator = creator;
  }

  /**
   * Gets the category of the quiz.
   *
   * @return The category of the quiz.
   */
  public QuizCategory getCategory() {
    return category;
  }

  /**
   * Sets the category of the quiz.
   *
   * @param category The category of the quiz.
   */
  public void setCategory(QuizCategory category) {
    this.category = category;
  }

  /**
   * Gets the difficulty of the quiz.
   *
   * @return The difficulty of the quiz.
   */
  public QuizDifficulty getDifficulty() {
    return difficulty;
  }

  /**
   * Sets the difficulty of the quiz.
   *
   * @param difficulty The difficulty of the quiz.
   */
  public void setDifficulty(QuizDifficulty difficulty) {
    this.difficulty = difficulty;
  }
}
