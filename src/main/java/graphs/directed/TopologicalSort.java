package graphs.directed;

/*
* a data type to compute a topological ordering of a directed graph (only possible if graph is acyclic)
* using depth first search
* */

import collections.stack.LinkedStack;


import java.util.HashMap;

public class TopologicalSort<Vertex> {
    private HashMap<Vertex, Boolean> markedVertices; // visited vertices
    private LinkedStack<Vertex> order; // add vertices
    private DiGraph<Vertex> G;


    public TopologicalSort(DiGraph<Vertex> G){
        this.G = G;
        DirectedCycle<Vertex> cycleFinder = new DirectedCycle<>(G);
        if(!cycleFinder.hasCycle()){
            order = new LinkedStack();
            markedVertices = new HashMap<>();
            for(Vertex v: G.getVertices()){
                markedVertices.put(v, false);
            }
            for(Vertex v: G.getVertices()){
                if(! markedVertices.get(v)){
                    topSort(v);
                }
            }

        }

    }

    private void topSort(Vertex v){
        markedVertices.put(v, true);
        for(Vertex w : G.getNeighbours(v)){
            if(! markedVertices.get(w)){
                topSort(w);
            }
        }
        order.push(v);
    }

    public LinkedStack<Vertex> getOrder(){
        return order;
    }
}
