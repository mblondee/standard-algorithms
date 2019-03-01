package graphs.undirected;


import collections.queue.LinkedQueue;
import percolation.unionfind.UFgeneric;
import sorting.priorityqueue.MinPQ;

/*
* a data type for computing a minimum spanning tree i an edge weighted (undirected) graph
* using Kruskal's algorithm and union find
* - edges are processed in order of weight (smallest -> largest)
* - each edge that does not form a cycle with previously added edges is added
* - until number-of-vertices - 1 edges have been added
*
* union find is used to check if adding an edge would form a cycle
* */
public class MSTKruskal<Vertex> {
    private EdgeWeightedGraph<Vertex> G;
    private LinkedQueue<Edge<Vertex>> mst; // edges in MST
    private double weight; // total weight of MST

    public MSTKruskal(EdgeWeightedGraph<Vertex> G){
        this.G = G;
        mst = new LinkedQueue<>();



        // put edges of G in  min pq to find smallest edges
        MinPQ<Edge<Vertex>> pq = new MinPQ<>();
        for(Edge<Vertex> e : G.getEdges()){
            pq.insert(e);
        }

        // union find to keep track of connected vertices
        UFgeneric<Vertex> uf = new UFgeneric<>(G.getVertices());
        while(! pq.isEmpty() && mst.sizeOfQueue() < G.numberOfVertices() -1){
            Edge<Vertex> e = pq.delMin();
            Vertex v = e.eitherVertex();
            Vertex w = e.otherVertex(v);

            // check if connected v and w would create a circle
            if(! uf.connected(v,w)){
                uf.union(v,w); // merge components of v and w
                mst.enqueue(e); // add edge to mst
                weight += e.getWeight();
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
