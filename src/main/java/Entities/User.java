package Entities;

/**
 * <h3>
 * <strong>Class that represents the structure of a {@link User User}</strong>
 * </h3>
 *
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
     * long reference to the highest score of the user
     */
    private long highScore;

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

    /**
     * Gets the user's highest score
     *
     * @return long user's highest score
     */
    public long getHighScore() {
        return highScore;
    }

    /**
     * Sets the user's highest score
     *
     * @param highScore long user's highest score
     */
    public void setHighScore(long highScore) {
        this.highScore = highScore;
    }
}
