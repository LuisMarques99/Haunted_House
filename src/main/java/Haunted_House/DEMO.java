package Haunted_House;

import Entities.JSONFile;
import Entities.Room;
import MyCollection.List.ArrayList;
import MyCollection.List.ArrayUnorderedList;

import java.io.IOException;

public class DEMO {
    public static void main(String[] args) {
        /*ArrayUnorderedList<String> connections = new ArrayUnorderedList<>();
        connections.addToRear("cozinha");

        Room room = new Room("room", 50, connections);
        ArrayUnorderedList<Room> rooms = new ArrayUnorderedList<>();
        rooms.addToRear(room);
        rooms.addToRear(room);

        JSONFile file = new JSONFile("nome", 100, rooms);

        System.out.println(file.toString());*/

        try {
            FileManager.readFile("./Documentos_Recursos/mapa.json");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
