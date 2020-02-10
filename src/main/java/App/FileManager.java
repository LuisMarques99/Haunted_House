package App;

import Entities.JSONFile;
import Entities.Room;
import Exceptions.InvalidFileException;
import MyCollection.List.ArrayUnorderedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * <h3>
 * <strong>Class that represents the structure of a {@link FileManager File Manager} used to manage the JSON file that
 * contains the map information</strong>
 * </h3>
 */
public class FileManager {

    /**
     * JSONFile static reference to the JSON File
     */
    private static JSONFile jsonFile = new JSONFile();

    /**
     * Reads the JSON file that contains the map information
     *
     * @param filePath String file path
     * @return JSONFile file with the map information
     * @throws IOException
     * @throws ParseException
     * @throws InvalidFileException
     */
    public static JSONFile readJsonFile(String filePath) throws IOException, ParseException, InvalidFileException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(filePath));
        JSONObject jsonObject = (JSONObject) obj;

        String name = (String) jsonObject.get("nome");
        jsonFile.setName(name);

        long points = (long) jsonObject.get("pontos");
        jsonFile.setPoints(points);

        ArrayUnorderedList<Room> rooms = new ArrayUnorderedList<>();
        JSONArray map = (JSONArray) jsonObject.get("mapa");
        for (int i = 0; i < map.size(); i++) {
            JSONObject mapObject = (JSONObject) map.get(i);
            Room tempRoom = new Room();

            tempRoom.setName((String) mapObject.get("aposento"));

            tempRoom.setGhost((long) mapObject.get("fantasma"));

            ArrayUnorderedList<String> connections = new ArrayUnorderedList<>();
            JSONArray connectionsArray = (JSONArray) mapObject.get("ligacoes");
            for (int j = 0; j < connectionsArray.size(); j++) {
                connections.addToRear(connectionsArray.get(j).toString());
            }
            tempRoom.setConnections(connections);

            rooms.addToRear(tempRoom);
        }

        jsonFile.setMap(rooms);

        return jsonFile;
    }
}
