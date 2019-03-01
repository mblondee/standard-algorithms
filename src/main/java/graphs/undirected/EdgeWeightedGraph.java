package graphs.undirected;

/*
 * representation of an undirected and weighted graph using adjacency lists
 * using a HashMap to keep track of vertices and the edges starting or ending in each vertex
 * */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EdgeWeightedGraph<Vertex> {
    // adjacency list containing for each vertex its neighbours
    private HashMap<Vertex, Set<Edge<Vertex>>> adjacencyList;
    private int numberOfVertices = 0;
    private int numberOfEdges = 0;

    /*
     * initialize new Graph object
     * */
    public EdgeWeightedGraph() {
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
        validate(v);
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
     * add a new edge between {@code v} and {@ code w} to the graph
     * if there is already an edge with a different weight, the weight will be overridden
     * */
    public void addEdge(Edge<Vertex> e) {
        // get endpoints of e
        Vertex v = e.eitherVertex();
        Vertex w = e.otherVertex(v);
        validate(v);
        validate(w);

        // add if not already there
        if (!adjacencyList.get(v).contains(e)) {
            // add w to neighbours of v
            adjacencyList.get(v).add(e);
            // add v to neighbours of w
            adjacencyList.get(w).add(e);
            numberOfEdges++;
        }
    }

    /*
     * remove a vertex (and edges) from the graph
     * */
    public void removeVertex(Vertex v) {
        validate(v);
        int edgesToRemove = getDegree(v);


        // remove v from all neighbours
        for(Edge<Vertex> e : adjacencyList.get(v)){
            // for every edge that is connected to v we get the other endpoint
            Vertex other = e.otherVertex(v);
            adjacencyList.get(other).remove(e);
        }

        adjacencyList.remove(v);
        numberOfVertices--;
        numberOfEdges = numberOfEdges - edgesToRemove;

    }

    public void removeEdge(Edge<Vertex> e) {
        Vertex v = e.eitherVertex();
        Vertex w = e.otherVertex(v);
        validate(v);
        validate(w);
        // only remove when present
        if(adjacencyList.get(v).contains(e)) {
            adjacencyList.get(v).remove(e);
            adjacencyList.get(w).remove(e);

            numberOfEdges--;
        }

    }

    /*
     * return all vertices
     * */
    public Iterable<Vertex> getVertices(){
        return adjacencyList.keySet();
    }

    /*
    * return all edges
    * */
    public Iterable<Edge<Vertex>> getEdges(){
        HashSet<Edge<Vertex>> edges = new HashSet<>();
        for(Vertex v : getVertices()){
            for(Edge<Vertex> e : getNeighbours(v)){
                edges.add(e);
            }

        }
        return edges;
    }


    /*
    * return all edges incident on Vertex {@code v}
    * */
    public Iterable<Edge<Vertex>> getNeighbours(Vertex v){
        return adjacencyList.get(v);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(numberOfVertices + " vertices " + numberOfEdges + " edges " + "\n");
        str.append("edges:\n");
        for (Vertex v : getVertices()) {
            str.append(v + " ->\n");
            for (Edge e : getNeighbours(v)) {
                str.append(" " + e.otherVertex(v) + " " + e.getWeight() +"\n");
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
