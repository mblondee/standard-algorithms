package graphs.directed;

/*
 * a data type for finding the strong component of a vertex in a  directed and unweighted graph
 *
 * for a vertex v in a graph G we check which vertices are on a path from v
 * in the reversed graph of G we check which vertices are on a path from v
 * intersection of these sets of vertices is the strong component of V
 *
 * */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StrongComponent<Vertex> {
    private final DiGraph<Vertex> G;
    private final DiGraph<Vertex> reverseG;
    private HashMap<Vertex, HashSet<Vertex>> onPathFrom; // for every vertex v: vertices that are on a path from v
    private HashMap<Vertex, HashSet<Vertex>> onPathTo; // for every vertex v: vertices that are on a path to v


    public StrongComponent(DiGraph<Vertex> G){
        this.G = G;
        this.reverseG = G.reverse();
        onPathFrom = new HashMap<>();
        onPathTo = new HashMap<>();


        for(Vertex v:G.getVertices()){
            // get vertices on path from v
            DirectedPath<Vertex> pathFromV = new DirectedPath<>(this.G, v);
            onPathFrom.put(v, pathFromV.getPathFromSource());

            // get vertices on path to v using the reversed graph
            DirectedPath<Vertex> pathToV = new DirectedPath<>(this.reverseG, v);
            onPathTo.put(v, pathToV.getPathFromSource());
        }

    }

    /*
    * return all vertices that are on a path from {@code v}
    * */
    public HashSet<Vertex> getPathFrom(Vertex v){
        return onPathFrom.get(v);
    }

    /*
     * return all vertices that are on a path to {@code v}
     * */
    public HashSet<Vertex> getPathTo(Vertex v){
        return onPathTo.get(v);
    }

    /*
    * return strong component containing {@code v}
    * */
    public HashSet<Vertex> strongComponent(Vertex v){
        // copy
        HashSet<Vertex> intersection = new HashSet<Vertex>(getPathTo(v));
        // remove duplicates
        intersection.retainAll(getPathFrom(v));
        return intersection;
    }


}
