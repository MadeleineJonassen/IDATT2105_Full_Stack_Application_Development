package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.QuestionDTO;
import edu.ntnu.idatt2105.model.Question;
import edu.ntnu.idatt2105.model.QuestionType;
import edu.ntnu.idatt2105.repository.QuestionRepository;
import edu.ntnu.idatt2105.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing questions.
 */
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    /**
     * Constructs a QuestionService.
     *
     * @param questionRepository The repository for question operations.
     * @param quizRepository     The repository for quiz operations.
     */
    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    /**
     * Creates or updates a question.
     *
     * @param questionDTO The DTO containing the question details.
     * @return The created or updated question.
     */
    @Transactional
    public Question createOrUpdateQuestion(QuestionDTO questionDTO) {
        Question question;
        if (questionDTO.getId() != null) {
            Optional<Question> optionalQuestion = questionRepository.findById(questionDTO.getId());
            if (!optionalQuestion.isPresent()) {
                return null;
            }
            question = optionalQuestion.get();
        } else {
            question = new Question();
        }

        question.setQuestionText(questionDTO.getQuestionText());
        question.setType(questionDTO.getType());

        if (questionDTO.getType().equals(QuestionType.MULTIPLE_CHOICE)) {
            question.setOptions(questionDTO.getOptionsAsString());
        } else if (questionDTO.getType().equals(QuestionType.TRUE_OR_FALSE)) {
            question.setOptions("TRUE*FALSE");
        } else {
            question.setOptions(null);
        }
        question.setAnswer(questionDTO.getAnswer());
        question.setScore(questionDTO.getScore());
        question.setQuiz(quizRepository.findById(questionDTO.getQuizId()).orElse(null));

        return questionRepository.save(question);
    }

    /**
     * Deletes a question by its ID.
     *
     * @param id The ID of the question to delete.
     */
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }

    /**
     * Finds all questions belonging to a quiz.
     *
     * @param quizId The ID of the quiz.
     * @return A list of questions belonging to the quiz.
     */
    public List<Question> findAllQuestionsByQuizId(Integer quizId) {
        return questionRepository.findAllByQuizId(quizId);
    }

    /**
     * Finds a question by its ID.
     *
     * @param id The ID of the question.
     * @return The found question, or null if not found.
     */
    public Question findQuestionById(Integer id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.orElse(null);
    }

    /**
     * Finds all questions for a quiz.
     *
     * @param quizId The ID of the quiz.
     * @return A list of questions for the quiz.
     */
    public List<Question> findAllQuestionsToAQuiz(Integer quizId) {
        return questionRepository.findAllByQuizId(quizId);
    }
}
