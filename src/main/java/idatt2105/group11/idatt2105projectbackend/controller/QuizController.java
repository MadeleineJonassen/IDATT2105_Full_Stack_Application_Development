package idatt2105.group11.idatt2105projectbackend.controller;

import idatt2105.group11.idatt2105projectbackend.dto.*;
import idatt2105.group11.idatt2105projectbackend.exception.UserNotFoundException;
import idatt2105.group11.idatt2105projectbackend.model.*;
import idatt2105.group11.idatt2105projectbackend.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/quiz")
public class QuizController {

  private final QuizService quizService;

  @Autowired
  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  @PostMapping("/create")
  public QuizDTO createQuiz(@RequestBody QuizDTO quizDTO) {
    Quiz quiz = quizService.createQuiz(quizDTO);
    return convertToDTO(quiz);
  }

  @PostMapping("/update")
  public QuizDTO updateQuiz(@RequestBody QuizDTO quizDTO) {
    return quizService.updateQuiz(quizDTO);
  }

  @PostMapping("/delete")
  public void deleteQuiz(@RequestBody Map<String, Integer> payload) {
    quizService.deleteQuiz(payload.get("id"));
  }

  @GetMapping("/quiz")
  public QuizDTO getQuizById(@RequestParam Integer quizId) {
    return convertToDTO(quizService.findQuizById(quizId));
  }


  @GetMapping("/")
  public List<QuizDTO> getQuizzes() {
    return quizService.findAllQuizzes().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @GetMapping("/category")
  public List<QuizDTO> getQuizzesByCategory(@RequestParam String category) {
    return quizService.findAllQuizzesByCategory(QuizCategory.valueOf(category)).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @GetMapping("/difficulty")
  public List<QuizDTO> getQuizzesByDifficulty(@RequestParam String difficulty) {
    return quizService.findAllQuizzesByDifficulty(QuizDifficulty.valueOf(difficulty)).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @GetMapping("/creator")
  public List<QuizDTO> getQuizzesByCreatorId(@RequestParam Integer creatorId) {
    return quizService.findAllQuizzesByCreatorId(creatorId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  public QuizDTO convertToDTO(Quiz quiz) {
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setId(quiz.getId());
    quizDTO.setTitle(quiz.getTitle());
    quizDTO.setCategory(quiz.getCategory());
    List<Integer> questionIds = quiz.getQuestions().stream()
            .map(Question::getId)
            .collect(Collectors.toList());
    quizDTO.setQuestionIds(questionIds);
    return quizDTO;
  }

}
