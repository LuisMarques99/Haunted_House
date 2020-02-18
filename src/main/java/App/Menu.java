package App;

import Entities.JSONFile;
import Entities.User;
import Exceptions.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
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
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";

        do {
            System.out.println("\n================= " + ANSI_GREEN + "User Selection" +  ANSI_RESET + " =================");
            System.out.println("  1 - Enter your username");
            System.out.println("  2 - Play as a guest");
            System.out.println("==================================================");
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
    }

    /**
     * Loads the JSON file
     *
     * @throws ParseException
     * @throws IOException
     */
    public static void loadFile() throws ParseException, IOException {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLUE = "\u001B[34m";

        System.out.println("\n\n====================== " + ANSI_BLUE + "File Loader" + ANSI_RESET + " =======================");
        System.out.println("  Enter the path to the map (JSON file) you wish to play");
        System.out.println("==========================================================");
        System.out.println("\n↓ Insert the file path ↓");
        filePath = scanner.nextLine();
        jsonFile = FileManager.readJsonFile(filePath);
    }

    public static void start() throws IOException, ParseException, FileNotFoundException {
        user.setLifePoints(jsonFile.getPoints());

        System.out.println("================== Haunted House =================");
        System.out.println("  Map: " + jsonFile.getName());
        System.out.println("  Player: " + user.getName());
        System.out.println("  Player life points: " + user.getLifePoints());
        System.out.println("==================================================\n\n");
        FileManager.playGame(user);
    }

    public static void copyright() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + ANSI_RED + "Program terminated!" + ANSI_RESET);
        System.out.println("\n\nDone by: ");
        System.out.println("Luis Marques - 8170485\nFrancisco Pinto - 8170580");
        scanner.close();
    }

    /**
     * Search the leaderboards from a given map
     * @param mapName the name of the map to be searched
     */
    public static void searchLeaderBoards(String mapName) {
        //falta fazer isto
    }
}