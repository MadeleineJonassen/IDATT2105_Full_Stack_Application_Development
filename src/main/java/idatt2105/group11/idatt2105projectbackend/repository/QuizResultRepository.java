package idatt2105.group11.idatt2105projectbackend.repository;

import idatt2105.group11.idatt2105projectbackend.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

  // Find all QuizResults for a specific user by the user's ID
  List<QuizResult> findByUserId(Long userId);

  // Find all QuizResults for a specific quiz by the quiz's ID
  List<QuizResult> findByQuizId(Long quizId);

}
