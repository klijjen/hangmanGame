package academy;

import java.util.Random;
import java.util.Scanner;

import static academy.Category.values;

public class ConsoleIO {
    private final Scanner scanner;

    public ConsoleIO() {
        this.scanner = new Scanner(System.in);
    }

    public char readLetter() {
        while (true) {
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().trim();

            if (input.equals("?")) {
                return '?';
            }

            if (input.isEmpty()) {
                System.out.println("Пожалуйста, введите букву");
                continue;
            }

            if (input.length() > 1) {
                System.out.println("Пожалуйста, введите только одну букву");
                continue;
            }

            char letter = input.charAt(0);
            if (!isValidRussianLetter(letter)) {
                System.out.println("Пожалуйста, введите букву (а-я)");
                continue;
            }

            return Character.toLowerCase(letter);
        }
    }

    private int readMenuChoice(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    int choice = new Random().nextInt(min, max);
                    System.out.println("Ваш выбор: " + choice + '\n');
                    return choice;
                }

                int choice = Integer.parseInt(input);

                if (choice >= min && choice <= max) {
                    System.out.println("Ваш выбор: " + choice + '\n');
                    return choice;
                }

                System.out.println("Пожалуйста, введите число от " + min + " до " + max);
            }
            catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное число");
            }
        }
    }

    public Category selectCategory() {
        System.out.println("Доступные категории:");
        for (Category category : Category.values()) {
            System.out.println(category.getId()+ ". " + category.getName());
        }
        System.out.print("\nВведите номер категории (или нажмите Enter для случайного выбора): ");

       int choice = readMenuChoice(1, Category.size());

       return Category.fromId(choice);

    }
    public Difficulty selectDifficulty() {
        System.out.println("Доступные уровни сложности:");
        for (Difficulty difficulty : Difficulty.values()) {
            System.out.println(difficulty.getId()+ ". " + difficulty.getDescription() + " (" + difficulty + " попыток)");
        }
        System.out.print("\nВведите номер уровня сложности (или нажмите Enter для случайного выбора): ");

       int choice = readMenuChoice(1, Difficulty.size());

       return Difficulty.fromId(choice);

    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayGameState(String state) {
        System.out.println(state);
    }

    public boolean askToPlayAgain() {
        while (true) {
            System.out.print("\nХотите сыграть еще раз? (да/нет): ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("да") || answer.equals("д") || answer.equals("yes") || answer.equals("y")) {
                System.out.println('\n');
                return true;
            }
            else if (answer.equals("нет") || answer.equals("н") || answer.equals("no") || answer.equals("n")) {
                System.out.println('\n');
                return false;
            }
            else {
                System.out.println("Пожалуйста, ответьте 'да' или 'нет'");
            }
        }
    }

    private boolean isValidRussianLetter(char letter) {
        char lowerLetter = Character.toLowerCase(letter);
        return (lowerLetter >= 'а' && lowerLetter <= 'я') || lowerLetter == 'ё';
    }

    public void close() {
        scanner.close();
    }
}
