package Collections.Graphs;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Lists.*;
import Collections.Queues.LinkedQueue;
import Collections.Stacks.LinkedStack;
import Collections.Trees.LinkedHeap;
import Collections.Trees.LinkedMaxHeap;
import Collections.SortSearch;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Random;

import capturetheflag.Local;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Network<T> extends Graph<T> implements NetworkADT<T> {

    protected double[][] adjMatrix;

    public Network() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), 0);
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    public void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    @Override
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = Double.POSITIVE_INFINITY;
            adjMatrix[index2][index1] = Double.POSITIVE_INFINITY;
        }
    }

    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
    }

    public void addVertex() {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = null;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }

    @Override
    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numVertices--;

            for (int i = index; i < numVertices; i++) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j <= numVertices; j++) {
                    adjMatrix[i][j] = adjMatrix[i + 1][j];
                }
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    adjMatrix[j][i] = adjMatrix[j][i + 1];
                }
            }
        }
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }

    public Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            //Find a vertex adjacent to x that has not been visited and push it
            //on the stack
            for (int i = 0; (i < numVertices) && !found; i++) {
                if ((adjMatrix[x][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) {

        return iteratorBFS(getIndex(startVertex));
    }

    public Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x]);

            //Find all vertices adjacent to x that have not been visited and
            //queue them up
            for (int i = 0; i < numVertices; i++) {
                if ((adjMatrix[x][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    @Override
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) {
        int index = 0;
        double weight = 0;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        //Update the pathWeight for each vertex except the startVertex. Notice
        //that all vertices not adjacent to the startVertex will have a
        //pathWeight of infinity for now
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i];
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }

        do {
            do {
                if (!traversalMinHeap.isEmpty()) {
                    weight = traversalMinHeap.removeMin();
                    index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, weight);
                } else {
                    break;
                }
            } while (index == -1);

            traversalMinHeap.removeAllElements();

            if (weight == Double.POSITIVE_INFINITY) { // no possible path
                return resultList.iterator();
            }

            visited[index] = true;

            //Update the pathWeight for each vertex that has not been
            //visited and is adjacent to the last vertex that was visited.
            //Also, add each unvisited vertex to the heap
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY) && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);
        while (it.hasNext()) {
            templist.addToRear(vertices[it.next()]);
        }
        return templist.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    public Iterator<T> iteratorLongestPath(int startIndex, int targetIndex) {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorLongestPathIndices(startIndex, targetIndex);
        while (it.hasNext()) {
            templist.addToRear(vertices[it.next()]);
        }
        return templist.iterator();
    }

    public Iterator<T> iteratorLongestPath(T startVertex, T targetVertex) {
        return iteratorLongestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    protected Iterator<Integer> iteratorLongestPathIndices(int startIndex, int targetIndex) {
        int index = 0;
        double weight = 0;
        int[] predecessor = new int[numVertices];
        LinkedMaxHeap<Double> traversalMaxHeap = new LinkedMaxHeap<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        //int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.NEGATIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;

        //Update the pathWeight for each vertex except the startVertex. Notice
        //that all vertices not adjacent to the startVertex will have a
        //pathWeight of infinity for now
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i];
                if (pathWeight[i] == Double.POSITIVE_INFINITY) {
                    pathWeight[i] = Double.NEGATIVE_INFINITY;
                }
                predecessor[i] = startIndex;
                traversalMaxHeap.addElement(pathWeight[i]);
            }
        }

        if (traversalMaxHeap.isEmpty()) { //no possible path
            return resultList.iterator();
        }

        do {
            do {
                if (!traversalMaxHeap.isEmpty()) {
                    weight = traversalMaxHeap.removeMin(); //actually removes max

                    index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, weight);
                } else {
                    break;
                }
            } while (index == -1);

            traversalMaxHeap.removeAllElements();

            if (weight == Double.NEGATIVE_INFINITY) { // no possible path
                return resultList.iterator();
            }

            visited[index] = true;

            //Update the pathWeight for each vertex that has not been
            //visited and is adjacent to the last vertex that was visited.
            //Also, add each unvisited vertex to the heap
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY) && (pathWeight[index] + adjMatrix[index][i]) > pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMaxHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMaxHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    public Iterator<T> iteratorRandomPath(int startIndex, int targetIndex) {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorRandomPathIndices(startIndex, targetIndex);
        while (it.hasNext()) {
            templist.addToRear(vertices[it.next()]);
        }
        return templist.iterator();
    }

    public Iterator<T> iteratorRandomPath(T startVertex, T targetVertex) {
        return iteratorRandomPath(getIndex(startVertex), getIndex(targetVertex));
    }

    protected Iterator<Integer> iteratorRandomPathIndices(int startIndex, int targetIndex) {
        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        //Update the pathWeight for each vertex except the startVertex. Notice
        //that all vertices not adjacent to the startVertex will have a
        //pathWeight of infinity for now
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i];
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }

        do {
            Double[] weightsList = new Double[traversalMinHeap.size()];

            int j = 0;
            while (!traversalMinHeap.isEmpty()) {
                weightsList[j] = traversalMinHeap.removeMin();
                j++;
            }

            double randomWeight;

            do {
                randomWeight = getRandomWeight(weightsList);
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, randomWeight);
            } while (index == -1);

                if (randomWeight == Double.POSITIVE_INFINITY) {
                    return resultList.iterator();
                }

            visited[index] = true;

            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY) && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }

        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear(stack.pop());
        }

        return resultList.iterator();
    }

    private double getRandomWeight(Double[] weightsArray) {
        int numNonInfinity = 0;
        for (double weight : weightsArray) {
            if (weight != Double.POSITIVE_INFINITY) {
                numNonInfinity++;
            }
        }

        if (numNonInfinity == 0) {
            return Double.POSITIVE_INFINITY;
        }

        Random random = new Random();
        double result;

        do {
            result = weightsArray[random.nextInt(weightsArray.length)];
        } while (result == Double.POSITIVE_INFINITY);

        return result;
    }

    protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited, double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numVertices; j++) {
                    if ((adjMatrix[i][j] < Double.POSITIVE_INFINITY) && visited[j]) {
                        return i;
                    }
                }
            }
        }

        return -1;  // should never get to here
    }

    //Other methods
    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if ((adjMatrix[i][j] == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }

        /**
         * Will only get to here if a valid edge is not found
         */
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }

    public double shortestPathWeight(int startIndex, int targetIndex) {
        double result = 0;
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);

        if (it.hasNext()) {
            index1 = it.next();
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (it.hasNext()) {
            index2 = it.next();
            result += adjMatrix[index1][index2];
            index1 = index2;
        }

        return result;
    }

    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        return shortestPathWeight(getIndex(startVertex), getIndex(targetVertex));
    }

    public boolean isConnected_Network() {
        if (isEmpty()) {
            return false;
        }

        for (int i = 0; i < numVertices; i++) {
            if (!isReachableFromAllVertices(i)) {
                return false;
            }
        }

        return true;
    }

    private boolean isReachableFromAllVertices(int startIndex) {
        Iterator<T> it = iteratorBFS(startIndex);
        int count = 0;

        while (it.hasNext()) {
            it.next();
            count++;
        }

        return (count == numVertices);
    }

    @Override
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        double[][] largerAdjMatrix = new double[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }

    public String toString() {
        if (numVertices == 0) {
            return "Graph is empty";
        }
        String result = "";

        //Print the adjacency Matrix
        result += "Adjacency Matrix\n";
        result += "----------------\n";
        result += "index\n\t";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i;
            if (i < 10) {
                result += " ";
            }
        }
        result += "\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";

            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    result += "1 ";
                } else {
                    result += "0 ";
                }
            }
            result += "\n";
        }

        //Print the vertex values
        result += "\n\nVertex Values";
        result += "\n-------------\n";
        result += "index\tvalue\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }

        //Print the weights of the edges
        result += "\n\nWeights of Edges";
        result += "\n----------------\n";
        result += "index\tweight\n\n";

        for (int i = 0; i < numVertices; i++) {
            for (int j = numVertices - 1; j > i; j--) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    result += i + " to " + j + "\t";
                    result += adjMatrix[i][j] + "\n";
                }
            }
        }

        result += "\n";

        return result;
    }

    /**
     * Converts the map data into a JSONObject.
     *
     * @return A JSONObject containing the map data.
     */
    private JSONObject toJsonObject() {
        JSONObject jsonMap = new JSONObject();

        // Adicionar a matriz de adjacência
        JSONArray adjacencyMatrixArray = new JSONArray();
        for (int i = 0; i < numVertices; i++) {
            JSONArray rowArray = new JSONArray();
            for (int j = 0; j < numVertices; j++) {
                rowArray.add(adjMatrix[i][j] < Double.POSITIVE_INFINITY ? adjMatrix[i][j] : null);
            }
            adjacencyMatrixArray.add(rowArray);
        }
        jsonMap.put("adjacencyMatrix", adjacencyMatrixArray);

        // Adicionar os valores dos vértices
        JSONArray vertexValuesArray = new JSONArray();
        for (int i = 0; i < numVertices; i++) {
            JSONObject vertexObject = new JSONObject();
            vertexObject.put("index", i);
            vertexObject.put("value", vertices[i].toString());  // Certifique-se de ter uma lógica adequada aqui
            vertexValuesArray.add(vertexObject);
        }
        jsonMap.put("vertexValues", vertexValuesArray);

        // Adicionar os pesos das arestas
        JSONArray edgeWeightsArray = new JSONArray();
        for (int i = 0; i < numVertices; i++) {
            for (int j = numVertices - 1; j > i; j--) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    JSONObject edgeObject = new JSONObject();
                    edgeObject.put("index", i + " to " + j);
                    edgeObject.put("weight", adjMatrix[i][j]);
                    edgeWeightsArray.add(edgeObject);
                }
            }
        }
        jsonMap.put("edgeWeights", edgeWeightsArray);

        return jsonMap;
    }

    /**
     * Saves the map data to a JSON file.
     *
     * @param fileName The name of the file to save the JSON data.
     */
    public void saveToJsonFile(String fileName) {
        try {
            String filePath = fileName + ".json";  // Adiciona a extensão .json ao nome do arquivo
            Files.write(Paths.get(filePath), Collections.singletonList(toJsonObject().toJSONString()));
            System.out.println("Map saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the map data from a JSON file.
     *
     * @param filePath The path of the JSON file to load.
     */
   public void loadFromJsonFile(String filePath) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {

            Object obj = parser.parse(reader);
            JSONObject jsonMap = (JSONObject) obj;


            clear();


            JSONArray vertexValuesArray = (JSONArray) jsonMap.get("vertexValues");
            int y = 1;
            for (Object vertexObj : vertexValuesArray) {
                JSONObject vertexJson = (JSONObject) vertexObj;
                T vertexValue = (T) vertexJson.get("value");
                addVertex((T) new Local(y));
                y++;
            }


            JSONArray adjacencyMatrixArray = (JSONArray) jsonMap.get("adjacencyMatrix");
            for (int i = 0; i < adjacencyMatrixArray.size(); i++) {
                JSONArray rowArray = (JSONArray) adjacencyMatrixArray.get(i);
                for (int j = 0; j < rowArray.size(); j++) {
                    Double weight = (Double) rowArray.get(j);
                    if (weight != null && weight < Double.POSITIVE_INFINITY) {
                        addEdge(vertices[i], vertices[j], weight);
                    }
                }
            }

            JSONArray edgeWeightsArray = (JSONArray) jsonMap.get("edgeWeights");
            for (Object edgeWeightObj : edgeWeightsArray) {
                JSONObject edgeWeightJson = (JSONObject) edgeWeightObj;
                String edgeIndex = (String) edgeWeightJson.get("index");
                Double edgeWeight = (Double) edgeWeightJson.get("weight");

                String[] indices = edgeIndex.split(" to ");
                int vertex1Index = Integer.parseInt(indices[0]);
                int vertex2Index = Integer.parseInt(indices[1]);

                setEdgeWeight(vertices[vertex1Index], vertices[vertex2Index], edgeWeight);
            }

            System.out.println("Graph loaded successfully from " + filePath);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void setEdgeWeight(T vertex1, T vertex2, double weight) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);

        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            adjMatrix[index2][index1] = weight;
        }
    }
}
