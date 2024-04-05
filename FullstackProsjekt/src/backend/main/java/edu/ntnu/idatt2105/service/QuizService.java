package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuizDTO;
import edu.ntnu.idatt2105.exception.QuizNotFoundException;
import edu.ntnu.idatt2105.model.*;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import edu.ntnu.idatt2105.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
  public QuizDTO createQuiz(QuizDTO quizDTO) {
    Quiz quiz = new Quiz();
    quiz.setTitle(quizDTO.getTitle());
    quiz.setCategory(quizDTO.getCategory());
    quiz.setDifficulty(quizDTO.getDifficulty());

    //creator

    quizRepository.save(quiz);
    return convertToQuizDTO(quiz);
  }

  @Transactional
  public QuizDTO updateQuiz(QuizDTO quizDTO) {
    if (quizDTO.getId() == null) {
      throw new IllegalArgumentException("Quiz ID must be provided for update.");
    }

    Optional<Quiz> existingQuiz = quizRepository.findById(quizDTO.getId());
    if (existingQuiz.isPresent()) {
      Quiz quiz = existingQuiz.get();
      quiz.setTitle(quizDTO.getTitle());
      quiz.setCategory(quizDTO.getCategory());
      quiz.setDifficulty(quizDTO.getDifficulty());

      Quiz updatedQuiz = quizRepository.save(quiz);



      return convertToQuizDTO(updatedQuiz);
    } else {
      throw new QuizNotFoundException("Quiz with ID " + quizDTO.getId() + " not found.");
    }
  }

  public void deleteQuiz(Integer id) {
    Quiz quiz = quizRepository.findById(id)
            .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id " + id));

    questionRepository.deleteAll(questionRepository.findAllByQuizId(id));

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
    quizDTO.setDifficulty(quiz.getDifficulty());
    //creator
    return quizDTO;
  }


}

