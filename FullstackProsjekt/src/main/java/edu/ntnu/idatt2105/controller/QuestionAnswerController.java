package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.dto.QuestionAnswerDTO;
import edu.ntnu.idatt2105.model.QuestionAnswer;
import edu.ntnu.idatt2105.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question-answers")
public class QuestionAnswerController {

  private final QuestionAnswerService questionAnswerService;

  @Autowired
  public QuestionAnswerController(QuestionAnswerService questionAnswerService) {
    this.questionAnswerService = questionAnswerService;
  }

  @PostMapping("/save")
  public QuestionAnswerDTO saveAnswer(@RequestParam Integer quizResultId,
                                      @RequestBody QuestionAnswerDTO answerDTO) {
    QuestionAnswer savedAnswer = questionAnswerService.saveAnswer(quizResultId, answerDTO);
    return convertToQuestionAnswerDTO(savedAnswer);
  }

  private QuestionAnswerDTO convertToQuestionAnswerDTO(QuestionAnswer questionAnswer) {
    QuestionAnswerDTO dto = new QuestionAnswerDTO();
    dto.setId(questionAnswer.getId());
    dto.setQuestionId(questionAnswer.getQuestion().getId());
    dto.setGivenAnswer(questionAnswer.getGivenAnswer());
    dto.setCorrect(questionAnswer.isCorrect());
    return dto;
  }
}
