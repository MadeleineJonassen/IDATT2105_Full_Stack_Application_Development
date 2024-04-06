package edu.ntnu.idatt2105.model;

import jakarta.persistence.*;

import java.util.List;


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

  public Quiz() {
  }

  public Quiz(String title, User creator, QuizCategory category, QuizDifficulty difficulty) {
    this.title = title;
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
