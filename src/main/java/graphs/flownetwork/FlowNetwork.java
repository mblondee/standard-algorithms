package graphs.flownetwork;

/*
* a capacitated network where each directed edge is a FlowEdge and has a real valued capacity and flow
* the flow of an edge cannot exceed ist capacity
* */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FlowNetwork<Vertex> {

    private int numberOfVertices = 0;
    private int numberOfEdges = 0;
    private HashMap<Vertex, Set<FlowEdge<Vertex>>> adjacencyList; // for every vertex v all edges that have v as start of end vertex

    public FlowNetwork(){
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
     * add new vertex to the network
     * if the vertex already exists no extra vertex is added
     * */
    public void addVertex(Vertex v) {
        if (containsVertex(v)) {
            return;
        }
        adjacencyList.put(v, new HashSet<>());
        numberOfVertices++;
    }

    /*
     * add a new edge {@code edge} to the network
     * */
    public void addEdge(FlowEdge<Vertex> edge) {
        Vertex v = edge.getStartVertex();
        Vertex w = edge.getEndVertex();
        validate(v);
        validate(w);
        // add if not already there
        if (!adjacencyList.get(v).contains(edge)) {
            // add w to neighbours of v
            adjacencyList.get(v).add(edge);
            // add v to neighbours of w
            adjacencyList.get(w).add(edge);
            numberOfEdges++;
        }
    }

    /*
     * return all vertices in the network
     * */
    public Iterable<Vertex> getVertices(){
        return adjacencyList.keySet();
    }

    /*
     * return all edges starting or ending in {@code v}
     * */
    public Iterable<FlowEdge<Vertex>> getNeighbours(Vertex v){
        return adjacencyList.get(v);
    }

    /*
     * return all edges except for self loops
     * */
    public Iterable<FlowEdge<Vertex>> getEdges(){
        HashSet<FlowEdge<Vertex>> edges = new HashSet<>();
        for(Vertex v : getVertices()){
            for(FlowEdge<Vertex> e : adjacencyList.get(v)){
                if(e.getEndVertex() != v) {
                    edges.add(e);
                }
            }

        }
        return edges;
    }

    /*validate vertex: is {@code v} a vertex in network?*/

    public void validate(Vertex v) {
        if (!adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException(("not a vertex in network"));
        }
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(numberOfVertices + " vertices " + numberOfEdges + " edges " + "\n");
        str.append("edges:\n");
        for (Vertex v : getVertices()) {
            str.append(v.toString() + " ->\n");
            for (FlowEdge<Vertex> e : adjacencyList.get(v)) {
                str.append(" " + e.getEndVertex().toString() + "\n");
            }
            str.append("\n");
        }
        return str.toString();
    }

}
