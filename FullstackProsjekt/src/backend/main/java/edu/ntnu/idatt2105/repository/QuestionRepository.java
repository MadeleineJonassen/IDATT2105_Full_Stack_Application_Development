package edu.ntnu.idatt2105.repository;

import edu.ntnu.idatt2105.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Question} entities in the database.
 * Provides methods for querying and manipulating questions.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

  /**
   * Finds a question by its ID.
   *
   * @param id The ID of the question to find.
   * @return An {@link Optional} containing the question if found, or empty if not found.
   */
  Optional<Question> findById(Integer id);

  /**
   * Finds all questions associated with a given quiz ID.
   *
   * @param quizId The ID of the quiz to find questions for.
   * @return A list of {@link Question} entities associated with the given quiz ID.
   */
  List<Question> findAllByQuizId(Integer quizId);
}
