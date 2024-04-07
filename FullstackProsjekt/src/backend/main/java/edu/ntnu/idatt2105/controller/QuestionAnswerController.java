package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.dto.QuestionAnswerDTO;
import edu.ntnu.idatt2105.exception.QuestionNotFoundException;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.model.QuestionAnswer;
import edu.ntnu.idatt2105.service.QuestionAnswerService;
import edu.ntnu.idatt2105.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class responsible for handling question-answer related endpoints.
 */
@RestController
@RequestMapping("/question-answers")
public class QuestionAnswerController {

  private final QuestionAnswerService questionAnswerService;
  private final QuestionService questionService;

  /**
   * Constructor for QuestionAnswerController.
   *
   * @param questionAnswerService An instance of QuestionAnswerService used for managing question answers.
   * @param questionService      An instance of QuestionService used for managing questions.
   */
  @Autowired
  public QuestionAnswerController(QuestionAnswerService questionAnswerService, QuestionService questionService) {
    this.questionAnswerService = questionAnswerService;
    this.questionService = questionService;
  }

  /**
   * Endpoint for saving a user's answer to a question.
   *
   * @param quizResultId The ID of the quiz result.
   * @param answerDTO    The answer provided by the user.
   * @return The saved question answer DTO.
   */
  @PostMapping("/save")
  public QuestionAnswerDTO saveAnswer(@RequestParam Integer quizResultId,
                                      @RequestBody QuestionAnswerDTO answerDTO) {
    QuestionAnswer savedAnswer = questionAnswerService.saveAnswer(quizResultId, answerDTO);
    return convertToQuestionAnswerDTO(savedAnswer);
  }

  /**
   * Endpoint for checking if a question answer is correct.
   *
   * @param questionAnswerId The ID of the question answer.
   * @return A ResponseEntity containing a boolean indicating whether the answer is correct or not.
   */
  @GetMapping("/is-correct/{questionAnswerId}")
  public ResponseEntity<Boolean> isCorrect(@PathVariable Integer questionAnswerId) {
    boolean correct = questionAnswerService.isCorrect(questionAnswerId);
    return new ResponseEntity<>(correct, HttpStatus.OK);
  }

  /**
   * Converts a QuestionAnswer entity to a QuestionAnswerDTO.
   *
   * @param questionAnswer The QuestionAnswer entity to convert.
   * @return The converted QuestionAnswerDTO.
   */
  private QuestionAnswerDTO convertToQuestionAnswerDTO(QuestionAnswer questionAnswer) {
    QuestionAnswerDTO dto = new QuestionAnswerDTO();
    dto.setId(questionAnswer.getId());
    dto.setQuestionId(questionAnswer.getQuestion().getId());
    dto.setGivenAnswer(questionAnswer.getGivenAnswer());
    dto.setCorrect(questionAnswer.isCorrect());
    return dto;
  }

  /**
   * Converts a QuestionAnswerDTO to a QuestionAnswer entity.
   *
   * @param questionAnswerDTO The QuestionAnswerDTO to convert.
   * @return The converted QuestionAnswer entity.
   * @throws QuestionNotFoundException If the associated question is not found.
   */
  private QuestionAnswer convertToQuestionAnswer(QuestionAnswerDTO questionAnswerDTO) {
    Optional<Question> questionOptional = Optional.ofNullable(questionService.findQuestionById(questionAnswerDTO.getQuestionId()));
    Question question = questionOptional.orElseThrow(() ->
            new QuestionNotFoundException("Question not found with ID: " + questionAnswerDTO.getQuestionId()));

    QuestionAnswer questionAnswer = new QuestionAnswer();
    questionAnswer.setId(questionAnswerDTO.getId());
    questionAnswer.setQuestion(question);
    questionAnswer.setGivenAnswer(questionAnswerDTO.getGivenAnswer());
    return questionAnswer;
  }
}
