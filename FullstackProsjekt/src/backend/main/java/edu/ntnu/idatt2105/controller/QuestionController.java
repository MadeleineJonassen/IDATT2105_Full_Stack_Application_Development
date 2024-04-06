package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.dto.QuestionDTO;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller class responsible for handling question-related endpoints.
 */
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

  private final QuestionService questionService;

  /**
   * Constructor for QuestionController.
   *
   * @param questionService An instance of QuestionService used for managing questions.
   */
  @Autowired
  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  /**
   * Endpoint for saving a question.
   *
   * @param questionDTO The question data to be saved.
   * @return The saved question DTO.
   */
  @PostMapping("/save")
  public QuestionDTO saveQuestion(@RequestBody QuestionDTO questionDTO) {
    Question question = questionService.createOrUpdateQuestion(questionDTO);

    // TODO: make a mapper class to do this
    return new QuestionDTO(
            question.getId(),
            question.getQuestionText(),
            question.getType(),
            question.getAnswer(),
            question.getOptionsList(),
            question.getScore(),
            question.getQuiz().getId()
    );
  }

  /**
   * Endpoint for retrieving a question by ID.
   *
   * @param questionId The ID of the question to retrieve.
   * @return The retrieved question DTO.
   */
  @GetMapping("/get/{questionId}")
  public QuestionDTO getQuestion(@PathVariable Integer questionId) {
    Question question = questionService.findQuestionById(questionId);
    return new QuestionDTO(
            question.getId(),
            question.getQuestionText(),
            question.getType(),
            question.getAnswer(),
            question.getOptionsList(),
            question.getScore(),
            question.getQuiz().getId()
    );
  }

  /**
   * Endpoint for deleting a question by ID.
   *
   * @param questionId The ID of the question to delete.
   */
  @PostMapping("/delete/{questionId}")
  public void deleteQuestion(@PathVariable Integer questionId) {
    questionService.deleteQuestion(questionId);
  }
}
