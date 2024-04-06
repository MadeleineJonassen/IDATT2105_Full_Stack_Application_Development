package edu.ntnu.idatt2105.repository;

import edu.ntnu.idatt2105.model.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link QuestionAnswer} entities in the database.
 * Provides methods for querying and manipulating question answers.
 */
@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Integer> {

  /**
   * Finds all question answers associated with a given question ID.
   *
   * @param questionId The ID of the question to find answers for.
   * @return A list of {@link QuestionAnswer} entities associated with the given question ID,
   *         or an empty list if no answers are found.
   */
  List<QuestionAnswer> findByQuestionId(Integer questionId);

}

