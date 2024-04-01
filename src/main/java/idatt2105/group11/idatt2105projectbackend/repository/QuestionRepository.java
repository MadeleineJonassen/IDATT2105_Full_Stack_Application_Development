package idatt2105.group11.idatt2105projectbackend.repository;

import idatt2105.group11.idatt2105projectbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

  Optional<Question> findById(Integer id);

  List<Question> findAllByQuizId(Integer quizId);
}
