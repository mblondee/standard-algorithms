package percolation.unionfind;


/*
* Class UF represents a union-find data type.
* Initially there are n components with each site in its own component.
* count returns the number of components
* find returns the component
* connected return true if sites are connected
* union connected two sites
*/

public abstract class UF {

    protected int[] id; // array id[i] represents connected component of i
    protected int count; // number of components

    //initialize component id array
    // n is number of sites
    public UF(int n){
        count = n; // initially n components
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i; // every integer has its own component
        }
    }

    // return number of components
    public int count(){
        return count;
    }

    // returns component of p
    public abstract int find(int p);

    // checks if p and q are connected
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    // connect p and q
    public abstract void union(int p, int q);
}
