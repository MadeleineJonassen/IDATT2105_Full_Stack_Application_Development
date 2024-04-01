package idatt2105.group11.idatt2105projectbackend;

import idatt2105.group11.idatt2105projectbackend.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class QuizCreationAndTest {

  // Initialize the logger for the class
  private static final Logger logger = LoggerFactory.getLogger(QuizCreationAndTest.class);

  public static void main(String[] args) {
    logger.info("Quiz creation and test started.");

    //Step 0: Create a User
    Role admin = new Role("ROLE_ADMIN");
    HashSet<Role> roles = new HashSet<>(Arrays.asList(admin));

    User JohnDoe = new User("John Doe", "password", roles);
    logger.info("Created user: {}", JohnDoe.getName());

    // Step 1: Create questions with assigned scores
    MultipleChoiceQuestion question1 = new MultipleChoiceQuestion(null,
            "What is the capital of France?",
            2,
            Arrays.asList("1. Paris", "2. London", "3. Berlin", "4. Madrid"),
            0
    );
    logger.info("Created question: {}", question1.getQuestionText());

    MultipleChoiceQuestion question2 = new MultipleChoiceQuestion(null,
            "Which language is primarily spoken in Brazil?",
            3,
            Arrays.asList("1. Spanish", "2. Portuguese", "3. French", "4. English"),
            1
    );
    logger.info("Created question: {}", question2.getQuestionText());

    // Step 2: Create a quiz and add questions
    List<Question> questions = new ArrayList<>();
    questions.add(question1);
    questions.add(question2);
    Quiz quiz = new Quiz("Sample Quiz", questions, JohnDoe, QuizCategory.GEOGRAPHY, QuizDifficulty.EASY);
    questions.forEach(question -> question.setQuiz(quiz));
    logger.info("Created quiz: {}", quiz.getTitle());

    // Step 3: Simulate answers from a user
    QuestionAnswer answer1 = new QuestionAnswer(question1, "2");
    QuestionAnswer answer2 = new QuestionAnswer(question2, "2");
    logger.info("Simulated answers for the quiz.");

    // Step 4: Create a QuizResult and add answers
    QuizResult quizResult = new QuizResult(quiz, new ArrayList<>(), JohnDoe, "påbegynt", LocalDateTime.now(), null); // Initialize with an empty answers list
    quizResult.addQuestionAnswer(answer1);
    quizResult.addQuestionAnswer(answer2);
    logger.info("Created quiz result for user: {}", JohnDoe.getName());

    // Output results
    quizResult.getAnswers().forEach(answer -> {
      logger.info("Question: {} - Your Answer: {} - Correct? {}", answer.getQuestion().getQuestionText(), answer.getGivenAnswer(), answer.isCorrect());
    });

    logger.info("Quiz completed!");
    int totalPossibleScore = questions.stream().mapToInt(Question::getScore).sum();
    logger.info("Your score: {}/{}", quizResult.getScore(), totalPossibleScore);
  }
}
