package graphs;

/*
* representation of a simple undirected and unweighted graph using adjacency lists
* using a HashMap to keep track of vertices and their neighbours
* */


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph<Vertex> {
    // adjacency list containing for each vertex its neighbours
    private HashMap<Vertex, Set<Vertex>> adjacencyList;
    private int numberOfVertices = 0;
    private int numberOfEdges = 0;

    /*
     * initialize new Graph object
     * */
    public Graph() {
        adjacencyList = new HashMap<>();
    }

    /*
     * does graph contain {@code v}?
     * */
    public boolean containsVertex(Vertex v) {
        return adjacencyList.containsKey(v);
    }

    /*
     * return number of vertices
     * */
    public int numberOfVertices() {
        return numberOfVertices;
    }

    /*
     * return number of edges
     * */
    public int numberOfEdges() {
        return numberOfEdges;
    }

    /*
    * return degree (number of neighbours) of {@code vertex}
    * */
    public int getDegree(Vertex v){
        return adjacencyList.get(v).size();
    }


    /*
     * add new vertex to the graph
     * */
    public void addVertex(Vertex v) {
        if (containsVertex(v)) {
            return;
        }
        adjacencyList.put(v, new HashSet<>());
        numberOfVertices++;
    }

    /*
     * remove a vertex from the graph
     * */
    public void removeVertex(Vertex v) {
        validate(v);
        int edgesToRemove = getDegree(v);
        adjacencyList.remove(v);

        // remove vertex from all neighbours
        for (Vertex w : adjacencyList.keySet()) {
            adjacencyList.get(w).remove(v);
        }

        numberOfVertices--;
        numberOfEdges = numberOfEdges - edgesToRemove;

    }



    /*
     * add a new edge between {@code v} and {@ code w} to the graph
     * */
    public void addEdge(Vertex v, Vertex w) {
        validate(v);
        validate(w);
        // add if not already there
        if (!adjacencyList.get(v).contains(w)) {
            // add w to neighbours of v
            adjacencyList.get(v).add(w);
            // add v to neighbours of w
            adjacencyList.get(w).add(v);
            numberOfEdges++;
        }
    }

    public void removeEdge(Vertex v, Vertex w) {
        validate(v);
        validate(w);
        // only remove when present
        if(adjacencyList.get(v).contains(w)) {
            adjacencyList.get(v).remove(w);
            adjacencyList.get(w).remove(v);

            numberOfEdges--;
        }

    }

    public Iterable<Vertex> getVertices(){
        return adjacencyList.keySet();
    }

    public Iterable<Vertex> getNeighbours(Vertex v){
        return adjacencyList.get(v);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(numberOfVertices + " vertices " + numberOfEdges + " edges " + "\n");
        str.append("edges:\n");
        for (Vertex v : getVertices()) {
            str.append(v + " :");
            for (Vertex w : getNeighbours(v)) {
                str.append(" " + w);
            }
            str.append("\n");
        }
        return str.toString();
    }



    /*validate vertex: is {@code v} a vertex in graph?*/

    public void validate(Vertex v) {
        if (!adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException(("not a vertex in Graph"));
        }
    }
}





