package edu.ntnu.idatt2105.repository;

import edu.ntnu.idatt2105.model.Quiz;
import edu.ntnu.idatt2105.model.QuizCategory;
import edu.ntnu.idatt2105.model.QuizDifficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Quiz} entities in the database.
 * Provides methods for querying and manipulating quizzes.
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

  /**
   * Finds a quiz by its ID.
   *
   * @param id The ID of the quiz to find.
   * @return An {@link Optional} containing the quiz if found, or empty if not found.
   */
  Optional<Quiz> findById(Integer id);

  /**
   * Finds all quizzes associated with a given category.
   *
   * @param category The category of the quizzes to find.
   * @return A list of {@link Quiz} entities associated with the given category.
   */
  List<Quiz> findAllByCategory(QuizCategory category);

  /**
   * Finds all quizzes with a given difficulty level.
   *
   * @param difficulty The difficulty level of the quizzes to find.
   * @return A list of {@link Quiz} entities with the given difficulty level.
   */
  List<Quiz> findAllByDifficulty(QuizDifficulty difficulty);

  /**
   * Finds all quizzes created by a specific user.
   *
   * @param creatorId The ID of the user who created the quizzes.
   * @return A list of {@link Quiz} entities created by the specified user.
   */
  List<Quiz> findAllByCreatorId(Integer creatorId);
}

