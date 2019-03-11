package strings;

import org.junit.Test;
import sorting.sort.Sort;
import strings.sort.MSDStringSort;

import static org.junit.Assert.*;

public class MSDTest {

    @Test
    public void test(){
        String[] testArray = {"she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she",
        "sells", "are", "surely", "seashells"};
        MSDStringSort.sort(testArray);
        assertTrue(Sort.isSorted(testArray));
    }
}
