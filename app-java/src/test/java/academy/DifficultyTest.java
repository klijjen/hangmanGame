package academy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DifficultyTest {

    @Test
    @DisplayName("Должен возвращать сложность по ID")
    void shouldReturnDifficultyById() {
        assertEquals(Difficulty.EASY, Difficulty.fromId(1));
        assertEquals(Difficulty.MEDIUM, Difficulty.fromId(2));
        assertEquals(Difficulty.HARD, Difficulty.fromId(3));
    }

    @Test
    @DisplayName("Должен возвращать корректное количество попыток")
    void shouldReturnCorrectMaxAttempts() {
        assertEquals(8, Difficulty.EASY.getMaxAttempts());
        assertEquals(6, Difficulty.MEDIUM.getMaxAttempts());
        assertEquals(4, Difficulty.HARD.getMaxAttempts());
    }

    @Test
    @DisplayName("Должен бросать исключение при неверном ID")
    void shouldThrowExceptionForInvalidDifficultyId() {
        assertThrows(IllegalArgumentException.class, () -> Difficulty.fromId(999));
    }

    @Test
    @DisplayName("Должен возвращать корректный размер")
    void shouldReturnCorrectDifficultySize() {
        assertEquals(3, Difficulty.size());
    }
}
