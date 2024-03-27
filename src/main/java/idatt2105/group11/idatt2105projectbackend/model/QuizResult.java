package idatt2105.group11.idatt2105projectbackend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_results")
public class QuizResult {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "quiz_id", nullable = false)
  private Quiz quiz;

  @OneToMany(mappedBy = "quizResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<QuestionAnswer> answers = new ArrayList<>();

  private int score;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  public QuizResult() {
    // JPA requires a no-arg constructor
  }

  // Constructor for initializing with a quiz and optionally with answers.
  public QuizResult(Quiz quiz, List<QuestionAnswer> answers, User user) {
    this.quiz = quiz;
    this.setAnswers(answers);
    this.user = user;
  }

  // Getter and Setter methods
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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


  public void addQuestionAnswer(QuestionAnswer questionAnswer) {
    this.answers.add(questionAnswer);
    questionAnswer.setQuizResult(this);
    calculateScore(); // Recalculate the score whenever a new answer is added
  }

  // Method to recalculate the score based on the correctness of answers and their assigned question scores
  private void calculateScore() {
    this.score = this.answers.stream()
            .filter(QuestionAnswer::isCorrect)
            .mapToInt(answer -> answer.getQuestion().getScore())
            .sum();
  }


}
