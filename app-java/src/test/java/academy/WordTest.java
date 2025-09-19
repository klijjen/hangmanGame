package academy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordTest {
    @Test
    void testWordCreation() {
        // Arrange and Act
        Word word = new Word("Бегемот", "Животные", 2, "В воде - кочкой, из воды - бочкой");

        // Assert
        assertNotNull(word);
    }

    @Test
    void testWordCreationAndGetters() {
        // Arrange and Act
        Word word = new Word("Бегемот", "Животные", 2, "В воде - кочкой, из воды - бочкой");

        // Assert
        assertEquals("бегемот", word.getWord());
        assertEquals(Category.ANIMALS, word.getCategory());
        assertEquals(Difficulty.MEDIUM, word.getDifficulty());
        assertEquals("В воде - кочкой, из воды - бочкой", word.getClue());
    }

    @Test
    void testToString() {
        // Arrange
        Word word = new Word("Бегемот", "Животные", 2, "В воде - кочкой, из воды - бочкой");

        // Act
        String string = word.toString();

        // Assert
        assertEquals("Слово: бегемот. Категория - Животные, уровень сложности - Средний (2)", word.toString());
    }

    @Test
    void testHashCodeSameObjects() {
        // Arrange
        Word word1 = new Word("Бегемот", "Животные", 2, "В воде - кочкой, из воды - бочкой");
        Word word2 = new Word("Бегемот", "Животные", 2, "В воде - кочкой, из воды - бочкой");

        // Assert
        assertEquals(word1.hashCode(), word2.hashCode());
    }

    @Test
    void testHashCodeDifferentObjects() {
        // Arrange
        Word word1 = new Word("Бегемот", "Животные", 2, "В воде - кочкой, из воды - бочкой");
        Word word2 = new Word("Лев", "Животные", 1, "Грива");

        // Assert
        assertNotEquals(word1.hashCode(), word2.hashCode());
    }

    @Test
    void testEqualsSameObjects() {
        // Arrange
        Word word1 = new Word("Бегемот", "Животные", 2, "В воде - кочкой, из воды - бочкой");
        Word word2 = new Word("Бегемот", "Животные", 2, "В воде - кочкой, из воды - бочкой");

        // Assert
        assertEquals(word1, word2);
        assertEquals(word2, word1);
    }

    @Test
    void testEqualsDifferentObjects() {
        // Arrange
        Word word1 = new Word("А", "Животные", 1, "а");
        Word word2 = new Word("Б", "Животные", 1, "б");
        Word word3 = new Word("А", "Профессии", 1, "а");
        Word word4 = new Word("А", "Животные", 2, "а");

        // Assert
        assertNotEquals(word1, word2); // разные слова
        assertNotEquals(word1, word3); // разные категории
        assertNotEquals(word1, word4); // разные уровни сложности
        assertNotEquals(word1, null); // сравнение с null
        assertNotEquals(word1, new Object()); // сравнение с объектом другого класса
    }

    @Test
    void testNullValues() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> {
            new Word(null, "животные", 1, "описание");
        });

        assertThrows(NullPointerException.class, () -> {
            new Word("огурец", null, 1, "описание");
        });

        // Подсказка может быть null
        Word word = new Word("пицца", "еда", 1, null);
        assertEquals("К загаданному слову нет подсказок", word.getClue());
    }

    @Test
    void testNonExistentCategory() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Word("слово", "несуществующая", 1, "описание");
        });
    }

    @Test
    void testInvalidDifficulty() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Word("слово", "еда", 999, "");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Word("слово", "еда", 0, "");
        });
    }

}
