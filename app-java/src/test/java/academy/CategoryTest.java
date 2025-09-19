package academy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    void testCategoryValues() {
        assertEquals("Фрукты", Category.FRUITS.getName());
        assertEquals("Овощи", Category.VEGETABLES.getName());
        assertEquals("Животные", Category.ANIMALS.getName());
        assertEquals("Профессии", Category.PROFESSIONS.getName());
        assertEquals("Страны", Category.COUNTRIES.getName());
        assertEquals("Спорт", Category.SPORTS.getName());
        assertEquals("Еда", Category.FOOD.getName());
    }

    @Test
    void testFromName() {
        assertEquals(Category.FRUITS, Category.fromName("Фрукты"));
        assertEquals(Category.FRUITS, Category.fromName("фрукты"));
        assertEquals(Category.VEGETABLES, Category.fromName("Овощи"));
        assertEquals(Category.ANIMALS, Category.fromName("Животные"));
    }

    @Test
    void testFromNameInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Category.fromName("Несуществующая категория"));
    }

    @Test
    void testToString() {
        assertEquals("Фрукты", Category.FRUITS.toString());
    }
}
