package graphs.directed;


/*
 * representation of a directed and weighted graph using adjacency lists
 * using a HashMap to keep track of vertices and their neighbours
 *
 * possible to have parallel edges
 * */


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EdgeWeightedDiGraph<Vertex> {
    // adjacency list containing for each vertex its neighbours (represented by the directed edge)
    private HashMap<Vertex, Set<DirectedEdge<Vertex>>> adjacencyList;
    private HashMap<Vertex, Integer> inDegree; // for each vertex, the indegree
    private int numberOfVertices = 0;
    private int numberOfEdges = 0;

    /*
     * initialize new Weighted DiGraph object
     * */
    public EdgeWeightedDiGraph() {
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
    public void addEdge(DirectedEdge<Vertex> edge) {
        Vertex v = edge.startVertex();
        Vertex w = edge.endVertex();
        validate(v);
        validate(w);
        // add if not already there
        if (!adjacencyList.get(v).contains(edge)) {
            // add edge to neighbours of v
            adjacencyList.get(v).add(edge);
            numberOfEdges++;
            inDegree.put(w, inDegree.get(w) + 1);
        }
    }

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

    public Iterable<Vertex> getVertices(){
        return adjacencyList.keySet();
    }

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
                str.append(" " + e.endVertex().toString() + " " + e.weight() +"\n");
            }
            str.append("\n");
        }
        return str.toString();
    }



    /*
     * return outdegree (number of directed edges with startpoint vertex {@code vertex})
     * */
    public int getOutDegree(Vertex v){
        validate(v);
        return adjacencyList.get(v).size();
    }

    /*
     * return indegree (number of directed edges with endpoint vertex {@code vertex})
     * */
    public int getInDegree(Vertex v){
        validate(v);
        return inDegree.get(v);
    }

    /*validate vertex: is {@code v} a vertex in graph?*/

    public void validate(Vertex v) {
        if (!adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException(("not a vertex in Graph"));
        }
    }
}
