import javax.swing.*;
import java.io.FileNotFoundException;

public class GameLauncher {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GameGui().setVisible(true);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Game myGame = new Game();

        myGame.startGame();
    }
}