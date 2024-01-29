import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {

    public int input1, input2;
    public void loadMenu() {

        Scanner reader = new Scanner(System.in);

        System.out.println();
        System.out.println("Welcome to Simon's Memory Match!");
        System.out.println();
        System.out.println("Please select grid difficulty");
        System.out.println("1. 4 X 4 Grid (Easy)");
        System.out.println("2. 6 x 6 Grid (Moderate)");
        System.out.println("3. 8 x 8 Grid (Difficult)");

        //Catch invalid integer/character input
        while (true) {
            try {
                System.out.println();
                System.out.print("Input choice of grid(1 - 3): ");
                input1 = reader.nextInt();

                if (input1 != 1 && input1 != 2 && input1 != 3) {
                    System.out.println();
                    System.out.println("Please input the correct numbers only!");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Input integers only!");
                reader = new Scanner(System.in);
            }
        }

        System.out.println();
        System.out.println("Please select time interval difficulty");
        System.out.println("1. 6 Seconds (Easy)");
        System.out.println("2. 4 Seconds (Moderate)");
        System.out.println("3. 2 Seconds (Difficult)");

        //Catch invalid integer/character input
        while (true) {
            try {
                System.out.println();
                System.out.print("Input choice of time interval: ");
                input2 = reader.nextInt();
                System.out.println();

                if (input2 != 1 && input2 != 2 && input2 != 3) {
                    System.out.println();
                    System.out.println("Please input the correct numbers only!");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Input integers only!");
                reader = new Scanner(System.in);
            }
        }
    }
}
