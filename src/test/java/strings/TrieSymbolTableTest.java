package strings;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
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

        assertFalse(ST.contains(""));

        ST.put("", 2);
        assertEquals(2, ST.size());
        assertEquals(new Integer(2), ST.get(""));
        assertEquals(new Integer(7), ST.get("hallo"));

    }

    @Test
    public void test1(){
        String[] testArray = {"she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she",
                "sells", "are", "surely", "seashells"};
        TrieSymbolTable<Integer> ST = new TrieSymbolTable();
        for(String str:testArray){
            ST.put(str, 1);
        }
        assertEquals(10, ST.size());
        for(String str: testArray){
            assertTrue(ST.contains(str));
            assertEquals(new Integer(1), ST.get(str));
        }

        String[] testArrayPrefixSE = {"sells", "seashells", "sea"};
        Assert.assertThat(ST.keysWithPrefix("se"), IsIterableContainingInAnyOrder.containsInAnyOrder(testArrayPrefixSE));

        String[] testArrayPrefixS = {"she", "sells", "seashells", "sea", "shore", "surely", "shells"};
        Assert.assertThat(ST.keysWithPrefix("s"), IsIterableContainingInAnyOrder.containsInAnyOrder(testArrayPrefixS));

        String[] testArrayWild = {"she", "seashells"};
        for(String s : ST.keysThatMatch("s.e...")){
            System.out.println(s);
        }
        //Assert.assertThat(ST.keysThatMatch("s.e..."), IsIterableContainingInAnyOrder.containsInAnyOrder(testArrayWild));



    }

    @Test
    public void test2(){
        String[] testArray = {"ha", "haha", "hallo", "hahaha", "hello"};
        TrieSymbolTable<Integer> ST = new TrieSymbolTable();
        for(String str: testArray){
            ST.put(str, 1);
        }
        assertEquals("hahaha", ST.longestPrefixOf("hahaha"));
        assertEquals("haha", ST.longestPrefixOf("hahal"));
        assertEquals("ha", ST.longestPrefixOf("hah"));
        assertEquals("", ST.longestPrefixOf("aha"));
        assertEquals("ha", ST.longestPrefixOf("hall"));
        assertEquals("", ST.longestPrefixOf("hell"));

        String[] test = {"your", "your mother", "your mother is", "your mother is in", "your mother is in the",
        "your mother is in the kitchen"};
        TrieSymbolTable<Integer> ST1 = new TrieSymbolTable();
        for(String str: test){
            ST1.put(str, 1);
        }
        assertEquals(6, ST1.size());
        assertEquals("your mother is in the kitchen", ST1.longestPrefixOf("your mother is in the kitchen"));
    }
}
