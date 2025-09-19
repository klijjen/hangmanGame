package academy;

public enum Difficulty {
    EASY(1, "Легкий"),
    MEDIUM(2, "Средний"),
    HARD(3, "Сложный");

    private final int level;
    private final String description;

    Difficulty(int level, String description) {
        this.level = level;
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public static Difficulty fromLevel(int level) {
        for (Difficulty difficulty : values()) {
            if (difficulty.level == level) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException("Неизвестный уровень сложности: " + level);
    }

    @Override
    public String toString() {
        return description + '(' + level + ')';
    }
}
