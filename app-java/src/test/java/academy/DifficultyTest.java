package academy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifficultyTest {
    @Test
    void testDifficultyValues() {
        assertEquals("Легкий", Difficulty.EASY.getDescription());
        assertEquals(8, Difficulty.EASY.getMaxAttempts());
        assertEquals("Средний", Difficulty.MEDIUM.getDescription());
        assertEquals(4, Difficulty.HARD.getMaxAttempts());
    }

    @Test
    void testFromLevel() {
        assertEquals(Difficulty.EASY, Difficulty.fromDescription("Легкий"));
        assertEquals(Difficulty.MEDIUM, Difficulty.fromDescription("Средний"));
        assertEquals(Difficulty.HARD, Difficulty.fromDescription("Сложный"));
    }

    @Test
    void testFromLevelInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Difficulty.fromDescription("Тестовый1"));
        assertThrows(IllegalArgumentException.class, () -> Difficulty.fromDescription("Тестовый1"));
    }

    @Test
    void testToString() {
        assertEquals("Легкий (8 попытки)", Difficulty.EASY.toString());
    }


}
