package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.dto.QuestionDTO;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


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
  @PostMapping("/newQuestion")
  public QuestionDTO createNewQuestion(@RequestBody QuestionDTO questionDTO) {
    if (questionDTO.getId() != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should be null for new questions");
    }
    Question question = questionService.createQuestion(questionDTO);
    return mapQuestionToQuestionDTO(question);
  }

  /**
   * Endpoint for updating an existing question.
   * @param questionDTO The question data to be updated
   * @return The updated question DTO.
   */
  @PutMapping("/questions/{id}")
  public QuestionDTO updateExistingQuestion(@PathVariable Integer id, @RequestBody QuestionDTO questionDTO) {
    if (questionDTO.getId() == null || !questionDTO.getId().equals(id)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question ID mismatch");
    }
    Question question = questionService.updateQuestion(questionDTO);
    return mapQuestionToQuestionDTO(question);
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

  /**
   * Endpoint for retrieving all questions for a quiz.
   *
   * @param quizId The ID of the quiz.
   * @return A list of question DTOs for the quiz.
   */
  @GetMapping("/allQuestionsToAQuiz/{quizId}")
    public List<QuestionDTO> getAllQuestionsToAQuiz(@PathVariable Integer quizId) {
        List<Question> questions = questionService.findAllQuestionsToAQuiz(quizId);
        return questions.stream()
                .map(question -> new QuestionDTO(
                        question.getQuestionText(),
                        question.getType(),
                        question.getAnswer(),
                        question.getOptionsList(),
                        question.getScore(),
                        question.getQuiz().getId()
                ))
                .collect(Collectors.toList());
    }

  private QuestionDTO mapQuestionToQuestionDTO(Question question) {
    return new QuestionDTO(
            question.getQuestionText(),
            question.getType(),
            question.getAnswer(),
            question.getOptionsList(),
            question.getScore(),
            question.getQuiz().getId()
    );
  }

}
