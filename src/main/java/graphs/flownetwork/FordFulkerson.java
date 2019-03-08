package graphs.flownetwork;

/*
* a data type for computing maximum st-flow and minimum st-cut in a flow network
*
* using the Ford Fulkerson algorithm (shortest augmenting path) heuristic
*
* algorithms works with the residual network (methods given in FlowEdge class)
* any path form source to sink in the residual network corresponds to an augmented path in the original network
* augmented path = no full forward edges or empty backward edges
* */

// TODO: has not been tested

import collections.queue.LinkedQueue;

import java.util.HashMap;

public class FordFulkerson<Vertex> {

    private static final double PRECISION = 1E-10;

    private FlowNetwork<Vertex> net;
    private Vertex source;
    private Vertex sink;
    private double maxFlow; // current max flow

    private HashMap<Vertex, FlowEdge<Vertex>> edgeTo; // on current path source->sink which flow edge goes to vertex
    private HashMap<Vertex, Boolean> marked; // on current path which vertices have been visited

    public FordFulkerson(FlowNetwork<Vertex> net, Vertex source, Vertex sink){
        this.net = net;
        net.validate(source);
        net.validate(sink);
        if(source == sink){
            throw new IllegalArgumentException("source equals sink");
        }
        this.source = source;
        this.sink = sink;

        // is there a path left to augment?
        while(hasAugmentingPath()){
            //use the path given in edgeTo

            // minimal remaining flow that can be added
            double bottleneck = Double.POSITIVE_INFINITY;
            for(Vertex v  = sink; v != source; v = edgeTo.get(v).getOtherVertex(v)){
                bottleneck = Math.min(bottleneck, edgeTo.get(v).getResidualCapacityTo(v));
            }

            // add flow
            for(Vertex v  = sink; v != source; v = edgeTo.get(v).getOtherVertex(v)){
                edgeTo.get(v).addResidualFlowTo(v, bottleneck);
            }

        }
    }


    /*
    * return current found maximal flow
    * */
    public double getMaxFlow(){
        return maxFlow;
    }

    // return excess flow at a vertex {@code v}
    // flow coming in is positive, flow going out is negative
    private double excess(Vertex v){
        double excess = 0.0;
        for(FlowEdge<Vertex> edge : net.getNeighbours(v)){
            if(v == edge.getStartVertex()){
                excess -= edge.getFlow(); // flow goes out
            }
            else{
                excess += edge.getFlow(); // flow comes in
            }
        }
        return excess;
    }

    /*
    * a flow is called feasible if all vertices except for source and sink have flow 0
    * source "produces" flow, sink "consumes" flow
    * */
    private boolean feasible(){
        // check capacity constraints
        for(FlowEdge<Vertex> edge : net.getEdges()){
            if (edge.getFlow() < -PRECISION || edge.getFlow() > edge.getCapacity() + PRECISION){
                System.err.println("Edge does not satisfy capacity constraints: " + edge);
                return false;
            }
        }

        // net flow into a vertex (!= source, != sink) should equal zero
        // source:
        if(Math.abs (maxFlow + excess(source)) > PRECISION ){
            // max flow =! flow going out from source
            return false;
        }
        if(Math.abs(maxFlow - excess(sink)) > PRECISION){
            // max flow != flow going in sink
            return false;
        }
        for(Vertex v : net.getVertices()){
            if(v == source || v == sink){
                continue;
            }
            else if ((Math.abs(excess(v))) > PRECISION)
                // net flow is not zero
                System.err.println("net flow is not zero " + v);
                return false;
            }
        return true;
    }

    /*
    * is there an augmenting path, ie a path from source to sink with available capacity
    * if there is such a path the path will be given by edgeTo
    * */
    private boolean hasAugmentingPath(){
        edgeTo = new HashMap<>();
        marked = new HashMap<>();
        LinkedQueue<Vertex> queue = new LinkedQueue<>();
        for(Vertex v : net.getVertices()){
            marked.put(v, false);
            edgeTo.put(v, null);
        }

        // bfs
        marked.put(source, true);
        queue.enqueue(source);

        // as long as there are eligible vertices and we have to not arrived at sink
        while(! queue.isEmpty() && ! marked.get(sink)){

            Vertex current = queue.dequeue();

            for(FlowEdge<Vertex> edge : net.getNeighbours(current)){
                Vertex w = edge.getOtherVertex(current);

                //for every vertex v->w capacity-flow >0 still capacity left
                if(edge.getResidualCapacityTo(w) >0 ){
                    if(!marked.get(w)){
                        edgeTo.put(w, edge);
                        marked.put(w, true);
                        queue.enqueue(w);
                    }
                }
            }
        }
        // have we reached sink?
        return marked.get(sink);


    }
}
