package academy.service;

import academy.ui.ConsoleIO;
import academy.domain.Dictionary;
import academy.domain.Difficulty;
import academy.domain.GameSession;
import academy.domain.GameStats;
import academy.domain.GuessResult;
import academy.ui.HangmanVisualizer;
import academy.domain.Word;
import academy.domain.Category;

public class GameEngine {
    private final Dictionary dictionary;
    private final ConsoleIO consoleIO;
    private final GameStats gameStats;
    private boolean playing;

    public GameEngine(Dictionary dictionary, ConsoleIO consoleIO, GameStats gameStats) {
//        this.dictionary = WordLoader.createDictionaryFromJson("words.json");
        this.dictionary = dictionary;
        this.consoleIO = consoleIO;
        this.gameStats = gameStats;
        this.playing = true;
    }

    public static String startNonInteractiveGame(String targetWord, String guessedWord) {
        validateWords(targetWord, guessedWord);

        GuessResult result = WordChecker.checkGuess(targetWord, guessedWord);

        return result.message() + ";" + (result.isCorrect() ? "POS" : "NEG");
    }

    public void startInteractiveGame() {
        if (dictionary.isEmpty()) {
            System.out.println("Словарь пуст. Невозможно начать игру.");
            return;
        }

        consoleIO.displayMessage("=== ИГРА 'ВИСЕЛИЦА' ===");
        consoleIO.displayMessage("Угадайте слово по буквам!");

        while (playing) {
            startNewGame();
            playing = consoleIO.askToPlayAgain();
        }

        displayFinalStats();
        consoleIO.close();
    }

    private void startNewGame() {
        Category category = consoleIO.selectCategory();

        Difficulty difficulty = consoleIO.selectDifficulty();

        Word targetWord = dictionary.getRandomWordByCategory(category);
        GameSession session = new GameSession(targetWord, difficulty);

        consoleIO.displayMessage("\n=== НОВАЯ ИГРА ===");
//        consoleIO.displayMessage("Категория: " + targetWord.getCategory().getName());
//        consoleIO.displayMessage("Уровень сложности: " + difficulty.getDescription());
//        consoleIO.displayMessage("Попыток: " + session.getMaxAttempts());

        playGameSession(session);
    }

    private void playGameSession(GameSession session) {
        while (!session.isGameOver()) {
            consoleIO.displayGameState(HangmanVisualizer.getGameStateDisplay(session));

            char input = consoleIO.readLetter();

            if (input == '?') {
                String hint = session.getClue();
                consoleIO.displayMessage("\n\nПодсказка: " + hint);
                continue;
            }

            GuessResult result = session.guessLetter(input);

            consoleIO.displayMessage(result.message());

            if (result.isGameOver()) {
                break;
            }
        }

        displayGameResult(session);
        updateStats(session);
    }

    private void displayGameResult(GameSession session) {
        consoleIO.displayGameState(HangmanVisualizer.getGameStateDisplay(session));

        if (session.isGameWon()) {
            consoleIO.displayMessage("Поздравляем! Вы угадали слово: " + session.getTargetWord().getWord());
        } else {
            consoleIO.displayMessage("Игра окончена! Загаданное слово было: " + session.getTargetWord().getWord());
        }
    }

    private void updateStats(GameSession session) {
        gameStats.incrementGamesPlayed();
        if (session.isGameWon()) {
            gameStats.incrementWins();
        }
    }

    private void displayFinalStats() {
        consoleIO.displayMessage("\n=== СТАТИСТИКА ИГРЫ ===");
        consoleIO.displayMessage("Сыграно игр: " + gameStats.getGamesPlayed());
        consoleIO.displayMessage("Побед: " + gameStats.getWins());
        consoleIO.displayMessage("Процент побед: " + gameStats.getWinPercentage() + "%");
        consoleIO.displayMessage("\nСпасибо за игру! До свидания!");
    }

    private static void validateWords(String targetWord, String guessedWord) {
        if (targetWord == null || guessedWord == null) {
            throw new IllegalArgumentException("Слова не могут быть null");
        }

        if (targetWord.length() != guessedWord.length()) {
            throw new IllegalArgumentException("Длина загаданного и угаданного слова должна совпадать");
        }

        if (targetWord.trim().isEmpty() || guessedWord.trim().isEmpty()) {
            throw new IllegalArgumentException("Слова не могут быть пустыми");
        }

        if (targetWord.length() < 2) {
            throw new IllegalArgumentException("Слова должны содержать хотя бы 2 символа");
        }
    }
}
