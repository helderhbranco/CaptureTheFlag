package capturetheflag;

import Collections.Lists.CircularLinkedList;
import Collections.Queues.LinkedQueue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import Enums.*;

/**
 * Represents a Capture The Flag game with two players, a map, and robots.
 * Players compete to capture each other's flags using robots.
 */
public class Game {

    private Map map;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private CircularLinkedList<Robot> robosMoveOrder;
    private int robotsNumber;

    /**
     * Constructs a new game with the specified parameters.
     *
     * @param numLocations  The number of locations in the map.
     * @param bidirectional A flag indicating whether edges are bidirectional.
     * @param density       The density of edges in the map.
     * @param player1Name   The name of the first player.
     * @param player2Name   The name of the second player.
     * @param robotsNumber  The number of robots each player has.
     * @throws IllegalArgumentException If the number of locations is less than 5,
     *                                  density is not between 0 and 1, or the number
     *                                  of robots is less than 3.
     */
    public Game(int numLocations, boolean bidirectional, double density, String player1Name, String player2Name, int robotsNumber) throws IllegalArgumentException {
        this.map = new Map(numLocations, bidirectional, density);
        this.player1 = new Player("Player1");
        this.player2 = new Player("Player2");

        if (robotsNumber < 3) {
            throw new IllegalArgumentException("Robots number must be 3 or more.");
        } else {
            this.robotsNumber = robotsNumber;
        }

        this.currentPlayer = setCurrentPlayerRandom();
        this.robosMoveOrder = new CircularLinkedList<>();
    }

    /**
     * Constructs a new game with the specified parameters.
     *
     * @param map     The map to use for the game.
     * @param player1 The first player.
     * @param player2 The second player.
     * @throws IllegalArgumentException If the number of robots is less than 3.
     */
    public Game(Map map, Player player1, Player player2, int robotsNumber) throws IllegalArgumentException {
        this.map = map;
        this.player1 = player1;
        this.player2 = player2;
        this.robotsNumber = robotsNumber;
        this.currentPlayer = setCurrentPlayerRandom();
        this.robosMoveOrder = new CircularLinkedList<>();
    }

    /**
     * Gets the map associated with the game.
     *
     * @return The map.
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * Sets the map for the game.
     *
     * @param map The new map.
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Gets the number of robots each player has.
     *
     * @return The number of robots.
     */
    public int getRobotsNumber() {
        return this.robotsNumber;
    }

    /**
     * Sets the number of robots each player has.
     *
     * @param robotsNumber The new number of robots.
     */
    public void setRobotsNumber(int robotsNumber) {
        this.robotsNumber = robotsNumber;
    }

    /**
     * Gets the first player in the game.
     *
     * @return The first player.
     */
    public Player getPlayer1() {
        return this.player1;
    }

    /**
     * Gets the second player in the game.
     *
     * @return The second player.
     */
    public Player getPlayer2() {
        return this.player2;
    }

    /**
     * Gets the current player in the game.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public CircularLinkedList<Robot> getRobosMoveOrder() {
        return robosMoveOrder;
    }


    /**
     * Switches the current player to the other player.
     */
    public void switchPlayer() {
        if (this.currentPlayer == this.player1) {
            this.currentPlayer = this.player2;
        } else {
            this.currentPlayer = this.player1;
        }
    }

    /**
     * Sets the flags for both players at specified locations.
     *
     * @param localId1 The location index for the first player's flag.
     * @param localId2 The location index for the second player's flag.
     */
    public void setFlag(int localId1, int localId2) {
        this.currentPlayer.setFlagIndex(localId1);
        this.currentPlayer.setEnimieFlagIndex(localId2);
        switchPlayer();
        this.currentPlayer.setEnimieFlagIndex(localId1);
        this.currentPlayer.setFlagIndex(localId2);
    }

    /**
     * Sets up robots for both players on their respective flags.
     */
    public void setRobots() {
        for (int i = 0; i < this.robotsNumber; i++) {
            Robot robot = new Robot(this.currentPlayer);
            this.map.getLocal(this.currentPlayer.getFlagIndex()).addRobot(this.currentPlayer, robot);
        }
        switchPlayer();
        for (int i = 0; i < this.robotsNumber; i++) {
            Robot robot = new Robot(this.currentPlayer);
            this.map.getLocal(this.currentPlayer.getFlagIndex()).addRobot(this.currentPlayer, robot);
        }
    }

    /**
     * Configures the order of robots for movement, alternating between the two players.
     * Robots are added to the 'robosMoveOrder' list in an interleaved fashion,
     * starting from the current player and alternating between the two players until reaching
     * the total number of robots multiplied by 2.
     */
    public void setOrderRobots() {
        int totalRobots = this.robotsNumber * 2;
        int robotsAdded = 0;

        while (robotsAdded < totalRobots) {
            this.robosMoveOrder.add(getCurrentPlayer().getRobots().get(robotsAdded % getRobotsNumber()));
            switchPlayer();
            robotsAdded++;
        }
    }

