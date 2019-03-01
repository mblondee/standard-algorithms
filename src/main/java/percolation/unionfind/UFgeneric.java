package percolation.unionfind;

/*
 * generic union find using weighted quick union and path compression
 *
 * */

import java.util.HashMap;


public class UFgeneric<Item> {

    private HashMap<Item, Item> id; // for every item connected component of item
    private int count; // number of components
    private HashMap<Item, Integer> height; // height of tree for roots

    //initialize component id array
    // n is number of sites
    public UFgeneric(Iterable<Item> items){
        int counter = 0;
        id = new HashMap<>();
        height = new HashMap<>();
        //initialize id and height
        for (Item i: items) {
            id.put(i, i); // initially every item in its own component
            height.put(i,1); // initially i is tree of root of height 1
            counter++;
        }
        count = counter;
    }

    // return number of components
    public int count(){
        return count;
    }

    // returns component of p
    public  Item find(Item p){
        //follow nodes until root
        Item root = p;
        while(root != id.get(root)){
            root = id.get(root);
        }
        // link all nodes on path from p to root directly to root
        while(p != root){
            Item nextp = id.get(p);
            id.put(p,root);
            p = nextp;
        }
        return root;
    }


    // checks if p and q are connected
    public boolean connected(Item p, Item q){
        return find(p) == find(q);
    }

    // connect p and q
    public void union(Item p, Item q){
        Item pRoot = find(p); // root of p
        Item qRoot = find(q); // root of q

        //if connected do nothing
        if(pRoot == qRoot){
            return;
        }
        // make smaller root point to larger one
        if(height.get(pRoot) < height.get(qRoot)){
            id.put(pRoot,qRoot);
            height.put(pRoot, height.get(qRoot) + 1);
        }
        else {
            id.put(qRoot,pRoot);
            height.put(qRoot, height.get(pRoot) + 1);
        }

        // there is one component less
        count --;
    }
}
