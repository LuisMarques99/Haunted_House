package App;

import Entities.JSONFile;
import Entities.Room;
import MyCollection.Graph.Network;
import MyCollection.List.ArrayUnorderedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

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

        Iterator<Room> iterator = divisoes.iterator();

        while (!found && iterator.hasNext()) {
            div = iterator.next();

            if ((div.getName()).equals(divisao)) {
                found = true;
            }
        }
        return div;
    }
}