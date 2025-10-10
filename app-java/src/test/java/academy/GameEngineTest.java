package academy;

import academy.domain.GameStats;
import academy.service.GameEngine;
import academy.service.WordLoader;
import academy.ui.ConsoleIO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Должен корректно создавать экземпляр GameEngine")
    void shouldCreateGameEngine() {
        assertDoesNotThrow(() -> {
            GameEngine engine = new GameEngine(WordLoader.createDictionaryFromJson("words.json"), new ConsoleIO(), new GameStats());
            assertNotNull(engine);
        });
    }

    @ParameterizedTest
    @DisplayName("Должен корректно обрабатывать неинтерактивную игру с одинаковыми словами")
    @CsvSource({
        "окно, окно, окно;POS",
        "тест, тест, тест;POS",
        "программа, программа, программа;POS"
    })
    void shouldHandleNonInteractiveGameWithSameWords(String target, String guessed, String expected) {
        String result = GameEngine.startNonInteractiveGame(target, guessed);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("Должен корректно обрабатывать неинтерактивную игру с разными словами")
    @CsvSource({
        "окно, толо, о**о;NEG",
        "программа, прогноззз, прогр****;NEG",
        "компьютер, компьютер, компьютер;POS"
    })
    void shouldHandleNonInteractiveGameWithDifferentWords(String target, String guessed, String expected) {
        String result = GameEngine.startNonInteractiveGame(target, guessed);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Должен сохранять регистр загаданного слова")
    void shouldPreserveCaseOfTargetWord() {
        String result = GameEngine.startNonInteractiveGame("ОКНО", "окно");
        assertEquals("ОКНО;POS", result);

        result = GameEngine.startNonInteractiveGame("ТеСт", "тест");
        assertEquals("ТеСт;POS", result);
    }

    @Test
    @DisplayName("Должен корректно обрабатывать слова с повторяющимися буквами")
    void shouldHandleWordsWithRepeatingLetters() {
        String result = GameEngine.startNonInteractiveGame("топот", "тапок");
        assertEquals("топот;POS", result);

        String result2 = GameEngine.startNonInteractiveGame("мама", "папа");
        assertEquals("*а*а;NEG", result2);

    }

    @Test
    @DisplayName("Должен бросать исключение при null словах")
    void shouldThrowExceptionForNullWords() {
        assertThrows(IllegalArgumentException.class,
            () -> GameEngine.startNonInteractiveGame(null, "тест"));
        assertThrows(IllegalArgumentException.class,
            () -> GameEngine.startNonInteractiveGame("тест", null));
    }

    @Test
    @DisplayName("Должен бросать исключение при разных длинах слов")
    void shouldThrowExceptionForDifferentLengthWords() {
        assertThrows(IllegalArgumentException.class,
            () -> GameEngine.startNonInteractiveGame("длинное", "коротк"));
        assertThrows(IllegalArgumentException.class,
            () -> GameEngine.startNonInteractiveGame("а", "аб"));
    }

    @Test
    @DisplayName("Должен бросать исключение для коротких слов")
    void shouldThrowExceptionForShortWords() {
        assertThrows(IllegalArgumentException.class,
            () -> GameEngine.startNonInteractiveGame("а", "б"));
        assertThrows(IllegalArgumentException.class,
            () -> GameEngine.startNonInteractiveGame("", ""));
    }

    @Test
    @DisplayName("Должен бросать исключение для пустых слов")
    void shouldThrowExceptionForEmptyWords() {
        assertThrows(IllegalArgumentException.class,
            () -> GameEngine.startNonInteractiveGame("", ""));
        assertThrows(IllegalArgumentException.class,
            () -> GameEngine.startNonInteractiveGame("   ", "   "));
        assertThrows(IllegalArgumentException.class,
            () -> GameEngine.startNonInteractiveGame("слово", "   "));
    }

    @Test
    @DisplayName("Должен игнорировать регистр угаданного слова")
    void shouldIgnoreCaseOfGuessedWord() {
        String result = GameEngine.startNonInteractiveGame("окно", "ОКНО");
        assertEquals("окно;POS", result);

        result = GameEngine.startNonInteractiveGame("ПРОГРАММА", "программа");
        assertEquals("ПРОГРАММА;POS", result);

        result = GameEngine.startNonInteractiveGame("Слово", "СЛОВО");
        assertEquals("Слово;POS", result);
    }

    @ParameterizedTest
    @DisplayName("Должен корректно работать с разной длиной слов")
    @ValueSource(ints = {2, 3, 5, 10, 15})
    void shouldWorkWithDifferentWordLengths(int length) {
        String target = "а".repeat(length);
        String guessed = "б".repeat(length);

        String result = GameEngine.startNonInteractiveGame(target, guessed);
        assertTrue(result.contains("NEG"));
        assertEquals("*".repeat(length) + ";NEG", result);
    }

    @Test
    @DisplayName("Должен корректно определять полное совпадение")
    void shouldDetectFullMatch() {
        String result = GameEngine.startNonInteractiveGame("тест", "тест");
        assertTrue(result.endsWith("POS"));

        result = GameEngine.startNonInteractiveGame("разработка", "разработка");
        assertTrue(result.endsWith("POS"));
    }

    @Test
    @DisplayName("Должен корректно определять частичное совпадение")
    void shouldDetectPartialMatch() {
        String result = GameEngine.startNonInteractiveGame("программа", "пропаганд");
        assertTrue(result.endsWith("NEG"));
        assertTrue(result.startsWith("програ**а")); //програ**а
    }
}
