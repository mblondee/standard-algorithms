package percolation.unionfind.impl;

/*
 * Class QuickUnion is an extension of abstract class UF.
 * the components array is such that id[i] = k, then i and k are in the same component
 * the representant (root) of a component is j such that id[j] = j
 * */

import percolation.unionfind.UF;

public class QuickUnion extends UF {

    public QuickUnion(int n){
        super(n);
    }

    @Override
    public int find(int p){
        // get root of p
        // follow links until root
        while(p != id[p]){
            p = id[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q){

        int pRoot = find(p); // component (root) of p
        int qRoot = find(q); // component (root) of q

        // p and q are in same component nothing to do
        if (pRoot == qRoot){
            return;
        }

        // make root of q the root of p
        id[pRoot] = qRoot;

        // there is one component less
        count--;
    }

}
