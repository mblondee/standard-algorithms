package graphs.undirected;

/*
 * a data type for computing a minimum spanning tree in an edge weighted (undirected) connected graph
 * using a lazy version of Prim's algorithm
 * - start with any vertex as a single vertex tree
 * - add number-of-vertices - 1 edges, taking the minimum weighted edge that connects a vertex on the tree
 * to a vertex not on the tree
 *
 * worst case space and time proportional to (number-of-edges)*log_2(number-of-edges)
 * since it uses a priority queue
 *
 * if the graph is not connected it computes a minimum spanning forest
 *
 * lazy version: edges that will be not used stay on the pq
 * */

import collections.queue.LinkedQueue;
import sorting.priorityqueue.MinPQ;

import java.util.HashMap;

public class MSTPrimLazy<Vertex> {

    private EdgeWeightedGraph<Vertex> G;
    private LinkedQueue<Edge<Vertex>> mst; // edges in the MST
    private double weight; // total weight of MST
    private HashMap<Vertex, Boolean> markedVertices; // for each vertex v: is it visisted (on the MST)
    private MinPQ<Edge<Vertex>> pq; // edges with one endpoint in MST

    public MSTPrimLazy(EdgeWeightedGraph<Vertex> G) {
        this.G = G;
        mst = new LinkedQueue<>();
        markedVertices = new HashMap<>();
        pq = new MinPQ<>();
        for (Vertex v : G.getVertices()) {
            markedVertices.put(v, false);
        }

        // run Prim's algorithm for all vertices
        for (Vertex v : G.getVertices()) {
            if (!markedVertices.get(v)) {
                prim(G, v);
            }
        }
    }

    private void prim(EdgeWeightedGraph<Vertex> G, Vertex source){
        // mark source and and edges to pq
        mark(G,source);

        while(! pq.isEmpty() && mst.sizeOfQueue() < G.numberOfVertices()){
            Edge<Vertex> e = pq.delMin();
            Vertex v = e.eitherVertex();
            Vertex w = e.otherVertex(v);

            // if both vertices v and w are already marked we do not add the edge
            if(markedVertices.get(v) && markedVertices.get(w)){
                continue;
            }
            mst.enqueue(e);
            weight += e.weight();

            // v added to tree
            if(! markedVertices.get(v)){
                mark(G,v);
            }
            // w added to tree
            if(! markedVertices.get(w)){
                mark(G,w);
            }
        }
    }

    /*
    * add all edges incident to {@code v} to the pq
    * only if other endpoint is not yet on the MST
    * */
    private void mark(EdgeWeightedGraph<Vertex> G, Vertex v){
        markedVertices.put(v, true);
        // check all edges incident to v
        for(Edge<Vertex> e : G.getNeighbours(v)){
            // only add when other endpoint has not been marked
            if(! markedVertices.get(e.otherVertex(v))){
                pq.insert(e);
            }
        }
    }

    /*
     * return weight of MST
     * */
    public double getWeight(){
        return weight;
    }

    /*
     * return edges in MST
     * */
    public Iterable<Edge<Vertex>>  MST(){
        return mst;
    }




















}
