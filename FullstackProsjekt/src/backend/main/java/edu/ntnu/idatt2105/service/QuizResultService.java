package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuizResultDTO;
import edu.ntnu.idatt2105.exception.QuizNotFoundException;
import edu.ntnu.idatt2105.exception.UserNotFoundException;
import edu.ntnu.idatt2105.model.Quiz;
import edu.ntnu.idatt2105.model.QuizResult;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.QuizRepository;
import edu.ntnu.idatt2105.repository.QuizResultRepository;
import edu.ntnu.idatt2105.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing quiz results.
 */
@Service
public class QuizResultService {

  private final QuizResultRepository quizResultRepository;
  private final QuizRepository quizRepository;
  private final UserRepository userRepository;

  /**
   * Constructs a QuizResultService.
   *
   * @param quizResultRepository The repository for quiz result operations.
   * @param quizRepository       The repository for quiz operations.
   * @param userRepository       The repository for user operations.
   */
  @Autowired
  public QuizResultService(QuizResultRepository quizResultRepository, QuizRepository quizRepository, UserRepository userRepository) {
    this.quizResultRepository = quizResultRepository;
    this.quizRepository = quizRepository;
    this.userRepository = userRepository;
  }

  /**
   * Finds all quiz results for a given user ID.
   *
   * @param userId The ID of the user.
   * @return A list of quiz results for the user.
   */
  public List<QuizResult> findAllResultsForUserId(Integer userId) {
    return quizResultRepository.findByUserId(userId);
  }

  /**
   * Starts a quiz result.
   *
   * @param creationDTO The DTO containing information to create the quiz result.
   * @return The created quiz result.
   */
  public QuizResult startQuizResult(QuizResultDTO creationDTO) {
    Quiz quiz = quizRepository.findById(creationDTO.getQuizId())
            .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id: " + creationDTO.getQuizId()));
    User user = userRepository.findById(creationDTO.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + creationDTO.getUserId()));

    QuizResult quizResult = new QuizResult();
    quizResult.setQuiz(quiz);
    quizResult.setUser(user);
    quizResult.setStatus("Started");
    quizResult.setStartedAt(LocalDateTime.now());
    return quizResultRepository.save(quizResult);
  }

  /**
   * Completes a quiz result.
   *
   * @param quizResultId The ID of the quiz result to complete.
   * @return The completed quiz result.
   */
  public QuizResultDTO completeQuiz(Integer quizResultId) {
    QuizResult quizResult = quizResultRepository.findById(quizResultId)
            .orElseThrow(() -> new EntityNotFoundException("QuizResult not found with id: " + quizResultId));

    quizResult.setCompletedAt(LocalDateTime.now());
    quizResult.setStatus("Completed");
    QuizResult updatedQuizResult = quizResultRepository.save(quizResult);

    return convertToQuizResultDTO(updatedQuizResult);
  }

  private QuizResultDTO convertToQuizResultDTO(QuizResult quizResult) {
    QuizResultDTO quizResultDTO = new QuizResultDTO();
    quizResultDTO.setId(quizResult.getId());
    quizResultDTO.setQuizId(quizResult.getQuiz().getId());
    quizResultDTO.setUserId(quizResult.getUser().getId());
    quizResultDTO.setTotalScore(quizResult.getTotalScore());
    quizResultDTO.setStatus(quizResult.getStatus());
    quizResultDTO.setStartedAt(quizResult.getStartedAt());
    quizResultDTO.setCompletedAt(quizResult.getCompletedAt());

    return quizResultDTO;
  }
}
