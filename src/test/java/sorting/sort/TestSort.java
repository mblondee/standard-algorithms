package sorting.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestSort {
    @Test
    public void testCompare(){
        int first = -5;
        int second = 10;
        assertTrue(Sort.isStrictLess(first, second));
        assertFalse(Sort.isStrictLess(second, first));

        String firstString = "aka";
        String secondString = "haha";
        assertTrue(Sort.isStrictLess(firstString, secondString));
        assertFalse(Sort.isStrictLess(secondString, firstString));
    }

    @Test
    public void testSwap(){
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1};
        Sort.swap(array1, 1,4);
        Integer[] array1Swapped = {9, 10, 7, 1, 3, 17, 2, 1};
        assertArrayEquals(array1Swapped, array1);

        Sort.swap(array1, 1,1);
        assertArrayEquals(array1Swapped, array1);



    }

    @Test
    public void testIsSorted(){
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1};
        assertFalse(Sort.isSorted(array1, 0, 7));

        Integer[] array2 = {1,1,2,3,7,7,10,17};
        assertTrue(Sort.isSorted(array2, 0, 7));
    }
}
