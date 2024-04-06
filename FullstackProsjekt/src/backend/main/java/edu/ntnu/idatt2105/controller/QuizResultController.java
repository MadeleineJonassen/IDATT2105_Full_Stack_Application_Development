package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.dto.QuizResultDTO;
import edu.ntnu.idatt2105.model.QuizResult;
import edu.ntnu.idatt2105.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class responsible for handling quiz result-related endpoints.
 */
@RestController
@RequestMapping("/api/results")
public class QuizResultController {

  private final QuizResultService quizResultService;

  /**
   * Constructor for QuizResultController.
   *
   * @param quizResultService An instance of QuizResultService used for managing quiz results.
   */
  @Autowired
  public QuizResultController(QuizResultService quizResultService) {
    this.quizResultService = quizResultService;
  }

  /**
   * Endpoint for starting a new quiz result.
   *
   * @param quizResultDTO The data for starting the quiz result.
   * @return The created quiz result DTO.
   */
  @PostMapping("/create")
  public QuizResultDTO startQuizResult(@RequestBody QuizResultDTO quizResultDTO) {
    return convertToQuizResultDTO(quizResultService.startQuizResult(quizResultDTO));
  }

  /**
   * Endpoint for completing a quiz.
   *
   * @param quizResultId The ID of the quiz result to complete.
   * @return The completed quiz result DTO.
   */
  @PostMapping("/complete/")
  public QuizResultDTO completeQuiz(@RequestParam Integer quizResultId) {
    return quizResultService.completeQuiz(quizResultId);
  }

  /**
   * Endpoint for retrieving all quiz results for a user.
   *
   * @param userId The ID of the user.
   * @return A list of quiz result DTOs for the user.
   */
  @GetMapping("/results")
  public List<QuizResultDTO> getQuizResultsForUser(@RequestParam("userId") Integer userId) {
    List<QuizResult> quizResults = quizResultService.findAllResultsForUserId(userId);
    return quizResults.stream()
            .map(this::convertToQuizResultDTO)
            .collect(Collectors.toList());
  }

  /**
   * Converts a QuizResult entity to a QuizResultDTO.
   *
   * @param quizResult The QuizResult entity to convert.
   * @return The converted QuizResultDTO.
   */
  public QuizResultDTO convertToQuizResultDTO(QuizResult quizResult) {
    QuizResultDTO quizResultDTO = new QuizResultDTO();
    quizResultDTO.setQuizId(quizResult.getQuiz().getId());
    quizResultDTO.setUserId(quizResult.getUser().getId());
    quizResultDTO.setTotalScore(quizResult.getScore());
    quizResultDTO.setStatus(quizResult.getStatus());
    quizResultDTO.setStartedAt(quizResult.getStartedAt());
    quizResultDTO.setCompletedAt(quizResult.getCompletedAt());

    return quizResultDTO;
  }
}
