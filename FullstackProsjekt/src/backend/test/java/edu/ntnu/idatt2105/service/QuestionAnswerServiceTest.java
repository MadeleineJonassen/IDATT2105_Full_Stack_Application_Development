package edu.ntnu.idatt2105.service;


import edu.ntnu.idatt2105.dto.QuestionAnswerDTO;
import edu.ntnu.idatt2105.exception.QuizNotFoundException;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.model.QuestionAnswer;
import edu.ntnu.idatt2105.model.QuestionType;
import edu.ntnu.idatt2105.model.QuizResult;
import edu.ntnu.idatt2105.repository.QuestionAnswerRepository;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import edu.ntnu.idatt2105.repository.QuizResultRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionAnswerServiceTest {

    @Mock
    private QuestionAnswerRepository questionAnswerRepository;

    @Mock
    private QuizResultRepository quizResultRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionAnswerService questionAnswerService;

    // Instances for test data
    private QuizResult quizResult;
    private Question question;
    private QuestionAnswerDTO answerDTO;

    @BeforeEach
    void setUp() {
        // Initialize your test data here
        quizResult = new QuizResult();
        question = new Question();
        answerDTO = new QuestionAnswerDTO();
    }

    @Test
    void saveAnswer_Success() {
        // Setup
        int quizResultId = 1;
        int questionId = 1;

        quizResult.setId(quizResultId);
        question.setId(questionId);

        answerDTO.setQuestionId(questionId);
        answerDTO.setGivenAnswer("Test Answer");

        when(quizResultRepository.findById(quizResultId)).thenReturn(Optional.of(quizResult));
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));
        when(questionAnswerRepository.save(any(QuestionAnswer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Execute
        QuestionAnswer savedAnswer = questionAnswerService.saveAnswer(quizResultId, answerDTO);

        // Verify
        assertNotNull(savedAnswer);
        assertEquals("Test Answer", savedAnswer.getGivenAnswer());
        assertEquals(question, savedAnswer.getQuestion());
        assertEquals(quizResult, savedAnswer.getQuizResult());
    }

    @Test
    void saveAnswer_QuizResultNotFound_ThrowsEntityNotFoundException() {
        when(quizResultRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> questionAnswerService.saveAnswer(1, new QuestionAnswerDTO()));
    }

    @Test
    void saveAnswer_QuestionNotFound_ThrowsQuizNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> questionAnswerService.saveAnswer(1, new QuestionAnswerDTO()));
    }
    @Test
    void isCorrect_ReturnsTrue() {
        // Setup
        Question question = new Question();
        question.setAnswer("Test Answer");

        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setQuestion(question);
        questionAnswer.setId(1);
        questionAnswer.setGivenAnswer("Test Answer");




        when(questionAnswerRepository.findById(1)).thenReturn(Optional.of(questionAnswer));

        // Execute
        boolean result = questionAnswerService.isCorrect(1);

        // Verify
        assertTrue(result);
    }

    @Test
    void isCorrect_QuestionAnswerNotFound_ThrowsEntityNotFoundException() {
        when(questionAnswerRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> questionAnswerService.isCorrect(1));
    }


}
