package strings;

import org.junit.Test;
import strings.search.TernarySearchTable;


import static org.junit.Assert.*;
public class TernarySearchTableTest {

    @Test
    public void test(){
        TernarySearchTable<Integer> ST = new TernarySearchTable<>();
        ST.put("hallo", 1);
        assertTrue(ST.contains("hallo"));
        assertEquals(1, ST.size());
        assertEquals(new Integer(1), ST.get("hallo"));
        ST.put("haha", 5);
        assertEquals(2, ST.size());
        assertEquals(new Integer(5), ST.get("haha"));
        ST.put("hallo", 7);
        assertEquals(2, ST.size());
        assertEquals(new Integer(7), ST.get("hallo"));
        assertEquals(null, ST.get("halo"));
        assertTrue(ST.contains("hallo"));
        assertFalse(ST.contains("halo"));
        assertTrue(ST.contains("haha"));
    }

}
