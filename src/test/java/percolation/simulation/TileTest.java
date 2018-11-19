package percolation.simulation;

import org.junit.Test;
import percolation.unionfind.UF;
import percolation.unionfind.impl.WeightedQuickUnionCompression;

import static org.junit.Assert.*;

public class TileTest {


    @Test
    public void tileTest(){
        UF algorithm = new WeightedQuickUnionCompression(5);
        Tile tile0 = new Tile(0);
        Tile tile1 = new Tile(1);

        assertEquals(0, tile0.getName());
        assertFalse(tile0.isOpen());
        tile0.openTile();
        assertTrue(tile0.isOpen());

        assertFalse(tile0.isConnectedTo(tile1, algorithm));
        tile0.connectTo(tile1, algorithm);
        assertTrue(tile0.isConnectedTo(tile1, algorithm));

    }
}
