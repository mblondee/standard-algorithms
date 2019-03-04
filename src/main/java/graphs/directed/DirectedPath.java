package graphs.directed;


/*
 * a data type for determining which vertices are on a directed path from a given vertex in a
 * directed (weighted and unweighte) graph
 * using depth first search
 * */


import java.util.HashMap;
import java.util.HashSet;

public class DirectedPath<Vertex> {
    private HashMap<Vertex, Boolean> markedVertices; // for every vertex v: has it been visited?
    private HashMap<Vertex, Vertex> edgeFrom; // for every vertex v: last vertex on path to v
    private final DiGraph<Vertex> G;
    private Vertex source;
    private HashSet<Vertex> path; // vertices on path from source (HashSet does not have duplicates)

    public DirectedPath(DiGraph<Vertex> G, Vertex source){
        this.G = G;
        G.validate(source);
        this.source = source;
        markedVertices = new HashMap<>();
        edgeFrom = new HashMap<>();
        path = new HashSet<>();
        //initialize hash maps
        for(Vertex v : G.getVertices()){
            markedVertices.put(v, false);
            edgeFrom.put(v, null);
        }
        dfs(G, source);
    }

    private void dfs(DiGraph<Vertex> G, Vertex v){
        markedVertices.put(v, true);
        for(DirectedEdge<Vertex> edge: G.getNeighbours(v)){
            Vertex w = edge.endVertex();
            if(! markedVertices.get(w)){
                edgeFrom.put(w,v);
                path.add(w);
                dfs(G,w);
            }
            // if already marked and equals source then it should be added to the path
            else if(w == source){
                path.add(w);
            }
        }
    }

    public boolean onPath(Vertex w){
        G.validate(w);
        return markedVertices.get(w);
    }

    /*
    * return vertices on the path from source
    * */
    public HashSet<Vertex> getPathFromSource(){
        return path;

    }
}
