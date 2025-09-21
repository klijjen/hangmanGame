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
        words.add(new Word("яблоко", "Фрукты", "Красный или зеленый фрукт"));
        words.add(new Word("апельсин", "Фрукты", "Цитрусовый фрукт"));
        words.add(new Word("морковь", "Овощи", "Оранжевый овощ"));
        words.add(new Word("помидор", "Овощи", "Красный овощ"));
        words.add(new Word("слон", "Животные", "Большое животное с хоботом"));
        words.add(new Word("кошка", "Животные", "Домашний питомец"));
        words.add(new Word("врач", "Профессии", "Лечит людей"));
        words.add(new Word("учитель", "Профессии", "Работает в школе"));
        words.add(new Word("Франция", "Страны", "Страна в Европе"));
        words.add(new Word("футбол", "Спорт", "Игра с мячом"));
        words.add(new Word("баскетбол", "Спорт", "Игра с кольцом"));
        words.add(new Word("пицца", "Еда", "Итальянское блюдо"));
        words.add(new Word("суши", "Еда", "Японское блюдо"));
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
        IllegalStateException exception = assertThrows(IllegalStateException.class, emtyDictionary::getRandomWord);
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
}

