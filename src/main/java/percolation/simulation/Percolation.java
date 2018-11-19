package percolation.simulation;

/*
* Class Percolation defines the set up for a percolation experiment
* grid: a grid of size sizeGrid x sizeGrid of tiles
* initially all tiles are closed, they are opened randomly one by one and each time it is checked
* whether the grid is connected, the probability is than given
* to check connection dummy tiles at top and bottom are used and it is checked whether these are connected
* top and bottom are open and connected to resp. top and bottom row in grid
* */

import percolation.unionfind.UF;

import java.util.ArrayList;
import java.util.Random;

public class Percolation {

    private int sizeGrid; // size n of a nxn grid
    private Tile[][] grid; // grid containing all tiles
    private Tile top; // dummy tile at top
    private Tile bottom; // dummy tile at bottom
    private UF algorithm; // algorithm to use
    private ArrayList<int[]> closedTiles; // list containing positions of tiles that are closed
    private int numberClosed; // number of closed tiles
    private int numberTotal; //total number of tiles

    // initialize grid with size n and specific UF algorithm
    public Percolation(int n, UF ufAlgorithm){
        sizeGrid = n;
        algorithm = ufAlgorithm;
        grid = new Tile[n][n];
        closedTiles = new ArrayList<int[]>();
        numberClosed = n*n;
        numberTotal = numberClosed;

        int nextInt= n*n;
        top = new Tile(nextInt); // dummy tile at top
        bottom = new Tile(nextInt +1); // dummy tile at bottom

        //initialize grid and closedTiles ArrayList
        int name = 0;
        for (int row = 0; row < n; row++){
            for (int column = 0; column < n; column++){
                grid[row][column] = new Tile(name);
                name++;

                int[] toAdd = new int[2];
                toAdd[0] = row;
                toAdd[1] = column;
                closedTiles.add(toAdd);
            }
        }

        //top and bottom are open
        top.openTile();

        bottom.openTile();

        System.out.println("grid initialized");


    }

    public Tile[][] getGrid(){
        return grid;
    }

    public Tile getTopTile(){
        return top;
    }

    public Tile getBottomTile(){
        return bottom;
    }

    // getLeft returns for given position in the grid the left Tile
    public Tile getLeft(int row, int column){
        //position has left tile if column>0
        if (column > 0){
            return grid[row][column-1];
        }
        else{
            return null;
        }
    }

    // getRight returns for given position in the grid the right Tile
    public Tile getRight(int row, int column){
        //position has right tile if column<sizeGrid-1
        if (column<sizeGrid - 1){
            return grid[row][column+1];
        }
        else{
            return null;
        }
    }

    // getTop returns for given position in the grid the top Tile
    public Tile getTop(int row, int column){
        //top row has above top
        if (row == 0){
            return top;
        }
        else {
            return grid[row-1][column];
        }
    }

    // getBottom returns for given position in the grid the bottom Tile
    public Tile getBottom(int row, int column){
        //bottom row has bellow bottom
        if (row == sizeGrid-1){
            return bottom;
        }
        else {
            return grid[row+1][column];
        }
    }


    // connect a Tile in a given position to its open neighbour Tiles
    public void connectToOpenNeighbours(int row, int column){
        Tile tile = grid[row][column];
        if(getLeft(row, column) != null ){
            Tile leftTile = getLeft(row, column);
            if(leftTile.isOpen()){
                tile.connectTo(leftTile, algorithm);
            }
        }

        if(getRight(row, column) != null ){
            Tile rightTile = getRight(row, column);
            if(rightTile.isOpen()){
                tile.connectTo(rightTile, algorithm);
            }
        }

        if(getBottom(row, column) != null ){
            Tile bottomTile = getBottom(row, column);
            if(bottomTile.isOpen()){
                tile.connectTo(bottomTile, algorithm);
            }
        }

        if(getTop(row, column) != null ){
            Tile topTile = getTop(row, column);
            if(topTile.isOpen()){
                tile.connectTo(topTile, algorithm);
            }
        }

    }

    public ArrayList<int[]> getClosedTiles(){
        return closedTiles;
    }

    //get random closed Tile in grid, open it and remove from closed tiles
    public void getClosedTile(){
        Random generator = new Random();
        //pick random index in list closedTiles (length = numberClosed)
        int index = generator.nextInt(numberClosed);
        //get position in grid
        int[] positionInGrid = closedTiles.get(index);
        int row = positionInGrid[0];
        int column = positionInGrid[1];
        // get Tile with the position
        Tile tileToChange = grid[row][column];
        // change status tile to open
        tileToChange.openTile();
        // connect tile with neighbours that are open
        connectToOpenNeighbours(row, column);
        // remove position from list of closed tiles
        closedTiles.remove(index);
        // one less tile is closed
        numberClosed --;
    }

    // grid is connected when top is connected to bottom
    public boolean gridIsConnected(){
        return top.isConnectedTo(bottom, algorithm);
    }

    // returns probability that grid is open (percentage of open tiles)
    public double getProbabilityOpen(){
        return 1 - (double) numberClosed/numberTotal;
    }


}
