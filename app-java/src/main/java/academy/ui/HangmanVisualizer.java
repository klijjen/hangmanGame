package academy.ui;

import academy.domain.GameSession;

public class HangmanVisualizer {
    private static final String[] HANGMAN_STAGES = {
        """
            .
            |
            |
            |
            |
            |
            |
           _|___
        """,
        """
            ________
            |/      |
            |
            |
            |
            |
            |
           _|___
        """,
        """
            ________
            |/      |
            |      (_)
            |
            |
            |
            |
           _|___
        """,
        """
            ________
            |/      |
            |      (_)
            |       |
            |
            |
            |
           _|___
        """,
        """
            ________
            |/      |
            |      (_)
            |      \\|
            |
            |
            |
           _|___
        """,
        """
            ________
            |/      |
            |      (_)
            |      \\|/
            |
            |
            |
           _|___
        """,
        """
            ________
            |/      |
            |      (_)
            |      \\|/
            |       |
            |
            |
           _|___
        """,
        """
            ________
            |/      |
            |      (_)
            |      \\|/
            |       |
            |      /
            |
           _|___
        """,
        """
            ________
            |/      |
            |      (_)
            |      \\|/
            |       |
            |      / \\
            |
           _|___
        """
    };

    public static String getHangmanStage(int wrongAttempts, int maxAttempts) {
        int totalStages = HANGMAN_STAGES.length - 1;
        int stage = (int) Math.round((double) wrongAttempts / maxAttempts * totalStages);
        stage = Math.min(stage, totalStages);
        return HANGMAN_STAGES[stage];
    }

    public static String getGameStateDisplay(GameSession session) {
        StringBuilder display = new StringBuilder();

        display.append(getHangmanStage(session.getWrongAttempts(), session.getMaxAttempts()));
        display.append("\n\n");

        display.append("Слово: ");
        for (char c : session.getCurrentState().toCharArray()) {
            display.append(c).append(" ");
        }
        display.append("\n");

        display.append("Категория: ").append(session.getTargetWord().getCategory()).append("\n");
        display.append("Сложность: ").append(session.getDifficulty()).append("\n");
        display.append("Осталось попыток: ").append(session.getRemainingAttempts()).append("\n");
        display.append("Введите '?' для подсказки\n");

        if (!session.getGuessedLetters().isEmpty()) {
            display.append("Использованные буквы: ");
            for (char letter : session.getGuessedLetters()) {
                display.append(letter).append(" ");
            }
            display.append("\n");
        }

        return display.toString();
    }
}
