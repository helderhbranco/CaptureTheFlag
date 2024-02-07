package capturetheflag;

import java.util.Random;

import Collections.Graphs.Network;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represents the map in the Capture The Flag game, defining the locations, connections, and density.
 */
public class Map extends Network {

    private int numLocations;
    private boolean bidirectional;
    private double density;
    private int numEdges;

    /**
     * Constructs a Map with the specified number of locations, bidirectional property, and density.
     *
     * The constructor validates the input parameters and ensures that the number of locations is at least 5,
     * the density is between 0 and 1, and the provided density is sufficient to create a connected graph with
     * the given number of locations.
     *
     * @param numLocations The number of locations in the map. Must be 5 or more.
     * @param bidirectional Indicates whether the map should have bidirectional edges.
     * @param density The density of edges in the map. Must be between 0 and 1.
     * @throws IllegalArgumentException if the number of locations is less than 5, if the density is outside the
     *         range [0, 1], or if the provided density is insufficient to create a connected graph with the given
     *         number of locations.
     */
    public Map(int numLocations, boolean bidirectional, double density) {
        super();
        if (numLocations < 5) {
            throw new IllegalArgumentException("Number of locations must be 5 or more.");
        }
        if (density < 0 || density > 1) {
            throw new IllegalArgumentException("Density must be between 0 and 1.");
        }

        this.numLocations = numLocations;
        this.bidirectional = bidirectional;

        double minDensity = calculateMinimumDensity(numLocations, bidirectional);
        if (density < minDensity) {
            throw new IllegalArgumentException("Density must be at least " + minDensity + ".");
        }
        this.density = density;
        this.numEdges = 0;
        generateRandomMap();
    }

    /**
     * Constructor for a map
     */
    public Map() {
        super();
        this.numLocations = 0;
        this.bidirectional = false;
        this.density = 0;
        this.numEdges = 0;

    }

    /**
     * Gets the number of locations in the map.
     *
     * @return The number of locations.
     */
    public int getNumLocations() {
        return this.numLocations;
    }

    /**
     * Sets the number of locations in the map.
     *
     * @param numLocations The number of locations.
     */
    public void setNumLocations(int numLocations) {
        this.numLocations = numLocations;
    }

    /**
     * Checks if the edges in the map are bidirectional.
     *
     * @return True if bidirectional, false otherwise.
     */
    public boolean isBidirectional() {
        return this.bidirectional;
    }

    /**
     * Sets whether the edges in the map are bidirectional.
     *
     * @param bidirectional True if bidirectional, false otherwise.
     */
    public void setBidirectional(boolean bidirectional) {
        this.bidirectional = bidirectional;
    }

    /**
     * Gets the density of edges in the map.
     *
     * @return The density value (between 0 and 1).
     */
    public double getDensity() {
        return this.density;
    }

    /**
     * Sets the density of edges in the map.
     *
     * @param density The density value (between 0 and 1).
     */
    public void setDensity(double density) {
        this.density = density;
    }

    /**
     * Gets the number of edges in the map.
     *
     * @return The number of edges.
     */
    public int getNumEdges() {
        return this.numEdges;
    }

    /**
     * Sets the number of edges in the map.
     *
     * @param numEdges The number of edges.
     */
    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }

    /**
     * Gets the location with the specified ID.
     *
     * @param id The ID of the location to retrieve.
     * @return The location with the specified ID, or null if not found.
     * @throws IllegalArgumentException If the ID is negative or greater than the number of locations.
     */
    public Local getLocal(int id) throws IllegalArgumentException {
        System.out.println("id: " + id);
        if (id < 0) {
            throw new IllegalArgumentException("Id must be positive.");
        }

        for (int i = 0; i < this.numLocations; i++) {
            if (((Local) getVertices()[i]).getId() == id) {
                Local local = (Local) getVertices()[i];
                return local;
            }
        }

        return null;
    }

    /**
     * Generates a random map by adding locations and connecting them with edges based on the specified density and bidirectionality.
     * <p>
     * The method ensures that the number of locations is at least 5 and the density is within the valid range [0, 1].
     * It then iterates over each pair of locations, randomly deciding whether to add an edge between them based on the density.
     * The distance of the edge is a random value between 1 and 15 kilometers.
     * The process continues until the actual number of edges reaches the expected number calculated from the density.
     * Finally, it checks whether the generated map is connected; if not, it raises an exception.
     * </p>
     *
     * @throws IllegalArgumentException If isn't possible to generate a connected map.
     */
    public void generateRandomMap() throws IllegalArgumentException {
        Random random = new Random();

        for (int i = 0; i < this.numLocations; i++) {
            addVertex(new Local(i));
        }

        for (int i = 0; i < this.numLocations; i++) {
            for (int j = i + 1; j < this.numLocations; j++) {
                if (getNumEdges() < calculateExpectedNumEdges() && random.nextDouble() < this.density) {
                    double distance = random.nextInt(15) + 1;  // Distância aleatória entre 1 e 15 km
                    if (isBidirectional()) {
                        addEdge(i, j, distance);
                        addEdge(j, i, distance);
                        this.numEdges++;
                    } else {
                        int added = 0;
                        if(random.nextInt(3) > 0) {
                            addEdge(i, j, distance);
                            added = 1;
                        }
                        if(random.nextInt(3) > 0) {
                            addEdge(j, i, distance);
                            added = 1;
                        }
                        if(added == 1) {
                            this.numEdges++;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        if (!isConnected()) {
            throw new IllegalArgumentException("The generated map is not connected. Please try again.");
        }

    }

    /**
     * Calculates the expected number of edges for a graph based on the number of locations and specified density.
     * <p>
     * The expected number of edges is calculated using the formula: (density * numLocations * (numLocations - 1)) / 2.
     * </p>
     *
     * @return The expected number of edges for the graph.
     */
    private int calculateExpectedNumEdges() {
        int numLocations = getNumLocations();
        double expectedDensity = getDensity();
        int numEdges = (int) (expectedDensity * numLocations * (numLocations - 1) / 2);
        if (isBidirectional()) {
            numEdges *= 2;
        }
        return numEdges;
    }

    /**
     * Calculates the density of a graph represented by its adjacency matrix.
     * <p>
     * The density of a graph is defined as the ratio of the number of edges to the
     * maximum possible number of edges in a complete graph with the same number of vertices.
     * <p>
     * The adjacency matrix should be a square matrix where each element represents the weight
     * of the edge between the corresponding vertices. A weight of Double.POSITIVE_INFINITY
     * indicates the absence of an edge.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The density of the graph.
     * @throws IllegalArgumentException if the adjacency matrix is not a square matrix.
     */
    private double calculateDensity(double[][] adjacencyMatrix) {
        int numEdges = 0;
        int numVertices = adjacencyMatrix.length;

        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (adjacencyMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    numEdges++;
                }
            }
        }

        return (double) numEdges / ((numVertices * (numVertices - 1)) / 2);
    }

    /**
     * Calculates the minimum density for a graph with the specified number of locations and bidirectionality.
     *
     * @param numLocations
     * @param isBidirectional
     * @return The minimum density for the graph.
     */
    private double calculateMinimumDensity(int numLocations, boolean isBidirectional) {
        if (numLocations <= 1) {
            return 0.0;
        }

        if (isBidirectional) {
            return 2.0 / (numLocations - 1);
        } else {
            return 1.0 / (numLocations - 1);
        }
    }


}
