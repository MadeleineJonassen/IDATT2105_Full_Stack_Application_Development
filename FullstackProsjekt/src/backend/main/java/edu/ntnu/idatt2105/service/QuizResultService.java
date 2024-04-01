package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuestionAnswerDTO;
import edu.ntnu.idatt2105.dto.QuizResultDTO;
import edu.ntnu.idatt2105.exception.QuestionNotFoundException;
import edu.ntnu.idatt2105.exception.QuizNotFoundException;
import edu.ntnu.idatt2105.exception.UserNotFoundException;
import edu.ntnu.idatt2105.model.*;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import edu.ntnu.idatt2105.repository.QuizRepository;
import edu.ntnu.idatt2105.repository.QuizResultRepository;
import edu.ntnu.idatt2105.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class QuizResultService {

  private final QuizResultRepository quizResultRepository;

  private final QuizRepository quizRepository;

  private final UserRepository userRepository;

  private final QuestionRepository questionRepository;

  @Autowired
  public QuizResultService(QuizResultRepository quizResultRepository, QuizRepository quizRepository, UserRepository userRepository, QuestionRepository questionRepository) {
    this.quizResultRepository = quizResultRepository;
    this.quizRepository = quizRepository;
    this.userRepository = userRepository;
    this.questionRepository = questionRepository;
  }


  public List<QuizResult> findAllResultsForUserId(Integer userId) {
    return quizResultRepository.findByUserId(userId);
  }

  public QuizResult startQuizResult(QuizResultDTO creationDTO) {
    Quiz quiz = quizRepository.findById(creationDTO.getQuizId())
            .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id: " + creationDTO.getQuizId()));
    User user = userRepository.findById(creationDTO.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + creationDTO.getUserId()));

    QuizResult quizResult = new QuizResult();
    quizResult.setQuiz(quiz);
    quizResult.setUser(user);
    quizResult.setStatus("Påbegynt");
    quizResult.setStartedAt(LocalDateTime.now());
    quizResult.setCompletedAt(creationDTO.getCompletedAt());
    return quizResultRepository.save(quizResult);
  }

  public QuizResultDTO completeQuiz(Integer quizResultId) {
    QuizResult quizResult = quizResultRepository.findById(quizResultId)
            .orElseThrow(() -> new EntityNotFoundException("QuizResult not found with id: " + quizResultId));

    quizResult.setCompletedAt(LocalDateTime.now());
    quizResult.setStatus("Fullført");
    QuizResult updatedQuizResult = quizResultRepository.save(quizResult);

    return convertToQuizResultDTO(updatedQuizResult);
  }


  public QuizResultDTO findLatestQuizResultForUser(Integer userId) {
    QuizResult latestQuizResult = quizResultRepository.findFirstByUserIdOrderByCompletedAtDesc(userId)
            .orElseThrow(() -> new QuizNotFoundException("No quiz results found for user with id: " + userId));
    return convertToQuizResultDTO(latestQuizResult);
  }

  private QuizResultDTO convertToQuizResultDTO(QuizResult quizResult) {
    QuizResultDTO quizResultDTO = new QuizResultDTO();
    quizResultDTO.setId(quizResult.getId());
    quizResultDTO.setQuizId(quizResult.getQuiz().getId());
    quizResultDTO.setUserId(quizResult.getUser().getId());
    quizResultDTO.setScore(quizResult.getScore());
    quizResultDTO.setStatus(quizResult.getStatus());
    quizResultDTO.setStartedAt(quizResult.getStartedAt());
    quizResultDTO.setCompletedAt(quizResult.getCompletedAt());

    // Anta at vi også trenger å konvertere listen av QuestionAnswer til QuestionAnswerDTO
    // Dette krever at vi har en metode for å gjøre denne konverteringen:
    List<QuestionAnswerDTO> answerDTOs = quizResult.getAnswers().stream()
            .map(this::convertToQuestionAnswerDTO)
            .collect(Collectors.toList());
    quizResultDTO.setAnswers(answerDTOs);

    return quizResultDTO;
  }

  private QuestionAnswerDTO convertToQuestionAnswerDTO(QuestionAnswer questionAnswer) {
    QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO();
    questionAnswerDTO.setId(questionAnswer.getId());
    questionAnswerDTO.setQuestionId(questionAnswer.getQuestion().getId());
    questionAnswerDTO.setGivenAnswer(questionAnswer.getGivenAnswer());
    questionAnswerDTO.setCorrect(questionAnswer.isCorrect());

    return questionAnswerDTO;
  }





  private List<QuestionAnswer> convertToQuestionAnswers(List<QuestionAnswerDTO> answerDTOs, QuizResult quizResult) {
    List<QuestionAnswer> answers = new ArrayList<>();
    for (QuestionAnswerDTO answerDTO : answerDTOs) {
      QuestionAnswer answer = new QuestionAnswer();

      MultipleChoiceQuestion question = (MultipleChoiceQuestion) questionRepository.findById(answerDTO.getQuestionId())
              .orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + answerDTO.getQuestionId()));
      answer.setQuestion(question);
      answer.setGivenAnswer(answerDTO.getGivenAnswer());
      answer.setCorrect(question.checkAnswer(answerDTO.getGivenAnswer()));
      answer.setQuizResult(quizResult);

      answers.add(answer);
    }
    return answers;
  }
}
