package graphs.directed;


/*
 * representation of a directed and unweighted graph using adjacency lists
 * using a HashMap to keep track of vertices and their neighbours
 * no parallel edges
 * */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DiGraph<Vertex> {
    // adjacency list containing for each vertex its neighbours represented by the (unweighted) edges
    protected HashMap<Vertex, Set<DirectedEdge<Vertex>>> adjacencyList;
    protected HashMap<Vertex, Integer> inDegree; // for each vertex, the indegree
    protected int numberOfVertices = 0;
    protected int numberOfEdges = 0;

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
     * if the vertex already exists no extra vertex is added
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
     * add a new edge {@code edge} to the graph
     * */
    public void addEdge(DirectedEdge<Vertex> edge) {
        Vertex v = edge.startVertex();
        Vertex w = edge.endVertex();
        validate(v);
        validate(w);
        // add if not already there
        if (!adjacencyList.get(v).contains(edge)) {
            // add w to neighbours of v
            adjacencyList.get(v).add(edge);
            numberOfEdges++;
            inDegree.put(w, inDegree.get(w) + 1);
        }
    }


    /*
    * remove an edge {@code edge} from the graph
    * */
    public void removeEdge(DirectedEdge<Vertex> edge) {
        Vertex v = edge.startVertex();
        Vertex w = edge.endVertex();
        validate(v);
        validate(w);
        // only remove when present
        if(adjacencyList.get(v).contains(edge)) {
            adjacencyList.get(v).remove(edge);
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

    /*
    * return all vertices in the graph
    * */
    public Iterable<Vertex> getVertices(){
        return adjacencyList.keySet();
    }

    /*
    * return all edges starting in {@code c}
    * */
    public Iterable<DirectedEdge<Vertex>> getNeighbours(Vertex v){
        return adjacencyList.get(v);
    }

    /*
     * return all edges
     * */
    public Iterable<DirectedEdge<Vertex>> getEdges(){
        HashSet<DirectedEdge<Vertex>> edges = new HashSet<>();
        for(Vertex v : getVertices()){
            for(DirectedEdge<Vertex> e : getNeighbours(v)){
                edges.add(e);
            }

        }
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(numberOfVertices + " vertices " + numberOfEdges + " edges " + "\n");
        str.append("edges:\n");
        for (Vertex v : getVertices()) {
            str.append(v.toString() + " ->\n");
            for (DirectedEdge<Vertex> e : getNeighbours(v)) {
                str.append(" " + e.endVertex().toString() + "\n");
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
        for(DirectedEdge<Vertex> edge : getEdges()){
            Vertex v = edge.startVertex();
            Vertex w = edge.endVertex();
            DirectedEdge<Vertex> newEdge = new DirectedEdge<>(w,v);
            reverseGraph.addEdge(newEdge);
        }
        return reverseGraph;
    }

}
