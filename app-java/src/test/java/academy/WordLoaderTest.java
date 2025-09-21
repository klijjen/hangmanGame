package academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WordLoaderTest {
        @Test
        void testLoadValidJsonFile() {
            // Arrange & Act
            List<Word> words = WordLoader.loadFromJson("words.json");

            // Assert
            assertNotNull(words);
            assertFalse(words.isEmpty());

            // Проверяем несколько слов
            Word firstWord = words.get(0);
            assertNotNull(firstWord.getWord());
            assertNotNull(firstWord.getCategory());
            assertNotNull(firstWord.getClue());
        }

        @Test
        void testCreateDictionaryFromJson() {
            // Arrange & Act
            Dictionary dictionary = WordLoader.createDictionaryFromJson("words.json");

            // Assert
            assertNotNull(dictionary);
            assertFalse(dictionary.getWords().isEmpty());
        }

}
