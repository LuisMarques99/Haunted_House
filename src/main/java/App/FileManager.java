package App;

import Entities.JSONFile;
import Entities.Room;
import Entities.User;
import MyCollection.Graph.Network;
import MyCollection.List.ArrayOrderedList;
import MyCollection.List.ArrayUnorderedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * <h3>
 * <strong>Class that represents the structure of a {@link FileManager File Manager} used to manage the JSON file that
 * contains the map information</strong>
 * </h3>
 */
public class FileManager {

    /**
     * Map name
     */
    private static String nome;

    /**
     * Starting player points
     */
    private static long pontos;

    /**
     * JSONFile static reference to the JSON File
     */
    private static JSONFile jsonFile = new JSONFile();

    /**
     * Network reference to add the vertices
     */
    private static Network<Room> network;

    /**
     * Map divisions to be stored here
     */
    private static ArrayUnorderedList<Room> vertices;

    /**
     * Reads the JSON file that contains the map information
     *
     * @param filePath String file path
     * @return JSONFile file with the map information
     * @throws IOException
     * @throws ParseException
     */
    public static JSONFile readJsonFile(String filePath) throws IOException, ParseException {
        network = new Network<>();
        vertices = new ArrayUnorderedList<>();

        JSONParser parser = new JSONParser();
        JSONObject jsonobj = (JSONObject) parser.parse(new FileReader(filePath));

        nome = (String) jsonobj.get("nome");
        pontos = ((Long) jsonobj.get("pontos")).intValue();
        jsonFile.setName(nome);
        jsonFile.setPoints(pontos);

        JSONArray array = (JSONArray) jsonobj.get("mapa");

        for (Object o : array) {
            ArrayUnorderedList<String> ligacoes = new ArrayUnorderedList<>();
            JSONObject obj = (JSONObject) o;

            String name = (String) obj.get("aposento");
            int ghostPoints = ((Long) obj.get("fantasma")).intValue();
            JSONArray arr = (JSONArray) obj.get("ligacoes");

            for (int i = 0; i < arr.size(); i++) {
                ligacoes.addToRear((String) arr.get(i));
            }
            Room d = new Room(name, ghostPoints, ligacoes);
            vertices.addToRear(d);
        }

        ArrayUnorderedList<String> ligacaoEnt = new ArrayUnorderedList<>();
        ArrayUnorderedList<String> ligacaoSai = new ArrayUnorderedList<>();
        boolean found = false;

        Iterator<Room> start = vertices.iterator();

        while (!found && start.hasNext()) {
            Room division = start.next();
            Iterator<String> lig = division.getConnections().iterator();

            while (!found && lig.hasNext()) {
                String ligacao = lig.next();

                if (ligacao.equals("entrada")) {
                    ligacaoEnt.addToRear(division.getName());
                    found = true;
                }
            }
        }

        Room entrada = new Room("entrada", 0, ligacaoEnt);
        Room exterior = new Room("exterior", 0, ligacaoSai);

        vertices.addToRear(exterior);
        vertices.addToFront(entrada);

        Iterator<Room> it = vertices.iterator();

        while (it.hasNext()) {
            Room div = it.next();
            network.addVertex(div);
        }

        Iterator<Room> iterator = vertices.iterator();

        while (iterator.hasNext()) {
            Room div = iterator.next();
            Iterator<String> iterator1 = div.getConnections().iterator();

            while (iterator1.hasNext()) {
                String lig = iterator1.next();

                if (!lig.equals("entrada")) {
                    network.addEdge(searchDivision(div.getName()), searchDivision(lig), searchDivision(lig).getGhost());
                }
            }
        }
        System.out.println(network.toString());
        jsonFile.setMap(network);
        return jsonFile;
    }

    /**
     * Method responsible to return a division given a name as parameter
     *
     * @param divisao Division name
     * @return A division
     */
    private static Room searchDivision(String divisao) {
        ArrayUnorderedList divisoes = vertices;
        Room div = null;
        boolean found = false;
        int count = 0;

        Iterator<Room> iterator = divisoes.iterator();

        while (!found && iterator.hasNext()) {
            div = iterator.next();

            if ((div.getName()).equals(divisao)) {
                found = true;
                count++;
            }
        }
        if (count == 0){
            System.out.println("Division not found!");
        }
        return div;
    }

