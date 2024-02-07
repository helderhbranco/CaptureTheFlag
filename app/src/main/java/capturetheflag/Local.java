package capturetheflag;
import Collections.Lists.LinkedUnorderedList;

/**
 * Represents a location in the Capture The Flag game.
 * Each location has a unique identifier, a name, and can host robots from different players.
 */
public class Local {
    private final static int ENEMIES_MAX = 1;
    //private static int idCounter = 0;
    private int id;
    private String name;
    private LinkedUnorderedList<Robot> robots;
    private int teamCounter = 0;
    private int enemiesCounter = 0;

    /**
     * Constructs a new local with a unique identifier, a default name, and initializes robot counters.
     */
    public Local(int id) {
        this.id = id;
        this.name = "Local " + id;
        this.robots = new LinkedUnorderedList<>();
        this.teamCounter = 0;
        this.enemiesCounter = 0;
        //idCounter++;
    }

    /**
     * Gets the unique identifier of the local.
     *
     * @return The local's identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the local.
     *
     * @param id The new identifier.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the local.
     *
     * @return The local's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the local.
     *
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of robots currently in the local.
     *
     * @return The list of robots.
     */
    public LinkedUnorderedList<Robot> getRobots() {
        return robots;
    }

    /**
     * Sets the list of robots in the local.
     *
     * @param robots The new list of robots.
     */
    public void setRobots(LinkedUnorderedList<Robot> robots) {
        this.robots = robots;
    }

    /**
     * Gets the counter of robots from the same team in the local.
     *
     * @return The team counter.
     */
    public int getTeamCounter() {
        return teamCounter;
    }

    /**
     * Sets the team counter in the local.
     *
     * @param teamCounter The new team counter.
     */
    public void setTeamCounter(int teamCounter) {
        this.teamCounter = teamCounter;
    }

    /**
     * Gets the counter of enemy robots in the local.
     *
     * @return The enemies counter.
     */
    public int getEnemiesCounter() {
        return enemiesCounter;
    }

    /**
     * Sets the enemies counter in the local.
     *
     * @param enemiesCounter The new enemies counter.
     */
    public void setEnemiesCounter(int enemiesCounter) {
        this.enemiesCounter = enemiesCounter;
    }

    /**
     * Adds a robot to the local based on the player's affiliation.
     *
     * @param player The player to whom the robot belongs.
     * @param robot  The robot to be added.
     * @throws IllegalArgumentException If the player or robot is null, or if adding more enemies than allowed.
     */
    public void addRobot(Player player, Robot robot) throws IllegalArgumentException {
        if (player == null || robot == null) {
            throw new IllegalArgumentException("Player or the Robot cannot be null!");
        }
        if (player.getFlagIndex() == this.getId()) {
            this.robots.addToRear(robot);
            this.teamCounter++;
        } else {
            if (this.enemiesCounter < ENEMIES_MAX) {
                this.robots.addToRear(robot);
                this.enemiesCounter++;
            } else {
                throw new IllegalArgumentException("Cannot add more enemies to this local!");
            }
        }

    }


    /**
     * Removes a robot from the local, updating counters accordingly.
     *
     * @param robot The robot to be removed.
     * @throws IllegalArgumentException If there are no robots, the robot is null, or the robot is not found.
     */
    public void removeRobot(Robot robot) throws IllegalArgumentException {
        if (getRobots().isEmpty()) {
            throw new IllegalArgumentException("There are no robots in this local!");
        }
        if (robot == null) {
            throw new IllegalArgumentException("Robot cannot be null!");
        }
        if (this.robots.contains(robot)) {
            this.robots.remove(robot);
            if (robot.getPlayer().getFlagIndex() == this.id) {
                this.teamCounter--;
            } else {
                this.enemiesCounter--;
            }
        } else {
            throw new IllegalArgumentException("Robot not found!");
        }

    }
}



