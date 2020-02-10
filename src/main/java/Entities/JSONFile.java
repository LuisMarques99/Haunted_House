package Entities;

import Exceptions.InvalidFileException;
import MyCollection.List.ArrayUnorderedList;

/**
 * <h3>
 * <strong>Class that represents the structure of a {@link JSONFile JSON File}</strong>
 * </h3>
 *
 * @author Luis Marques
 */
public class JSONFile {

    /**
     * String reference to the file name
     */
    private String name;

    /**
     * long reference to the initial life points of the user
     */
    private long points;

    /**
     * ArrayUnorderedList reference to the map (which is a list of rooms)
     */
    private ArrayUnorderedList<Room> map;

    /**
     * Creates an instance of a {@link JSONFile JSON File} without atributes
     */
    public JSONFile() {
    }

    /**
     * Creates an instance of a {@link JSONFile JSON File} with the default attributes
     *
     * @param name   String name
     * @param points long life points of the user
     * @param map    ArrayUnorderedList map (list of rooms)
     */
    public JSONFile(String name, long points, ArrayUnorderedList<Room> map) {
        this.name = name;
        this.points = points;
        this.map = map;
    }

    /**
     * Gets the file name
     *
     * @return String file name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the file name
     *
     * @param name String file name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the life points of the user
     *
     * @return long life points of the user
     */
    public long getPoints() {
        return points;
    }

    /**
     * Sets the life points of the user
     *
     * @param points long life points of the user
     */
    public void setPoints(long points) {
        this.points = points;
    }

    /**
     * Gets the map (list of rooms)
     *
     * @return ArrayUnorderedList map (list of rooms)
     */
    public ArrayUnorderedList<Room> getMap() {
        return map;
    }

    /**
     * Sets the map (list of rooms)
     *
     * @param map ArrayUnorderedList map (list of rooms)
     */
    public void setMap(ArrayUnorderedList<Room> map) throws InvalidFileException {
        int entry = 0, exit = 0;
        for (int i = 0; i < map.size(); i++) {
            ArrayUnorderedList<String> tempConnections = map.get(i).getConnections();
            for (int j = 0; j < tempConnections.size(); j++) {
                if (tempConnections.get(j).equals("entrada")) {
                    entry++;
                }
                if (tempConnections.get(j).equals("exterior")) {
                    exit++;
                }
            }
        }
        if (entry < 1) {
            throw new InvalidFileException("The map must have at least one entry!");
        } else if (entry > 1) {
            throw new InvalidFileException("The map can't have more than one entry!");
        } else if (exit < 0) {
            throw new InvalidFileException("The map must have at least one exit!");
        }

        this.map = map;
    }

    @Override
    public String toString() {
        return "JSONFile\n{\nname = \"" + name + "\"\npoints = " + points + "\nmap = " + map.toString() + "\n}";
    }
}
