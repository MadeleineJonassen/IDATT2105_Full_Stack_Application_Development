package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuizDTO;
import edu.ntnu.idatt2105.exception.QuizNotFoundException;
import edu.ntnu.idatt2105.model.Quiz;
import edu.ntnu.idatt2105.model.QuizCategory;
import edu.ntnu.idatt2105.model.QuizDifficulty;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import edu.ntnu.idatt2105.repository.QuizRepository;
import edu.ntnu.idatt2105.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing quizzes.
 */
@Service
public class QuizService {

  private final QuizRepository quizRepository;
  private final QuestionRepository questionRepository;
  private final UserRepository userRepository;

  /**
   * Constructs a QuizService.
   *
   * @param quizRepository      The repository for quiz operations.
   * @param questionRepository The repository for question operations.
   * @param userRepository      The repository for user operations.
   */
  @Autowired
  public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, UserRepository userRepository) {
    this.quizRepository = quizRepository;
    this.questionRepository = questionRepository;
    this.userRepository = userRepository;
  }

  /**
   * Creates a new quiz.
   *
   * @param quizDTO The DTO containing information to create the quiz.
   * @return The created quiz.
   */
  @Transactional
  public QuizDTO createQuiz(QuizDTO quizDTO) {
    Quiz quiz = new Quiz();
    quiz.setTitle(quizDTO.getTitle());
    quiz.setCategory(quizDTO.getCategory());
    quiz.setDifficulty(quizDTO.getDifficulty());
    userRepository.findById(quizDTO.getCreatorId())
            .ifPresent(quiz::setCreator);

    quizRepository.save(quiz);
    return convertToQuizDTO(quiz);
  }

  /**
   * Updates an existing quiz.
   *
   * @param quizDTO The DTO containing information to update the quiz.
   * @return The updated quiz.
   */
  @Transactional
  public QuizDTO updateQuiz(QuizDTO quizDTO) {
    if (quizDTO.getId() == null) {
      throw new IllegalArgumentException("Quiz ID must be provided for update.");
    }

    Optional<Quiz> existingQuiz = quizRepository.findById(quizDTO.getId());
    if (existingQuiz.isPresent()) {
      Quiz quiz = existingQuiz.get();
      quiz.setTitle(quizDTO.getTitle());
      quiz.setCategory(quizDTO.getCategory());
      quiz.setDifficulty(quizDTO.getDifficulty());

      Quiz updatedQuiz = quizRepository.save(quiz);

      return convertToQuizDTO(updatedQuiz);
    } else {
      throw new QuizNotFoundException("Quiz with ID " + quizDTO.getId() + " not found.");
    }
  }

  /**
   * Deletes a quiz by its ID.
   *
   * @param id The ID of the quiz to delete.
   */
  public void deleteQuiz(Integer id) {
    Quiz quiz = quizRepository.findById(id)
            .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id " + id));

    questionRepository.deleteAll(questionRepository.findAllByQuizId(id));

    quizRepository.delete(quiz);
  }

  /**
   * Finds all quizzes.
   *
   * @return A list of all quizzes.
   */
  public List<Quiz> findAllQuizzes() {
    return quizRepository.findAll();
  }

  /**
   * Finds all quizzes by category.
   *
   * @param category The category of the quizzes to find.
   * @return A list of quizzes in the specified category.
   */
  public List<Quiz> findAllQuizzesByCategory(QuizCategory category) {
    return quizRepository.findAllByCategory(category);
  }

  /**
   * Finds all quizzes by difficulty.
   *
   * @param difficulty The difficulty of the quizzes to find.
   * @return A list of quizzes with the specified difficulty.
   */
  public List<Quiz> findAllQuizzesByDifficulty(QuizDifficulty difficulty) {
    return quizRepository.findAllByDifficulty(difficulty);
  }

  /**
   * Finds all quizzes by creator ID.
   *
   * @param creatorId The ID of the creator of the quizzes to find.
   * @return A list of quizzes created by the specified user.
   */
  public List<Quiz> findAllQuizzesByCreatorId(Integer creatorId) {
    return quizRepository.findAllByCreatorId(creatorId);
  }

  /**
   * Finds a quiz by its ID.
   *
   * @param id The ID of the quiz to find.
   * @return The quiz with the specified ID.
   */
  public Quiz findQuizById(Integer id) {
    return quizRepository.findById(id)
            .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id " + id));
  }

  private QuizDTO convertToQuizDTO(Quiz quiz) {
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setId(quiz.getId());
    quizDTO.setTitle(quiz.getTitle());
    quizDTO.setCategory(quiz.getCategory());
    quizDTO.setDifficulty(quiz.getDifficulty());
    quizDTO.setCreatorId(quiz.getCreator().getId());

    return quizDTO;
  }
}
