import java.io.FileNotFoundException;
import java.util.Random;

public class Grid {

    //play grid
    public void playGrid(int timeInput) throws FileNotFoundException, InterruptedException {

    }

    //randomize game array
    public void randomizeArray(String[][] array) {
        Random rand = new Random();
        String tmp;
        int r;
        int r2;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                r = rand.nextInt(array.length);
                r2 = rand.nextInt(array[i].length);
                tmp = array[i][j];
                array[i][j] = array[r][r2];
                array[r][r2] = tmp;
            }
        }
    }

    //print theme squares
    public void printTheme(String[][] themeArray) {
        for (int i = 0; i < themeArray.length; i++) {

            for (int j = 0; j < themeArray[i].length; j++) {
                System.out.print("*---------");
            }
            System.out.println("*");

            for (int j = 0; j < themeArray[i].length; j++) {
                System.out.printf("| %-8s", themeArray[i][j] );
            }
            System.out.println("|");
        }
        for (int i = 0; i < themeArray[0].length; i++) {
            System.out.print("*---------");
        }
        System.out.println("*");
        System.out.println();
    }

    //Time difficulty method
    public void timeInterval(String[][] themeArray, String[][] gridArray, int timeInput,
                             int row, int col, int row2, int col2) throws InterruptedException {
        if (timeInput == 1) {
            themeArray[row2][col2] = gridArray[row2][col2];

            //Print out theme squares
            printTheme(themeArray);

            System.out.println("No Match. Refreshing in 6 seconds");
            System.out.println("Waiting...");
            System.out.println();
            Thread.sleep(6000);
            themeArray[row][col] = "City";
            themeArray[row2][col2] = "City";

            //Print out theme squares
            printTheme(themeArray);

        } else if (timeInput == 2) {
            themeArray[row2][col2] = gridArray[row2][col2];

            //Print out theme squares
            printTheme(themeArray);

            System.out.println("No Match. Refreshing in 4 Seconds");
            System.out.println("Waiting...");
            System.out.println();
            Thread.sleep(4000);
            themeArray[row][col] = "City";
            themeArray[row2][col2] = "City";

            //Print out theme squares
            printTheme(themeArray);

        } else if (timeInput == 3) {
            themeArray[row2][col2] = gridArray[row2][col2];

            //Print out theme squares
            printTheme(themeArray);

            System.out.println("No Match. Refreshing in 2 seconds");
            System.out.println("Waiting...");
            System.out.println();
            Thread.sleep(2000);
            themeArray[row][col] = "City";
            themeArray[row2][col2] = "City";

            //Print out theme squares
            printTheme(themeArray);
        }
    }
}
