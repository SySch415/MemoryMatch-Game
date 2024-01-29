import java.io.FileNotFoundException;
import java.util.*;

public class FourByFour extends Grid {

    public void playGrid(int timeInput) throws FileNotFoundException, InterruptedException {

        Game myGame = new Game();

        String[][] fourArray = new String[4][4];

        String[] fileArray = myGame.readIntoArray();

        int indexForFileArray = 0;

        //Fill city names array with doubles of city names
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                fourArray[i][j] = fileArray[indexForFileArray];
                fourArray[i + 2][j] = fileArray[indexForFileArray];
                indexForFileArray++;
            }
        }

        //randomize city names array
        randomizeArray(fourArray);

        //Create 2d array of theme name and fill with "City"
        String[][] themeArray = new String[4][4];
        for (int i = 0; i < themeArray.length; i++) {
            Arrays.fill(themeArray[i], "City");
        }

        //Print out theme squares
        printTheme(themeArray);
        


        Scanner reader = new Scanner(System.in);
        System.out.println("Please select square to reveal city name");
        System.out.println();
        System.out.println("select row and column by numbers 1 - 4 (ex: Row 1, Column 2)");
        System.out.println();

        while (true) {

            int row = 0;
            int col = 0;

            //input for row/column of first pick
            try {
                System.out.print("Row: ");
                row = reader.nextInt();
                System.out.print("Column: ");
                col = reader.nextInt();
                System.out.println();

                if (row > 4 || col > 4) {
                    System.out.println("you can only pick a row/column from 1 to 4!");
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
            }

            if (col == 1) {
                col = 0;
            } else if (col == 2) {
                col = 1;
            } else if (col == 3) {
                col = 2;
            } else if (col == 4) {
                col = 3;
            }

            //Catch if user picked already matched city on first pick
            if (Objects.equals(themeArray[row][col], fourArray[row][col])) {
                System.out.println("You have already matched this city! Please pick again");
                System.out.println();
                continue;
            }

            themeArray[row][col] = fourArray[row][col];

            //Print out theme squares
            printTheme(themeArray);

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

                    if (row2 > 4 || col2 > 4) {
                        System.out.println("You can only pick row/column 1 - 4!");
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
            }

            if (col2 == 1) {
                col2 = 0;
            } else if (col2 == 2) {
                col2 = 1;
            } else if (col2 == 3) {
                col2 = 2;
            } else if (col2 == 4) {
                col2 = 3;
            }

            //Catch if user picks same square twice
            if (row2 == row && col2 == col) {
                System.out.println("Can't do that! Try again fucker!");
                System.out.println("Your choice of " + themeArray[row][col] + " on row " + (row + 1) + " column " + (col + 1) + " has been refreshed");
                themeArray[row][col] = "City";
                System.out.println();
                continue;
            }

            //Catch if user picks already matched city on second pick
            if (Objects.equals(themeArray[row2][col2], fourArray[row2][col2])) {
                System.out.println("You have already matched this city! Please pick again");
                System.out.println("Your choice of " + themeArray[row][col] + " on row " + (row + 1) + " column " + (col + 1) + " has been refreshed");
                themeArray[row][col] = "City";
                System.out.println();
                continue;
            }

            //Game match
            if (Objects.equals(fourArray[row][col], fourArray[row2][col2])) {

                themeArray[row2][col2] = fourArray[row2][col2];

                //Print out theme squares
                printTheme(themeArray);

                System.out.println("Match!");

                //Game win
                if (Arrays.deepEquals(themeArray, fourArray)) {
                    System.out.println();
                    System.out.println("Congratulations! You Win!");
                    System.out.println();
                    break;
                }
            } else {
                timeInterval(themeArray, fourArray, timeInput, row, col, row2, col2);
            }
        }
    }
}
