package graphs;

/*
* a data type for determining whether a simple (no loops) undirected and unweighted graph
* has a simple cycle
* */

import java.util.HashMap;
import java.util.Stack;

public class Cycle<Vertex> {
    private HashMap<Vertex, Boolean> markedVertices; // for every vertex v: has it been visited?
    private HashMap<Vertex, Vertex> edgeFrom; // for every vertex v: last vertex on path to v
    private Stack<Vertex> cycle;
    private final Graph<Vertex> G;

    public Cycle(Graph<Vertex> G){
        this.G = G;
        markedVertices = new HashMap<>();
        edgeFrom = new HashMap<>();
        //initialize hash maps
        for(Vertex v : G.getVertices()){
            markedVertices.put(v, false);
            edgeFrom.put(v, null);
        }

        for(Vertex v: G.getVertices()){
            if(! markedVertices.get(v)){
                dfs(G, null, v);
            }
        }

    }

    private void dfs(Graph<Vertex> G, Vertex startVertex, Vertex v){
        markedVertices.put(v, true);
        // as long as a cycle hasn't been found: check neighbours
        // if marked: then we have encountered a cycle
        // if not marked: vertex is added to path
        for(Vertex w : G.getNeighbours(v)){
            // stop if a cycle has been found
            if(cycle != null){return;}
            if(! markedVertices.get(w)){
                edgeFrom.put(w, v);
                dfs(G,v,w);
            }
            // if w is marked but not last edge (this would not be a cycle)
            else if(w != startVertex){
                cycle = new Stack<>();
                // add all vertices from v to w to cycle
                for(Vertex x = v; x != w ; x = edgeFrom.get(x)){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }

    }

    public Iterable<Vertex> getCycle(){
        return cycle;
    }

    public boolean hasCycle(){
        return cycle != null;
    }
}
