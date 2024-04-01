package edu.ntnu.idatt2105.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Quiz {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String title;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "quiz_id")
  private List<Question> questions;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id")
  private User creator;

  @Enumerated(EnumType.STRING)
  private QuizCategory category;

  @Enumerated(EnumType.STRING)
  private QuizDifficulty difficulty;

  public Quiz() {
  }

  public Quiz(String title, List<Question> questions, User creator, QuizCategory category, QuizDifficulty difficulty) {
    this.title = title;
    this.questions = questions;
    this.creator = creator;
    this.category = category;
    this.difficulty = difficulty;

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public User getCreator() {
    return creator;
  }

  public QuizCategory getCategory() {
    return category;
  }
  public void setCategory(QuizCategory category) {
    this.category = category;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public QuizDifficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(QuizDifficulty difficulty) {
    this.difficulty = difficulty;
  }
}
