package Entities;

import MyCollection.Graph.Network;

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
    private Network<Room> map;

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
    public JSONFile(String name, long points, Network<Room> map) {
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
    public Network<Room> getMap() {
        return map;
    }

    /**
     * Sets the map (list of rooms)
     *
     * @param map ArrayUnorderedList map (list of rooms)
     */
    public void setMap(Network<Room> map) { this.map = map; }

    @Override
    public String toString() {
        return "JSONFile\n{\nname = \"" + name + "\"\npoints = " + points + "\nmap = " + map.toString() + "\n}";
    }
}
