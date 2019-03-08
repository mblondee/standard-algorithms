package graphs.flownetwork;

/*
* a data type representing a capacitated edge with a flow
* each edge consists of 2 vertices, a (real valued) capacity and a (real valued) flow
*
* methods for computing the residual network for a flow network are also given
* every edge v -> w is converted as follows:
* forward edge is the remaining flow
* backward edge is the flow
* */

public class FlowEdge<Vertex> {
    private static final double PRECISION = 1E-10; // for dealing with rounding errors

    private final Vertex startVertex;//start vertex edge
    private final Vertex endVertex;// end vertex edge
    private final double capacity;// capacity of edge
    private double flow; // flow of edge not final, can change

    /*
     * initialize edge with given start and end vertex and given capacity
     * */
    public FlowEdge(Vertex startVertex, Vertex endVertex, double capacity){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    /*
     * initialize edge with given start and end vertex and given capacity and flow
     * */
    public FlowEdge(Vertex startVertex, Vertex endVertex, double capacity, double flow){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.capacity = capacity;
        this.flow = flow;
    }

    /*
    * getters
    * */

    public Vertex getStartVertex(){
        return startVertex;
    }

    public Vertex getEndVertex(){
        return endVertex;
    }

    public double getCapacity(){
        return capacity;
    }

    public double getFlow(){
        return flow;
    }

    public Vertex getOtherVertex(Vertex v){
        if (startVertex == v){
            return endVertex;
        }
        else if (endVertex == v){
            return startVertex;
        }
        else{
            throw new IllegalArgumentException("invalid vertex");
        }
    }

    /*
    * return residual capacity of the edge in the direction of {@code v}
    * if v is start vertex, this is the flow
    * if v is end vertex, this is capacity-flow
    * */
    public double getResidualCapacityTo(Vertex v){
        if(v == startVertex){
            return flow; // backward edge
        }
        else if(v == endVertex){
            return capacity - flow; // forward edge
        }
        else{
            throw new IllegalArgumentException("invalid vertex");
        }
    }

    /*
    * increase flow on the edge in the direction of {@code v}
    * if v is start vertex, decrease the flow by delta
    * if w is end vertex, increase the flow by delta
    * */
    public void addResidualFlowTo(Vertex v, double delta){
        if(! (delta>= 0.0)){
            throw new IllegalArgumentException("delta may not be negative");
        }

        if (v == startVertex) {
            flow -= delta; // backward edge

        }
        else if(v == endVertex){
            flow += delta; // forward edge
        }

        //round within floating point precision
        if(Math.abs(flow) <= PRECISION){
            flow = 0;
        }
        if(Math.abs(flow -capacity) < PRECISION){
            flow = capacity;
        }

        // flow may not become negative or exceed capacity
        if(!(flow >= 0.0)){
            throw new IllegalArgumentException("flow is negative");
        }
        if(!(flow <= capacity)){
            throw new IllegalArgumentException(("flow exceeds capacity"));
        }
    }

    @Override
    public String toString(){
        return startVertex.toString() + " -> " + endVertex.toString() + " " + flow + "/" + capacity;
    }




}
