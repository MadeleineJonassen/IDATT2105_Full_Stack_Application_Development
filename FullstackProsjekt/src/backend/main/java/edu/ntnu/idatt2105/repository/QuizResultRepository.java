package edu.ntnu.idatt2105.repository;

import edu.ntnu.idatt2105.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link QuizResult} entities in the database.
 * Provides methods for querying and manipulating quiz results.
 */
@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Integer> {

  /**
   * Finds all quiz results associated with a specific user.
   *
   * @param userId The ID of the user.
   * @return A list of {@link QuizResult} entities associated with the specified user.
   */
  List<QuizResult> findByUserId(Integer userId);

  /**
   * Finds the latest quiz result completed by a specific user, ordered by completion date in descending order.
   *
   * @param userId The ID of the user.
   * @return An {@link Optional} containing the latest {@link QuizResult} completed by the user,
   *         or empty if no quiz result is found.
   */
  Optional<QuizResult> findFirstByUserIdOrderByCompletedAtDesc(Integer userId);
}

