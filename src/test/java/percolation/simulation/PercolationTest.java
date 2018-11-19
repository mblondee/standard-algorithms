package percolation.simulation;

import org.junit.BeforeClass;
import org.junit.Test;
import percolation.unionfind.UF;
import percolation.unionfind.impl.WeightedQuickUnionCompression;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class PercolationTest {

    private static int numberOfRows;
    private static int totalNumber;
    private static Percolation grid;
    private static UF algorithm;

    @BeforeClass
    public static void initialize(){
        numberOfRows = 3;
        totalNumber = numberOfRows*numberOfRows;
        algorithm = new WeightedQuickUnionCompression(totalNumber + 2);
        grid = new Percolation(numberOfRows, algorithm);
    }

    @Test
    public void testInitializationGrid() {
        int i = 0;
        assertEquals(numberOfRows, grid.getGrid().length);
        for (Tile[] row : grid.getGrid()) {
            assertEquals(numberOfRows, row.length);
            for (Tile column : row) {
                assertEquals(i, column.getName());
                i++;
            }

        }
        assertEquals(totalNumber, grid.getTop(0, 0).getName());
        assertEquals(totalNumber + 1, grid.getBottom(2, 0).getName());
    }

    @Test
    public void checkingBorderConditions(){
        //getleft
        for(int index = 0; index < numberOfRows; index++){
            assertNull(grid.getLeft(index,0));
        }
        //getright
        for(int index = 0; index < numberOfRows; index++){
            assertNull(grid.getRight(index,numberOfRows -1));
        }
        //getTop
        for(int index = 0; index < numberOfRows; index++){
            assertEquals(grid.getTopTile()  ,grid.getTop(0, index));
        }
        //getBottom
        for(int index = 0; index < numberOfRows; index++){
            assertEquals(grid.getBottomTile()  ,grid.getBottom(numberOfRows-1, index));
        }
    }

    @Test
    public void checkingNotBorderConditions(){
        int randomRow = ThreadLocalRandom.current().nextInt(1, numberOfRows-1);
        int randomColumn = ThreadLocalRandom.current().nextInt(1, numberOfRows-1);
        //getLeft
        assertEquals(grid.getGrid()[randomRow][randomColumn-1] , grid.getLeft(randomRow, randomColumn));
        //getRight
        assertEquals(grid.getGrid()[randomRow][randomColumn+1] , grid.getRight(randomRow, randomColumn));
        //getTop
        assertEquals(grid.getGrid()[randomRow-1][randomColumn] , grid.getTop(randomRow, randomColumn));
        //getBottom
        assertEquals(grid.getGrid()[randomRow+1][randomColumn] , grid.getBottom(randomRow, randomColumn));
    }

    @Test
    public void testClosed(){
        ArrayList<int[]> closed = grid.getClosedTiles();
        //initially top and bottom are open
        assertTrue(grid.getTopTile().isOpen());
        assertTrue(grid.getBottomTile().isOpen());
        // initially all other tiles are closed
        int row = 0;
        int column = 0;
        for(int[] el: closed){
            assertEquals(row, el[0]);
            assertEquals(column, el[1]);
            assertFalse(grid.getGrid()[row][column].isOpen());
            if (column == numberOfRows -1){column = 0; row++;}
            else {column++;}
        }
        assertEquals(0, grid.getProbabilityOpen(), 0.00001);
    }

}
