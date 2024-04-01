package idatt2105.group11.idatt2105projectbackend.controller;

import idatt2105.group11.idatt2105projectbackend.dto.QuizResultDTO;
import idatt2105.group11.idatt2105projectbackend.model.QuizResult;
import idatt2105.group11.idatt2105projectbackend.service.QuizResultService;
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
    quizResultDTO.setScore(quizResult.getScore());
    quizResultDTO.setStatus(quizResult.getStatus());
    quizResultDTO.setStartedAt(quizResult.getStartedAt());
    quizResultDTO.setCompletedAt(quizResult.getCompletedAt());

    return quizResultDTO;
  }


}
