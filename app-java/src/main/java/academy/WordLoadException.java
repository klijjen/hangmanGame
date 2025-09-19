package academy;

class WordLoadException extends RuntimeException {
    public WordLoadException(String message) {
        super(message);
    }

    public WordLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
