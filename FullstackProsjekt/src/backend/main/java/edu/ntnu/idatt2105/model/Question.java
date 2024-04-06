package edu.ntnu.idatt2105.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "quiz_id")
  private Quiz quiz;

  @Column(nullable = false)
  private String questionText;

  @Column(nullable = false)
  private int score;

  @Column(nullable = false)
  private QuestionType type;

  @Column(nullable = false)
  private String answer;

  @Column()
  @Nullable
  private String options; //Used for MCQ and TrueOrFalse to store answer options, divided by *

  /**
   * Default constructor for Question.
   */
  public Question() {
  }

  /**
   * Constructor for Question.
   *
   * @param questionText The text of the question.
   * @param type         The type of the question.
   * @param score        The score assigned to the question.
   * @param answer       The correct answer to the question.
   * @param options      The list of options for the question (if applicable).
   */
  public Question(String questionText, QuestionType type, int score, String answer, @Nullable String options) {
    this.questionText = questionText;
    this.type = type;
    this.score = score;
    this.answer = answer;
    this.options = options;
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
   * @param id The ID of the question.
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
   * @param questionText The text of the question.
   */
  public void setQuestionText(String questionText) {
    this.questionText = questionText;
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
   * @param score The score assigned to the question.
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Get the quiz to which the question belongs.
   *
   * @return The quiz to which the question belongs.
   */
  public Quiz getQuiz() {
    return quiz;
  }

  /**
   * Set the quiz to which the question belongs.
   *
   * @param quiz The quiz to which the question belongs.
   */
  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
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
   * @param type The type of the question.
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
   * @param answer The correct answer to the question.
   */
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  /**
   * Get the options for the question (if applicable).
   *
   * @return The options for the question (if applicable).
   */
  @Nullable
  public String getOptions() {
    return options;
  }

  /**
   * Set the options for the question (if applicable).
   *
   * @param options The options for the question (if applicable).
   */
  public void setOptions(@Nullable String options) {
    this.options = options;
  }

  /**
   * Get a list of all options for the question.
   *
   * @return A list of all options for the question.
   */
  public List<String> getOptionsList() {
    if (this.options != null && !this.options.isEmpty()) {
      return List.of(this.options.split("\\*"));
    }
    return new ArrayList<>();
  }
}
