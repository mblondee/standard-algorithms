package sorting.sort;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestSorting {

    private static Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1};
    private static Integer[] array1_sorted = {1,1,2,3,7,9,10,17};

    private static Integer[] array2 = {};
    private static Integer[] array2_sorted = {};

    private static String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
    private static String[] array0_sorted = {"a", "e", "e", "e", "h", "l","l", "l", "m", "o", "p", "r", "s", "s", "t", "x"};



    @Test
    public void testSelection(){
        Selection.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        Selection.sort(array2);
        assertArrayEquals(array2_sorted, array2);

    }

    @Test
    public void testInsertion(){
        Insertion.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        Insertion.sort(array2);
        assertArrayEquals(array2_sorted, array2);

    }

    @Test
    public void testInsertionSentinel() {
        InsertionSentinel.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        InsertionSentinel.sort(array2);
        assertArrayEquals(array2_sorted, array2);

    }

    @Test
    public void testShellSort(){

        ShellSort.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        ShellSort.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        ShellSort.sort(array2);
        assertArrayEquals(array2_sorted, array2);

    }
}
