package strings;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
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

    @Test
    public void test1() {
        String[] testArray = {"she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she",
                "sells", "are", "surely", "seashells"};
        TernarySearchTable<Integer> ST = new TernarySearchTable<>();
        for (String str : testArray) {
            ST.put(str, 1);
        }
        Assert.assertThat(ST.keys(), IsIterableContainingInAnyOrder.containsInAnyOrder(new String[]{
                "she", "sells", "seashells", "by", "the", "sea", "shore", "shells", "are", "surely"
        }));
        String[] testArrayPrefixSE = {"sells", "seashells", "sea"};
        Assert.assertThat(ST.keysWithPrefix("se"), IsIterableContainingInAnyOrder.containsInAnyOrder(testArrayPrefixSE));

        String[] testArrayPrefixS = {"she", "sells", "seashells", "sea", "shore", "surely", "shells"};
        Assert.assertThat(ST.keysWithPrefix("s"), IsIterableContainingInAnyOrder.containsInAnyOrder(testArrayPrefixS));



    }

    @Test
    public void test2(){
        String[] testArray = {"ha", "haha", "hallo", "hahaha", "hello"};
        TernarySearchTable<Integer> ST = new TernarySearchTable<>();
        for(String str: testArray){
            ST.put(str, 1);
        }
        Assert.assertThat(ST.keysThatMatch("h.llo"), IsIterableContainingInAnyOrder.containsInAnyOrder(
                new String[]{"hallo", "hello"}
        ));
        assertEquals("hahaha", ST.longestPrefixOf("hahaha"));
        assertEquals("haha", ST.longestPrefixOf("hahal"));
        assertEquals("ha", ST.longestPrefixOf("hah"));
        assertEquals("", ST.longestPrefixOf("aha"));
        assertEquals("ha", ST.longestPrefixOf("hall"));
        assertEquals("", ST.longestPrefixOf("hell"));

        String[] test = {"your", "your mother", "your mother is", "your mother is in", "your mother is in the",
                "your mother is in the kitchen"};
        TernarySearchTable<Integer> ST1 = new TernarySearchTable<>();
        for(String str: test){
            ST1.put(str, 1);
        }
        assertEquals(6, ST1.size());
        assertEquals("your mother is in the kitchen", ST1.longestPrefixOf("your mother is in the kitchen"));
    }

}
