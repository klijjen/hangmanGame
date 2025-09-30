package academy;

import java.util.HashSet;
import java.util.Set;

public class GameSession {
    private final Word targetWord;
    private final Difficulty difficulty;
    private final Set<Character> guessedLetters;
    private final int maxAttempts;
    private int wrongAttempts;
    private boolean gameOver;
    private boolean gameWon;

    public GameSession(Word targetWord, Difficulty difficulty) {
        this.targetWord = targetWord;
        this.difficulty = difficulty;
        this.guessedLetters = new HashSet<>();
        this.maxAttempts = difficulty.getMaxAttempts();
        this.wrongAttempts = 0;
        this.gameOver = false;
        this.gameWon = false;
    }

    public GuessResult guessLetter(char letter) {
        if (gameOver) {
            return new GuessResult(false, "Игра уже завершена", false, false);
        }

        char lowerLetter = Character.toLowerCase(letter);

        if (guessedLetters.contains(lowerLetter)) {
            return new GuessResult(false, "Буква '" + letter + "' уже была", false, false);
        }

        guessedLetters.add(lowerLetter);
        boolean isCorrect = targetWord.containsLetter(letter);

        if (!isCorrect) {
            wrongAttempts++;
        }

        checkGameState();

        String message = isCorrect ?
            "\nПравильно! Буква '" + letter + "' есть в слове" :
            "\nНеверно! Буквы '" + letter + "' нет в слове. Осталось попыток: " + getRemainingAttempts();

        return new GuessResult(true, message, isCorrect, gameOver);
    }

    private void checkGameState() {
        gameWon = isWordGuessed();
        gameOver = gameWon || (wrongAttempts >= maxAttempts);
    }

    public boolean isWordGuessed() {
        for (char c : targetWord.getWord().toLowerCase().toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public String getCurrentState() {
        StringBuilder state = new StringBuilder();
        for (char c : targetWord.getWord().toCharArray()) {
            if (guessedLetters.contains(Character.toLowerCase(c))) {
                state.append(c);
            } else {
                state.append('_');
            }
        }
        return state.toString().trim();
    }

    public Word getTargetWord() { return targetWord; }
    public Difficulty getDifficulty() { return difficulty; }
    public Set<Character> getGuessedLetters() { return new HashSet<>(guessedLetters); }
    public int getMaxAttempts() { return maxAttempts; }
    public int getWrongAttempts() { return wrongAttempts; }
    public int getRemainingAttempts() { return maxAttempts - wrongAttempts; }
    public boolean isGameOver() { return gameOver; }
    public boolean isGameWon() { return gameWon; }
    public String getClue() {
        return targetWord.getClue();
    }
}
