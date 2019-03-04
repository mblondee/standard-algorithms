package graphs.directed;


/*
 * edge abstraction for (weighted) directed edges
 * */


public class DirectedEdge<Vertex>  {

    private Vertex v; // start vertex
    private Vertex w; // end vertex
    private Double weight = null;
    private static double COMPAREWEIGHT = 0.0001;


    /*
    * initialize a directed edge with weight
    * */
    public DirectedEdge(Vertex v, Vertex w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /*
     * initialize a directed edge without weight
     * */
    public DirectedEdge(Vertex v, Vertex w){
        this.v = v;
        this.w = w;
    }

    /*
     * return start vertex
     * */
    public Vertex startVertex(){
        return v;
    }

    /*
     * return end vertex
     * */
    public Vertex endVertex(){
        return w;
    }

    /*
     * return weight, if it is an unweighted edge returns null
     * */
    public Double weight(){
            return weight;

    }



    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        }
        if(! (other instanceof DirectedEdge)){
            return false;
        }
        DirectedEdge<Vertex> otherEdge = (DirectedEdge<Vertex>) other;
        if(weight == null){
        return (startVertex() == otherEdge.startVertex()
                && endVertex() == otherEdge.endVertex());
        }
        return (startVertex() == otherEdge.startVertex()
        && endVertex() == otherEdge.endVertex()
        && (weight()-otherEdge.weight()) < COMPAREWEIGHT);
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31*hash + startVertex().hashCode();
        hash = 31*hash + endVertex().hashCode();
        if ( weight != null){
            hash = 31*hash + weight().hashCode();
        }
        return hash;
    }

    @Override
    public String toString(){
        if(weight == null){
            String.format("%s->%s",v.toString(),w.toString());
        }
        return String.format("%s->%s %5f",v.toString(),w.toString(), weight);
    }
}
