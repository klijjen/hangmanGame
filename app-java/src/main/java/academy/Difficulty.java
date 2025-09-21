package academy;

public enum Difficulty {
    EASY(8, "Легкий"),
    MEDIUM(6, "Средний"),
    HARD(4, "Сложный");

    private final int maxAttempts;
    private final String description;

    Difficulty(int maxAttempts, String description) {
        this.maxAttempts = maxAttempts;
        this.description = description;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public String getDescription() {
        return description;
    }

    public static Difficulty fromDescription(String description) {
        for (Difficulty difficulty : values()) {
            if (difficulty.description.equals(description)) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException("Неизвестный уровень сложности: " + description);
    }

    @Override
    public String toString() {
        return description + " (" + maxAttempts + " попытки)";
    }
}
