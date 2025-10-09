package academy;

import java.util.Random;

public enum Difficulty {
    EASY(1, 8, "Легкий"),
    MEDIUM(2, 6, "Средний"),
    HARD(3, 4, "Сложный");

    private final int id;
    private final int maxAttempts;
    private final String description;

    private static final Random random = new Random();

    Difficulty(int id, int maxAttempts, String description) {
        this.id = id;
        this.maxAttempts = maxAttempts;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public String getDescription() {
        return description;
    }

    public static Difficulty fromId(int id) {
        for (Difficulty difficulty : values()) {
            if (difficulty.id == id ) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException("Неизвестный уровень сложности: " + id);
    }

    @Override
    public String toString() {
        return description + " (" + maxAttempts + " попыток)";
    }

    public static int size() {
        return values().length;
    }

    public static int getRandom() {
        return random.nextInt(values().length) + 1;
    }
}
