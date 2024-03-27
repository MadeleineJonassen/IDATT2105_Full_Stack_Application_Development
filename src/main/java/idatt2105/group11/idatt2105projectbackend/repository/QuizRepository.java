package idatt2105.group11.idatt2105projectbackend.repository;

import idatt2105.group11.idatt2105projectbackend.model.QuizCategory;
import idatt2105.group11.idatt2105projectbackend.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

  Optional<Quiz> findById(Long id);


  List<Quiz> findByTitleContainingIgnoreCase(String title);

  List<Quiz> findByCategory(QuizCategory category);
}
