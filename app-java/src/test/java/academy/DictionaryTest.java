package academy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {
    private Dictionary dictionary;
    private List<Word> testWords;

    @BeforeEach
    void setUp() {
        testWords = createTestWords();
        dictionary = new Dictionary(testWords);
    }

    private List<Word> createTestWords() {
        List<Word> words = new ArrayList<>();
        words.add(new Word("яблоко", "Фрукты", 1, "Красный или зеленый фрукт"));
        words.add(new Word("апельсин", "Фрукты", 2, "Цитрусовый фрукт"));
        words.add(new Word("морковь", "Овощи", 1, "Оранжевый овощ"));
        words.add(new Word("помидор", "Овощи", 2, "Красный овощ"));
        words.add(new Word("слон", "Животные", 3, "Большое животное с хоботом"));
        words.add(new Word("кошка", "Животные", 1, "Домашний питомец"));
        words.add(new Word("врач", "Профессии", 2, "Лечит людей"));
        words.add(new Word("учитель", "Профессии", 1, "Работает в школе"));
        words.add(new Word("Франция", "Страны", 2, "Страна в Европе"));
        words.add(new Word("футбол", "Спорт", 1, "Игра с мячом"));
        words.add(new Word("баскетбол", "Спорт", 2, "Игра с кольцом"));
        words.add(new Word("пицца", "Еда", 1, "Итальянское блюдо"));
        words.add(new Word("суши", "Еда", 3, "Японское блюдо"));
        return words;
    }

    @Test
    void testGetRandomWord() {
        // Act
        Word word = dictionary.getRandomWord();

        // Assert
        assertNotNull(word);
        assertTrue(testWords.contains(word));
    }

    @Test
    void testGetRandomWordFromEmptyDictionary() {
        // Arrange
        Dictionary emtyDictionary = new Dictionary(List.of());
        Word word = dictionary.getRandomWord();

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> emtyDictionary.getRandomWord());
        assertEquals("Словарь пуст.", exception.getMessage());
    }

    @Test
    void testGetRandomWordByCategory() {
        // Act
        Word word1 = dictionary.getRandomWordByCategory(Category.FOOD);
        Word word2 = dictionary.getRandomWordByCategory(Category.PROFESSIONS);
        Word word3 = dictionary.getRandomWordByCategory(Category.VEGETABLES);

        // Assert
        assertNotNull(word1);
        assertNotNull(word2);
        assertNotNull(word3);
        assertEquals(Category.FOOD, word1.getCategory());
        assertEquals(Category.PROFESSIONS, word2.getCategory());
        assertEquals(Category.VEGETABLES, word3.getCategory());
    }

    @Test
    void testGetRandomWordByDifficulty() {
        // Act
        Word word1 = dictionary.getRandomWordByDifficulty(Difficulty.EASY);
        Word word2 = dictionary.getRandomWordByDifficulty(Difficulty.MEDIUM);
        Word word3 = dictionary.getRandomWordByDifficulty(Difficulty.HARD);

        // Assert
        assertNotNull(word1);
        assertNotNull(word2);
        assertNotNull(word3);
        assertEquals(Difficulty.EASY, word1.getDifficulty());
        assertEquals(Difficulty.MEDIUM, word2.getDifficulty());
        assertEquals(Difficulty.HARD, word3.getDifficulty());
    }

    @Test
    void testGetRandomWordByFruitsCategory() {
        // Act
        Word result = dictionary.getRandomWordByCategory(Category.FRUITS);

        // Assert
        assertNotNull(result);
        assertEquals(Category.FRUITS, result.getCategory());
        assertTrue(List.of("яблоко", "апельсин").contains(result.getWord()));
    }

    @Test
    void testGetRandomWordByNonExistentCategory() {
        // Arrange
        List<Word> fruitsWords = testWords.stream()
            .filter(word -> word.getCategory() == Category.FRUITS)
            .toList();
        Dictionary fruitsDict = new Dictionary(fruitsWords);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fruitsDict.getRandomWordByCategory(Category.ANIMALS));
        assertEquals("Слова категории Животные не найдены.", exception.getMessage());
    }

    @Test
    void testGetRandomWordByEasyDifficulty() {
        // Act
        Word result = dictionary.getRandomWordByDifficulty(Difficulty.EASY);

        // Assert
        assertNotNull(result);
        assertEquals(Difficulty.EASY, result.getDifficulty());
    }

    @Test
    void testGetRandomWordByNonExistentDifficulty() {
        // Arrange
        List<Word> easyWords = testWords.stream()
            .filter(word -> word.getDifficulty() == Difficulty.EASY)
            .toList();
        Dictionary easyDict = new Dictionary(easyWords);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> easyDict.getRandomWordByDifficulty(Difficulty.HARD));
        assertEquals("Слова с уровнем Сложный (3) не найдены.", exception.getMessage());
    }

    @Test
    void testGetRandomWordBySportsAndMedium() {
        // Act
        Word result = dictionary.getRandomWordByCategoryAndDifficulty(Category.SPORTS, Difficulty.MEDIUM);

        // Assert
        assertNotNull(result);
        assertEquals(Category.SPORTS, result.getCategory());
        assertEquals(Difficulty.MEDIUM, result.getDifficulty());
        assertEquals("баскетбол", result.getWord());
    }

    @Test
    void testGetRandomWordByNonExistentCombination() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> dictionary.getRandomWordByCategoryAndDifficulty(Category.FRUITS, Difficulty.HARD));
        assertEquals("Слова категории Фрукты и с уровнем Сложный (3) не найдены.", exception.getMessage());
    }
}

