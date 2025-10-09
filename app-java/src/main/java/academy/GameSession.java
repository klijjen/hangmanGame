package academy;

import java.util.LinkedHashSet;
import java.util.Set;

public class GameSession {
    private final Word targetWord;
    private final Difficulty difficulty;
    private final Set<Character> guessedLetters;
    private final int maxAttempts;
    private int wrongAttempts;
    private int correctLettersCount;
    private boolean gameOver;
    private boolean gameWon;

    public GameSession(Word targetWord, Difficulty difficulty) {
        this.targetWord = targetWord;
        this.difficulty = difficulty;
        this.guessedLetters = new LinkedHashSet<>();
        this.maxAttempts = difficulty.getMaxAttempts();
        this.wrongAttempts = 0;
        this.correctLettersCount = 0;
        this.gameOver = false;
        this.gameWon = false;
//        this.clueUsed = false;
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
        boolean isCorrect = targetWord.containsLetter(lowerLetter);

        if (!isCorrect) {
            wrongAttempts++;
        }
        else {
            correctLettersCount++;
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
        return correctLettersCount == targetWord.targetLetterCount;
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
        return state.toString();
    }

    public Word getTargetWord() { return targetWord; }
    public Difficulty getDifficulty() { return difficulty; }
    public Set<Character> getGuessedLetters() { return guessedLetters; }
    public int getMaxAttempts() { return maxAttempts; }
    public int getWrongAttempts() { return wrongAttempts; }
    public int getRemainingAttempts() { return maxAttempts - wrongAttempts; }
    public boolean isGameOver() { return gameOver; }
    public boolean isGameWon() { return gameWon; }
    public String getClue() {
        return targetWord.getClue();
    }
}
