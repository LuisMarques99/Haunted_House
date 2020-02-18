package App;

import Exceptions.FileNotFoundException;
import MyCollection.Exceptions.InputMismatchException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

/**
 * <h3>
 * <strong>Class that runs the project</strong>
 * </h3>
 */
public class DEMO {

    public static void main(String[] args) throws ParseException, IOException, FileNotFoundException {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        int opt = 0;

        do {
            Scanner tec = new Scanner(System.in);
            try {
                System.out.println("\n\n========== Menu ==========");
                System.out.println(" 1- Play game");
                System.out.println(" 2- Leaderboards");
                System.out.println(" 3- Exit");
                System.out.println("==========================");
                opt = tec.nextInt();
            } catch (java.util.InputMismatchException e) {
            }

            switch (opt) {
                case 1:
                    try {
                        Menu.userDefinition();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input!");
                        break;
                    }
                    try {
                        Menu.loadFile();
                    } catch (FileNotFoundException e) {
                        System.out.println(ANSI_RED + "File not found" + ANSI_RESET);
                        break;
                    }
                    Menu.start();
                    break;
                case 2:
                    Scanner tec2 = new Scanner(System.in);
                    String res;
                    System.out.println("What is the map you wish to search?");
                    res = tec2.nextLine();
                    Menu.searchLeaderBoards(res);
                    break;
                case 3:
                    Menu.copyright();
                    break;
                default:
                    System.out.println(ANSI_RED + "\nChoose a valid option." + ANSI_RESET);
            }
        } while (opt != 3);
    }
}
