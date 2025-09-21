package academy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordTest {
    @Test
    void testWordCreation() {
        // Arrange and Act
        Word word = new Word("Бегемот", "Животные", "В воде - кочкой, из воды - бочкой");

        // Assert
        assertNotNull(word);
    }

    @Test
    void testWordCreationAndGetters() {
        // Arrange and Act
        Word word = new Word("Бегемот", "Животные", "В воде - кочкой, из воды - бочкой");

        // Assert
        assertEquals("бегемот", word.getWord());
        assertEquals(Category.ANIMALS, word.getCategory());
        assertEquals("В воде - кочкой, из воды - бочкой", word.getClue());
    }

    @Test
    void testToString() {
        // Arrange
        Word word = new Word("Бегемот", "Животные", "В воде - кочкой, из воды - бочкой");

        // Act
        String string = word.toString();

        // Assert
        assertEquals("Слово: бегемот. Категория - Животные", word.toString());
    }

    @Test
    void testHashCodeSameObjects() {
        // Arrange
        Word word1 = new Word("Бегемот", "Животные", "В воде - кочкой, из воды - бочкой");
        Word word2 = new Word("Бегемот", "Животные", "В воде - кочкой, из воды - бочкой");

        // Assert
        assertEquals(word1.hashCode(), word2.hashCode());
    }

    @Test
    void testHashCodeDifferentObjects() {
        // Arrange
        Word word1 = new Word("Бегемот", "Животные", "В воде - кочкой, из воды - бочкой");
        Word word2 = new Word("Лев", "Животные", "Грива");

        // Assert
        assertNotEquals(word1.hashCode(), word2.hashCode());
    }

    @Test
    void testEqualsSameObjects() {
        // Arrange
        Word word1 = new Word("Бегемот", "Животные", "В воде - кочкой, из воды - бочкой");
        Word word2 = new Word("Бегемот", "Животные", "В воде - кочкой, из воды - бочкой");

        // Assert
        assertEquals(word1, word2);
        assertEquals(word2, word1);
    }

    @Test
    void testEqualsDifferentObjects() {
        // Arrange
        Word word1 = new Word("А", "Животные", "а");
        Word word2 = new Word("Б", "Животные", "б");
        Word word3 = new Word("А", "Профессии", "а");
        Word word4 = new Word("А", "Животные", "а");

        // Assert
        assertNotEquals(word1, word2); // разные слова
        assertNotEquals(word1, word3); // разные категории
        assertNotEquals(word1, null); // сравнение с null
        assertNotEquals(word1, new Object()); // сравнение с объектом другого класса
    }

    @Test
    void testNullValues() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> {
            new Word(null, "животные", "описание");
        });

        assertThrows(NullPointerException.class, () -> {
            new Word("огурец", null, "описание");
        });

        // Подсказка может быть null
        Word word = new Word("пицца", "еда", null);
        assertEquals("К загаданному слову нет подсказок", word.getClue());
    }

    @Test
    void testNonExistentCategory() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Word("слово", "несуществующая", "описание");
        });
    }
}
