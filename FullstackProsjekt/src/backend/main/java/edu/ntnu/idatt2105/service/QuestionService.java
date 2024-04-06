package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuestionDTO;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.model.QuestionType;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import edu.ntnu.idatt2105.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

  private final QuestionRepository questionRepository;
  private final QuizRepository quizRepository;

  @Autowired
  public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository) {
    this.questionRepository = questionRepository;
    this.quizRepository = quizRepository;
  }

  @Transactional
  public Question createOrUpdateQuestion(QuestionDTO questionDTO) {
      Question question;
      if (questionDTO.getId() != null) {
        // Oppdaterer et eksisterende spørsmål
        Optional<Question> optionalQuestion = questionRepository.findById(questionDTO.getId());
        if (!optionalQuestion.isPresent()) {
          return null; // Eller kaste en egendefinert unntak
        }
        question = optionalQuestion.get();
      } else {
        // Oppretter et nytt spørsmål
        question = new Question();
      }

      question.setQuestionText(questionDTO.getQuestionText());
      question.setType(questionDTO.getType());

      if (questionDTO.getType().equals(QuestionType.MULTIPLE_CHOICE)) {
        question.setOptions(questionDTO.getOptionsAsString());
      } else if (questionDTO.getType().equals(QuestionType.TRUE_OR_FALSE)) {
        question.setOptions("TRUE*FALSE");
      } else {
        question.setOptions(null);
      }
      question.setAnswer(questionDTO.getAnswer());
      question.setScore(questionDTO.getScore());
      question.setQuiz(quizRepository.findById(questionDTO.getQuizId()).orElse(null));

      return questionRepository.save(question);
    }

  public void deleteQuestion(Integer id) {
    questionRepository.deleteById(id);
  }

  public List<Question> findAllQuestionsByQuizId(Integer quizId) {
    return questionRepository.findAllByQuizId(quizId);
  }

  public Question findQuestionById(Integer id) {
    Optional<Question> question = questionRepository.findById(id);
    return question.orElse(null);
  }
}
