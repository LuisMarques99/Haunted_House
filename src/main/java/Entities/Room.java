package Entities;

import MyCollection.List.ArrayList;

/**
 * <h3>
 * <strong>Class that represents the structure of a {@link Room Room}</strong>
 * </h3>
 *
 * @author Luis Marques
 */
public class Room {
    /**
     * String reference to the room name
     */
    private String name;

    /**
     * int reference to the number of points the ghost takes from the user (0 means the room has no ghost)
     */
    private int ghost;

    /**
     * ArrayList reference to the connections the room has
     */
    private ArrayList<String> connections;

    /**
     * Creates na instance of a {@link Room room} with the default attributes
     *
     * @param name        String name
     * @param ghost       int number of points taken by the ghost
     * @param connections ArrayList connections
     */
    public Room(String name, int ghost, ArrayList<String> connections) {
        this.name = name;
        this.ghost = ghost;
        this.connections = connections;
    }

    /**
     * Gets the name of the room
     *
     * @return String name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the room
     *
     * @param name String name of the room
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ghost of the room
     *
     * @return int points the ghost of the room takes the user
     */
    public int getGhost() {
        return ghost;
    }

    /**
     * Sets the ghost of the room
     *
     * @param ghost int points the ghost of the room takes the user
     */
    public void setGhost(int ghost) {
        this.ghost = ghost;
    }

    /**
     * Gets the connections of the room
     *
     * @return ArrayList connections of the room
     */
    public ArrayList<String> getConnections() {
        return connections;
    }

    /**
     * Sets the connections of the room
     *
     * @param connections ArrayList connections of the room
     */
    public void setConnections(ArrayList<String> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", ghost=" + ghost +
                ", connections=" + connections +
                '}';
    }
}
