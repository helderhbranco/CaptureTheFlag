package capturetheflag;
import java.util.Scanner;
import java.util.Locale;

import Collections.Lists.CircularLinkedList;
import Enums.MoveType;

public class Play {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Welcome to Capture The Flag Game!");

        // Menu options
            System.out.println("\nMenu:");
            System.out.println("1.Start Game");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

        System.out.println("\nMenu:");
        System.out.println("1.Start Game");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                startGame(scanner);
                break;
            case 2:
                System.out.println("Exiting. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }


    private static void startGame(Scanner scanner) {
        System.out.println("\nCreate Game:");

        Map map = null;
        Player player1 = null;
        Player player2 = null;

        System.out.print("Do you want to restore a map from a JSON file? (yes/no): ");
        String restoreMapChoice = scanner.next().toLowerCase();
        if (restoreMapChoice.equals("yes")) {
            System.out.print("Enter the name of the file to load the map JSON: ");
            String fileName = scanner.next();

            try {
                MapLoader mapLoader = new MapLoader();
                map = mapLoader.loadMapFromJson(fileName);
                map.setNumLocations(map.getVertices().length);
                System.out.println("Map loaded successfully!");
                System.out.println(map.toString());
                System.out.println("Number of locations: " + map.getNumLocations());
            } catch (IllegalArgumentException e) {
                System.out.println("Error loading the map: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Creating a new map...");

            try {
                System.out.print("Enter the number of locations in the map: ");
                int numLocations = scanner.nextInt();

                System.out.print("Is the map bidirectional? (true/false): ");
                boolean bidirectional = scanner.nextBoolean();

                System.out.print("Enter the density of edges in the map (between 0 and 10): ");
                int density_temp = scanner.nextInt();
                double density = (double) density_temp / 10;

            try {
            map = new Map(numLocations, bidirectional, density);
            } catch (IllegalArgumentException e) {
                System.out.println("Error creating the map: " + e.getMessage());
                return;
            }

            } catch (IllegalArgumentException e) {
                System.out.println("Error creating the map: " + e.getMessage());
                return;
            }
        }

        System.out.print("Enter the name of the first player: ");
        String player1Name = scanner.next();
        player1 = new Player(player1Name);


        System.out.print("Enter the name of the second player: ");
        String player2Name = scanner.next();
        player2 = new Player(player2Name);

        System.out.println("How many robots do you want to create for each player: ");
        int numRobots = scanner.nextInt();

        try {
            Game game = new Game(map, player1, player2, numRobots);

            System.out.println(game.getMap().toString());

            System.out.print("Enter the flag location for " + game.getPlayer1().getName() + ": ");
            int flag1Location = scanner.nextInt();

            System.out.print("Enter the flag location for " + game.getPlayer2().getName() + ": ");
            int flag2Location = scanner.nextInt();

            game.setFlag(flag1Location, flag2Location);

            System.out.println("Game created successfully!");

            game.setRobots();
            game.setOrderRobots();
            setMoveType(scanner, game.getRobosMoveOrder());
            System.out.println(game.getMap().toString());
            game.setPaths();
            System.out.println("Game started!");
            while(game.victory() == 0){
                try{
                    game.moveRobots();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error moving the robots: " + e.getMessage());
                    return;
                }
            }
            if (game.victory() == 1) {
                System.out.println("The winner is " + game.getPlayer1().getName());
            } else {
                System.out.println("The winner is " + game.getPlayer2().getName());
            }

            System.out.print("Do you want to save the map to a JSON file? (yes/no): ");
            String saveMapChoice = scanner.next().toLowerCase();

            if (saveMapChoice.equals("yes")) {
                System.out.print("Enter the name of the file (without extension): ");
                String fileName = scanner.next();
                game.getMap().saveToJsonFile(fileName);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error creating the game: " + e.getMessage());
        }
    }

    public static void setMoveType(Scanner scanner, CircularLinkedList<Robot> robosMoveOrder) {
        System.out.println("Choose the type of movement for the robots:");
        System.out.println("1. Shortest path");
        System.out.println("2. Longest path");
        System.out.println("3. Random path");

        for (Robot robot : robosMoveOrder) {
            System.out.print(robot.getPlayer().getName() + " enter the move type for Robot " + robot.getId() + ": ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    robot.setMoveType(MoveType.DIJSKTRA);
                    break;
                case 2:
                    robot.setMoveType(MoveType.LONGEST_PATH);
                    break;
                case 3:
                    robot.setMoveType(MoveType.RANDOM_PATH);
                    break;
                default:
                    System.out.println("Invalid option. Setting default move type for Robot " + robot.getId());
            }
        }
    }
}
