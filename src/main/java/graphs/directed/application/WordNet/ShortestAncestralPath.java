package graphs.directed.application.WordNet;

/*
* a data type for computing a shortest ancestral path between two vertices in a directed graph
*
* an ancestral path between vertices v and w is a directed path from v to a common ancestor, together with a directed from w to the ancestor
* a shortest ancestral path is an ancestral path of minimum total length
* the common ancestor in a shortest ancestral path is called a shortest common ancestor
* */

import graphs.directed.DiGraph;


public class ShortestAncestralPath<Vertex> {
    private DiGraph<Vertex> G;

    public ShortestAncestralPath(DiGraph<Vertex> G){
        this.G = G;

    }

    /*
    * return length of a shortest ancestral path between {@code v} and {@code w}
    * return -1 if such a path does not exist
    * */
    public int getLength(Vertex v, Vertex w){
        G.validate(v);
        G.validate(w);

        AncestorBFS<Vertex> bfs = new AncestorBFS<>(G, v, w);
        if(bfs.getMinLengthPath() == Integer.MAX_VALUE){
            return -1;
        }
        return bfs.getMinLengthPath();
    }


    /*
    * return a shortest common ancestor of {@code v} and {@code w}
    * return null is there is no shortest ancestral path
    * */
    public Vertex getAncestor(Vertex v, Vertex w) {
        G.validate(v);
        G.validate(w);

        AncestorBFS<Vertex> bfs = new AncestorBFS<>(G, v, w);
        return bfs.getMinAncestor();
    }


    /*
     * return length of a shortest ancestral path between any vertex in {@code vIter} and any vertex in {@code wIter}
     * return -1 if such a path does not exist
     * */
    public int getLength(Iterable<Vertex> vIter, Iterable<Vertex> wIter){
        int distance = Integer.MAX_VALUE;
        for(Vertex v : vIter){
            for(Vertex w : wIter){
                G.validate(v);
                G.validate(w);
                AncestorBFS<Vertex> bfs = new AncestorBFS<>(G, v, w);
                if(bfs.getMinLengthPath() < distance){
                    distance = bfs.getMinLengthPath();
                }

            }
        }
        return distance;
    }

    /*
     * return a shortest common ancestor of any vertex in {@code vIter} and any vertex in {@code wIter}
     * return null is there is no shortest ancestral path
     * */
    public Vertex getAncestor(Iterable<Vertex> vIter, Iterable<Vertex> wIter) {
        Vertex ancestor = null;
        int distance = Integer.MAX_VALUE;
        for(Vertex v : vIter){
            for(Vertex w : wIter){
                G.validate(v);
                G.validate(w);
                AncestorBFS<Vertex> bfs = new AncestorBFS<>(G, v, w);
                if(bfs.getMinLengthPath() < distance){
                    distance = bfs.getMinLengthPath();
                    ancestor = bfs.getMinAncestor();
                }

            }
        }
        return ancestor;


    }



}
