package graphs.directed;


/*
 * edge abstraction for weighted directed edges
 * */

public class DirectedEdge<Vertex>  {

    private Vertex v; // start vertex
    private Vertex w; // end vertex
    private double weight;

    public DirectedEdge(Vertex v, Vertex w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public Vertex startVertex(){
        return v;
    }

    public Vertex endVertex(){
        return w;
    }

    public double weight(){
        return weight;
    }

    @Override
    public String toString(){
        return String.format("%s->%s %5f",v.toString(),w.toString(), weight);
    }
}
