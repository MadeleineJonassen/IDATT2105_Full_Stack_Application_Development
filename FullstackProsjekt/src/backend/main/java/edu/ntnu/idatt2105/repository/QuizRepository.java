package edu.ntnu.idatt2105.repository;

import edu.ntnu.idatt2105.model.Quiz;
import edu.ntnu.idatt2105.model.QuizCategory;
import edu.ntnu.idatt2105.model.QuizDifficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

  Optional<Quiz> findById(Integer id);

  List<Quiz> findAllByCategory(QuizCategory category);
  List<Quiz> findAllByDifficulty(QuizDifficulty difficulty);
  List<Quiz> findAllByCreatorId(Integer creatorId);

}
