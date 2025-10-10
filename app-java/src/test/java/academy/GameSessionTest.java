package academy;

import academy.domain.Category;
import academy.domain.Difficulty;
import academy.domain.GameSession;
import academy.domain.GuessResult;
import academy.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionTest {
    private Word testWord;
    private GameSession session;

    @BeforeEach
    void setUp() {
        testWord = new Word("тест", Category.ANIMALS, "эксперимент");
        session = new GameSession(testWord, Difficulty.MEDIUM);
    }

    @Test
    @DisplayName("Должен корректно создавать игровую сессию")
    void shouldCreateGameSession() {
        assertNotNull(session);
        assertEquals(testWord, session.getTargetWord());
        assertEquals(Difficulty.MEDIUM, session.getDifficulty());
        assertEquals(6, session.getMaxAttempts());
        assertEquals(6, session.getRemainingAttempts());
        assertFalse(session.isGameOver());
        assertFalse(session.isGameWon());
    }

    @Test
    @DisplayName("Должен возвращать начальное состояние слова")
    void shouldReturnInitialState() {
        String state = session.getCurrentState();
        assertEquals("____", state);
    }

    @Test
    @DisplayName("Должен корректно обрабатывать правильную букву")
    void shouldHandleCorrectGuess() {
        GuessResult result = session.guessLetter('т');

        assertTrue(result.isCorrect());
        assertFalse(result.isGameOver());
        assertEquals("т__т", session.getCurrentState());
        assertEquals(6, session.getRemainingAttempts());
    }

    @Test
    @DisplayName("Должен корректно обрабатывать неправильную букву")
    void shouldHandleWrongGuess() {
        GuessResult result = session.guessLetter('х');

        assertFalse(result.isCorrect());
        assertFalse(result.isGameOver());
        assertEquals("____", session.getCurrentState());
        assertEquals(5, session.getRemainingAttempts());
    }

    @Test
    @DisplayName("Должен выигрывать игру при полном угадывании")
    void shouldWinGameWhenAllLettersGuessed() {
        session.guessLetter('т');
        session.guessLetter('е');
        session.guessLetter('с');

        assertTrue(session.isGameWon());
        assertTrue(session.isGameOver());
        assertEquals("тест", session.getCurrentState());
    }

    @Test
    @DisplayName("Должен проигрывать игру при исчерпании попыток")
    void shouldLoseGameWhenAttemptsExhausted() {
        // Делаем 6 неправильных попыток
        session.guessLetter('а');
        session.guessLetter('б');
        session.guessLetter('в');
        session.guessLetter('г');
        session.guessLetter('д');
        session.guessLetter('ё');


        assertFalse(session.isGameWon());
        assertTrue(session.isGameOver());
        assertEquals(0, session.getRemainingAttempts());
    }

    @Test
    @DisplayName("Должен возвращать подсказку")
    void shouldReturnClue() {
        String clue = session.getClue();
        assertEquals("эксперимент", clue);
    }

    @Test
    @DisplayName("Должен отслеживать использованные буквы")
    void shouldTrackGuessedLetters() {
        session.guessLetter('т');
        session.guessLetter('е');
        session.guessLetter('х');

        var guessedLetters = session.getGuessedLetters();
        assertTrue(guessedLetters.contains('т'));
        assertTrue(guessedLetters.contains('е'));
        assertTrue(guessedLetters.contains('х'));
        assertEquals(3, guessedLetters.size());
    }

    @Test
    @DisplayName("Должен игнорировать повторные буквы")
    void shouldIgnoreDuplicateLetters() {
        session.guessLetter('т');
        GuessResult result = session.guessLetter('т');

        assertFalse(result.isCorrect());
        assertEquals(6, session.getRemainingAttempts());
    }

    @ParameterizedTest
    @DisplayName("Должен корректно работать с разными уровнями сложности")
    @CsvSource({
        "EASY, 8",
        "MEDIUM, 6",
        "HARD, 4"
    })
    void shouldWorkWithDifferentDifficulties(Difficulty difficulty, int expectedAttempts) {
        GameSession customSession = new GameSession(testWord, difficulty);
        assertEquals(expectedAttempts, customSession.getMaxAttempts());
        assertEquals(expectedAttempts, customSession.getRemainingAttempts());
    }
}
