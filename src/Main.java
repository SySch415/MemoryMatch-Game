import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        System.out.println("Simon Schmidt - 5.17.23");

        Game myGame = new Game();
        
        myGame.startGame();
    }
}