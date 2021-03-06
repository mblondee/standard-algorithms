package graphs.undirected;

/*
* edge abstraction for unweighted undirected edges
* */



public class Edge<Vertex> implements Comparable<Edge> {

    private Vertex v; // endpoint of the edge
    private Vertex w; // other endpoint of the edge
    private double weight;
    private static double COMPAREWEIGHT = 0.0001;



    /*
     * initialize an undirected edge
     * */
    public Edge(Vertex v, Vertex w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
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

    /*
     * return weight
     * */
    public double weight(){
        return weight;

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
    public String toString(){
        return String.format("%s-%s %5f",v.toString(),w.toString(), weight);
    }
}
