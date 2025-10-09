package academy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Word {
    private final String word;
    private final Category category;
    private final String clue;
    private final Set<Character> targetLetters;
    public final int targetLetterCount;


    @JsonCreator
    public Word(@JsonProperty("word") String word,
                @JsonProperty("category") Category category,
                @JsonProperty("clue") String clue) {
        this.word = word;
        this.category = category;
        this.clue = clue;

        this.targetLetters = new HashSet<>();
        for (char c : word.toCharArray()) {
            targetLetters.add(Character.toLowerCase(c));
        }
        this.targetLetterCount = targetLetters.size();
    }

    public String getWord() {
        return word;
    }

    public Category getCategory() {
        return category;
    }

    public String getClue() {
        return (clue == null ? "К загаданному слову нет подсказок" : clue);
    }

    public int getLength() {
        return word.length();
    }

    public boolean containsLetter (char letter) {
        return targetLetters.contains(Character.toLowerCase(letter));
    }

    @Override
    public String toString() {
        return "Слово: " + word + ". Категория - " + category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, category);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Word word1 = (Word) obj;
        return Objects.equals(word, word1.word) &&
            category == word1.category;
    }
}
