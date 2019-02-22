package graphs.directed;

/*
 * a data type for determining whether a simple (no loops) directed and unweighted graph
 * has a euler cycle (visiting all edges)
 * */

import collections.stack.LinkedStack;
import java.util.HashMap;
import java.util.Iterator;

public class DirectedEuler<Vertex> {
    private LinkedStack<Vertex> cycle;
    private HashMap<Vertex, Iterator<Vertex>> neighbours; //used to iterate over all neighbours
    private final DiGraph<Vertex> G;

    public DirectedEuler(DiGraph<Vertex> G){
        this.G = G;

        // initialize neighbours
        neighbours = new HashMap<>();
        for(Vertex v : G.getVertices()){
            neighbours.put(v, G.getNeighbours(v).iterator());
        }
        cycle = new LinkedStack<>();

        // vertex to start
        Vertex start = startVertex();
        // we go as far as we can go from start until there is no out edge

        LinkedStack<Vertex> stillToCheck = new LinkedStack<>();
        stillToCheck.push(start);

        while(! stillToCheck.isEmpty()){
            Vertex checking = stillToCheck.pop();

            while(neighbours.get(checking).hasNext()){
                stillToCheck.push(checking);
                checking = neighbours.get(checking).next();
            }
            cycle.push(checking);
        }



    }

    /*
    * return a vertex that has an outgoing edge
    * */
    private Vertex startVertex(){
        for(Vertex v: G.getVertices()){
            if(G.getOutDegree(v) > 0){
                return v;
            }
        }
        return null;
    }
}
