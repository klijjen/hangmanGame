package academy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    @DisplayName("Должен возвращать категорию по ID")
    void shouldReturnCategoryById() {
        assertEquals(Category.FRUITS, Category.fromId(1));
        assertEquals(Category.VEGETABLES, Category.fromId(2));
        assertEquals(Category.ANIMALS, Category.fromId(3));
    }

    @Test
    @DisplayName("Должен возвращать категорию по имени")
    void shouldReturnCategoryByName() {
        assertEquals(Category.FRUITS, Category.fromName("Фрукты"));
        assertEquals(Category.ANIMALS, Category.fromName("Животные"));
    }

    @Test
    @DisplayName("Должен бросать исключение при неверном ID")
    void shouldThrowExceptionForInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> Category.fromId(999));
    }

    @Test
    @DisplayName("Должен бросать исключение при неверном имени")
    void shouldThrowExceptionForInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> Category.fromName("Несуществующая"));
    }

    @Test
    @DisplayName("Должен возвращать случайную категорию")
    void shouldReturnRandomCategory() {
        Category randomCategory = Category.getRandom();
        assertNotNull(randomCategory);
        assertTrue(randomCategory.getId() >= 1 && randomCategory.getId() <= Category.size());
    }

    @Test
    @DisplayName("Должен возвращать корректный размер")
    void shouldReturnCorrectSize() {
        assertTrue(Category.size() > 0);
    }
}

