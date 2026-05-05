import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/* Michael Kenny 
 * CSC 140
 * Dijkstra's Algorithm Solver
 * 30th April, 2025
 * 
 * Takes in an input file with series of triples(vertex1, vertex2, distance) after a double(vertexCount, edgeCount).
 * Creates a solution for Dijkstra's Algorithm Shortest Path Distance based on these input triples that represent edges.
*/

class DijkstraMain {
    public static void main(String[] args) {
        String filePath = "input4.txt"; // Change file path here to path of input file.
        // Create new controller based on input file.
        DijkstraController controller = new DijkstraController(filePath);
        // Solve dijkstra based on given graph from input.
        controller.solve();
        // Try writing data from solution to output file.
        try {
            FileWriter outputFile = new FileWriter("output.txt");
            outputFile.write(controller.printSolution());
            outputFile.close();
        } catch (IOException e) {
            System.err.println("An error occurred with output file.");
            e.printStackTrace();
        }
    }
}

class DijkstraController {
    private File inputFile;
    private Scanner inputStream;
    private DijkstraSolution solution;

    DijkstraController(String filePath) {
        try { // On object instantization, try to open file.
            this.inputFile = new File(filePath);
            this.inputStream = new Scanner(inputFile);
        } catch (Exception e) {
            System.err.println("File invalid or missing.");
        }
        readInput();
    }

    private void readInput() {
        solution = new DijkstraSolution(
            this.inputStream.nextInt(), // Will throw exception if input file has anything but ints.
            this.inputStream.nextInt()
        );

        while (this.inputStream.hasNextInt()) {
            solution.addEdge(this.inputStream.nextInt(), this.inputStream.nextInt(), this.inputStream.nextInt());
        }
    }

    public void solve() {
        solution.solveDijkstra();
    }

    public String printSolution() {
        return solution.toString();
    }
}

class DijkstraSolution {
    private int vertexCount;
    private int edgeCount;

    private int[][] distTable;

    private ArrayList<Vertex> vertices;
    private ArrayList<Integer> verticeTracker;
    private PriorityQueue<Vertex> minHeap;

    DijkstraSolution(int vertexC, int edgeC) {
        this.vertexCount = vertexC;
        this.edgeCount = edgeC;
        this.distTable = new int[this.vertexCount][this.vertexCount];
        for (int i = 0; i < this.vertexCount; i++) { 
            // Sets all values of the lookup table to 0.
            for (int j = 0; j < this.vertexCount; j++) {
                this.distTable[i][j] = 0;
            }
        }
        this.minHeap = new PriorityQueue<Vertex>();
        this.vertices = new ArrayList<Vertex>();
        this.verticeTracker = new ArrayList<Integer>();
    }

    public void addEdge(int v, int u, int dist) {
        Vertex first = null;
        Vertex second = null;
        
        for (Vertex vertex : this.vertices) {
            // If the vertex is in vertices, grab it.
            if (vertex.getData() == v) {
                first = vertex;
            }
            if (vertex.getData() == u) {
                second = vertex;
            }
        }
        // If vertice tracker doesn't have the data value, create it.
        if (!(verticeTracker.contains(v))) {
            first = new Vertex(v);
            this.vertices.add(first);
            this.verticeTracker.add(v);
        }
        // If vertice tracker doesn't have the data value, create it.
        if (!(verticeTracker.contains(u))) {
            second = new Vertex(u);
            this.vertices.add(second);
            this.verticeTracker.add(u);
        }
        // Same as vertice tracker but for vertex objects.
        if (!(first.isNeighbor(second))) {
            first.addAdj(second);
        }
        // Same as vertice tracker but for vertex objects.
        if (!(second.isNeighbor(first))) {
            second.addAdj(first);
        }
        this.distTable[v][u] = dist;
    }

    public void solveDijkstra() {
        Vertex source = vertices.get(0);
        source.setDist(0);

        for (int i = 0; i < this.vertexCount; i++) {
            this.minHeap.add(vertices.get(i)); // Add all objects to heap.
        }

        while (!(this.minHeap.isEmpty())) {
            Vertex u = this.minHeap.remove();
            for (int i = 0; i < u.adjListSize(); i++) {
                Vertex v = u.getAdj(i);
                if (this.distTable[u.getData()][v.getData()] == 0) {
                    continue; // If this value from the distTable is 0, indicates v and u are the same.
                }
                int pathDist = u.getDist() + this.distTable[u.getData()][v.getData()];
                if (pathDist < v.getDist()) {
                    this.minHeap.remove(v); // Remove from heap then readd to update values.
                    v.setDist(pathDist);
                    v.setParent(u);
                    this.minHeap.add(v);
                }
            }
        }
    }

    @Override
    public String toString() {
        String vertexString = "Vertices:";
        String distanceString = "Distances:";
        
        ArrayList<Integer> vertices = new ArrayList<Integer>();

        for (int i = 0; i < this.vertices.size(); i++) {
            Vertex current = this.vertices.get(i);
            if (!(vertices.contains(current.getData()))) {
                vertexString += (" " + String.valueOf(current.getData()));
                distanceString += (" " + String.valueOf(current.getDist()));
                vertices.add(current.getData());
            }
        }

        return "Vertex Count: " + this.vertexCount +
                "   Edge Count: " + this.edgeCount +
                "\n" + vertexString + "\n" + distanceString;
    }

}

class Vertex implements Comparable<Vertex> {
    private int data;
    private int dist;
    private Vertex parent;

    private ArrayList<Vertex> adjList;
    // This is unnecessary but I could not find another way of
    // keeping track of both vertex objects and all vertices.
    // It's dumb but it works.
    private ArrayList<Integer> adjTracker;

    Vertex(int data) {
        this.data = data;
        this.dist = 1000000; // Represents infinity.
        this.parent = null;
        this.adjList = new ArrayList<Vertex>();
        this.adjTracker = new ArrayList<Integer>();
    }

    public int getData() {
        return this.data;
    }

    public int getDist() {
        return this.dist;
    }

    public Vertex getParent() {
        return this.parent;
    }

    public void setParent(Vertex newParent) {
        this.parent = newParent;
    }

    public void setDist(int newDist) {
        this.dist = newDist;
    }

    public boolean isNeighbor(Vertex otherVertex) {
        if (this != otherVertex && this.adjList.contains(otherVertex)) {
            return true;
        }
        return false;
    }

    public void addAdj(Vertex other) {
        if (!(this.isNeighbor(other)) && !(this.adjTracker.contains(other.getData()))) {
            this.adjList.add(other);
            this.adjTracker.add(other.getData());
        }
    }

    public Vertex getAdj(int index) {
        return this.adjList.get(index);
    }

    public int adjListSize() {
        return this.adjList.size();
    }

    // This is absolutely necessary to heapify vertices in the minHeap
    // based on their distances.
    @Override
    public int compareTo(Vertex other) {
        return Integer.compare(this.getDist(), other.getDist());
    }
}