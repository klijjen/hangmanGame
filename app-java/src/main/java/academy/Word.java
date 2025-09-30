package academy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Word {
    private String word;
    private Category category;
    private String clue;

    public Word() {}

    // Аннотированный конструктор для Jackson
    @JsonCreator
    public Word(@JsonProperty("word") String word,
                @JsonProperty("category") Category category,
                @JsonProperty("clue") String clue) {
        this.word = word;
        this.category = category;
        this.clue = clue;
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
        return word.toLowerCase().indexOf(Character.toLowerCase(letter)) >= 0;
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
