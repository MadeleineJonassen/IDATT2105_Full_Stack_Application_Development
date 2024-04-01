package idatt2105.group11.idatt2105projectbackend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_results")
public class QuizResult {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "quiz_id", nullable = false)
  private Quiz quiz;

  @OneToMany(mappedBy = "quizResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<QuestionAnswer> answers = new ArrayList<>();

  @Column(nullable = false)
  private int score;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;


  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private LocalDateTime startedAt;

  @Column
  private LocalDateTime completedAt;




  public QuizResult() {
    // JPA requires a no-arg constructor
  }

  // Constructor for initializing with a quiz and optionally with answers.
  public QuizResult(Quiz quiz, List<QuestionAnswer> answers, User user, String status, LocalDateTime startedAt, LocalDateTime completedAt) {
    this.quiz = quiz;
    this.setAnswers(answers);
    this.user = user;
    this.status = status;
    this.startedAt = startedAt;
    this.completedAt = completedAt;
  }

  // Getter and Setter methods
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<QuestionAnswer> getAnswers() {
    return answers;
  }

  // When setting answers, also recalculate the score based on the correctness and question score.
  public void setAnswers(List<QuestionAnswer> answers) {
    this.answers = answers;
    this.answers.forEach(answer -> answer.setQuizResult(this));
    calculateScore(); // Recalculate the score based on the new set of answers
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  public LocalDateTime getCompletedAt() {
    return completedAt;
  }

  public void setCompletedAt(LocalDateTime completedAt) {
    this.completedAt = completedAt;
  }

  public void addQuestionAnswer(QuestionAnswer questionAnswer) {
    this.answers.add(questionAnswer);
    questionAnswer.setQuizResult(this);
    calculateScore();
  }

  private void calculateScore() {
    this.score = this.answers.stream()
            .filter(QuestionAnswer::isCorrect)
            .mapToInt(answer -> answer.getQuestion().getScore())
            .sum();
  }
}
