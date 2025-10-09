package academy;

import java.util.Random;

public enum Category {
    FRUITS(1, "Фрукты"),
    VEGETABLES(2, "Овощи"),
    ANIMALS(3, "Животные"),
    PROFESSIONS(4, "Профессии"),
    COUNTRIES(5, "Страны"),
    SPORTS(6, "Спорт"),
    FOOD(7,"Еда");

    private final int id;
    private final String name;

    private static final Random random = new Random();

    Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Category fromId(int id) {
        for (Category category : values()) {
            if (category.id == id) {
                return category;
            }
        }
        throw new IllegalArgumentException("Неизвестная категория: " + id);
    }

//    public static Category fromName(String name) {
//        for (Category category : values()) {
//            if (category.name.equalsIgnoreCase(name)) {
//                return category;
//            }
//        }
//        throw new IllegalArgumentException("Неизвестная категория: " + name);
//    }

    @Override
    public String toString() {
        return name;
    }

    public static int size() {
        return values().length;
    }

    public static int getRandom() {
        return random.nextInt(values().length) + 1;
    }
}
