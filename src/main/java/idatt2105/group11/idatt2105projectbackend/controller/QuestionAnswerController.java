package idatt2105.group11.idatt2105projectbackend.controller;

import idatt2105.group11.idatt2105projectbackend.dto.QuestionAnswerDTO;
import idatt2105.group11.idatt2105projectbackend.model.QuestionAnswer;
import idatt2105.group11.idatt2105projectbackend.service.QuestionAnswerService;
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
