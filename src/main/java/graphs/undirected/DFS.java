package graphs.undirected;

/*
* data type for finding paths from a source vertex to every other vertex in a
* simple undirected and unweighted graph using depth first search
* to search in a graph: mark a vertex as visited and visit recursively all neighbouring vertices that have not yet been marked
* */

import java.util.HashMap;
import java.util.Stack;

public class DFS <Vertex> {
    private HashMap<Vertex, Boolean> markedVertices; // for every vertex v: is there a path from source to v?
    private HashMap<Vertex, Vertex> edgeFrom; // for every vertex v: last vertex from source to v
    private final Vertex source; // source vertex
    private final Graph<Vertex> G;

    public DFS(Graph<Vertex> G, Vertex source){
        this.G = G;
        this.source = source;
        G.validate(source);
        markedVertices = new HashMap<>();
        edgeFrom = new HashMap<>();
        //initialize hash maps
        for(Vertex v : G.getVertices()){
            markedVertices.put(v, false);
            edgeFrom.put(v, null);
        }
        dfs(G, source);
    }

    // depth first search from vertex {@code v}
    private void dfs(Graph<Vertex> G, Vertex v){
        // mark v as visited
        markedVertices.put(v, true);
        // recursively visit all of v's neighbours that are not marked yet
        for(Vertex w : G.getNeighbours(v)){
            // if w is not marked
            if(! markedVertices.get(w)){
                edgeFrom.put(w, v);
                dfs(G,w);
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
    * return path from source to vertex {@code w}
    * */
    public Iterable<Vertex> pathTo(Vertex w){
        G.validate(w);
        Stack<Vertex> path = new Stack<Vertex>() {
        };
        if(hasPathTo(w)){
            for(Vertex v = w; v != source; v = edgeFrom.get(v)){
                path.push(v);
            }
            path.push(source);
        }
        return path;
    }


}
