package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuizDTO;
import edu.ntnu.idatt2105.exception.QuizNotFoundException;
import edu.ntnu.idatt2105.model.Quiz;
import edu.ntnu.idatt2105.model.QuizCategory;
import edu.ntnu.idatt2105.model.QuizDifficulty;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import edu.ntnu.idatt2105.repository.QuizRepository;
import edu.ntnu.idatt2105.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private QuizService quizService;

    private QuizDTO quizDTO;
    private Quiz quiz;
    private User user;

    @BeforeEach
    void setUp() {
        quizDTO = new QuizDTO();
        quizDTO.setId(1);
        quizDTO.setTitle("Java Basics Quiz");
        quizDTO.setCategory(QuizCategory.BUSINESS);
        quizDTO.setDifficulty(QuizDifficulty.EASY);
        quizDTO.setCreatorId(1);

        user = new User();
        user.setId(1);
        user.setUsername("testUser");

        quiz = new Quiz();
        quiz.setId(1);
        quiz.setTitle("Java Basics Quiz");
        quiz.setCategory(QuizCategory.FINANCE);
        quiz.setDifficulty(QuizDifficulty.EASY);
        quiz.setCreator(user);
    }

    @Test
    void createQuiz_Success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

        QuizDTO result = quizService.createQuiz(quizDTO);

        assertNotNull(result);
        assertEquals(quizDTO.getTitle(), result.getTitle());
        assertEquals(quizDTO.getCategory(), result.getCategory());
        assertEquals(quizDTO.getDifficulty(), result.getDifficulty());
        assertEquals(quizDTO.getCreatorId(), result.getCreatorId());
    }

    @Test
    void updateQuiz_Success() {
        when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

        QuizDTO updatedQuizDTO = new QuizDTO();
        updatedQuizDTO.setId(1);
        updatedQuizDTO.setTitle("Updated Java Quiz");
        updatedQuizDTO.setCategory(QuizCategory.BUSINESS);
        updatedQuizDTO.setDifficulty(QuizDifficulty.MEDIUM);
        updatedQuizDTO.setCreatorId(1);

        QuizDTO result = quizService.updateQuiz(updatedQuizDTO);

        assertNotNull(result);
        assertEquals(updatedQuizDTO.getTitle(), result.getTitle());
        assertEquals(updatedQuizDTO.getDifficulty(), result.getDifficulty());
    }

    @Test
    void deleteQuiz_Success() {
        when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));
        doNothing().when(quizRepository).delete(quiz);
        doNothing().when(questionRepository).deleteAll(any());

        assertDoesNotThrow(() -> quizService.deleteQuiz(1));
    }

    @Test
    void findAllQuizzes_Success() {
        List<Quiz> quizzes = List.of(quiz);
        when(quizRepository.findAll()).thenReturn(quizzes);

        List<Quiz> result = quizService.findAllQuizzes();

        assertNotNull(result);
        assertEquals(quizzes.size(), result.size());
        assertThat(result, containsInAnyOrder(quiz));
    }

    @Test
    void findAllQuizzesByCategory_Success() {
        List<Quiz> quizzes = List.of(quiz);
        when(quizRepository.findAllByCategory(QuizCategory.BUSINESS)).thenReturn(quizzes);

        List<Quiz> result = quizService.findAllQuizzesByCategory(QuizCategory.BUSINESS);

        assertNotNull(result);
        assertEquals(quizzes.size(), result.size());
        assertThat(result, containsInAnyOrder(quiz));
    }

    @Test
    void findAllQuizzesByDifficulty_Success() {
        List<Quiz> quizzes = List.of(quiz);
        when(quizRepository.findAllByDifficulty(QuizDifficulty.EASY)).thenReturn(quizzes);

        List<Quiz> result = quizService.findAllQuizzesByDifficulty(QuizDifficulty.EASY);

        assertNotNull(result);
        assertEquals(quizzes.size(), result.size());
        assertThat(result, containsInAnyOrder(quiz));
    }

    @Test
    void findAllQuizzesByCreatorId_Success() {
        List<Quiz> quizzes = List.of(quiz);
        when(quizRepository.findAllByCreatorId(1)).thenReturn(quizzes);

        List<Quiz> result = quizService.findAllQuizzesByCreatorId(1);

        assertNotNull(result);
        assertEquals(quizzes.size(), result.size());
        assertThat(result, containsInAnyOrder(quiz));
    }

    @Test
    void findQuizById_Success() {
        when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));

        Quiz result = quizService.findQuizById(1);

        assertNotNull(result);
        assertEquals(quiz.getId(), result.getId());
        assertEquals(quiz.getTitle(), result.getTitle());
        assertEquals(quiz.getCategory(), result.getCategory());
        assertEquals(quiz.getDifficulty(), result.getDifficulty());
    }

    @Test
    void findQuizById_NotFound() {
        when(quizRepository.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(QuizNotFoundException.class, () -> quizService.findQuizById(999));

        String expectedMessage = "Quiz not found with id 999";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
