package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuestionAnswerDTO;
import edu.ntnu.idatt2105.exception.QuizNotFoundException;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.model.QuestionAnswer;
import edu.ntnu.idatt2105.model.QuizResult;
import edu.ntnu.idatt2105.repository.QuestionAnswerRepository;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import edu.ntnu.idatt2105.repository.QuizResultRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing question answers.
 */
@Service
public class QuestionAnswerService {

  private final QuestionAnswerRepository questionAnswerRepository;
  private final QuizResultRepository quizResultRepository;
  private final QuestionRepository questionRepository;

  /**
   * Constructs a QuestionAnswerService.
   *
   * @param questionAnswerRepository The repository for question answer operations.
   * @param quizResultRepository     The repository for quiz result operations.
   * @param questionRepository       The repository for question operations.
   */
  @Autowired
  public QuestionAnswerService(QuestionAnswerRepository questionAnswerRepository,
                               QuizResultRepository quizResultRepository,
                               QuestionRepository questionRepository) {
    this.questionAnswerRepository = questionAnswerRepository;
    this.quizResultRepository = quizResultRepository;
    this.questionRepository = questionRepository;
  }

  /**
   * Saves an answer to a question.
   *
   * @param quizResultId The ID of the quiz result.
   * @param answerDTO    The DTO containing the answer details.
   * @return The saved question answer.
   * @throws EntityNotFoundException if the quiz result or question is not found.
   */
  public QuestionAnswer saveAnswer(Integer quizResultId, QuestionAnswerDTO answerDTO) {
    QuizResult quizResult = quizResultRepository.findById(quizResultId)
            .orElseThrow(() -> new EntityNotFoundException("QuizResult not found with id: " + quizResultId));

    Question question = questionRepository.findById(answerDTO.getQuestionId())
            .orElseThrow(() -> new QuizNotFoundException("Question not found with id: " + answerDTO.getQuestionId()));

    QuestionAnswer answer = new QuestionAnswer();
    answer.setQuestion(question);
    answer.setGivenAnswer(answerDTO.getGivenAnswer());
    answer.setQuizResult(quizResult);

    return questionAnswerRepository.save(answer);
  }

  /**
   * Checks if a question answer is correct.
   *
   * @param questionAnswerId The ID of the question answer.
   * @return true if the answer is correct, false otherwise.
   * @throws EntityNotFoundException if the question answer is not found.
   */
  public boolean isCorrect(Integer questionAnswerId) {
    QuestionAnswer questionAnswer = questionAnswerRepository.findById(questionAnswerId)
            .orElseThrow(() -> new EntityNotFoundException("QuestionAnswer not found with id: " + questionAnswerId));
    return questionAnswer.isCorrect();
  }
}
