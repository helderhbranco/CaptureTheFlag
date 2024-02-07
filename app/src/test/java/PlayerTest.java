import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import capturetheflag.*;

public class PlayerTest {
    Player player;
    Robot robot;

    @BeforeEach
    void setUp() {
        this.player = new Player("Player1");
        this.robot = new Robot(player);
    }
    @Test
    void testSetName() {
        player.setName("NewName");

        assertEquals("NewName", player.getName());
    }

    @Test
    void testSetFlagIndex() {
        player.setFlagIndex(1);

        assertEquals(1, player.getFlagIndex());
    }

    @Test
    void testSetEnimieFlagIndex() {
        player.setEnimieFlagIndex(2);

        assertEquals(2, player.getEnimieFlagIndex());
    }

    @Test
    void testAddRobot() {

        assertEquals(1, player.getRobots().size());
        assertEquals(robot, player.getRobots().first());
    }
}
