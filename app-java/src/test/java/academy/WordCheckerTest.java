package academy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class WordCheckerTest {
    @ParameterizedTest
    @DisplayName("Должен корректно проверять угаданные слова")
    @CsvSource({
        "окно, окно, окно, true",
        "волокно, толокно, *олокно, false",
        "банан, банан, банан, true",
        "программа, прогноз, прогр****, false",
        "компьютер, компьютер, компьютер, true"
    })
    void shouldCheckGuessCorrectly(String target, String guessed, String expectedMessage, boolean expectedCorrect) {
        GuessResult result = WordChecker.checkGuess(target, guessed);

        assertEquals(expectedMessage, result.message());
        assertEquals(expectedCorrect, result.isCorrect());
        assertTrue(result.valid());
        assertTrue(result.isGameOver());
    }

    @Test
    @DisplayName("Должен сохранять регистр загаданного слова")
    void shouldPreserveTargetWordCase() {
        GuessResult result = WordChecker.checkGuess("ОКНО", "окно");
        assertEquals("ОКНО", result.message());
        assertTrue(result.isCorrect());

        result = WordChecker.checkGuess("ТеСт", "тест");
        assertEquals("ТеСт", result.message());
        assertTrue(result.isCorrect());
    }

    @Test
    @DisplayName("Должен корректно обрабатывать слова с повторяющимися буквами")
    void shouldHandleWordsWithRepeatedLetters() {
        GuessResult result = WordChecker.checkGuess("мама", "папа");
        assertEquals("*а*а", result.message());
        assertFalse(result.isCorrect());

        result = WordChecker.checkGuess("топот", "тапок");
        assertEquals("топот", result.message());
        assertTrue(result.isCorrect());
    }
}
