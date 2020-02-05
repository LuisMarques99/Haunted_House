package App;

import Entities.JSONFile;
import Entities.Room;
import MyCollection.List.ArrayUnorderedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class FileManager {
    private static JSONFile jsonFile = new JSONFile();

    public static void readJsonFile(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(filePath));
        JSONObject jsonObj = (JSONObject) obj;

        String name = (String) jsonObj.get("nome");
        jsonFile.setName(name);

        long points = (long) jsonObj.get("pontos");
        jsonFile.setPoints(points);

        ArrayUnorderedList<Room> rooms = new ArrayUnorderedList<>();
        JSONArray map = (JSONArray) jsonObj.get("mapa");

    }
}
