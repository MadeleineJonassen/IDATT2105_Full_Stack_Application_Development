package idatt2105.group11.idatt2105projectbackend.service;

import idatt2105.group11.idatt2105projectbackend.dto.QuestionAnswerDTO;
import idatt2105.group11.idatt2105projectbackend.exception.QuizNotFoundException;
import idatt2105.group11.idatt2105projectbackend.model.Question;
import idatt2105.group11.idatt2105projectbackend.model.QuestionAnswer;
import idatt2105.group11.idatt2105projectbackend.model.QuizResult;
import idatt2105.group11.idatt2105projectbackend.repository.QuestionAnswerRepository;
import idatt2105.group11.idatt2105projectbackend.repository.QuestionRepository;
import idatt2105.group11.idatt2105projectbackend.repository.QuizResultRepository;
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
    answer.setCorrect(question.checkAnswer(answerDTO.getGivenAnswer()));
    answer.setQuizResult(quizResult);

    quizResult.addQuestionAnswer(answer);

    return questionAnswerRepository.save(answer);
  }
}
