package edu.ntnu.idatt2105.controller;


import edu.ntnu.idatt2105.dto.QuizResultDTO;
import edu.ntnu.idatt2105.model.QuizResult;
import edu.ntnu.idatt2105.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/results")
public class QuizResultController {

  private final QuizResultService quizResultService;

  @Autowired
  public QuizResultController(QuizResultService quizResultService) {
    this.quizResultService = quizResultService;
  }

  @PostMapping("/create")
  public QuizResultDTO startQuizResult(@RequestBody QuizResultDTO quizResultDTO) {
    return convertToQuizResultDTO(quizResultService.startQuizResult(quizResultDTO));
  }

  @PostMapping("/complete/")
  public QuizResultDTO completeQuiz(@RequestParam Integer quizResultId) {
    return quizResultService.completeQuiz(quizResultId);
  }

  @GetMapping("/latest-result")
  public QuizResultDTO getLatestQuizResultForUser(@RequestParam Integer userId) {
    return quizResultService.findLatestQuizResultForUser(userId);
  }


  @GetMapping("/results")
  public List<QuizResultDTO> getQuizResultsForUser(@RequestParam("userId") Integer userId) {
    List<QuizResult> quizResults = quizResultService.findAllResultsForUserId(userId);
    return quizResults.stream()
            .map(this::convertToQuizResultDTO)
            .collect(Collectors.toList());
  }

  public QuizResultDTO convertToQuizResultDTO(QuizResult quizResult) {
    QuizResultDTO quizResultDTO = new QuizResultDTO();
    quizResultDTO.setQuizId(quizResult.getQuiz().getId());
    quizResultDTO.setUserId(quizResult.getUser().getId());
    quizResultDTO.setTotalScore(quizResult.getScore());
    quizResultDTO.setStatus(quizResult.getStatus());
    quizResultDTO.setStartedAt(quizResult.getStartedAt());
    quizResultDTO.setCompletedAt(quizResult.getCompletedAt());

    return quizResultDTO;
  }


}
