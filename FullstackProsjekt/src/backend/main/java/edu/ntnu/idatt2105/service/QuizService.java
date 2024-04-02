package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuizDTO;
import edu.ntnu.idatt2105.exception.QuizNotFoundException;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.model.Quiz;
import edu.ntnu.idatt2105.model.QuizCategory;
import edu.ntnu.idatt2105.model.QuizDifficulty;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import edu.ntnu.idatt2105.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {
  @Autowired
  private QuizRepository quizRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
    this.quizRepository = quizRepository;
    this.questionRepository = questionRepository;
  }

  @Transactional
  public Quiz createQuiz(QuizDTO quizDTO) {
    Quiz quiz = new Quiz();
    quiz.setTitle(quizDTO.getTitle());
    quiz.setCategory(quizDTO.getCategory());

    List<Question> questions = questionRepository.findAllById(quizDTO.getQuestionIds());
    quiz.setQuestions(questions);

    return quizRepository.save(quiz);
  }

  public QuizDTO updateQuiz(QuizDTO quizDTO) {
    Quiz quiz = quizRepository.findById(quizDTO.getId())
            .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id " + quizDTO.getId()));

    quiz.setTitle(quizDTO.getTitle());
    quiz.setCategory(quizDTO.getCategory());

    Quiz updatedQuiz = quizRepository.save(quiz);
    return convertToQuizDTO(updatedQuiz);
  }

  public void deleteQuiz(Integer id) {
    Quiz quiz = quizRepository.findById(id)
            .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id " + id));

    questionRepository.deleteAll(quiz.getQuestions());

    quizRepository.delete(quiz);
  }

  public List<Quiz> findAllQuizzes() {
    return quizRepository.findAll();
  }

  public List<Quiz> findAllQuizzesByCategory(QuizCategory category) {
    return quizRepository.findAllByCategory(category);
  }
  public List<Quiz> findAllQuizzesByDifficulty(QuizDifficulty difficulty) {
    return quizRepository.findAllByDifficulty(difficulty);
  }
  public List<Quiz> findAllQuizzesByCreatorId(Integer creatorId) {
    return quizRepository.findAllByCreatorId(creatorId);
  }

  public Quiz findQuizById(Integer id) {
    return quizRepository.findById(id)
            .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id " + id));
  }

  public QuizDTO convertToQuizDTO(Quiz quiz) {
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setId(quiz.getId());
    quizDTO.setTitle(quiz.getTitle());
    quizDTO.setCategory(quiz.getCategory());

    List<Integer> questionIds = quiz.getQuestions().stream()
            .map(Question::getId)
            .collect(Collectors.toList());
    quizDTO.setQuestionIds(questionIds);

    return quizDTO;
  }


}

