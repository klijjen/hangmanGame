package academy;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Dictionary {
    private List<Word> words;
    private final Random random;

    public Dictionary() {
        try {
            this.words = WordLoader.loadFromJson("words.json");
        }
        catch (WordLoadException e) {
            System.err.println("Ошибка загрузки словаря: " + e.getMessage());
            System.out.println("Используется стандартный словарь");
            this.words = WordLoader.getDefaultWords();
        }
        this.random = new Random();
    }

    public Dictionary(List<Word> words) {
        this.words = List.copyOf(words);
        this.random = new Random();
    }

    public List<Word> getWords() {
        return List.copyOf(words);
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
            .toList();

        if (wordsByCategory.isEmpty()) {
            throw new IllegalArgumentException("Слова категории " + category + " не найдены.");
        }

        return wordsByCategory.get(random.nextInt(wordsByCategory.size()));
    }

    public boolean isEmpty() {
        return words.isEmpty();
    }
}
