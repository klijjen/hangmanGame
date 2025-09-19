package academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WordLoader {

    private WordLoader() { }
    private static final ObjectMapper objectMapper = new ObjectMapper();;

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
}
