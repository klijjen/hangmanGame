package academy;

public class Main {
    public static void main(String[] args) {
        String[] processedArgs = processSpringBootArgs(args);
        // Неинтерактивный режим (2 аргумента)
        if (processedArgs.length == 2) {
//            System.out.println(processedArgs[0]);
//            System.out.println(processedArgs[1]);
            String result = GameEngine.startNonInteractiveGame(processedArgs[0], processedArgs[1]);
            System.out.println(result);
        }

        // Интерактивный режим (без аргументов)
        else if (args.length == 0) {
            GameEngine game = new GameEngine();
            game.startInteractiveGame();
        }


        else {
            System.out.println("Неверное количество аргументов!");
            System.out.println("Для неинтерактивного режима укажите два аргумента: загаданное слово и угаданное слово");
            System.out.println("Для интерактивного режима запустите программу без аргументов");
        }
        System.exit(0);

    }

    private static String[] processSpringBootArgs(String[] args) {
        if (args.length == 1 && args[0].contains(" ")) {
            return args[0].split(" ");
        }
        return args;
    }
}
