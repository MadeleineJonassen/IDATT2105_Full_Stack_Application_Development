package idatt2105.group11.idatt2105projectbackend.repository;

import idatt2105.group11.idatt2105projectbackend.model.QuizCategory;
import idatt2105.group11.idatt2105projectbackend.model.Quiz;
import idatt2105.group11.idatt2105projectbackend.model.QuizDifficulty;
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
