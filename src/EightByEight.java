import java.io.FileNotFoundException;
import java.util.*;

public class EightByEight extends Grid {

    @Override
    public void playGrid(int timeInput) throws FileNotFoundException, InterruptedException {

        Game myGame = new Game();

        String[][] eightArray = new String[8][8];

        String[] fileArray = myGame.readIntoArray();

        int indexForFileArray = 0;

        //Fill city names array with doubles of city names
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                eightArray[i][j] = fileArray[indexForFileArray];
                eightArray[i + 4][j] = fileArray[indexForFileArray];
                indexForFileArray++;
            }
        }

        //randomize array of city names
        randomizeArray(eightArray);

        //2D array for theme squares
        String[][] eightThemeArray = new String[8][8];
        for (int i = 0; i < 8; i++) {
            Arrays.fill(eightThemeArray[i], "City");
        }

        //Print out theme squares
        printTheme(eightThemeArray);

        Scanner reader = new Scanner(System.in);
        System.out.println("Please select square to reveal city name");
        System.out.println();
        System.out.println("select row and column by numbers 1 - 8 (ex: Row 1, Column 7)");
        System.out.println();

        //input for row/column of first pick
        while (true) {
            int row = 0;
            int col = 0;

            try {
                System.out.print("Row: ");
                row = reader.nextInt();
                System.out.print("Column: ");
                col = reader.nextInt();
                System.out.println();

                if (row > 8 || col > 8) {
                    System.out.println("you can only pick a row/column from 1 to 8!");
                    System.out.println();
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Input integers only!");
                System.out.println();
                reader = new Scanner(System.in);
                continue;
            }

            //Change user input to correlated zero-index values
            if (row == 1) {
                row = 0;
            } else if (row == 2) {
                row = 1;
            } else if (row == 3) {
                row = 2;
            } else if (row == 4) {
                row = 3;
            } else if (row == 5) {
                row = 4;
            } else if (row == 6) {
                row = 5;
            } else if (row == 7) {
                row = 6;
            } else if (row == 8) {
                row = 7;
            }

            if (col == 1) {
                col = 0;
            } else if (col == 2) {
                col = 1;
            } else if (col == 3) {
                col = 2;
            } else if (col == 4) {
                col = 3;
            } else if (col == 5) {
                col = 4;
            } else if (col == 6) {
                col = 5;
            } else if (col == 7) {
                col = 6;
            } else if (col == 8) {
                col = 7;
            }

            //Catch if user picked already matched city on first pick
            if (Objects.equals(eightThemeArray[row][col], eightArray[row][col])) {
                System.out.println("You have already matched this city! Please pick again");
                System.out.println();
                continue;
            }

            eightThemeArray[row][col] = eightArray[row][col];

            //Print out theme squares
            printTheme(eightThemeArray);

            int row2;
            int col2;

            //input for row/column of second pick
            while (true) {
                try {
                    System.out.print("Row: ");
                    row2 = reader.nextInt();
                    System.out.print("Column: ");
                    col2 = reader.nextInt();
                    System.out.println();

                    if (row2 > 8 || col2 > 8) {
                        System.out.println("You can only pick row/column 1 to 8!");
                        System.out.println();
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println();
                    System.out.println("Input integers only!");
                    System.out.println();
                    reader = new Scanner(System.in);
                }
            }

            //change input to correlated zero-index value
            if (row2 == 1) {
                row2 = 0;
            } else if (row2 == 2) {
                row2 = 1;
            } else if (row2 == 3) {
                row2 = 2;
            } else if (row2 == 4) {
                row2 = 3;
            } else if (row2 == 5) {
                row2 = 4;
            } else if (row2 == 6) {
                row2 = 5;
            } else if (row2 == 7) {
                row2 = 6;
            } else if (row2 == 8) {
                row2 = 7;
            }

            if (col2 == 1) {
                col2 = 0;
            } else if (col2 == 2) {
                col2 = 1;
            } else if (col2 == 3) {
                col2 = 2;
            } else if (col2 == 4) {
                col2 = 3;
            } else if (col2 == 5) {
                col2 = 4;
            } else if (col2 == 6) {
                col2 = 5;
            } else if (col2 == 7) {
                col2 = 6;
            } else if (col2 == 8) {
                col2 = 7;
            }

            //Catch if user picks same square twice
            if (row2 == row && col2 == col) {
                System.out.println("You can't pick the same square twice! Please try again");
                System.out.println("Your choice of " + eightThemeArray[row][col] + " on row " + (row + 1) + " column " + (col + 1) + " has been refreshed");
                eightThemeArray[row][col] = "City";
                System.out.println();
                continue;
            }

            //Catch if user picks already matched city on second pick
            if (Objects.equals(eightThemeArray[row2][col2], eightArray[row2][col2])) {
                System.out.println("You have already matched this city! Please pick again");
                System.out.println("Your choice of " + eightThemeArray[row][col] + " on row " + (row + 1) + " column " + (col + 1) + " has been refreshed");
                eightThemeArray[row][col] = "City";
                System.out.println();
                continue;
            }

            //Game match
            if (Objects.equals(eightArray[row][col], eightArray[row2][col2])) {
                eightThemeArray[row2][col2] = eightArray[row2][col2];

                //Print out theme squares
                printTheme(eightThemeArray);

                System.out.println("Match!");

                //Game win
                if (Arrays.deepEquals(eightThemeArray, eightArray)) {
                    System.out.println();
                    System.out.println("Congratulations! You Win!");
                    System.out.println();
                    break;
                }
            } else {
                timeInterval(eightThemeArray, eightArray, timeInput, row, col, row2, col2);
            }
        }
    }
}
