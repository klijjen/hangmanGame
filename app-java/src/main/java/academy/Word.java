package academy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Word {
    private final String word;
    private final Category category;
    private final Difficulty difficulty;
    private final String clue;


    @JsonCreator
    public Word(@JsonProperty("word") String word,
                @JsonProperty("category") String category,
                @JsonProperty("difficulty") int difficulty,
                @JsonProperty("clue") String clue) {
        this.word = word;
        this.category = Category.fromName(category);
        this.difficulty = Difficulty.fromLevel(difficulty);
        this.clue = clue;
    }

    public String getWord() {
        return word;
    }

    public Category getCategory() {
        return category;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getClue() {
        return clue;
    }

    @Override
    public String toString() {
        return "Слово:" + word + ". Категория - " + category + ", сложность - " + difficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, category, difficulty);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Word word1 = (Word) obj;
        return Objects.equals(word, word1.word) &&
            category == word1.category &&
            difficulty == word1.difficulty;
    }
}
