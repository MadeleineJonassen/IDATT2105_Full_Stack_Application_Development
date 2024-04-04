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



  public Question() {
  }

  public Question(String questionText, QuestionType type, int score, String answer, @Nullable String options) {
    this.questionText = questionText;
    this.type = type;
    this.score = score;
    this.answer = answer;
    this.options = options;
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

  public Quiz getQuiz() {
    return quiz;
  }
  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }
  public QuestionType getType() {
    return type;
  }
  public void setType(QuestionType type) {
    this.type = type;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  @Nullable
  public String getOptions() {
    return options;
  }

  public void setOptions(@Nullable String options) {
    this.options = options;
  }

  // Metode for å få en liste av alle alternativer
  public List<String> getOptionsList() {
    if (this.options != null && !this.options.isEmpty()) {
      return List.of(this.options.split("\\*"));
    }
    return new ArrayList<>();
  }
}
