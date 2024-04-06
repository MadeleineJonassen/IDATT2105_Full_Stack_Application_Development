package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.dto.QuizDTO;
import edu.ntnu.idatt2105.model.Quiz;
import edu.ntnu.idatt2105.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * Controller class responsible for handling quiz-related endpoints.
 */
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

  private final QuizService quizService;

  /**
   * Constructor for QuizController.
   *
   * @param quizService An instance of QuizService used for managing quizzes.
   */
  @Autowired
  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  /**
   * Endpoint for creating a new quiz.
   *
   * @param quizDTO The quiz data to be created.
   * @return The created quiz DTO.
   */
  @PostMapping("/create")
  public QuizDTO createQuiz(@RequestBody QuizDTO quizDTO) {
    return quizService.createQuiz(quizDTO);
  }

  /**
   * Endpoint for updating an existing quiz.
   *
   * @param quizDTO The quiz data to be updated.
   * @return The updated quiz DTO.
   * @throws IllegalArgumentException If quiz ID is not provided for update.
   */
  @PostMapping("/update")
  public QuizDTO updateQuiz(@RequestBody QuizDTO quizDTO) {
    if (quizDTO.getId() == null) {
      throw new IllegalArgumentException("Quiz ID must be provided for update.");
    }
    return quizService.updateQuiz(quizDTO);
  }

  /**
   * Endpoint for deleting a quiz.
   *
   * @param payload A map containing the ID of the quiz to be deleted.
   */
  @PostMapping("/delete")
  public void deleteQuiz(@RequestBody Map<String, Integer> payload) {
    quizService.deleteQuiz(payload.get("id"));
  }

  /**
   * Endpoint for retrieving a quiz by ID.
   *
   * @param quizId The ID of the quiz to retrieve.
   * @return The retrieved quiz DTO.
   */
  @GetMapping("/quiz/{quizId}")
  public QuizDTO getQuizById(@PathVariable Integer quizId) {
    return convertToDTO(quizService.findQuizById(quizId));
  }

  // Other endpoint methods...

  /**
   * Converts a Quiz entity to a QuizDTO.
   *
   * @param quiz The Quiz entity to convert.
   * @return The converted QuizDTO.
   */
  public QuizDTO convertToDTO(Quiz quiz) {
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setId(quiz.getId());
    quizDTO.setTitle(quiz.getTitle());
    quizDTO.setCategory(quiz.getCategory());
    return quizDTO;
  }
}
