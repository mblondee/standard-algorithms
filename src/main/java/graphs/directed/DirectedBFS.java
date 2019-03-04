package graphs.directed;

/*
 * data type for finding paths from a source vertex to every other vertex in a
 * simple directed (weighted and unweighted) graph using breadth first search
 * it uses a queue to save the next vertex to visit, hence the paths found are shortest paths
 * when a vertex is removed from the queue, its neighbours that are not visited yet are
 * marked and added to the queue
 * this is repeated until the queue is empty
 * */

import collections.queue.LinkedQueue;
import collections.stack.LinkedStack;

import java.util.HashMap;


public class DirectedBFS<Vertex> {

    private HashMap<Vertex, Boolean> markedVertices; // for every vertex v: is there a path from source to v?
    private HashMap<Vertex, Vertex> edgeFrom; // for every vertex v: last vertex from source to v
    private HashMap<Vertex, Integer> distanceTo; // for every vertex v: distance (number of edges) from source to v
    private final Vertex source; // source vertex
    private final DiGraph<Vertex> G;

    public DirectedBFS(DiGraph<Vertex> G, Vertex source){
        this.G = G;
        this.source = source;
        G.validate(source);

        markedVertices = new HashMap<>();
        edgeFrom = new HashMap<>();
        distanceTo = new HashMap<>();
        //initialize hash maps
        for(Vertex v : G.getVertices()){
            markedVertices.put(v, false);
            edgeFrom.put(v, null);
            distanceTo.put(v, Integer.MAX_VALUE);
        }
        bfs(G,source);
    }

    private void bfs(DiGraph<Vertex> G, Vertex source){
        LinkedQueue<Vertex> queue = new LinkedQueue<>();
        queue.enqueue(source);
        markedVertices.put(source, true);
        distanceTo.put(source, 0);

        while(! queue.isEmpty()){
            Vertex v = queue.dequeue();
            for(DirectedEdge<Vertex> edge : G.getNeighbours(v)){
                Vertex w = edge.endVertex();
                // add all neighbours that are not marked
                if(! markedVertices.get(w)){
                    markedVertices.put(w, true);
                    edgeFrom.put(w,v);
                    distanceTo.put(w, distanceTo.get(v) + 1);
                    queue.enqueue(w);

                }
            }
        }

    }

    /*
     * is there a path from source to vertex {@code w}?
     * */
    public boolean hasPathTo(Vertex w){
        return marked(w);
    }

    // is {@code vertex} marked?
    private boolean marked(Vertex w){
        G.validate(w);
        return markedVertices.get(w);
    }

    /*
     * return shortest distance from source to vertex {@code w}
     * */
    public int distanceTo(Vertex w){
        G.validate(w);
        return distanceTo.get(w);
    }

    /*
     * return shortest path from source to vertex {@code w}
     * */
    public Iterable<Vertex> pathTo(Vertex w){
        G.validate(w);
        LinkedStack <Vertex> path = new LinkedStack<>();
        if(hasPathTo(w)){
            for(Vertex v = w; v != source; v = edgeFrom.get(v)){
                path.push(v);
            }
            path.push(source);
        }
        return path;
    }
}
