package idatt2105.group11.idatt2105projectbackend.model;

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

  @Column(nullable = false)
  private boolean correct;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "quiz_result_id")
  private QuizResult quizResult;

  public QuestionAnswer() {
    // JPA requires a no-arg constructor
  }

  public QuestionAnswer(Question question, String givenAnswer) {
    this.question = question;
    this.givenAnswer = givenAnswer;
    this.correct = validateAnswer(givenAnswer);
  }

  // Validation method
  private boolean validateAnswer(String givenAnswer) {
    return question.checkAnswer(givenAnswer);
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
    // Update 'correct' whenever the question changes
    this.correct = validateAnswer(this.givenAnswer);
  }

  public String getGivenAnswer() {
    return givenAnswer;
  }

  public void setGivenAnswer(String givenAnswer) {
    this.givenAnswer = givenAnswer;
    // Update 'correct' whenever the given answer changes
    this.correct = validateAnswer(givenAnswer);
  }

  public boolean isCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }


  public QuizResult getQuizResult() {
    return quizResult;
  }
  public void setQuizResult(QuizResult quizResult) {
    this.quizResult = quizResult;
  }
}