package percolation.unionfind;

import org.junit.Test;

import java.util.HashSet;
import static org.junit.Assert.*;

public class UFgenericTest {

    @Test
    public void testGeneric(){
        HashSet<Integer> set = new HashSet<>();
        for(Integer i = 0; i<10; i++){
            set.add(i);
        }
        UFgeneric<Integer> uf = new UFgeneric<>(set);
        uf.union(4,3);
        uf.union(3,8);
        uf.union(6,5);
        uf.union(9,4);
        uf.union(2,1);
        uf.union(8,9);
        uf.union(5,0);
        uf.union(7,2);
        uf.union(6,1);
        uf.union(1,0);
        uf.union(6,7);

        assertTrue(uf.connected(4,3));
        assertTrue(uf.connected(4,8));
        assertTrue(uf.connected(4,9));
        assertFalse(uf.connected(4,6));
        assertFalse(uf.connected(4,7));

        assertTrue(uf.connected(5,0));
        assertTrue(uf.connected(1,2));
        assertTrue(uf.connected(2,6));
        assertFalse(uf.connected(0,3));
        assertFalse(uf.connected(1,9));

        assertEquals(2, uf.count());

    }
}
