package idatt2105.group11.idatt2105projectbackend.controller;

import idatt2105.group11.idatt2105projectbackend.dto.QuestionDTO;
import idatt2105.group11.idatt2105projectbackend.model.MultipleChoiceQuestion;
import idatt2105.group11.idatt2105projectbackend.model.Question;
import idatt2105.group11.idatt2105projectbackend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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

  @PostMapping
  public QuestionDTO createMCQuestion(@RequestBody QuestionDTO questionDTO) {
    MultipleChoiceQuestion question = questionService.createMCQuestion(questionDTO);
    return convertToDTO(question);
  }

  @PostMapping("/update")
  public QuestionDTO updateQuestion(@RequestBody QuestionDTO questionDTO) {
    return questionService.updateQuestion(questionDTO);
  }

  @PostMapping("/delete")
  public void deleteQuestion(@RequestBody Map<String, Integer> payload) {
    questionService.deleteQuestion(payload.get("id"));
  }

  @GetMapping("/list")
  public List<QuestionDTO> getQuestionsByQuizId(@RequestParam Integer quizId) {
    return questionService.findAllQuestionsByQuizId(quizId).stream()
            .map((Question question) -> convertToDTO((MultipleChoiceQuestion) question))
            .collect(Collectors.toList());
  }

  public QuestionDTO convertToDTO(MultipleChoiceQuestion question) {
    QuestionDTO questionDTO = new QuestionDTO();
    questionDTO.setId(question.getId());
    questionDTO.setQuestionText(question.getQuestionText());
    questionDTO.setScore(question.getScore());
    questionDTO.setAnswerOptions(question.getAnswerOptions());
    questionDTO.setCorrectAnswerIndex(question.getCorrectAnswerIndex());
    return questionDTO;
  }
}
