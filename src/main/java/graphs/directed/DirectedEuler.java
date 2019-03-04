package graphs.directed;

/*
 * a data type for determining whether a simple (no loops and no parallel edges) directed and (weighted and unweighted) graph
 * has a directed eulerian cycle (visiting all edges)
 *
 * A directed graph G has a directed eulerian cycle if and only if
 * - all vertices in G has indegree equal to outdegree
 * - all vertices with non zero degree belong to the same strong component
 *
 * finding the eulerian path is done by using Hierholzer's algorithm
 * - start at any vertex with outgoing edges and follow a trail of edges until returning to v
 * it is not possible to get stuck because of in and out degree are the same for each vertex
 * - as long as there exist a vertex belonging to the current tour that has adjacent edges not part
 * of the tour another trail has to be started from this vertex
 * */


import collections.stack.LinkedStack;

import java.util.HashMap;
import java.util.Iterator;

public class DirectedEuler<Vertex> {
    private final DiGraph<Vertex> G;
    private boolean hasEuler;
    private StrongComponent<Vertex> strongComponent; // data type containing strong components
    private HashMap<Vertex, Iterator<DirectedEdge<Vertex>> > adjacencyList; // adjacency list -> to be able to iterate one at a time
    private LinkedStack<Vertex> cycle;
    private LinkedStack<Vertex> backTrackStack;



    public DirectedEuler(DiGraph<Vertex> G){
        this.G = G;
        strongComponent = new StrongComponent<>(G);
        Vertex random = getRandomNonZeroDegree();
        // if in and out of a vertex is not equal
        // or the strong component of a random non zero degree vertex does not contain all other non zero degree vertices
        // then there is no eulerian cycle
        if(! inOutDegree() || !checkComponent(random)){
            hasEuler = false;
            return;
        }
        hasEuler = true;

        adjacencyList = new HashMap<>();
        for(Vertex v : G.getVertices()){
            adjacencyList.put(v, G.getNeighbours(v).iterator());
        }

        backTrackStack = new LinkedStack<>();
        cycle = new LinkedStack<>();
        backTrackStack.push(random);
        while(! backTrackStack.isEmpty()){
            Vertex top = backTrackStack.pop();
            while(adjacencyList.get(top).hasNext()){
                //put current on stack
                backTrackStack.push(top);
                //get neighbour
                top = adjacencyList.get(top).next().endVertex();
            }
            cycle.push(top);

        }
    }

    private boolean inOutDegree(){
        for(Vertex v : G.getVertices()){
            if(G.getInDegree(v) != G.getOutDegree(v)){
                return false;
            }
        }
        return true;
    }

    /*
    * does graph have a directed Eulerian cycle?
    * */
    public boolean hasEulerianCycle(){
        return hasEuler;
    }

    /*
    * return Eulerian cycle
    * */
    public Iterable<Vertex> getEulerianCycle(){
        return cycle;
    }


    /*
    * get a random vertex that is on an edge
    * */
    private Vertex getRandomNonZeroDegree(){
        for(Vertex v : G.getVertices()){
            if(G.getOutDegree(v) > 0){
                return v;
            }
        }
        return null;
    }

    /*
    * does the strong component of {@code v} contain all vertices with non zero degree?
    * */
    private boolean checkComponent(Vertex v){
        for(Vertex w: G.getVertices()){
            if(G.getOutDegree(w) > 0 && ! strongComponent.strongComponent(v).contains(w)){
                return false;
            }
        }
        return true;
    }
}
