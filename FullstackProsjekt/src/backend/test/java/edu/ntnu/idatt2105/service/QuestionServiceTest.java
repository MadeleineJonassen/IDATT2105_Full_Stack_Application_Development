/*
package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuestionDTO;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.model.QuestionType;
import edu.ntnu.idatt2105.model.Quiz;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import edu.ntnu.idatt2105.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {


    private QuestionService questionService;

    private QuestionRepository questionRepository;

    private QuizRepository quizRepository;

    private Quiz quiz;

    @BeforeEach
    void setUp() {
        questionRepository = mock(QuestionRepository.class);
        quizRepository = mock(QuizRepository.class);
        questionService = new QuestionService(questionRepository, quizRepository);
        quiz = new Quiz();
        quiz.setId(1);

    }

    @Test
    void testCreateQuestion_NewMultipleChoice() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setType(QuestionType.MULTIPLE_CHOICE);
        questionDTO.setQuestionText("What is the largest planet?");
        questionDTO.setOptionsFromString("Earth*Mars*Jupiter*Venus");
        questionDTO.setAnswer("Jupiter");
        questionDTO.setScore(2);
        questionDTO.setQuizId(1);

        when(questionRepository.save(any(Question.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Question result = questionService.createQuestion(questionDTO);

        verify(questionRepository).save(any(Question.class));

        assertNotNull(result);
        assertEquals(1,result.getId());
        assertEquals("Jupiter", result.getAnswer());
        assertEquals(2, result.getScore());
        assertEquals("What is the largest planet?", result.getQuestionText());
        assertEquals(QuestionType.MULTIPLE_CHOICE, result.getType());
        assertEquals("Earth*Mars*Jupiter*Venus", result.getOptions());
    }

    @Test
    void testCreateOrUpdateQuestion_TrueOrFalse() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setType(QuestionType.TRUE_OR_FALSE);
        questionDTO.setQuestionText("Is Jupiter the largest planet?");
        questionDTO.setAnswer("TRUE");
        questionDTO.setScore(5);
        questionDTO.setQuizId(1);

        when(questionRepository.save(any(Question.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Question result = questionService.createQuestion(questionDTO);

        verify(questionRepository).save(any(Question.class));

        assertNotNull(result);
        assertEquals("TRUE", result.getAnswer());
        assertEquals(5, result.getScore());
        assertEquals("Is Jupiter the largest planet?", result.getQuestionText());
        assertEquals(QuestionType.TRUE_OR_FALSE, result.getType());
        assertEquals("TRUE*FALSE", result.getOptions());
    }


    @Test
    void testCreateOrUpdateQuestion_String() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setType(QuestionType.STRING);
        questionDTO.setQuestionText("What is the largest planet?");
        questionDTO.setAnswer("Jupiter");
        questionDTO.setScore(6);
        questionDTO.setQuizId(1);

        when(questionRepository.save(any(Question.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Question result = questionService.createQuestion(questionDTO);

        verify(questionRepository).save(any(Question.class));

        assertNotNull(result);
        assertEquals("Jupiter", result.getAnswer());
        assertEquals(6, result.getScore());
        assertEquals("What is the largest planet?", result.getQuestionText());
        assertEquals(QuestionType.STRING, result.getType());
        assertNull(result.getOptions());
    }

    @Test
    void testCreateOrUpdateQuestion_UpdateExistingQuestion_String() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setType(QuestionType.STRING);
        questionDTO.setQuestionText("What is the largest planet?");
        questionDTO.setAnswer("Jupiter");
        questionDTO.setScore(6);
        questionDTO.setQuizId(1);

        when(questionRepository.save(any(Question.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Question result = questionService.createQuestion(questionDTO);

        verify(questionRepository).save(any(Question.class));

        assertEquals("Jupiter", result.getAnswer());
        assertEquals(6, result.getScore());
        assertEquals("What is the largest planet?", result.getQuestionText());
        assertEquals(QuestionType.STRING, result.getType());
        assertNull(result.getOptions());

        questionDTO.setQuestionText("What is the smallest planet?");
        questionDTO.setAnswer("Mercury");
        questionDTO.setScore(3);
        questionDTO.setQuizId(1);
        questionDTO.setType(QuestionType.STRING);

        when(questionRepository.save(any(Question.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Question updatedResult = questionService.updateQuestion(questionDTO);


        assertEquals("Mercury", updatedResult.getAnswer());
        assertEquals(3, updatedResult.getScore());
        assertEquals("What is the smallest planet?", updatedResult.getQuestionText());
        assertEquals(QuestionType.STRING, updatedResult.getType());
        assertNull(updatedResult.getOptions());
    }

    @Test
    void testFindAllQuestionsByQuizId() {
        // Mock behavior of the repository to return a list of questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        when(questionRepository.findAllByQuizId(1)).thenReturn(questions);

        // Call the method under test
        List<Question> result = questionService.findAllQuestionsByQuizId(1);

        // Verify that the repository's findAllByQuizId method was called with the correct argument
        verify(questionRepository).findAllByQuizId(1);

        // Assert that the result is not null and contains the expected number of questions
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testDeleteQuestion() {
        // Mock behavior of the repository
        doNothing().when(questionRepository).deleteById(1);

        // Call the method under test
        questionService.deleteQuestion(1);

        // Verify that the repository's deleteById method was called with the correct argument
        verify(questionRepository).deleteById(1);
    }


}


 */