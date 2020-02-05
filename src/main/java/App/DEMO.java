package App;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class DEMO {
    public static void main(String[] args) {
        try {
            FileManager.readJsonFile("./Documentos_Recursos/mapa.json");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
    }
}
