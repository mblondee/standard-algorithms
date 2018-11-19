package percolation.simulation;

import percolation.unionfind.UF;

/*
* Class RunSimulation is used to run a percolation simulation with given grid size
 * grid: a grid of size sizeGrid x sizeGrid of tiles
 * initially all tiles are closed, they are opened randomly one by one and each time it is checked
 * whether the grid is connected, the probability is than given
 * to check connection dummy tiles at top and bottom are used and it is checked whether these are connected
 * top and bottom are open and connected to resp. top and bottom row in grid
* */
public class RunSimulation {

    private int gridSize;
    private UF algorithm;
    private double probability;

    public RunSimulation(int number, UF algorithm){
        gridSize = number;
        this.algorithm = algorithm;
    }

    public double run(){
        System.out.println("running simulation");
       // algorithm = new WeightedQuickUnionCompression(gridSize*gridSize + 2);
        Percolation gridSimulation = new Percolation(gridSize, algorithm);
        // as long as grid is not connected keep opening Tiles
        while(! gridSimulation.gridIsConnected() ){
            gridSimulation.getClosedTile();
        }
        System.out.println("grid is connected " + gridSimulation.gridIsConnected());
        probability = gridSimulation.getProbabilityOpen();
        System.out.println("probability " + probability);

        return probability;

    }
}
