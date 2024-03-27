package idatt2105.group11.idatt2105projectbackend.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Quiz {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "quiz_id")
  private List<Question> questions;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id")
  private User creator;

  @Enumerated(EnumType.STRING)
  private QuizCategory category;

  public Quiz() {
  }

  public Quiz(String title, List<Question> questions, User creator, QuizCategory category) {
    this.title = title;
    this.questions = questions;
    this.creator = creator;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
}
