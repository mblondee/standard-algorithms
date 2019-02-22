package graphs.directed;

/*
 * a data type for determining whether a simple (no loops) directed and unweighted graph
 * has a simple cycle
 * */

import collections.stack.LinkedStack;

import java.util.HashMap;

public class DirectedCycle<Vertex> {
    private HashMap<Vertex, Boolean> markedVertices; // for every vertex v: has it been visited?
    private HashMap<Vertex, Vertex> edgeFrom; // for every vertex v: last vertex on path to v
    private LinkedStack<Vertex> cycle;
    private HashMap<Vertex, Boolean> currentPath; //current directed path on consideration
    private final DiGraph<Vertex> G;

    public DirectedCycle(DiGraph<Vertex> G){
        this.G = G;
        markedVertices = new HashMap<>();
        edgeFrom = new HashMap<>();
        currentPath = new HashMap<>();
        //initialize hash maps
        for(Vertex v : G.getVertices()){
            markedVertices.put(v, false);
            edgeFrom.put(v, null);
            currentPath.put(v, false);
        }

        for(Vertex v: G.getVertices()){
            if(! markedVertices.get(v)){
                dfs(G, null, v);
            }
        }

    }

    private void dfs(DiGraph<Vertex> G, Vertex startVertex, Vertex v){
        markedVertices.put(v, true);
        currentPath.put(v, true);
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
            // if w is marked and on current path
            else if(currentPath.get(w)){
                cycle = new LinkedStack<>();
                // add all vertices from v to w to cycle
                for(Vertex x = v;  x != w ; x = edgeFrom.get(x)){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
            // otherwise we continue to next neighbour
        }
        // if a cycle has not been found, then v is not on a cycle
        currentPath.put(v, false);
    }

    public Iterable<Vertex> getCycle(){
        return cycle;
    }

    public boolean hasCycle(){
        return cycle != null;
    }
}
