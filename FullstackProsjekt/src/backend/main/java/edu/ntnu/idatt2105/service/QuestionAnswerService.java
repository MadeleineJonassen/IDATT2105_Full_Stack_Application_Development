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

@Service
public class QuestionAnswerService {

  private final QuestionAnswerRepository questionAnswerRepository;
  private final QuizResultRepository quizResultRepository;
  private final QuestionRepository questionRepository;

  @Autowired
  public QuestionAnswerService(QuestionAnswerRepository questionAnswerRepository,
                               QuizResultRepository quizResultRepository,
                               QuestionRepository questionRepository) {
    this.questionAnswerRepository = questionAnswerRepository;
    this.quizResultRepository = quizResultRepository;
    this.questionRepository = questionRepository;
  }

  public QuestionAnswer saveAnswer(Integer quizResultId, QuestionAnswerDTO answerDTO) {
    QuizResult quizResult = quizResultRepository.findById(quizResultId)
            .orElseThrow(() -> new EntityNotFoundException("QuizResult not found with id: " + quizResultId));

    Question question = questionRepository.findById(answerDTO.getQuestionId())
            .orElseThrow(() -> new QuizNotFoundException("Question not found with id: " + answerDTO.getQuestionId()));

    QuestionAnswer answer = new QuestionAnswer();
    answer.setQuestion(question);
    answer.setGivenAnswer(answerDTO.getGivenAnswer());
    answer.setQuizResult(quizResult);

    quizResult.addQuestionAnswer(answer);

    return questionAnswerRepository.save(answer);
  }
  public boolean isCorrect(QuestionAnswer questionAnswer) {
    return questionAnswer.getQuestion().getAnswer().equalsIgnoreCase(questionAnswer.getGivenAnswer());
  }
}
