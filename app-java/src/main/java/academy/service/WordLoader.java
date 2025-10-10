package academy.service;

import academy.domain.Word;
import academy.exception.WordLoadException;
import academy.domain.Category;
import academy.domain.Dictionary;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WordLoader {

    private WordLoader() { }
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Word> loadFromJson(String jsonFilePath) {
        try (InputStream inputStream = getResourceStream(jsonFilePath)) {
            return objectMapper.readValue(inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Word.class));
        } catch (IOException e) {
            throw new WordLoadException("Ошибка загрузки слов из JSON: " + jsonFilePath, e);
        }
    }

    private static InputStream getResourceStream(String filePath) {
        InputStream stream = WordLoader.class.getClassLoader().getResourceAsStream(filePath);
        if (stream == null) {
            throw new WordLoadException("Файл не найден в resources: " + filePath);
        }
        return stream;
    }

    public static Dictionary createDictionaryFromJson(String jsonFilePath) {
        List<Word> words = loadFromJson(jsonFilePath);
        return new Dictionary(words);
    }

    public static List<Word> getDefaultWords() {
        List<Word> defaultWords = new ArrayList<>();
        defaultWords.add(new Word("слон", Category.ANIMALS, "Крупное животное с хоботом"));
        defaultWords.add(new Word("тигр", Category.ANIMALS, "Полосатый хищник"));
        defaultWords.add(new Word("баскетбол", Category.SPORTS, "Игра с мячом и кольцом"));
        defaultWords.add(new Word("пицца", Category.FOOD, "Итальянское блюдо"));
        return defaultWords;
    }
}
