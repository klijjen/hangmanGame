package academy.service;

import academy.domain.GuessResult;
import java.util.HashSet;
import java.util.Set;

public class WordChecker {
    private WordChecker() {

    }

    public static GuessResult checkGuess(String targetWord, String guessedWord) {

        Set<Character> guessedLetters = new HashSet<>();

        StringBuilder result = new StringBuilder();
        boolean allCorrect = true;

        for (int i = 0; i < guessedWord.length(); i++) {
            guessedLetters.add(Character.toLowerCase(guessedWord.charAt(i)));
        }
        for (int i = 0; i < targetWord.length(); i++) {
            char letter = targetWord.charAt(i);
            char lowLetter = Character.toLowerCase(letter);
            if (!guessedLetters.contains(lowLetter)) {
                allCorrect = false;
                result.append('*');
            }
            else {
                result.append(letter);
            }
        }
        return new GuessResult(true, result.toString(), allCorrect, true);
    }

}
