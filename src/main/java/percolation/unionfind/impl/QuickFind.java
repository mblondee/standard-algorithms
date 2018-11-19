package percolation.unionfind.impl;

/*
* Class QuickFind is an extension of abstract class UF.
* the components array is such that if id[i] = k, then k is component of i
* */

import percolation.unionfind.UF;

public class QuickFind extends UF {


    public QuickFind(int n){
        super(n);
    }


    @Override
    public int find(int p){
        return id[p];
    }

    @Override
    public void union(int p, int q){
        int pID = find(p); // component of p
        int qID = find(q); // component of q

        // p and q have same component id: nothing to do
        if (pID == qID){
            return;
        }

        // if not in same component rename p's component to q's component
        for (int i = 0; i < id.length; i++){
            if (id[i] == pID) {
                id[i] = qID;
            }
        }

        // there is one component less
        count --;


    }


}
