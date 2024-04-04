package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.dto.QuestionDTO;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

  private final QuestionService questionService;

  @Autowired
  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @PostMapping("/save")
  public QuestionDTO saveQuestion(@RequestBody QuestionDTO questionDTO) {
    Question question = questionService.createOrUpdateQuestion(questionDTO);

    //TODO: make a mapper class to do this
    return new QuestionDTO(
            question.getId(),
            question.getQuestionText(),
            question.getType(),
            question.getAnswer(),
            question.getOptionsList(),
            question.getScore()
    );
  }

  @PostMapping("/delete")
  public void deleteQuestion(@RequestBody Map<String, Integer> payload) {
    questionService.deleteQuestion(payload.get("id"));
  }
}