    /**
     * Method responsible for the string representation of all the player options in a given division
     * @param current_div the player´s current division placement
     */
    public static void showOptions(Room current_div){
        Iterator<String> iterator = current_div.getConnections().iterator();
        System.out.println("Connections available: ");
        while (iterator.hasNext()) {
            String lig = iterator.next();
            System.out.println(">> " + lig);
        }
    }

    /**
     * Method responsible to start the game and play it until the player loses all their lives or finished
     * by going throw the exit division
     * @param user the user playing the game
     */
    public static void playGame(User user){
        Room current_div = searchDivision("entrada");
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_BLUE = "\u001B[34m";

        while (current_div != searchDivision("exterior") && user.getLifePoints() > 0){
            System.out.println("=====================================================");
            System.out.println("  >> Life points: " + ANSI_RED + user.getLifePoints() + ANSI_RESET);
            System.out.println("  >> Current position: " + ANSI_BLUE + current_div.getName() + ANSI_RESET);
            System.out.println("=====================================================\n");
            Scanner scanner = new Scanner((System.in));
            String option;
            System.out.println("Choose your move, but be careful, there are ghosts nearby!");
            showOptions(current_div);
            option = scanner.nextLine();
            current_div = chooseDivision(option , current_div , user);
        }

        if (current_div.equals(searchDivision("exterior"))){
            System.out.println("\n" + ANSI_GREEN + "===============================================================" + ANSI_RESET);
            System.out.println(ANSI_GREEN + " You did it! You escaped this level with " + user.getLifePoints() + " points remaining." + ANSI_RESET);
            System.out.println(ANSI_GREEN + "===============================================================\n\n" + ANSI_RESET);
            saveToLeaderBoards(user.getName() , user.getLifePoints() , jsonFile.getName());
        }
        else{
            System.out.println("\n" + ANSI_RED + "====================================" + ANSI_RESET);
            System.out.println(ANSI_RED + " You died! Better luck next time..." + ANSI_RESET);
            System.out.println(ANSI_RED + "====================================\n\n" + ANSI_RESET);
        }
    }

    /**
     * Method responsible to store data about games played
     * @param name the username from the user who played the game
     * @param points the number of life points remaining
     * @param map the name of the map played
     */
    private static void saveToLeaderBoards(String name , Long points , String map) {
        //ISTO AINDA NAO ESTA A FUNCIONAR CORRETAMENTE!!!
        JSONArray gamesPlayed = new JSONArray();
        JSONObject details = new JSONObject();
        details.put("Player name" , name);
        details.put("Life points " , points);
        details.put("Map" , map);

        JSONObject gameObject = new JSONObject();
        gameObject.put("Game" , details);

        gamesPlayed.add(gameObject);

        try (FileWriter file = new FileWriter("leaderboards.json")) {

            file.write(gamesPlayed.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method responsible to change the player current position given the current position of the player {@param div}
     * and the division (String) where the player chooses to go next {@param division} and the current user playing the game {@param user}
     * @param division the division where the player wants to go
     * @param div the current position of the player
     * @return the new current position of the player in the map
     */
    public static Room chooseDivision(String division , Room div , User user){
        Iterator<String> iterator = div.getConnections().iterator();
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";

        int count = 0;
        while (iterator.hasNext()){
            String lig = iterator.next();
            if (division.equals(lig)){
                div = searchDivision(lig);
                count++;
                if (div.getGhost() > 0){
                    System.out.println(ANSI_RED + "\nOh no! A ghost just appeared... RUN!" + ANSI_RESET);
                    long hit = div.getGhost();
                    long life = user.getLifePoints() - hit;
                    user.setLifePoints(life);
                    System.out.println("\n");
                }
            }
        }
        if(count == 0){
            System.out.println("\nInvalid division choice...");
        }
        return div;
    }
}