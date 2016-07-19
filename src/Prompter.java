import java.util.Scanner;

public class Prompter {
    private Scanner scanner = new Scanner(System.in);
    private Game mGame;
    private User user;

    private String promptForUserName() {
        System.out.print("Enter your name: \n");
        return scanner.nextLine();
    }

    private String promptToSetAJarItemType() {
        System.out.print("ADMINISTRATOR SETUP\n=========================\nName of items in the jar: ");
        return scanner.nextLine();
    }

    private int promptToSetMaxAmountForJar(String itemName) {
        int number;
        do {
            System.out.format("Maximum %s in the jar: ", itemName);
            checkForInt();
            number = scanner.nextInt();
            if(number <= 0){
                System.out.format("You can't put \033[1m%d\033[0m %s in the jar. Number must be positive%n", number, itemName);
            }
        }
        while(number <= 0);
        return number;
    }

    void play() {
        System.out.println("\033[1mWELCOME TO GUESS HOW MANY IN THE JAR!!!\033[0m");
        user = new User(promptForUserName(),Integer.MAX_VALUE);
        System.out.println("Press \033[1mEnter\033[0m to start");
        while (!"exit".equalsIgnoreCase(scanner.nextLine())){
            String itemName = promptToSetAJarItemType();
            int maxAmount = promptToSetMaxAmountForJar(itemName);
            mGame = new Game(itemName, maxAmount);
            mGame.init();
            promptForGuess();
            System.out.println("\nPlay again - Press \033[1mEnter\033[0m\nExit - type \033[1mexit\033[0m");
            scanner.nextLine();
        }
        System.out.println("Thanks for plying this awesome game!\n Will appreciate feedback at \033[1mmykolanagorskyi@gamil.com\033[0m");
    }

    private void promptForGuess() {
        int guess = 0;
        System.out.format("PLAYER\n=========================\nYour goal is to guess how many %s are in the jar. Your guess should be between 1 and %d.%n", mGame.jar.getMItemType(), mGame.jar.getMaxItemForJar());
        while (guess != mGame.randomAmount) {
            promptWithMaxNumberOfItems();
            checkForInt();
            guess = scanner.nextInt();
            checkGuessRange(guess);
            mGame.numberOfGuesses++;
            System.out.format("%nAmount of Tries - %d%n", mGame.numberOfGuesses);
        }
        promptWithResultsOfTheGame();

    }

    private void checkGuessRange(int guess) {
        if (mGame.isToHigh(guess) && !mGame.moreThanCanFit(guess)) {
            System.out.println("Your guess is too HIGH");
        } else if (!mGame.isToHigh(guess) && guess != mGame.randomAmount) {
            System.out.println("Your guess is too LOW");
        } else if (mGame.moreThanCanFit(guess)) {
            System.out.format("You went over maximum number!");
        }
    }

    private void checkForInt(){
        while (!scanner.hasNextInt()) {
            System.out.println("That is NOT a number, please try again:");
            scanner.next();
        }
    }

    private void promptWithMaxNumberOfItems(){
        System.out.format("Maximum number of %s allowed - %d%nGuess: ", mGame.jar.getMItemType(), mGame.jar.getMaxItemForJar());
    }

    private void promptWithResultsOfTheGame() {
        System.out.format("Congratulation %s - you guessed that there is %d %s in the jar. It took you %d to guess it.%n", user.getName(), mGame.randomAmount, mGame.jar.getMItemType(), mGame.numberOfGuesses);
        mGame.trackScore(user);
        System.out.format("High score is \033[1m%d\033[0m tries", user.getScore());
    }
}
