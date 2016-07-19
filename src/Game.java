import java.util.Random;

class Game {
    Jar jar;
    int randomAmount;
    int numberOfGuesses = 0;

    Game(String itemName, int maxAmount) {
        this.jar = new Jar(itemName, maxAmount);
    }

    private int randomAmountForJar() {
        Random random = new Random();
        int maxItemsAllowed = jar.getMaxItemForJar();
        return random.nextInt(maxItemsAllowed - 1) + 1;
    }
    void init(){
        randomAmount=randomAmountForJar();
    }

    boolean isToHigh(int guess){
        return guess > randomAmount;
    }

    boolean moreThanCanFit(int guess){
        return guess > jar.getMaxItemForJar();
    }

    void trackScore(User user){
        if (numberOfGuesses<user.getScore()){
            user.setScore(numberOfGuesses);
        }
        numberOfGuesses = 0;
    }
}
