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

@RestController
@RequestMapping("/question-answers")
public class QuestionAnswerController {

  private final QuestionAnswerService questionAnswerService;
  private QuestionService questionService;

  @Autowired
  public QuestionAnswerController(QuestionAnswerService questionAnswerService, QuestionService questionService) {
    this.questionAnswerService = questionAnswerService;
    this.questionService = questionService;
  }

  @PostMapping("/save")
  public QuestionAnswerDTO saveAnswer(@RequestParam Integer quizResultId,
                                      @RequestBody QuestionAnswerDTO answerDTO) {
    QuestionAnswer savedAnswer = questionAnswerService.saveAnswer(quizResultId, answerDTO);
    return convertToQuestionAnswerDTO(savedAnswer);
  }

  @GetMapping("/is-correct")
public ResponseEntity<Boolean> isCorrect(@RequestBody QuestionAnswerDTO answerDTO) {
    boolean correct = questionAnswerService.isCorrect(convertToQuestionAnswer(answerDTO));
    return new ResponseEntity<>(correct, HttpStatus.OK);
  }

  private QuestionAnswerDTO convertToQuestionAnswerDTO(QuestionAnswer questionAnswer) {
    QuestionAnswerDTO dto = new QuestionAnswerDTO();
    dto.setId(questionAnswer.getId());
    dto.setQuestionId(questionAnswer.getQuestion().getId());
    dto.setGivenAnswer(questionAnswer.getGivenAnswer());
    dto.setCorrect(questionAnswer.isCorrect());
    return dto;
  }

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
