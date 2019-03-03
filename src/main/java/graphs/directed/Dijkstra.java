package graphs.directed;

/*
* Dijkstra's algorithm:
* a data type for solving the single source shortest path problem in an edge weighted digraph
* (weights are nonnegative)
* */

import collections.stack.LinkedStack;

import java.util.HashMap;
import java.util.HashSet;

public class Dijkstra<Vertex> {
    private EdgeWeightedDiGraph<Vertex> G;
    private Vertex source;
    private HashMap<Vertex, Double> distanceTo; // shortest path from source to each vertex
    private HashMap<Vertex, DirectedEdge<Vertex>> edgeFrom; // last edge on shortest path from source to each vertex
    private HashSet<Vertex> settledVertices;  // set of settled vertices for which the shortest path is known
    private HashSet<Vertex> unsettledVertices; // set of vertices for which the shortest path is not yet known

    public Dijkstra(EdgeWeightedDiGraph<Vertex> G, Vertex source){
        this.G = G;
        this.source = source;
        distanceTo = new HashMap<>();
        edgeFrom = new HashMap<>();
        settledVertices = new HashSet<>();
        unsettledVertices = new HashSet<>();

        for(Vertex v: G.getVertices()){
            // all distances are infinity
            distanceTo.put(v, Double.POSITIVE_INFINITY);
        }
        // source is at distance 0
        distanceTo.put(source, 0.0);
        unsettledVertices.add(source);

        while(unsettledVertices.size() != 0){
            // remove vertex form unsettledVertices that is closest to source
            Vertex current = getLowestDistance(unsettledVertices);
            unsettledVertices.remove(current);

            // check all neighbours
            // iterable of edges current->w
            for(DirectedEdge<Vertex>  edge:G.getNeighbours(current)){
                Vertex w = edge.endVertex(); // endpoint
                double weight = edge.weight(); // weight
                // if w is not in settled vertices
                if(! settledVertices.contains(w)){
                    // current distance source->w
                    // can distance be shorter by going through current?
                    // (source->current->w) < (source->w)?
                    if(distanceTo.get(current) + weight < distanceTo.get(w)){
                        // if so update new distance through current (via edge)
                        distanceTo.put(w, distanceTo.get(current) + weight);
                        edgeFrom.put(w, edge);
                        unsettledVertices.add(w);
                    }
                }

            }
            settledVertices.add(current);
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


    /*
    * return vertex with lowest distance from a given set
    * */
    private Vertex getLowestDistance(HashSet<Vertex> unsettledVertices){
        Vertex lowest = null;
        Double lowestDistance = Double.MAX_VALUE;
        for(Vertex v: unsettledVertices){
            double dist = distanceTo.get(v);
            if(dist < lowestDistance){
                lowest = v;
                lowestDistance = dist;
            }
        }
        return lowest;

    }
}
