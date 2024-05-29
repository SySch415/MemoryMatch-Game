import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class GameGui extends JFrame {

    private JButton firstButtonClick = null;
    private String firstCityname = null;
    private int clickCounter = 0, gridSize, matchCount = 0, intervalVal;
    private String[] cities;
    private JLabel messageBox = new JLabel();
    private JButton[] buttons;
    private Boolean[] buttonState;
    private Boolean gridSizeSelected;
    private Boolean intervalSelected;

    // enum for game state
    private enum STATE {
        MENU, GAME
    };
    private STATE state = STATE.MENU;
    private JLabel menuTitle;
    private JLabel fourGrid, sixGrid, eightGrid;
    private JButton fourByFourButton, sixBySixButton, eightByEightButton;

    private JLabel sixSecond, fourSecond, twoSecond;
    private JButton sixSecButton, fourSecButton, twoSecButton;

    // window and game components configuration
    public GameGui() throws FileNotFoundException {

      // set title
        super("Memory Match");

        // configure gui to end the programs process once its closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // layout manager set to gridSize chosen by player
        setLayout(null);

        // add game grid
        addGuiComponents(gridSize);

        // set pixel size of gui
        setSize(800, 750);

        // load gui to center of the screen
        setLocationRelativeTo(null);

        // prevent resize of gui
        setResizable(false);

        // initialize menu flags to false
        gridSizeSelected = false;
        intervalSelected = false;
    }

    private void addGuiComponents(int gridSize) throws FileNotFoundException {
        if (state == STATE.GAME) {
            // initialize gridSize variable
            this.gridSize = gridSize;

            // initialize buttons array
            buttons = new JButton[gridSize * gridSize];

            //initialize button state array
            buttonState = new Boolean[gridSize * gridSize];
            Arrays.fill(buttonState, true);

            // populate city array
            cities = readIntoArray();

            // create grid panel object and set bounds of panel
            JPanel grid = new JPanel(new GridLayout(gridSize, gridSize));
            grid.setBounds(30, 30, 600, 600);

            // Initialize buttons and add to grid panel
            for (int i = 0; i < gridSize * gridSize; i++) {
                JButton gridButton = new JButton("City");

                // fill buttons array with button objects
                buttons[i] = gridButton;

                // set cursor to hand cursor when hovering over each button
                gridButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                // grab city from array and set as action command for each button
                // listen for button clicks
                String cityName = cities[i];
                gridButton.setActionCommand(cityName);
                gridButton.addActionListener(new GameButtonClickListener());

                grid.add(gridButton);

            }
            add(grid);

            messageBox.setBounds(30, 650, 450, 36);
            messageBox.setFont(new Font("Dialog", Font.PLAIN, 24));
            add(messageBox);

            // program state is MENU, load up menu components
        } else if (state == STATE.MENU) {
            menuTitle = new JLabel();
            menuTitle.setBounds(200,10,450,50);
            menuTitle.setFont(new Font("Dialog", Font.BOLD, 50));
            menuTitle.setText("Memory Match");
            add(menuTitle);

            //grid size menu selection button for 4x4 grid
            fourGrid = new JLabel();
            fourGrid.setBounds(100, 200, 200, 36);
            fourGrid.setFont(new Font("Dialog", Font.PLAIN, 24));
            fourGrid.setText("4 x 4 Grid:");
            add(fourGrid);

            // select button for 4x4 grid
            fourByFourButton = new JButton();
            fourByFourButton.setBounds(250, 200, 100, 36);
            fourByFourButton.setFont(new Font("Dialog", Font.PLAIN, 24));
            fourByFourButton.setText("Select");
            // set cursor to hand cursor when hovering over button
            fourByFourButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            fourByFourButton.setActionCommand("Grid Size Four");
            fourByFourButton.addActionListener(new MenuButtonClickListener());
            add(fourByFourButton);

            //grid size menu selection button for 4x4 grid
            sixGrid = new JLabel();
            sixGrid.setBounds(100, 250, 200, 36);
            sixGrid.setFont(new Font("Dialog", Font.PLAIN, 24));
            sixGrid.setText("6 x 6 Grid:");
            add(sixGrid);

            // select button for 6x6 grid
            sixBySixButton = new JButton();
            sixBySixButton.setBounds(250, 250, 100, 36);
            sixBySixButton.setFont(new Font("Dialog", Font.PLAIN, 24));
            sixBySixButton.setText("Select");

            // set cursor to hand cursor when hovering over button
            sixBySixButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            sixBySixButton.setActionCommand("Grid Size Six");
            sixBySixButton.addActionListener(new MenuButtonClickListener());
            add(sixBySixButton);

            //grid size menu selection button for 8x8 grid
            eightGrid = new JLabel();
            eightGrid.setBounds(100, 300, 200, 36);
            eightGrid.setFont(new Font("Dialog", Font.PLAIN, 24));
            eightGrid.setText("8 x 8 Grid:");
            add(eightGrid);

            // select button for 8x8 grid
            eightByEightButton = new JButton();
            eightByEightButton.setBounds(250, 300, 100, 36);
            eightByEightButton.setFont(new Font("Dialog", Font.PLAIN, 24));
            eightByEightButton.setText("Select");

            // set cursor to hand cursor when hovering over button
            eightByEightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            eightByEightButton.setActionCommand("Grid Size Eight");
            eightByEightButton.addActionListener(new MenuButtonClickListener());
            add(eightByEightButton);

            // option for 6 second interval
            sixSecond = new JLabel();
            sixSecond.setBounds(100, 400, 200, 36);
            sixSecond.setFont(new Font("Dialog", Font.PLAIN, 24));
            sixSecond.setText("6 Seconds: ");
            add(sixSecond);

            // button for six second interval
            sixSecButton = new JButton();
            sixSecButton.setBounds(250, 400, 100, 36);
            sixSecButton.setFont(new Font("Dialog", Font.PLAIN, 24));
            sixSecButton.setText("Select");

            // set cursor to hand cursor; set action command and listener
            sixSecButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            sixSecButton.setActionCommand("Interval Six");
            sixSecButton.addActionListener(new MenuButtonClickListener());
            add(sixSecButton);

            // option for four second interval
            fourSecond = new JLabel();
            fourSecond.setBounds(100, 450, 200, 36);
            fourSecond.setFont(new Font("Dialog", Font.PLAIN, 24));
            fourSecond.setText("4 Seconds: ");
            add(fourSecond);

            // button for four second interval
            fourSecButton = new JButton();
            fourSecButton.setBounds(250, 450, 100, 36);
            fourSecButton.setFont(new Font("Dialog", Font.PLAIN, 24));
            fourSecButton.setText("Select");

            // set cursor to hand cursor; set action command and listener
            fourSecButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            fourSecButton.setActionCommand("Interval Four");
            fourSecButton.addActionListener(new MenuButtonClickListener());
            add(fourSecButton);

            twoSecond = new JLabel();
            twoSecond.setBounds(100, 500, 200, 36);
            twoSecond.setFont(new Font("Dialog", Font.PLAIN, 24));
            twoSecond.setText("2 Seconds: ");
            add(twoSecond);

            // button for six second interval
            twoSecButton = new JButton();
            twoSecButton.setBounds(250, 500, 100, 36);
            twoSecButton.setFont(new Font("Dialog", Font.PLAIN, 24));
            twoSecButton.setText("Select");

            // set cursor to hand cursor; set action command and listener
            twoSecButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            twoSecButton.setActionCommand("Interval Two");
            twoSecButton.addActionListener(new MenuButtonClickListener());
            add(twoSecButton);

        }
    }

    private class MenuButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedMenuButton = (JButton) e.getSource();
            String menuCommand = clickedMenuButton.getActionCommand();

            // switch case to listen for buttons selected
            switch (menuCommand) {
                case "Grid Size Four":
                    gridSize = 4;
                    gridSizeSelected = true;
                    clickedMenuButton.setEnabled(false);
                    break;
                case "Grid Size Six":
                    gridSize = 6;
                    gridSizeSelected = true;
                    clickedMenuButton.setEnabled(false);
                    break;
                case "Grid Size Eight":
                    gridSize = 8;
                    gridSizeSelected = true;
                    clickedMenuButton.setEnabled(false);
                    break;
                case "Interval Six":
                    intervalVal = 6000;
                    intervalSelected = true;
                    clickedMenuButton.setEnabled(false);
                    break;
                case "Interval Four":
                    intervalVal = 4000;
                    intervalSelected = true;
                    clickedMenuButton.setEnabled(false);
                    break;
                case "Interval Two":
                    intervalVal = 2000;
                    intervalSelected = true;
                    clickedMenuButton.setEnabled(false);
                    break;
            }

            // if menu options have been selected, start game
            if (gridSizeSelected && intervalSelected) {

                clickedMenuButton.setEnabled(false);

                removeMenuCompenents();
                state = STATE.GAME;

                // add grid component and start game
                try {
                    addGuiComponents(gridSize);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }

            // revalidate and repaint components
            revalidate();
            repaint();
        }
    }
    private class GameButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // get reference of to identify each button
            // set text to random city
            JButton clickedButton = (JButton) e.getSource();
            String cityName = clickedButton.getActionCommand();
            clickedButton.setText(cityName);

            // increment counter
            clickCounter++;

            if (clickCounter == 1) {
                // set button clicked as first choice
                // store first choices city
                firstButtonClick = clickedButton;
                firstCityname = cityName;


                // temporarily disable first choice
                firstButtonClick.setEnabled(false);

            } else if (clickCounter == 2) {
                if (clickedButton != firstButtonClick && firstCityname.equals(cityName)) {
                    messageBox.setText("Match Found!");

                    // disable button action on matched buttons
                    clickedButton.setEnabled(false);
                    firstButtonClick.setEnabled(false);

                    for (int i = 0; i < gridSize * gridSize; i++) {
                        if (clickedButton == buttons[i]) {
                            buttonState[i] = false;
                        }
                        if (firstButtonClick == buttons[i]) {
                            buttonState[i] = false;
                        }
                    }

                    // increment match count
                    matchCount++;

                    // reset for next attempts
                    clickCounter = 0;
                    firstButtonClick = null;
                    firstCityname = null;

                } else {
                    messageBox.setText("No Match!");

                    // disable all buttons to prevent playing during game pause
                    for (JButton b : buttons) {
                        b.setEnabled(false);
                    }

                    Timer timer = new Timer(intervalVal, evt -> {

                        // enable all buttons that are not matched
                        for (int i = 0; i < gridSize * gridSize; i++) {
                            if (buttonState[i]) {
                                buttons[i].setEnabled(true);
                            }
                        }

                        // reset for next attempts
                        clickCounter = 0;
                        firstButtonClick.setText("City");
                        clickedButton.setText("City");

                        ((Timer) evt.getSource()).stop();
                    });
                    timer.setRepeats(false);
                    timer.start();
                }

                // send winning message if all matches made
                if (matchCount == (gridSize * gridSize) / 2) {
                    JOptionPane.showMessageDialog(GameGui.this, "You Win!");
                }
            }
        }
    }

    // reads city names from .txt file into array
    // will be randomized to load into grid buttons
    private String[] readIntoArray() throws FileNotFoundException {

        File myFile = new File("/Users/sy/dev/java-projects/memory_match/MemoryMatch_American-Cities.txt");

        String[] wordArray1 = new String[gridSize * gridSize];

        Scanner reader = new Scanner(myFile);

        int indexForFileArray = 0;

        //Fill city names array with doubles of city names
        for (int i = 0; i < wordArray1.length / 2; i++) {
            String city = reader.nextLine();
            wordArray1[i] = city;
            wordArray1[i + (wordArray1.length / 2)] = city;
        }
        reader.close();

        Collections.shuffle(Arrays.asList(wordArray1));

        return wordArray1;
    }

    private void removeMenuCompenents() {
        remove(menuTitle);
        remove(fourGrid);
        remove(fourByFourButton);
        remove(sixGrid);
        remove(sixBySixButton);
        remove(eightGrid);
        remove(eightByEightButton);

        // remove interval selections
        remove(fourSecond);
        remove(fourSecButton);
        remove(sixSecond);
        remove(sixSecButton);
        remove(twoSecond);
        remove(twoSecButton);
    }
}
