import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import capturetheflag.*;
import Collections.Queues.LinkedQueue;

public class GameTest {

    Game game;

    @BeforeEach
    void setUp() {
        this.game = new Game(10, true, 1, "Player1", "Player2", 3);
    }



    @Test
    void testGameInitialization() {
        assertDoesNotThrow(() -> new Game(10, true, 0.5, "Player1", "Player2", 3));
    }

    @Test
    void testGetters() {

        assertEquals(10, game.getMap().getNumLocations());
        assertEquals(3, game.getRobotsNumber());
        assertNotNull(game.getPlayer1());
        assertNotNull(game.getPlayer2());
        assertNotNull(game.getCurrentPlayer());
    }

    @Test
    void testSwitchPlayer() {

        Player initialPlayer = game.getCurrentPlayer();
        game.switchPlayer();
        assertNotEquals(initialPlayer, game.getCurrentPlayer());
        game.switchPlayer();
        assertEquals(initialPlayer, game.getCurrentPlayer());
    }

    @Test
    void testSetFlag() {

        assertDoesNotThrow(() -> game.setFlag(1, 2));
        assertEquals(1, game.getPlayer1().getFlagIndex());
        assertEquals(2, game.getPlayer1().getEnimieFlagIndex());
        assertEquals(2, game.getPlayer2().getFlagIndex());
        assertEquals(1, game.getPlayer2().getEnimieFlagIndex());
    }

    @Test
    void testSetRobots() {
        game.setFlag(1, 8);

        game.setRobots();
        assertEquals(3, game.getPlayer1().getRobots().size());
        assertEquals(3, game.getMap().getLocal(1).getTeamCounter());
        assertEquals(3, game.getPlayer2().getRobots().size());
        assertEquals(3, game.getMap().getLocal(8).getTeamCounter());
    }

    @Test
    void testSetPaths() {
        game.setPaths();

        for (Robot robot : game.getPlayer1().getRobots()) {
            assertNotNull(robot.getPath());
            assertFalse(robot.getPath().isEmpty());
        }

        for (Robot robot : game.getPlayer2().getRobots()) {
            assertNotNull(robot.getPath());
            assertFalse(robot.getPath().isEmpty());
        }
    }
    @Test
    void testCalculatePath_shortPath() {
        game.setFlag(1, 8);
        game.setPaths();

        for (Robot robot : game.getPlayer1().getRobots()) {
            assertNotNull(robot.getPath());
            assertFalse(robot.getPath().isEmpty());
        }

        for (Robot robot : game.getPlayer2().getRobots()) {
            assertNotNull(robot.getPath());
            assertFalse(robot.getPath().isEmpty());
        }
    }

    @Test
    void testCalculatePath_longPath() {
        game.setFlag(1, 8);

        for (Robot robot : game.getPlayer1().getRobots()) {
            robot.setPath(game.calculatePath_LongestPath());
            assertFalse(robot.getPath().isEmpty());
            assertTrue(robot.getPath().size() > 0);
        }

        for (Robot robot : game.getPlayer2().getRobots()) {
            assertNotNull(robot.getPath());
            assertFalse(robot.getPath().isEmpty());
        }
    }
}
