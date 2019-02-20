package graphs.undirected;

/*
 * a data type for determining whether a simple (no loops) undirected and unweighted graph
 * is a bipartite graph
 * */

import java.util.HashMap;

public class Bipartite<Vertex> {
    private boolean isBipartite = true; // is the graph bipartite
    private HashMap<Vertex, Boolean> color; //for every vertex v: what is its color
    private HashMap<Vertex, Boolean> markedVertices; // for every vertex v: has it been visited? has it gotten a color?
    private HashMap<Vertex, Vertex> edgeFrom; // for every vertex v: last vertex on path to v
    private final Graph<Vertex> G;


    public Bipartite(Graph<Vertex> G){
        this.G = G;
        markedVertices = new HashMap<>();
        edgeFrom = new HashMap<>();
        color = new HashMap<>();
        //initialize hash maps
        for(Vertex v : G.getVertices()){
            markedVertices.put(v, false);
            edgeFrom.put(v, null);
            color.put(v, false);
        }
        for(Vertex v : G.getVertices()){
            if(! markedVertices.get(v)){
                dfs(G,v);
            }
        }
    }

    private void dfs(Graph<Vertex> G, Vertex v){
        markedVertices.put(v, true);

        for(Vertex w: G.getNeighbours(v)){
/*            if(cycle != null){
                // the graph is not bipartite
                return;
            }*/

            // w is not yet colored
            if(! markedVertices.get(w)){
                //give it opposite color of v
                edgeFrom.put(w,v);
                color.put(w, !color.get(v));
                // continue from w
                dfs(G,w);
            }

            // w is colored and has same color as v -> graph is not bipartite
            else if(color.get(w) == color.get(v)){
                isBipartite = false;
            }
        }

    }

    public boolean isBipartite(){
        return isBipartite;
    }
}
