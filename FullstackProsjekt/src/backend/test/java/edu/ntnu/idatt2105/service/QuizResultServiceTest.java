package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuizResultDTO;
import edu.ntnu.idatt2105.exception.QuizNotFoundException;
import edu.ntnu.idatt2105.exception.UserNotFoundException;
import edu.ntnu.idatt2105.model.Quiz;
import edu.ntnu.idatt2105.model.QuizResult;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.QuizRepository;
import edu.ntnu.idatt2105.repository.QuizResultRepository;
import edu.ntnu.idatt2105.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuizResultServiceTest {

    @Mock
    private QuizResultRepository quizResultRepository;

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private QuizResultService quizResultService;

    @Test
    void findAllResultsForUserId_WhenResultsExist() {
        Integer userId = 1;
        when(quizResultRepository.findByUserId(userId)).thenReturn(Collections.singletonList(new QuizResult()));

        var results = quizResultService.findAllResultsForUserId(userId);

        assertFalse(results.isEmpty());
        verify(quizResultRepository).findByUserId(userId);
    }

    @Test
    void startQuizResult_WhenQuizAndUserExist() {
        QuizResultDTO creationDTO = new QuizResultDTO();
        creationDTO.setQuizId(1);
        creationDTO.setUserId(1);

        when(quizRepository.findById(creationDTO.getQuizId()))
                .thenReturn(Optional.of(new Quiz()));
        when(userRepository.findById(creationDTO.getUserId()))
                .thenReturn(Optional.of(new User()));

        assertDoesNotThrow(() -> quizResultService.startQuizResult(creationDTO));
    }

    @Test
    void startQuizResult_WhenQuizNotFound_ThrowsQuizNotFoundException() {
        QuizResultDTO creationDTO = new QuizResultDTO();
        creationDTO.setQuizId(1);
        creationDTO.setUserId(1);

        when(quizRepository.findById(creationDTO.getQuizId()))
                .thenReturn(Optional.empty());

        assertThrows(QuizNotFoundException.class, () -> quizResultService.startQuizResult(creationDTO));
    }

    @Test
    void completeQuiz_WhenQuizResultExists() {
        Integer quizResultId = 1;
        Quiz quiz = new Quiz();
        quiz.setId(1);
        User user = new User();
        user.setId(1);

        QuizResult quizResult = new QuizResult();
        quizResult.setId(quizResultId);
        quizResult.setQuiz(quiz);
        quizResult.setUser(user);
        quizResult.setStatus("Started");
        quizResult.setStartedAt(LocalDateTime.now());
        quizResult.setCompletedAt(LocalDateTime.now().plusHours(1));

        when(quizResultRepository.findById(quizResultId)).thenReturn(Optional.of(quizResult));
        when(quizResultRepository.save(any(QuizResult.class))).thenReturn(quizResult);

        QuizResultDTO resultDTO = assertDoesNotThrow(() -> quizResultService.completeQuiz(quizResultId));

        assertNotNull(resultDTO);
        assertEquals(quizResultId, resultDTO.getId());
        assertEquals(quiz.getId(), resultDTO.getQuizId());
        assertEquals(user.getId(), resultDTO.getUserId());
        assertEquals("Completed", resultDTO.getStatus());
    }

}
