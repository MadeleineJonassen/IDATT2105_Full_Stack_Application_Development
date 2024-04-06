package edu.ntnu.idatt2105.model;

import jakarta.persistence.*;

@Entity
public class QuestionAnswer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;

  @Column(nullable = false)
  private String givenAnswer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "quiz_result_id")
  private QuizResult quizResult;

  /**
   * Default constructor for QuestionAnswer.
   */
  public QuestionAnswer() {
    // JPA requires a no-arg constructor
  }

  /**
   * Constructor for QuestionAnswer.
   *
   * @param question    The question associated with the answer.
   * @param givenAnswer The given answer.
   */
  public QuestionAnswer(Question question, String givenAnswer) {
    this.question = question;
    this.givenAnswer = givenAnswer;
  }

  /**
   * Get the ID of the question answer.
   *
   * @return The ID of the question answer.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Set the ID of the question answer.
   *
   * @param id The ID of the question answer.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Get the question associated with the answer.
   *
   * @return The question associated with the answer.
   */
  public Question getQuestion() {
    return question;
  }

  /**
   * Set the question associated with the answer.
   *
   * @param question The question associated with the answer.
   */
  public void setQuestion(Question question) {
    this.question = question;
  }

  /**
   * Get the given answer.
   *
   * @return The given answer.
   */
  public String getGivenAnswer() {
    return givenAnswer;
  }

  /**
   * Set the given answer.
   *
   * @param givenAnswer The given answer.
   */
  public void setGivenAnswer(String givenAnswer) {
    this.givenAnswer = givenAnswer;
  }

  /**
   * Check if the given answer is correct.
   *
   * @return True if the given answer is correct, false otherwise.
   */
  public boolean isCorrect() {
    return this.question.getAnswer().equalsIgnoreCase(this.givenAnswer);
  }

  /**
   * Get the quiz result associated with the question answer.
   *
   * @return The quiz result associated with the question answer.
   */
  public QuizResult getQuizResult() {
    return quizResult;
  }

  /**
   * Set the quiz result associated with the question answer.
   *
   * @param quizResult The quiz result associated with the question answer.
   */
  public void setQuizResult(QuizResult quizResult) {
    this.quizResult = quizResult;
  }
}
