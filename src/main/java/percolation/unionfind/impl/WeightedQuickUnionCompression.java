package percolation.unionfind.impl;

/*
 * Class WeightedQuickUnionCompression is an extension of abstract class UF and of WeightedQuickUnion
 * it uses path compression by linking all examined nodes (in find) directly to the root
 * */

public class WeightedQuickUnionCompression extends WeightedQuickUnion {

    public WeightedQuickUnionCompression(int n){
        super(n);
    }

    @Override
    public int find(int p){
        //follow nodes until root
        int root = p;
        while(root != id[root]){
            root = id[root];
        }
        // link all nodes on path from p to root directly to root
        while(p != root){
            int nextp = id[p];
            id[p] = root;
            p = nextp;
        }
        return root;
    }
}
