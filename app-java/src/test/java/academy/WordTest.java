package academy;

import academy.domain.Category;
import academy.domain.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    @DisplayName("Должен корректно создавать слово")
    void shouldCreateWord() {
        Word word = new Word("компьютер", Category.ANIMALS, "электронное устройство");

        assertEquals("компьютер", word.getWord());
        assertEquals(Category.ANIMALS, word.getCategory());
        assertEquals("электронное устройство", word.getClue());
        assertEquals(9, word.getLength());
    }

    @Test
    @DisplayName("Должен использовать дефолтную подсказку при отсутствии")
    void shouldUseDefaultClueWhenNull() {
        Word word = new Word("тест", Category.FRUITS, null);

        assertEquals("К загаданному слову нет подсказок", word.getClue());
    }

    @ParameterizedTest
    @DisplayName("Должен корректно проверять наличие буквы")
    @CsvSource({
        "компьютер, к, true",
        "компьютер, о, true",
        "компьютер, м, true",
        "компьютер, х, false",
        "компьютер, ё, false"
    })
    void shouldCheckLetterPresence(String wordStr, char letter, boolean expected) {
        Word word = new Word(wordStr, Category.ANIMALS, "тест");
        assertEquals(expected, word.containsLetter(letter));
    }

    @Test
    @DisplayName("Должен игнорировать регистр при проверке букв")
    void shouldIgnoreCaseWhenCheckingLetters() {
        Word word = new Word("Компьютер", Category.ANIMALS, "тест");

        assertTrue(word.containsLetter('к'));
        assertTrue(word.containsLetter('К'));
        assertTrue(word.containsLetter('м'));
        assertTrue(word.containsLetter('М'));
    }

    @Test
    @DisplayName("Должен корректно реализовать equals и hashCode")
    void shouldImplementEqualsAndHashCode() {
        Word word1 = new Word("тест", Category.FRUITS, "подсказка");
        Word word2 = new Word("тест", Category.FRUITS, "подсказка");
        Word word3 = new Word("другое", Category.FRUITS, "подсказка");
        Word word4 = new Word("тест", Category.ANIMALS, "подсказка");

        assertEquals(word1, word2);
        assertNotEquals(word1, word3);
        assertNotEquals(word1, word4);
        assertEquals(word1.hashCode(), word2.hashCode());
    }

    @Test
    @DisplayName("Должен корректно преобразовываться в строку")
    void shouldConvertToString() {
        Word word = new Word("яблоко", Category.FRUITS, "фрукт");
        String stringRepresentation = word.toString();

        assertTrue(stringRepresentation.contains("яблоко"));
        assertTrue(stringRepresentation.contains("Фрукты"));
    }
}
