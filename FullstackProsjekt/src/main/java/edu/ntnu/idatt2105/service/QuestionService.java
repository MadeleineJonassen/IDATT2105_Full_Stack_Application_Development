package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuestionDTO;
import edu.ntnu.idatt2105.exception.QuestionNotFoundException;
import edu.ntnu.idatt2105.model.MultipleChoiceQuestion;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

  private final QuestionRepository questionRepository;

  @Autowired
  public QuestionService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
    MultipleChoiceQuestion question = (MultipleChoiceQuestion) questionRepository.findById(questionDTO.getId())
            .orElseThrow(() -> new QuestionNotFoundException("Question not found with id " + questionDTO.getId()));

    question.setQuestionText(questionDTO.getQuestionText());
    question.setScore(questionDTO.getScore());
    question.setAnswerOptions(questionDTO.getAnswerOptions());
    question.setCorrectAnswerIndex(questionDTO.getCorrectAnswerIndex());

    MultipleChoiceQuestion updatedQuestion = questionRepository.save(question);
    return convertToQuestionDTO(updatedQuestion);
  }


  public void deleteQuestion(Integer id) {
    questionRepository.deleteById(id);
  }

  public List<Question> findAllQuestionsByQuizId(Integer quizId) {
    return questionRepository.findAllByQuizId(quizId);
  }

  @Transactional
  public MultipleChoiceQuestion createMCQuestion(QuestionDTO questionDTO) {
    MultipleChoiceQuestion question = new MultipleChoiceQuestion();
    question.setQuestionText(questionDTO.getQuestionText());
    question.setScore(questionDTO.getScore());
    question.setAnswerOptions(questionDTO.getAnswerOptions());
    question.setCorrectAnswerIndex(questionDTO.getCorrectAnswerIndex());
    return questionRepository.save(question);
  }

  private QuestionDTO convertToQuestionDTO(MultipleChoiceQuestion question) {
    QuestionDTO questionDTO = new QuestionDTO();
    questionDTO.setId(question.getId());
    questionDTO.setQuestionText(question.getQuestionText());
    questionDTO.setScore(question.getScore());
    questionDTO.setAnswerOptions(question.getAnswerOptions());
    questionDTO.setCorrectAnswerIndex(question.getCorrectAnswerIndex());

    return questionDTO;
  }


}