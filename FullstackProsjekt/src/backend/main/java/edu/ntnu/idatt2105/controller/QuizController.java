package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.dto.QuizDTO;
import edu.ntnu.idatt2105.model.Quiz;
import edu.ntnu.idatt2105.model.QuizCategory;
import edu.ntnu.idatt2105.model.QuizDifficulty;
import edu.ntnu.idatt2105.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller class for managing quizzes.
 */
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

  private final QuizService quizService;

  /**
   * Constructor for QuizController.
   *
   * @param quizService The service for handling quiz-related operations.
   */
  @Autowired
  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  /**
   * Endpoint for creating a new quiz.
   *
   * @param quizDTO The DTO containing information for creating the quiz.
   * @return The created quiz DTO.
   */
  @PostMapping("/create")
  public QuizDTO createQuiz(@RequestBody QuizDTO quizDTO) {
    return quizService.createQuiz(quizDTO);
  }

  /**
   * Endpoint for updating an existing quiz.
   *
   * @param quizDTO The DTO containing information for updating the quiz.
   * @return The updated quiz DTO.
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
   * @param payload The request payload containing the ID of the quiz to delete.
   */
  @PostMapping("/delete")
  public void deleteQuiz(@RequestBody Map<String, Integer> payload) {
    quizService.deleteQuiz(payload.get("id"));
  }

  /**
   * Endpoint for retrieving a quiz by its ID.
   *
   * @param quizId The ID of the quiz to retrieve.
   * @return The quiz DTO.
   */
  @GetMapping("/quiz/{quizId}")
  public QuizDTO getQuizById(@PathVariable Integer quizId) {
    return convertToDTO(quizService.findQuizById(quizId));
  }

  /**
   * Endpoint for retrieving all quizzes.
   *
   * @return A list of quiz DTOs.
   */
  @GetMapping("/")
  public List<QuizDTO> getQuizzes() {
    return quizService.findAllQuizzes().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  /**
   * Endpoint for retrieving quizzes by category.
   *
   * @param category The category of quizzes to retrieve.
   * @return A list of quiz DTOs.
   */
  @GetMapping("/category")
  public List<QuizDTO> getQuizzesByCategory(@RequestParam String category) {
    return quizService.findAllQuizzesByCategory(QuizCategory.valueOf(category)).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  /**
   * Endpoint for retrieving quizzes by difficulty.
   *
   * @param difficulty The difficulty of quizzes to retrieve.
   * @return A list of quiz DTOs.
   */
  @GetMapping("/difficulty")
  public List<QuizDTO> getQuizzesByDifficulty(@RequestParam String difficulty) {
    return quizService.findAllQuizzesByDifficulty(QuizDifficulty.valueOf(difficulty)).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  /**
   * Endpoint for retrieving quizzes by creator ID.
   *
   * @param creatorId The ID of the creator of the quizzes to retrieve.
   * @return A list of quiz DTOs.
   */
  @GetMapping("/creator/{creatorId}")
  public List<QuizDTO> getQuizzesByCreatorId(@PathVariable Integer creatorId) {
    return quizService.findAllQuizzesByCreatorId(creatorId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  /**
   * Converts a Quiz entity to a QuizDTO.
   *
   * @param quiz The quiz entity to convert.
   * @return The corresponding QuizDTO.
   */
  private QuizDTO convertToDTO(Quiz quiz) {
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setId(quiz.getId());
    quizDTO.setTitle(quiz.getTitle());
    quizDTO.setCategory(quiz.getCategory());
    quizDTO.setDifficulty(quiz.getDifficulty());
    quizDTO.setCreatorId(quiz.getCreator().getId());
    return quizDTO;
  }
}