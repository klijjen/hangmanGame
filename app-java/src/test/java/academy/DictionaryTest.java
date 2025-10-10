package academy;

import academy.domain.Category;
import academy.domain.Dictionary;
import academy.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {
    private Dictionary dictionary;
    private List<Word> testWords;

    @BeforeEach
    void setUp() {
        testWords = List.of(
            new Word("яблоко", Category.FRUITS, "фрукт"),
            new Word("банан", Category.FRUITS, "тропический фрукт"),
            new Word("морковь", Category.VEGETABLES, "овощ"),
            new Word("собака", Category.ANIMALS, "домашнее животное")
        );
        dictionary = new Dictionary(testWords);
    }

    @Test
    @DisplayName("Должен корректно создавать словарь")
    void shouldCreateDictionary() {
        assertNotNull(dictionary);
        assertFalse(dictionary.isEmpty());
        assertEquals(4, dictionary.getWords().size());
    }

    @Test
    @DisplayName("Должен возвращать случайное слово")
    void shouldReturnRandomWord() {
        Word randomWord = dictionary.getRandomWord();
        assertNotNull(randomWord);
        assertTrue(testWords.contains(randomWord));
    }

    @Test
    @DisplayName("Должен возвращать слова по категории")
    void shouldReturnWordsByCategory() {
        Word fruitWord = dictionary.getRandomWordByCategory(Category.FRUITS);
        assertNotNull(fruitWord);
        assertEquals(Category.FRUITS, fruitWord.getCategory());

        Word animalWord = dictionary.getRandomWordByCategory(Category.ANIMALS);
        assertNotNull(animalWord);
        assertEquals(Category.ANIMALS, animalWord.getCategory());
    }

    @Test
    @DisplayName("Должен бросать исключение для пустой категории")
    void shouldThrowExceptionForEmptyCategory() {
        assertThrows(IllegalArgumentException.class,
            () -> dictionary.getRandomWordByCategory(Category.SPORTS));
    }

    @Test
    @DisplayName("Должен корректно обрабатывать пустой словарь")
    void shouldHandleEmptyDictionary() {
        Dictionary emptyDict = new Dictionary(List.of());
        assertTrue(emptyDict.isEmpty());
        assertThrows(IllegalStateException.class, emptyDict::getRandomWord);
    }

    @Test
    @DisplayName("Должен создавать стандартный словарь при ошибке загрузки")
    void shouldCreateDefaultDictionaryOnLoadError() {
        Dictionary dict = new Dictionary();
        assertNotNull(dict);
        assertFalse(dict.isEmpty());
    }
}
