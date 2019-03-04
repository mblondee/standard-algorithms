package graphs.directed;


/*
 * representation of a directed and weighted graph using adjacency lists
 * using a HashMap to keep track of vertices and their neighbours
 *
 * no parallel edges
 * */


public class EdgeWeightedDiGraph<Vertex> extends DiGraph<Vertex>  {


    /*
     * initialize new Weighted DiGraph object
     * */
    public EdgeWeightedDiGraph() {
        super();
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(numberOfVertices + " vertices " + numberOfEdges + " edges " + "\n");
        str.append("edges:\n");
        for (Vertex v : getVertices()) {
            str.append(v.toString() + " ->\n");
            for (DirectedEdge<Vertex> e : getNeighbours(v)) {
                str.append(" " + e.endVertex().toString() + " " + e.weight() +"\n");
            }
            str.append("\n");
        }
        return str.toString();
    }
}
