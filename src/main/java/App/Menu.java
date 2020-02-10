package App;

import Entities.JSONFile;
import Entities.User;
import Exceptions.InvalidFileException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    /**
     * User reference to the user
     */
    private static User user;

    /**
     * JSONFile reference to the map
     */
    private static JSONFile jsonFile = new JSONFile();

    /**
     * Defines the user name to be used in the game
     */
    public static void userDefinition() {
        user = new User();

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("================= User Selection =================\n");
            System.out.println("  1 - Enter your username");
            System.out.println("  2 - Play as a guest");
            System.out.println("\n==================================================");
            System.out.println("\n↓ Insert your option ↓");

            option = scanner.nextInt();

            if (option == 1) {
                System.out.println("\n\n↓ Insert your username ↓");
                String name = scanner.next();
                user.setName(name);
                break;
            } else if (option == 2) {
                user.setName("Guest");
                break;
            } else {
                System.err.println("Unfortunately, that feature is not available yet! We are very sorry! :(");
            }

        } while (option != 0);

        scanner.close();
        clear();
    }

    /**
     * Loads the JSON file
     *
     * @throws ParseException
     * @throws InvalidFileException
     * @throws IOException
     */
    public static void loadFile() throws ParseException, InvalidFileException, IOException {
        Scanner scanner = new Scanner(System.in);
        String filePath;

        System.out.println("=================== File Loader ==================\n");
        System.out.println("  Enter the path to the map (JSON file)");
        System.out.println("\n==================================================");
        System.out.println("\n↓ Insert the file path ↓");

        filePath = scanner.nextLine();

        jsonFile = FileManager.readJsonFile(filePath);

        scanner.close();
        clear();
    }

    public static void start() throws ParseException, InvalidFileException, IOException {
        Scanner scanner = new Scanner(System.in);
        String str;

        user.setLifePoints(jsonFile.getPoints());

        System.out.println("================== Haunted House =================\n");
        System.out.println("  Map: " + jsonFile.getName() + "\n");
        System.out.println("  Life points: " + jsonFile.getPoints() + "\n");
        System.out.println("\n==================================================");
        System.out.println("\n↓ " + user.getName() + " ↓");

        str = scanner.nextLine();

        System.out.println("\n" + user.getName() + ": \"" + str + "\"");

        scanner.close();
        clear();
    }

    public static void copyright() {
        System.out.println("==================== Done by: ====================\n");
        System.out.println("  Luis Marques - 8170485");
        System.out.println("\n==================================================");
    }

    /**
     * Clears the screen
     */
    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }
    }
}
