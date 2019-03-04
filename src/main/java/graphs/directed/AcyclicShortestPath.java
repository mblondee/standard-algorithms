package graphs.directed;

/*
* computes shortest paths in an edgeweighted acyclic digraph
* used a topological sort based algorithm:
* - initialize all distances to +infinity (except for source)
* - relax all edges taking the vertices in topological order
*
*
* edge weights can be positive, negative or zero
*
* */

import collections.stack.LinkedStack;

import java.util.HashMap;

public class AcyclicShortestPath<Vertex> {
    private EdgeWeightedDiGraph<Vertex> G;
    private HashMap<Vertex, Double> distanceTo; // shortest path from source to each vertex
    private HashMap<Vertex, DirectedEdge<Vertex>> edgeFrom; // last edge on shortest path from source to each vertex

    public AcyclicShortestPath(EdgeWeightedDiGraph<Vertex> G, Vertex source){
        this.G = G;
        G.validate(source);

        // if G has a cyclic throw exception
        // topological sorting iff acyclic
        TopologicalSort<Vertex> top = new TopologicalSort<>(G);
        if(!top.hashTopologicalOrder()){
            throw new IllegalArgumentException("Graph is not acyclic");
        }

        //initialize
        distanceTo = new HashMap<>();
        edgeFrom = new HashMap<>();
        for(Vertex v: G.getVertices()){
            // all distances are infinity
            distanceTo.put(v, Double.POSITIVE_INFINITY);
        }
        // source is at distance 0
        distanceTo.put(source, 0.0);

        // visit vertices in topological order
        for(Vertex v : top.getOrder()){
            relaxEdges(v);
        }

    }

    private void relaxEdges(Vertex v ){
        for(DirectedEdge<Vertex> edge : G.getNeighbours(v)){
            Vertex w = edge.endVertex();
            if(distanceTo.get(w) > distanceTo.get(v) + edge.weight()){
                distanceTo.put(w, distanceTo.get(v) + edge.weight());
                edgeFrom.put(w, edge);
            }

        }
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
