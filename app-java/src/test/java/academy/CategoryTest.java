package academy;

import academy.domain.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    @DisplayName("Должен возвращать категорию по ID")
    void shouldReturnCategoryById() {
        assertEquals(Category.FRUITS, Category.values()[0]);
        assertEquals(Category.VEGETABLES, Category.values()[1]);
        assertEquals(Category.ANIMALS, Category.values()[2]);
    }


    @Test
    @DisplayName("Должен бросать исключение при неверном ID")
    void shouldThrowExceptionForInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> Category.fromId(999));
    }


    @Test
    @DisplayName("Должен возвращать случайную категорию")
    void shouldReturnRandomCategory() {
        int randomCategory = Category.getRandom();
        assertTrue(randomCategory >= 1 && randomCategory<= Category.size());
    }

    @Test
    @DisplayName("Должен возвращать корректный размер")
    void shouldReturnCorrectSize() {
        assertTrue(Category.size() > 0);
    }
}

