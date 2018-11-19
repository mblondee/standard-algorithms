package percolation.simulation;

/*
 * Class Tile represents tiles
 * tiles have an integer name and are open or closed
 * */


import percolation.unionfind.UF;

public class Tile {
    private int name;
    private boolean open;

    public Tile(int n) {
        name = n;
        open = false; // initially a tile is closed
    }

    public void openTile() {
        open = true;
    }

    public boolean isOpen() {
        return open;
    }

    public int getName() {
        return name;
    }

    public boolean isConnectedTo(Tile otherTile, UF algorithm) {
        return algorithm.connected(name, otherTile.getName());
    }

    public void connectTo(Tile otherTile, UF algorithm) {
        int thisName = name;
        int otherName = otherTile.getName();
        if (!algorithm.connected(thisName, otherName)) {
            algorithm.union(thisName, otherName);
        }
    }

    public String toString() {
        String status;
        if (open) status = "open";
        else status = "closed";
        return "tile with name " + name + " is " + status;
    }
}

