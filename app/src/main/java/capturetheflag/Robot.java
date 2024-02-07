package capturetheflag;
import Collections.Queues.LinkedQueue;


import Enums.MoveType;

/**
 * Represents a robot in the Capture The Flag game.
 * Robots are controlled by players and can move through the game map.
 */
public class Robot {
    private static int idCounter = 0;
    private int id;
    private Player player;
    private MoveType moveType;
    private LinkedQueue<Local> path;


    /**
     * Constructs a robot with a specified player.
     *
     * @param player The player who controls the robot.
     * @throws IllegalArgumentException If the player is null.
     */
    public Robot(Player player) throws IllegalArgumentException {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null!");
        }
        this.id = idCounter++;
        this.player = player;
        this.moveType = null;
        this.path = new LinkedQueue<>();
        this.player.addRobot(this);
    }

    /**
     * Gets the player who controls the robot.
     *
     * @return The player controlling the robot.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the unique identifier for the robot.
     *
     * @return The robot's identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the type of movement the robot uses.
     *
     * @return The move type of the robot.
     */
    public MoveType getMoveType() {
        return moveType;
    }

    /**
     * Sets the type of movement for the robot.
     *
     * @param moveType The move type to set.
     */
    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    /**
     * Gets a specific robot by its identifier.
     *
     * @param id The identifier of the robot to retrieve.
     * @return The robot with the specified identifier, or null if not found.
     */
    public Robot getRobotById(int id) {
        for (Robot robot : this.player.getRobots()) {
            if (robot.getId() == id) {
                return robot;
            }
        }
        return null;
    }

    /**
     * Gets the current path of the robot.
     *
     * @return The path of the robot.
     */
    public LinkedQueue<Local> getPath() {
        return path;
    }

    /**
     * Sets the path for the robot.
     *
     * @param path The path to set for the robot.
     * @throws IllegalArgumentException If the path is null.
     */
    public void setPath(LinkedQueue<Local> path) throws IllegalArgumentException {
        this.path = path;
    }

    /**
     * Moves the robot along its current path.
     *
     * @throws IllegalArgumentException If the path is empty.
     */
    public void moveRobot() throws IllegalArgumentException {
        if (this.path.isEmpty()) {
            throw new IllegalArgumentException("Robot"+this.id + "Path can not be empty!");
        }
        move();
    }

    /**
     * Moves the robot to the next location in its path.
     *
     * @throws IllegalArgumentException If the robot cannot move to the next location.
     */
    private void move() throws IllegalArgumentException {
        Local atual = this.path.dequeue();
        Local proximo = this.path.first();
        if (canMove(proximo)) {
            proximo.addRobot(getPlayer(), this);
            System.out.println("Robot " + this.id + " moved from " + atual.getName() + " to " + proximo.getName() + "!");
        } else {
            throw new IllegalArgumentException("Cannot move to this local!");
        }
    }

    /**
     * Checks if the robot can move to a specific location.
     *
     * @param local The target location to check.
     * @return True if the robot can move to the location, false otherwise.
     */
    private boolean canMove(Local local) {
        if (local.getEnemiesCounter() == 0) {
            return true;
        }
        return false;
    }
}
