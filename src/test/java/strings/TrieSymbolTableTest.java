package strings;

import org.junit.Test;
import strings.search.TrieSymbolTable;

import static org.junit.Assert.*;

public class TrieSymbolTableTest {

    @Test
    public void test() {
        TrieSymbolTable<Integer> ST = new TrieSymbolTable();
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

        ST.delete("haha");
        assertTrue(ST.contains("hallo"));
        assertFalse(ST.contains("halo"));
        assertFalse(ST.contains("haha"));
        assertEquals(1, ST.size());

        ST.delete("halo");
        assertTrue(ST.contains("hallo"));
        assertFalse(ST.contains("halo"));
        assertFalse(ST.contains("haha"));
        assertEquals(1, ST.size());
    }
}
