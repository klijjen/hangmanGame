package academy.domain;

public record GuessResult(boolean valid, String message, boolean isCorrect, boolean isGameOver) {
}
