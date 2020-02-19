package App;

import Exceptions.FileNotFoundException;
import Exceptions.InvalidFileException;
import MyCollection.Exceptions.InputMismatchException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

/**
 * <h3>
 * <strong>Class that runs the project</strong>
 * </h3>
 *
 * @author Francisco Pinto
 * @author Luis Marques
 */
public class DEMO {

    public static void main(String[] args) throws ParseException, IOException, FileNotFoundException {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BOLD = "\u001B[1m";

        int opt = 0;

        do {
            System.out.println(ANSI_BOLD + "\n\n\n             ⤷ 👻" + ANSI_RED + " Haunted House " + ANSI_RESET
                    + ANSI_BOLD + "👻 ⤶" + ANSI_RESET);

            Scanner tec = new Scanner(System.in);
            try {
                System.out.println("\n\n====================== Menu ======================\n");
                System.out.println("  ➊ ➜ Play game");
                System.out.println("  ➋ ➜ Leaderboards");
                System.out.println("  ➌ ➜ Instructions");
                System.out.println("   ➜ Exit");
                System.out.println("\n==================================================");
                System.out.println("\n↓ Insert your option ↓");
                opt = tec.nextInt();
            } catch (java.util.InputMismatchException e) {
            }

            switch (opt) {
                case 1:
                    try {
                        Menu.userDefinition();
                    } catch (InputMismatchException e) {
                        System.out.println("\nInvalid input!");
                        break;
                    }
                    try {
                        Menu.loadFile();
                    } catch (FileNotFoundException e) {
                        System.out.println(ANSI_RED + "\n>> File not found" + ANSI_RESET);
                        break;
                    } catch (InvalidFileException e) {
                        System.out.println(ANSI_RED + "\n>> This map is invalid!" + ANSI_RESET);
                        break;
                    }
                    Menu.start();
                    break;
                case 2:
                    Scanner tec2 = new Scanner(System.in);
                    String res;
                    System.out.println("\nWhat is the map you wish to search?");
                    res = tec2.nextLine();
                    try {
                        Menu.searchLeaderBoards(res);
                    } catch (FileNotFoundException e) {
                        System.out.println(ANSI_RED + "\nFile not found" + ANSI_RESET);
                        break;
                    }
                    break;
                case 3:
                    Menu.showInstructions();
                    break;
                case 4:
                    Menu.copyright();
                    break;
                default:
                    System.out.println(ANSI_RED + "\nChoose a valid option." + ANSI_RESET);
            }
        } while (opt != 4);
    }
}
