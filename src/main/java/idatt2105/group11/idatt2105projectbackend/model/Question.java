package idatt2105.group11.idatt2105projectbackend.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question {
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

  public Question() {
  }

  public Question(Quiz quiz, String questionText, int score) {
    this.questionText = questionText;
    this.score = score;
  }

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

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public abstract boolean checkAnswer(String answer);

  public Quiz getQuiz() {
    return quiz;
  }
  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }
}
