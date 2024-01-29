import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
public class Game {

    public int input2;

    //Read from city file into array and shuffle
    public String[] readIntoArray() throws FileNotFoundException {

        File myFile = new File("/Users/sy/Desktop/java_projects/memory_match/MemoryMatch_American-Cities.txt");

        String[] wordArray1 = new String[50];

        Scanner reader = new Scanner(myFile);

        for (int i = 0; i < wordArray1.length; i++) {
            wordArray1[i] = reader.nextLine();
        }
        reader.close();

        Collections.shuffle(Arrays.asList(wordArray1));

        return wordArray1;

    }

    public void startGame() throws FileNotFoundException, InterruptedException {

        Menu myMenu = new Menu();
        Grid myFour = new FourByFour();
        Grid mySix = new SixBySix();
        Grid myEight = new EightByEight();

        myMenu.loadMenu();

        input2 = myMenu.input2;

        //Load Grid depending on user choice
        if (myMenu.input1 == 1) {
            myFour.playGrid(input2);
        } else if (myMenu.input1 == 2) {
            mySix.playGrid(input2);
        } else if (myMenu.input1 == 3) {
            myEight.playGrid(input2);
        }
    }
}
