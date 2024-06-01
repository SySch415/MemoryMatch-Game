import javax.swing.*;
import java.awt.*;

public class BackGround extends JPanel {

    private Image backgroundImage;

    public BackGround(String fileName) {
        // load background image
        backgroundImage = new ImageIcon(fileName).getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

    }
}
