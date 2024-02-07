
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import capturetheflag.*;

class LocalTest {

    private Local local;
    @BeforeEach
    void setUp() {
        this.local = new Local(1);

    }
    @Test
    void testAddRobot() {
        Player player1 = new Player("Player1");
        player1.setFlagIndex(this.local.getId());
        Player player2 = new Player("Player2");
        Robot teamRobot = new Robot(player1);
        Robot enemyRobot = new Robot(player2);

        assertDoesNotThrow(() -> {
            local.addRobot(player1, teamRobot);
            assertEquals(1, local.getTeamCounter());
            assertEquals(0, local.getEnemiesCounter());
        });

        assertDoesNotThrow(() -> {
            local.addRobot(player2, enemyRobot);
            assertEquals(1, local.getTeamCounter());
            assertEquals(1, local.getEnemiesCounter());
        });

        assertThrows(IllegalArgumentException.class, () -> local.addRobot(null, new Robot(player1)));
        assertThrows(IllegalArgumentException.class, () -> local.addRobot(player1, null));
    }

    @Test
    void testRemoveRobot() {
        Player player1 = new Player("Player1");
        player1.setFlagIndex(this.local.getId());
        Player player2 = new Player("Player2");
        Robot teamRobot = new Robot(player1);
        Robot enemyRobot = new Robot(player2);

        local.addRobot(player1, teamRobot);
        local.addRobot(player2, enemyRobot);

        assertDoesNotThrow(() -> {
            local.removeRobot(teamRobot);
            assertEquals(0, local.getTeamCounter());
            assertEquals(1, local.getEnemiesCounter());
        });

        assertDoesNotThrow(() -> {
            local.removeRobot(enemyRobot);
            assertEquals(0, local.getTeamCounter());
            assertEquals(0, local.getEnemiesCounter());
        });

        assertThrows(IllegalArgumentException.class, () -> local.removeRobot(null));
        assertThrows(IllegalArgumentException.class, () -> local.removeRobot(new Robot(player1)));
    }
}