package idatt2105.group11.idatt2105projectbackend.service;

import idatt2105.group11.idatt2105projectbackend.model.User;
import idatt2105.group11.idatt2105projectbackend.repository.UserRepository;
import idatt2105.group11.idatt2105projectbackend.model.QuestionAnswer;
import idatt2105.group11.idatt2105projectbackend.model.Quiz;
import idatt2105.group11.idatt2105projectbackend.model.QuizResult;
import idatt2105.group11.idatt2105projectbackend.repository.QuizRepository;
import idatt2105.group11.idatt2105projectbackend.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

  @Autowired
  private QuizRepository quizRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private QuizResultRepository quizResultRepository;

  public Quiz createQuiz(Long userId, Quiz quiz) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    quiz.setCreator(user);
    return quizRepository.save(quiz);
  }

  public QuizResult startQuiz(Long userId, Long quizId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));

    QuizResult quizResult = new QuizResult();
    quizResult.setUser(user);
    quizResult.setQuiz(quiz);
    quizResult.setAnswers(List.of());
    return quizResultRepository.save(quizResult);
  }

  public QuizResult submitAnswers(Long quizResultId, List<QuestionAnswer> answers) {
    QuizResult quizResult = quizResultRepository.findById(quizResultId).orElseThrow(() -> new RuntimeException("Quiz attempt not found"));
    quizResult.setAnswers(answers);
    return quizResultRepository.save(quizResult);
  }

  public Quiz getQuiz(Long id) {
    // Attempt to find the quiz by ID
    Optional<Quiz> quizOptional = quizRepository.findById(id);

    // Throw a custom exception or handle the case where the quiz isn't found
    return quizOptional.orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
  }
}
