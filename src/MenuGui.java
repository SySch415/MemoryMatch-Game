import java.awt.*;
import javax.swing.*;

public class MenuGui extends JFrame {

    private int gridSize = 0;
    private int pauseDifficulty = 0;
    private JLabel title = new JLabel();

    public void startMenu(JFrame frame) {
        title.setBounds(30,500,450,36);
        title.setFont(new Font("Dialog", Font.BOLD, 50));
        title.setText("Memory Match");
        frame.add(title);

    }
}
