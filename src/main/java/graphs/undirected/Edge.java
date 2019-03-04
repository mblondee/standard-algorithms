package graphs.undirected;

/*
* edge abstraction for (weighted) undirected edges
* */



public class Edge<Vertex> implements Comparable<Edge> {

    private Vertex v; // endpoint of the edge
    private Vertex w; // other endpoint of the edge
    private Double weight = null;
    private static double COMPAREWEIGHT = 0.0001;


    /*
     * initialize an undirected edge with weight
     * */
    public Edge(Vertex v, Vertex w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /*
     * initialize an undirected edge without weight
     * */
    public Edge(Vertex v, Vertex w){
        this.v = v;
        this.w = w;
    }

    /*
     * return weight, if it is an unweighted edge returns null
     * */
    public Double weight(){
        return weight;
    }

    /*
    * return one of the two endpoints
    * */
    public Vertex eitherVertex(){
        return v;
    }

    /*
     * return other endpoint different from the given vertex
     * */
    public Vertex otherVertex(Vertex vertex){
        if(vertex == v){
            return w;
        }
        else if(vertex == w){
            return v;
        }
        else{
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    @Override
    public int compareTo(Edge otherEdge){
        if(weight < otherEdge.weight()){
            return -1;
        }
        else if(weight > otherEdge.weight()){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        }
        if(! (other instanceof Edge)){
            return false;
        }
        Edge<Vertex> otherEdge = (Edge<Vertex>) other;
        Vertex otherv = otherEdge.eitherVertex();
        Vertex otherw = otherEdge.otherVertex(otherv);

        if(weight == null){
            if(v==otherv){
                return w==otherw;
            }
            else if (v == otherw){
                return w == otherv;
            }
            else{
                return false;
            }
        }
        else{
            if(v == otherv){
                return w==otherw && weight - otherEdge.weight() < COMPAREWEIGHT;
            }
            else if (v == otherw){
                return w==otherv && weight - otherEdge.weight() < COMPAREWEIGHT;
            }
            else{
                return false;
            }
        }
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31*hash + v.hashCode();
        hash = 31*hash + w.hashCode();
        if ( weight != null){
            hash = 31*hash + weight.hashCode();
        }
        return hash;
    }

    @Override
    public String toString(){
        if(weight == null){
            String.format("%s-%s",v.toString(),w.toString());
        }
        return String.format("%s-%s %5f",v.toString(),w.toString(), weight);
    }
}
