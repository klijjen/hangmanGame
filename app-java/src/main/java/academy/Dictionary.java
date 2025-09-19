package academy;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Dictionary {
    private List<Word> words;
    private Random random;

    public Dictionary(List<Word> words) {
        this.words = List.copyOf(words);
        this.random = new Random();
    }

    public Word getRandomWord() {
        if (words.isEmpty()) {
            throw new IllegalStateException("Словарь пуст.");
        }
        return words.get(random.nextInt(words.size()));
    }

    public Word getRandomWordByCategory(Category category) {
        List<Word> wordsByCategory = words.stream()
            .filter(word -> word.getCategory() == category)
            .collect(Collectors.toList());
        if (wordsByCategory.isEmpty()) {
            throw new IllegalArgumentException("Слова категории '" + category + "' не найдены.");
        }
        return wordsByCategory.get(random.nextInt(wordsByCategory.size()));
    }

    public Word getRandomWordByDifficulty(Difficulty difficulty) {
        List<Word> wordsByDifficulty = words.stream()
            .filter(word -> word.getDifficulty() == difficulty)
            .collect(Collectors.toList());
        if (wordsByDifficulty.isEmpty()) {
            throw new IllegalArgumentException("Слова со сложностью " + difficulty + " не найдены.");
        }
        return wordsByDifficulty.get(random.nextInt(wordsByDifficulty.size()));
    }

    public Word getRandomWordByCategoryAndDifficulty(Category category, Difficulty difficulty) {
        List<Word> wordsByFilter = words.stream()
            .filter(word -> word.getCategory() == category)
            .filter(word -> word.getDifficulty() == difficulty)
            .collect(Collectors.toList());
        if (wordsByFilter.isEmpty()) {
            throw new IllegalArgumentException("Слова категории '" + category + " и сложностью " + difficulty + " не найдены.");
        }
        return wordsByFilter.get(random.nextInt(wordsByFilter.size()));
    }
}
