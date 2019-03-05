package graphs.directed;

/*
 * computes shortest paths in an edgeweighted digraph from a source vertex or finds a negative cycle reachable from source
 *
 * check for negative cycles to avoid an infinite loop
 *
 * edge weights can be positive, negative or zero
 * a digraph has a negative cycle reachable from source iff the queue is nonempty after #vertices passed through all edges
 * */

import collections.queue.LinkedQueue;
import collections.stack.LinkedStack;

import java.util.HashMap;

public class BellmanFordShortestPath<Vertex> {
    private EdgeWeightedDiGraph<Vertex> G;
    private HashMap<Vertex, Double> distanceTo; // shortest path from source to each vertex
    private HashMap<Vertex, DirectedEdge<Vertex>> edgeFrom; // last edge on shortest path from source to each vertex
    private HashMap<Vertex, Boolean> onQueue; // is a vertex on the queue: still to be relaxed?
    private LinkedQueue<Vertex> queue; // queue of vertices still to relax -> the only edges that could lead to a change in distanceTo
    // are those having a vertex that was changed in the previous pass
    private int calls; // number of calls to relax()
    private Iterable<Vertex> cycle; // negative cycle if exists

    public BellmanFordShortestPath(EdgeWeightedDiGraph<Vertex> G, Vertex source){
        this.G = G;

        //initialize
        distanceTo = new HashMap<>();
        edgeFrom = new HashMap<>();
        onQueue = new HashMap<>();
        queue = new LinkedQueue<>();
        for(Vertex v: G.getVertices()){
            // all distances are infinity
            distanceTo.put(v, Double.POSITIVE_INFINITY);
            onQueue.put(v, false);
        }

        distanceTo.put(source, 0.0);
        queue.enqueue(source);
        onQueue.put(source, true);

        // while there are still vertices to be relaxed and there has not been found a negative cycle
        // we continue looking for shortest paths
        while(! queue.isEmpty() && !hasNegativeCycle()){
            // take vertex of queue
            Vertex v = queue.dequeue();
            onQueue.put(v, false);
            relax(v);
        }
    }

    // relax {@code v} and put other endpoints on queue if changed
    private void relax(Vertex v){
        for(DirectedEdge<Vertex> edge : G.getNeighbours(v)){
            Vertex w = edge.endVertex();
            if(distanceTo.get(w) > distanceTo.get(v) + edge.weight()){
                distanceTo.put(w, distanceTo.get(v) + edge.weight());
                edgeFrom.put(w, edge);
                // if w was not on queue put it there
                if(! onQueue.get(w)){
                    queue.enqueue(w);
                    onQueue.put(w, true);
                }
            }
            calls++;
            // check if there is a cycle but only do it after a multiple of #vertices calls to relax()
            // a digraph has a negative cycle reachable from source iff the queue is nonempty after #vertices passed
            // through all edges
            if(calls % G.numberOfVertices() == 0){
                findNegativeCycle();
                if(hasNegativeCycle()){
                    return;
                }
            }
        }

    }

    private void findNegativeCycle(){
        // get graph from vertices already checked (edgeFrom)
        EdgeWeightedDiGraph<Vertex> newG = new EdgeWeightedDiGraph<>();
        for(Vertex v : edgeFrom.keySet()){
            Vertex w = edgeFrom.get(v).startVertex();
            newG.addVertex(v);
            newG.addVertex(w);
            newG.addEdge(edgeFrom.get(v));
        }
        DirectedCycle<Vertex> cyc = new DirectedCycle<>(newG);
        cycle = cyc.getCycle();
    }

    /*
    * does graph have a negative cycle?
    * */
    public boolean hasNegativeCycle(){
        return cycle != null;
    }

    /*
     * return length of shortest path from source to {@code v}
     * */
    public double getDistance(Vertex v){
        G.validate(v);
        return distanceTo.get(v);
    }

    /*
     * is there a path from source to {@code v}?
     * */
    public boolean hasPathTo(Vertex v){
        G.validate(v);
        return getDistance(v) < Double.POSITIVE_INFINITY;
    }

    /*
     * return shortest path from source to {@code v}
     * */
    public Iterable<DirectedEdge<Vertex>> getPathTo(Vertex v){
        G.validate(v);
        LinkedStack<DirectedEdge<Vertex>> path = new LinkedStack<>();
        if(hasPathTo(v)){
            for(DirectedEdge<Vertex> edge = edgeFrom.get(v); edge!= null; edge = edgeFrom.get(edge.startVertex())){
                path.push(edge);
            }
        }
        return path;
    }

}
