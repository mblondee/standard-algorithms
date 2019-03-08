package graphs.directed.application.wordnet;
/*
 * data type for finding a shortest common ancestor between two vertices in a simple directed and unweighted graph using breadth first search
 * */


import collections.queue.LinkedQueue;
import graphs.directed.DiGraph;
import graphs.directed.DirectedBFS;
import graphs.directed.DirectedEdge;

import java.util.HashMap;

public class AncestorBFS<Vertex> {

    private HashMap<Vertex, Boolean> markedVertices; // for every vertex v: is there a path from source to v?
    private HashMap<Vertex, Integer> distanceToSource; // for every vertex v: distance (number of edges) from source to v
    private final Vertex source; // source vertex (one of the two vertices)
    private final Vertex goal; //the other vertex
    private final DiGraph<Vertex> G;
    private Vertex minAncestor = null; // a shortest common ancestor, initially null
    private int minLengthPath = Integer.MAX_VALUE; // min length of ancestral path between vertices source and goal, initially max value

    public AncestorBFS(DiGraph<Vertex> G, Vertex source, Vertex goal){
        this.G = G;
        this.source = source;
        this.goal = goal;

        // breadth first search in G with source goal
        // for all ancestors v of source pathToGoal will check if there is a path goal->v
        DirectedBFS<Vertex> pathToGoal = new DirectedBFS<>(G, goal);

        // initialize hashmaps
        markedVertices = new HashMap<>();
        distanceToSource = new HashMap<>();
        for(Vertex v : G.getVertices()){
            markedVertices.put(v, false);
            distanceToSource.put(v, Integer.MAX_VALUE);
        }

        // breath first search for finding shortest paths to ancestors
        LinkedQueue<Vertex> queue = new LinkedQueue<>();
        queue.enqueue(source);


        // initialize correct values for source
        markedVertices.put(source, true);
        distanceToSource.put(source, 0);

        while(! queue.isEmpty()){
            // candidate ancestor
            Vertex v = queue.dequeue();

            // if length of source -> v is larger than current min length for an ancestral path, this iteration can be skipped
            if(distanceToSource.get(v) > minLengthPath){
                continue;
            }

            //there is a path from goal to v
            //if the ancestral path is shorter than current found, it has to be updated
            if(pathToGoal.hasPathTo(v) && distanceToSource.get(v) + pathToGoal.distanceTo(v) < minLengthPath){
                minLengthPath = distanceToSource.get(v) + pathToGoal.distanceTo(v);
                minAncestor = v;
            }

            //check neighbours of v
            for(DirectedEdge<Vertex> edge : G.getNeighbours(v)){
                Vertex w = edge.endVertex();
                // add all neighbours that are not marked
                if(! markedVertices.get(w)){
                    markedVertices.put(w, true);
                    distanceToSource.put(w, distanceToSource.get(v) + 1);
                    queue.enqueue(w);

                }
            }
        }
    }

    public int getMinLengthPath(){
        return minLengthPath;
    }

    public Vertex getMinAncestor(){
        return minAncestor;
    }


}