    /**
     * Sets paths for the robots of the current player using the shortest path algorithm.
     */
    public void setPaths() {

        for (Robot robot : this.currentPlayer.getRobots()) {
            if (robot.getMoveType() == MoveType.DIJSKTRA) {
                robot.setPath(calculatePath_shortPath());
                if (robot.getPath().isEmpty()) {
                    System.out.println("Robot " + robot.getId() + "'s path is blocked!");
                } else {
                    System.out.println(robot.getId() + " " + robot.getPath());
                }
            } else if (robot.getMoveType() == MoveType.LONGEST_PATH) {
                robot.setPath(calculatePath_LongestPath());
                if (robot.getPath().isEmpty()) {
                    System.out.println("Robot " + robot.getId() + "'s path is blocked!");
                } else {
                    System.out.println(robot.getId() + " " + robot.getPath());
                }
            } else if (robot.getMoveType() == MoveType.RANDOM_PATH) {
                robot.setPath(calculatePath_RandomPath());
                if (robot.getPath().isEmpty()) {
                    System.out.println("Robot " + robot.getId() + "'s path is blocked!");
                } else {
                    System.out.println(robot.getId() + " " + robot.getPath());
                }
            }
        }
        switchPlayer();
        for (Robot robot : this.currentPlayer.getRobots()) {
            if (robot.getMoveType() == MoveType.DIJSKTRA) {
                robot.setPath(calculatePath_shortPath());
                if (robot.getPath().isEmpty()) {
                    System.out.println("Robot " + robot.getId() + "'s path is blocked!");
                } else {
                    System.out.println(robot.getId() + " " + robot.getPath());
                }
            } else if (robot.getMoveType() == MoveType.LONGEST_PATH) {
                robot.setPath(calculatePath_LongestPath());
                if (robot.getPath().isEmpty()) {
                    System.out.println("Robot " + robot.getId() + "'s path is blocked!");
                } else {
                    System.out.println(robot.getId() + " " + robot.getPath());
                }
            } else if (robot.getMoveType() == MoveType.RANDOM_PATH) {
                robot.setPath(calculatePath_RandomPath());
                if (robot.getPath().isEmpty()) {
                    System.out.println("Robot " + robot.getId() + "'s path is blocked!");
                } else {
                    System.out.println(robot.getId() + " " + robot.getPath());
                }
            }
        }

    }

    /**
     * Move each robot in the list, introducing a delay of 3 seconds between each move.
     * If a victory condition is met, a message indicating the winner is displayed.
     *
     * @throws IllegalArgumentException if the list of robots to move is empty.
     */
    public void moveRobots() throws IllegalArgumentException {
        if (getRobosMoveOrder().isEmpty()) {
            throw new IllegalArgumentException("Robots list move is empty!");
        }

        Iterator<Robot> iterator = this.robosMoveOrder.iterator();
        while (iterator.hasNext()) {
            Robot robot = iterator.next();
            try {
                robot.moveRobot();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Determines the current state of the game.
     *
     * @return 0 if the game is ongoing, 1 if player1 wins, 2 if player2 wins.
     */
    public int victory() {
        if (this.getMap().getLocal(this.player1.getFlagIndex()).getEnemiesCounter() > 0) {
            return 2;
        } else if (this.getMap().getLocal(this.player2.getFlagIndex()).getEnemiesCounter() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Calculates the shortest path from the current player's flag to the enemy flag.
     *
     * @return A queue of locations representing the calculated path.
     * @throws IllegalArgumentException If the start index is out of bounds.
     */
    private LinkedQueue<Local> calculatePath_shortPath() throws IllegalArgumentException {

        int startIndex = this.currentPlayer.getFlagIndex();
        int targetIndex = this.currentPlayer.getEnimieFlagIndex();

        if (startIndex < 0 || startIndex >= this.map.getNumLocations()) {
            throw new IllegalArgumentException("Start index must be between 0 and " + (this.map.getNumLocations() - 1) + ".");
        }
        Iterator<Local> iterator = this.map.iteratorShortestPath(startIndex, targetIndex);
        LinkedQueue<Local> path = new LinkedQueue<>();

        while (iterator.hasNext()) {
            path.enqueue(iterator.next());
        }

        return path;

    }


    /**
     * Calculates the longest path between the current player's flag location and the enemy's flag location.
     *
     * @return A queue representing the longest path between the flag locations.
     * @throws IllegalArgumentException if the start index is outside the valid range [0, numLocations - 1].
     */
    private LinkedQueue<Local> calculatePath_LongestPath() throws IllegalArgumentException {

        int startIndex = this.currentPlayer.getFlagIndex();
        int targetIndex = this.currentPlayer.getEnimieFlagIndex();

        if (startIndex < 0 || startIndex >= this.map.getNumLocations()) {
            throw new IllegalArgumentException("Start index must be between 0 and " + (this.map.getNumLocations() - 1) + ".");
        }
        Iterator<Local> iterator = this.map.iteratorLongestPath(startIndex, targetIndex);
        LinkedQueue<Local> path = new LinkedQueue<>();

        while (iterator.hasNext()) {
            path.enqueue(iterator.next());
        }

        return path;

    }

    /**
     * Calculates a random path between the current player's flag location and the enemy's flag location.
     *
     * @return A queue representing a random path between the flag locations.
     * @throws IllegalArgumentException if the start index is outside the valid range [0, numLocations - 1].
     */
    private LinkedQueue<Local> calculatePath_RandomPath() throws IllegalArgumentException {

        int startIndex = this.currentPlayer.getFlagIndex();
        int targetIndex = this.currentPlayer.getEnimieFlagIndex();

        if (startIndex < 0 || startIndex >= this.map.getNumLocations()) {
            throw new IllegalArgumentException("Start index must be between 0 and " + (this.map.getNumLocations() - 1) + ".");
        }
        Iterator<Local> iterator = this.map.iteratorRandomPath(startIndex, targetIndex);
        LinkedQueue<Local> path = new LinkedQueue<>();

        while (iterator.hasNext()) {
            path.enqueue(iterator.next());
        }

        return path;

    }

    /**
     * Sets the current player randomly between the available players.
     */
    private Player setCurrentPlayerRandom() {
        Player player;
        LinkedList<Player> players = new LinkedList<>();

        players.add(player1);
        players.add(player2);

        Random random = new Random();
        int randomIndex = random.nextInt(players.size());
        player = players.get(randomIndex);
        return player;
    }

}


