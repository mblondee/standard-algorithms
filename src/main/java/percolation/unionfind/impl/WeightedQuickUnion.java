package percolation.unionfind.impl;

/*
 * Class WeightedQuickUnion is an extension of abstract class UF and of QuickUnion
 * it is a weighted version of QuickUnion
 * height is an array that keeps track of height of trees for roots
 * if i is a root, then height[i] gives height of tree with root i
 * */

public class WeightedQuickUnion extends QuickUnion {

    private int[] height; // height of tree for roots


    public WeightedQuickUnion(int n){
        super(n);
        height = new int[n];
        // initialize height: for all i: i is tree of root of height 1
        for(int i = 0; i < n ; i++){
            height[i] = 1;
        }
    }

/*    @Override
    public int find(int p){
        //follow nodes until root
        int root = p;
        while(root != id[root]){
            root = id[root];
        }
        return root;
    }*/

    @Override
    public void union(int p, int q){
        int pRoot = find(p); // root of p
        int qRoot = find(q); // root of q

        //if connected do nothing
        if(pRoot == qRoot){
            return;
        }

        // make smaller root point to larger one
        if(height[pRoot] < height[qRoot]){
            id[pRoot] = qRoot;
            height[pRoot] = height[qRoot] + 1;
        }
        else {
            id[qRoot] = pRoot;
            height[qRoot] = height[pRoot] + 1;
        }

        // there is one component less
        count --;
    }
}
