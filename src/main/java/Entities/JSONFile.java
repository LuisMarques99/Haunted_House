package Entities;

import MyCollection.List.ArrayList;

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
     * int reference to the initial life points of the user
     */
    private int points;

    /**
     * ArrayList reference to the map (which is a list of rooms)
     */
    private ArrayList<Room> map;

    /**
     * Creates an instance of a {@link JSONFile JSON File} without atributes
     */
    public JSONFile() {
    }

    /**
     * Creates an instance of a {@link JSONFile JSON File} with the default attributes
     *
     * @param name   String name
     * @param points int life points of the user
     * @param map    ArrayList map (list of rooms)
     */
    public JSONFile(String name, int points, ArrayList<Room> map) {
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
     * @return int life points of the user
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the life points of the user
     *
     * @param points int life points of the user
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the map (list of rooms)
     *
     * @return ArrayList map (list of rooms)
     */
    public ArrayList<Room> getMap() {
        return map;
    }

    /**
     * Sets the map (list of rooms)
     *
     * @param map ArrayList map (list of rooms)
     */
    public void setMap(ArrayList<Room> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "JSONFile{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", map=" + map.toString() +
                '}';
    }
}
