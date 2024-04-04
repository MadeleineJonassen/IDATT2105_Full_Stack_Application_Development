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

  public QuestionAnswer() {
    // JPA requires a no-arg constructor
  }

  public QuestionAnswer(Question question, String givenAnswer) {
    this.question = question;
    this.givenAnswer = givenAnswer;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public String getGivenAnswer() {
    return givenAnswer;
  }

  public void setGivenAnswer(String givenAnswer) {
    this.givenAnswer = givenAnswer;
  }

  public boolean isCorrect() {
    return this.question.getAnswer().equalsIgnoreCase(this.givenAnswer);
  }

  public QuizResult getQuizResult() {
    return quizResult;
  }
  public void setQuizResult(QuizResult quizResult) {
    this.quizResult = quizResult;
  }
}