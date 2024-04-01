package idatt2105.group11.idatt2105projectbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class MultipleChoiceQuestion extends Question {
  @ElementCollection
  @CollectionTable(name = "answer_options", joinColumns = @JoinColumn(name = "question_id"))
  @Column(name = "option")
  private List<String> answerOptions;

  @Column(nullable = false)
  private int correctAnswerIndex;

  public MultipleChoiceQuestion() {
  }

  public MultipleChoiceQuestion(Quiz quiz, String questionText, int score, List<String> answerOptions, int correctAnswerIndex) {
    super(quiz, questionText, score);
    this.answerOptions = answerOptions;
    this.correctAnswerIndex = correctAnswerIndex;
  }

  public List<String> getAnswerOptions() {
    return answerOptions;
  }

  public void setAnswerOptions(List<String> answerOptions) {
    this.answerOptions = answerOptions;
  }

  public int getCorrectAnswerIndex() {
    return correctAnswerIndex;
  }

  public void setCorrectAnswerIndex(int correctAnswerIndex) {
    this.correctAnswerIndex = correctAnswerIndex;
  }

  @Override
  public boolean checkAnswer(String answer) {
    try {
      int answerIndex = Integer.parseInt(answer);
      return answerIndex == correctAnswerIndex + 1;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}