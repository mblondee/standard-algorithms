package graphs;

/*
* a connected component in a graph is a maximal set of connected vertices
*
* initialize all vertices as unmarked
* to visit a vertex: mark as visited and recursively visit neighbours that are not visited yet
* uses depth first search to find vertices in the same component
* */

import java.util.HashMap;

public class ConnectedComponents<Vertex> {
    private final Graph<Vertex> G;
    private int count; // number of components
    private HashMap<Vertex, Integer> idComponent; // for each vertex v: component to which it belongs
    private HashMap<Vertex, Boolean> markedVertices; // for every vertex v: has it been visited
    private int[] size; // size[i]: size of component i

    /*
    * initialize an object
    * */
    public ConnectedComponents(Graph<Vertex> G){
        this.G = G;
        idComponent = new HashMap<>();
        markedVertices = new HashMap<>();
        size = new int[G.numberOfVertices()];
        //initialize hash map
        for(Vertex v : G.getVertices()){
            idComponent.put(v, null);
            markedVertices.put(v, false);
        }
        for(Vertex v : G.getVertices()){
            if(! markedVertices.get(v)){
                // find all vertices in same component
                dfs(G,v);
                // one more component
                count++;
            }
        }

    }

    private void dfs(Graph<Vertex> G, Vertex v){
        markedVertices.put(v, true);
        idComponent.put(v, count);
        size[count] ++;
        for(Vertex w: G.getNeighbours(v)){
            if(! markedVertices.get(w)){
                dfs(G,w);
            }
        }
    }

    /*
    * are two vertices connected?
    * */
    public boolean connected(Vertex v, Vertex w){
        G.validate(v);
        G.validate(w);
        return idComponent.get(v).equals(idComponent.get(w));
    }

    /*
     * return size of connected component containing {@code v}
     * */
    public int size(Vertex v){
        G.validate(v);
        return size[idComponent.get(v)];
    }

    /*
     * return number of connected components
     * */
    public int numberOfConnectedComponents(){
        return count;
    }


}
