package edu.ntnu.idatt2105.repository;

import edu.ntnu.idatt2105.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Integer> {
  List<QuizResult> findByUserId(Integer userId);

  Optional<QuizResult> findFirstByUserIdOrderByCompletedAtDesc(Integer userId);


}
