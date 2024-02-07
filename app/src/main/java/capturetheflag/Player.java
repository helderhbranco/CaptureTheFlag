package capturetheflag;

import Collections.Lists.LinkedUnorderedList;

/**
 * Represents a player in the game.
 */
public class Player {

    private static int idCounter = 0;
    private int id;
    private String name;
    private int flagIndex;
    private int enimieFlagIndex;
    private LinkedUnorderedList<Robot> robots;

    /**
     * Constructs a player with the given name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.id = idCounter++;
        this.name = name;
        this.flagIndex = -1;
        this.enimieFlagIndex = -1;
        this.robots = new LinkedUnorderedList<>();
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the ID of the player.
     *
     * @return The ID of the player.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The new name for the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the index of the player's flag.
     *
     * @return The index of the player's flag.
     */
    public int getFlagIndex() {
        return flagIndex;
    }

    /**
     * Sets the index of the player's flag.
     *
     * @param flagIndex The index of the player's flag.
     */
    public void setFlagIndex(int flagIndex) {
        this.flagIndex = flagIndex;
    }

    /**
     * Gets the index of the enemy flag targeted by the player.
     *
     * @return The index of the enemy flag targeted by the player.
     */
    public int getEnimieFlagIndex() {
        return enimieFlagIndex;
    }

    /**
     * Sets the index of the enemy flag targeted by the player.
     *
     * @param enimieFlagIndex The index of the enemy flag targeted by the player.
     */
    public void setEnimieFlagIndex(int enimieFlagIndex) {
        this.enimieFlagIndex = enimieFlagIndex;
    }

    /**
     * Gets the list of robots owned by the player.
     *
     * @return The list of robots owned by the player.
     */
    public LinkedUnorderedList<Robot> getRobots() {
        return robots;
    }

    /**
     * Adds a robot to the player's list of robots.
     *
     * @param robot The robot to be added.
     * @throws IllegalArgumentException if the robot is null.
     */
    public void addRobot(Robot robot) throws IllegalArgumentException {
        if (robot == null) {
            throw new IllegalArgumentException("Robot cannot be null!");
        }
        this.robots.addToRear(robot);
    }
}
