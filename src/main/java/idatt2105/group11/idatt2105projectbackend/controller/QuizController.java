package idatt2105.group11.idatt2105projectbackend.controller;

import idatt2105.group11.idatt2105projectbackend.model.QuizResult;
import idatt2105.group11.idatt2105projectbackend.service.QuizService;
import idatt2105.group11.idatt2105projectbackend.model.QuestionAnswer;
import idatt2105.group11.idatt2105projectbackend.model.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

  private final QuizService quizService;

  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  @PostMapping("/{userId}")
  public ResponseEntity<Quiz> createQuiz(@PathVariable Long userId, @RequestBody Quiz quiz) {
    // Handle quiz creation with userId
    return ResponseEntity.ok(quizService.createQuiz(userId, quiz));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
    // Retrieve a quiz
    return ResponseEntity.ok(quizService.getQuiz(id));
  }

  @PostMapping("/{quizId}/answers")
  public ResponseEntity<Object> submitAnswers(@PathVariable Long quizId, @RequestBody List<QuestionAnswer> answers) {
    // Process answers and return results
    QuizResult result = quizService.submitAnswers(quizId, answers);
    return ResponseEntity.ok(result); // Assuming submitAnswers returns a QuizResult
  }
}

