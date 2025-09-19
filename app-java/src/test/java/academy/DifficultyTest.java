package academy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifficultyTest {
    @Test
    void testDifficultyValues() {
        assertEquals("Легкий", Difficulty.EASY.getDescription());
        assertEquals(1, Difficulty.EASY.getLevel());
        assertEquals("Средний", Difficulty.MEDIUM.getDescription());
        assertEquals(3, Difficulty.HARD.getLevel());
    }

    @Test
    void testFromLevel() {
        assertEquals(Difficulty.EASY, Difficulty.fromLevel(1));
        assertEquals(Difficulty.MEDIUM, Difficulty.fromLevel(2));
        assertEquals(Difficulty.HARD, Difficulty.fromLevel(3));
    }

    @Test
    void testFromLevelInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Difficulty.fromLevel(0));
        assertThrows(IllegalArgumentException.class, () -> Difficulty.fromLevel(4));
    }

    @Test
    void testToString() {
        assertEquals("Легкий (1)", Difficulty.EASY.toString());
    }


}
