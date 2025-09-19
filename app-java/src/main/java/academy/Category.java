package academy;

public enum Category {
    FRUITS("Фрукты"),
    VEGETABLES("Овощи"),
    ANIMALS("Животные"),
    PROFESSIONS("Профессии"),
    COUNTRIES("Страны"),
    SPORTS("Спорт"),
    FOOD("Еда");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category fromName(String name) {
        for (Category category : values()) {
            if (category.name.equalsIgnoreCase(name)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Неизвестная категория: " + name);
    }

    @Override
    public String toString() {
        return name;
    }
}
