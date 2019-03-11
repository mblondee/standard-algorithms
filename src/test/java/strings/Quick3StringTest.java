package strings;

import org.junit.Test;
import sorting.sort.Sort;
import strings.sort.Quick3string;

import static org.junit.Assert.assertTrue;

public class Quick3StringTest {

    @Test
    public void test(){
        String[] testArray = {"she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she",
                "sells", "are", "surely", "seashells"};
        Quick3string.sort(testArray);
        assertTrue(Sort.isSorted(testArray));
    }
}
