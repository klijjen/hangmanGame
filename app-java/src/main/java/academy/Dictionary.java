package academy;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {
    private List<Word> words;
    private final Random random;
    private static final Logger logger = Logger.getLogger(Dictionary.class.getName());


    public Dictionary() {
        try {
            this.words = WordLoader.loadFromJson("words.json");
        }
        catch (WordLoadException e) {
            logger.log(Level.WARNING, "Ошибка загрузки словаря из файла, используется стандартный словарь", e);
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
