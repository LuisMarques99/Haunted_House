package Entities;

import MyCollection.List.ArrayUnorderedList;

import java.util.Iterator;

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
     * long reference to the number of points the ghost takes from the user (0 means the room has no ghost)
     */
    private long ghost;

    /**
     * ArrayUnorderedList reference to the connections the room has
     */
    private ArrayUnorderedList<String> connections;

    /**
     * Creates na instance of a {@link Room room} with the default attributes
     *
     * @param name        String name
     * @param ghost       long number of points taken by the ghost
     * @param connections ArrayUnorderedList connections
     */
    public Room(String name, long ghost, ArrayUnorderedList<String> connections) {
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
     * @return long points the ghost of the room takes the user
     */
    public long getGhost() {
        return ghost;
    }

    /**
     * Sets the ghost of the room
     *
     * @param ghost long points the ghost of the room takes the user
     */
    public void setGhost(long ghost) {
        this.ghost = ghost;
    }

    /**
     * Gets the connections of the room
     *
     * @return ArrayUnorderedList connections of the room
     */
    public ArrayUnorderedList<String> getConnections() {
        return connections;
    }

    /**
     * Sets the connections of the room
     *
     * @param connections ArrayUnorderedList connections of the room
     */
    public void setConnections(ArrayUnorderedList<String> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "{ name = \"" + name + "\", ghost = " + ghost + ", connections=" + connections + "}\n";
    }
}