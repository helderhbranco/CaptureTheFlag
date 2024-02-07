import Collections.Lists.LinkedUnorderedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import capturetheflag.*;
import Enums.*;

import java.util.Random;
import java.util.List;

import java.util.Iterator;

public class MainGameTest {

    private Game game;
    private Player player;
    private Robot robot;

    @BeforeEach
    void setUp() {
        game = new Game(100, true, 0.8, "Player1", "Player2", 10);
        this.game.setFlag(0, 99);
        this.game.setRobots();
        this.game.setOrderRobots();
    }

    @Test
    void testMoveRobotWithDijkstra() {
        // Define a localização inicial do robô e o alvo usando índices
        int startIndex = 0; // substitua pelo índice correto
        int targetIndex = 8; // substitua pelo índice correto

        robot.setMoveType(MoveType.DIJSKTRA);

        // Calcula o caminho mais curto usando Dijkstra
        this.game.setPaths();

        System.out.println(robot.getPath());

        // Move o robô
        robot.moveRobot();

        // Verifica se o robô foi movido para o local de destino
        //assertEquals(targetIndex, this.game.getMap().getLocal(targetIndex).getRobots().toString());
        assertEquals(1, this.game.getMap().getLocal(1).getEnemiesCounter());

        // Verifica se o local inicial está vazio
        assertFalse(game.getMap().getLocal(startIndex).getRobots().contains(robot));

        // Verifica se o local de destino contém o robô
        assertFalse(game.getMap().getLocal(targetIndex).getRobots().isEmpty());
        assertEquals(true, game.getMap().getLocal(targetIndex).getRobots().contains(robot));
    }

    @Test
    public void testSetOrderRobots() {


        int expectedSize = game.getRobotsNumber() * 2;
        assertEquals(expectedSize, game.getRobosMoveOrder().size());

        System.out.println("Robôs na ordem de movimento:");
        Iterator<Robot> iterator = game.getRobosMoveOrder().iterator();
        while (iterator.hasNext()) {
            Robot robot = iterator.next();
            System.out.println("Nome do robô: " + robot.getId() + ", Pertence ao jogador: " + robot.getPlayer().getName());
        }

    }

    @Test
    public void testGameFlow() {
        //System.out.println(game.getMap().toString() + "\n");

        assertEquals(20, game.getRobosMoveOrder().size());

        // Set random moveType for each robot
        setRandomMoveType(game.getPlayer1().getRobots());
        setRandomMoveType(game.getPlayer2().getRobots());

        game.setOrderRobots();
        game.setPaths();



        // Move robots and check for victory conditions
        int moveCount = 0;
        while (game.victory() == 0 && moveCount < 20) {
            game.moveRobots();
            moveCount++;
        }

        // Check if there is a winner
        int winner = game.victory();
        assertTrue(winner != 0, "There should be a winner");
        System.out.println("Game ended. Winner: Player " + winner);
    }

    private void setRandomMoveType(LinkedUnorderedList<Robot> robots) {
        Random random = new Random();
        for (Robot robot : robots) {
            MoveType randomMoveType = MoveType.values()[random.nextInt(MoveType.values().length)];
            robot.setMoveType(randomMoveType);
        }
    }

}

