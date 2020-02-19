package App;

import Entities.JSONFile;
import Entities.User;
import Exceptions.FileNotFoundException;
import Exceptions.InvalidFileException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h3>
 * <strong>Class that represents the structure of the {@link Menu Game Menu}</strong>
 * </h3>
 *
 * @author Francisco Pinto
 * @author Luis Marques
 */
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
     * long[] reference to a array of scores
     */
    private static long[] scores = new long[10];

    /**
     * String[] reference to a array of players
     */
    private static String[] players = new String[10];

    /**
     * Defines the user name to be used in the game
     */
    public static void userDefinition() throws MyCollection.Exceptions.InputMismatchException {
        user = new User();
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_BOLD = "\u001B[1m";

        do {
            try {
                System.out.println("\n\n=================" + ANSI_GREEN + " User Selection " + ANSI_RESET
                        + "=================\n");
                System.out.println(ANSI_BOLD + "  1" + ANSI_RESET + " ➜ Enter your username");
                System.out.println(ANSI_BOLD + "  2" + ANSI_RESET + " ➜ Play as a guest");
                System.out.println("\n================<==================================");
                System.out.println("\n↓ Insert your option ↓");
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                throw new MyCollection.Exceptions.InputMismatchException("Invalid input.");
            }

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
    public static void loadFile() throws ParseException, IOException, FileNotFoundException, InvalidFileException {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLUE = "\u001B[34m";

        System.out.println("\n\n===================" + ANSI_BLUE + " File Loader " + ANSI_RESET
                + "==================\n");
        System.out.println("  Enter the path to the map (JSON file) you wish\n  to play");
        System.out.println("\n==================================================");
        System.out.println("\n↓ Insert the file path ↓");
        filePath = scanner.nextLine();
        jsonFile = FileManager.readJsonFile(filePath);
    }

    /**
     * Start the game method
     *
     * @throws IOException
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public static void start() throws IOException, ParseException {
        user.setLifePoints(jsonFile.getPoints());

        System.out.println("\n\n================== Haunted House =================\n");
        System.out.println("  Map: " + jsonFile.getName());
        System.out.println("  Player: " + user.getName());
        System.out.println("  Player life points: " + user.getLifePoints());
        System.out.println("\n==================================================");
        FileManager.playGame(user);
    }

    /**
     * Final string in the end of the program
     */
    public static void copyright() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + ANSI_RED + "Program terminated!" + ANSI_RESET);
        System.out.println("\n\nDone by: ");
        System.out.println(" 🠞 Francisco Pinto - 8170580\n 🠞 Luis Marques - 8170485");
        System.out.println("\n\n\n\n\n");
        scanner.close();
    }

    /**
     * Search the leaderboards from a given map
     *
     * @param mapName the name of the map to be searched
     */
    public static boolean searchLeaderBoards(String mapName) throws ParseException, FileNotFoundException, IOException {
        FileManager.readExistingLeaderBoard();
        JSONArray leaderBoard = FileManager.getLeaderBoard();
        int count = 0;

        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        for (int i = 0; i < leaderBoard.size(); i++) {
            JSONObject jsonObj = (JSONObject) leaderBoard.get(i);

            if (jsonObj.get("Map").equals(mapName)) {
                if (players[players.length - 1] != null) {
                    extendLeaderboardCapacity();
                }
                for (int j = 0; j < scores.length; j++) {
                    if (players[i] == null) {
                        scores[i] = (long) jsonObj.get("Life points");
                        players[i] = (String) jsonObj.get("Player name");
                    }
                }
                count++;
            }
        }
        if (count == 0) {
            System.out.println(ANSI_RED + "This map has no leaderboard or does not exist!" + ANSI_RESET);
            return false;
        } else {
            while (true) {
                boolean end = true;
                for (int i = 0; i < scores.length - 1; i++) {
                    long currentScore = scores[i];
                    String currentPlayer = players[i];
                    long nextScore = scores[i + 1];
                    String nextPlayer = players[i + 1];
                    if (currentScore < nextScore) {
                        scores[i] = nextScore;
                        players[i] = nextPlayer;
                        scores[i + 1] = currentScore;
                        players[i + 1] = currentPlayer;
                        end = false;
                    }
                }
                if (end) {
                    break;
                }
            }

            System.out.println("\n\n=================== Leaderboard ==================\n");
            System.out.println("Map: " + mapName + "\n");

            for (int i = 0; i < scores.length; i++) {
                if (players[i] != null) {
                    System.out.println((i + 1) + "º ⮊ " + players[i] + ": " + scores[i] + " points");
                }
            }
            System.out.println("\n==================================================\n\n");
            return true;
        }
    }

    public static void showInstructions() {
        System.out.println("\n\n================== Instructions ==================\n");
        System.out.println(" In this game you are placed at the entrance of a");
        System.out.println(" house, but the problem is that this is a Haunted");
        System.out.println(" House. You start the game with an amount of life");
        System.out.println(" points, however, in some rooms there are ghosts");
        System.out.println(" that can deal damage taking some life points");
        System.out.println(" from you. The good news is that in a random room");
        System.out.println(" there is a shield that can heal you with a random");
        System.out.println(" number of life points from 1 to the highest value");
        System.out.println(" of damage dealt by a ghost.");
        System.out.println("\n These are the rules of the game! Your mission");
        System.out.println(" is to reach the exit without being killed!");
        System.out.println("\n GOOD LUCK! Trust me, you'll need it! 😉");
        System.out.println("\n==================================================\n\n");
    }

    /**
     * Method responsible for extending the leaderboard structure capacity
     */
    private static void extendLeaderboardCapacity() {
        long[] newScores = new long[scores.length + 10];
        String[] newPlayers = new String[players.length + 10];

        for (int i = 0; i < scores.length; i++) {
            newScores[i] = scores[i];
            newPlayers[i] = players[i];
        }
        scores = newScores;
        players = newPlayers;
    }
}