package Entities;

/**
 * <h3>
 * <strong>Class that represents the structure of a {@link User User}</strong>
 * </h3>
 *
 * @author Francisco Pinto
 * @author Luis Marques
 */
public class User {

    /**
     * String reference to the user name
     */
    private String name;

    /**
     * long reference to the life points
     */
    private long lifePoints;

    /**
     * Creates an instance of a {@link User user}
     */
    public User() {
    }

    /**
     * Gets the user name
     *
     * @return String name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user name
     *
     * @param name String name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's life points
     *
     * @return long user's life points
     */
    public long getLifePoints() {
        return lifePoints;
    }

    /**
     * Sets the user's life points
     *
     * @param lifePoints long user's life points
     */
    public void setLifePoints(long lifePoints) {
        this.lifePoints = lifePoints;
    }
}
