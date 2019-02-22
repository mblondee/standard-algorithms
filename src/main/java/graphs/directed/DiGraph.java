package graphs.directed;


/*
 * representation of a directed and unweighted graph using adjacency lists
 * using a HashMap to keep track of vertices and their neighbours
 * */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DiGraph<Vertex> {
    // adjacency list containing for each vertex its neighbours
    private HashMap<Vertex, Set<Vertex>> adjacencyList;
    private HashMap<Vertex, Integer> inDegree; // for each vertex, the indegree
    private int numberOfVertices = 0;
    private int numberOfEdges = 0;

    /*
     * initialize new DiGraph object
     * */
    public DiGraph() {
        adjacencyList = new HashMap<>();
        inDegree = new HashMap<>();
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
     * add new vertex to the graph
     * */
    public void addVertex(Vertex v) {
        if (containsVertex(v)) {
            return;
        }
        adjacencyList.put(v, new HashSet<>());
        inDegree.put(v,0);
        numberOfVertices++;
    }

    /*
     * remove a vertex from the graph
     * */
    public void removeVertex(Vertex v) {
        validate(v);
        int edgesToRemove = getOutDegree(v);
        adjacencyList.remove(v);

        numberOfVertices--;
        numberOfEdges = numberOfEdges - edgesToRemove;

    }

    /*
     * add a new edge from {@code v} to {@ code w} to the graph
     * */
    public void addEdge(Vertex v, Vertex w) {
        validate(v);
        validate(w);
        // add if not already there
        if (!adjacencyList.get(v).contains(w)) {
            // add w to neighbours of v
            adjacencyList.get(v).add(w);
            numberOfEdges++;
            inDegree.put(w, inDegree.get(w) + 1);
        }
    }

    public void removeEdge(Vertex v, Vertex w) {
        validate(v);
        validate(w);
        // only remove when present
        if(adjacencyList.get(v).contains(w)) {
            adjacencyList.get(v).remove(w);
            numberOfEdges--;
            inDegree.put(w, inDegree.get(w) -1);
        }

    }


    /*
     * return outdegree (number of directed edges incident from vertex {@code vertex})
     * */
    public int getOutDegree(Vertex v){
        validate(v);
        return adjacencyList.get(v).size();
    }

    /*
     * return indegree (number of directed edges incident to vertex {@code vertex})
     * */
    public int getInDegree(Vertex v){
        validate(v);
        return inDegree.get(v);
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

    /*
    * Returns a new directed graph in which all edges have opposite direction
    *
    * */
    public DiGraph reverse(){
        DiGraph<Vertex> reverseGraph = new DiGraph<>();
        // add all vertices
        for(Vertex v : getVertices()){
            reverseGraph.addVertex(v);
        }
        // add all edges but reversed
        for(Vertex v : getVertices()){
            for (Vertex w : getNeighbours(v)){
                reverseGraph.addEdge(w,v);
            }
        }
        return reverseGraph;
    }

}
