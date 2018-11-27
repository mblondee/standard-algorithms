package sorting.sort;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;

public class TestSorting {

    private static Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
    private static Integer[] array1_sorted = {1,1,2,3,5,7,9,10,17};

    private static Integer[] array2 = {};
    private static Integer[] array2_sorted = {};

    private static String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
    private static String[] array0_sorted = {"a", "e", "e", "e", "h", "l","l", "l", "m", "o", "p", "r", "s", "s", "t", "x"};

    private static Integer[] arrayEven = {1,5,7,9, 3,6,7,10};
    private static Integer[] arrayOdd = {1,2,4,5,7,7, 9,10, 13};
    private static Integer[] arrayEvenMerged = {1,3,5,6, 7, 7,9,10};
    private static Integer[] arrayOddMerged = {1,2,4,5,7,7,9,10,13};

    private static Integer[] array3 = {9, 3, 7, 1, 100, 170, 20, 10};
    private static Integer[] array3_sorted = {1,3,7,9,10,20,100,170};


    private static Person person1 = new Person("Anna", 50, "Brussels");
    private static Person person2 = new Person("Tom", 38, "Rome");
    private static Person person3 = new Person("Louis", 38, "Brussels");
    private static Person[] arrayOfPersons = {person1, person2, person3};
    private static Person[] arrayOfPersonsByAge = {person2, person3, person1};



    @Test
    public void testSelection(){
        Selection.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        Selection.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        Selection.sort(array0);
        assertArrayEquals(array0_sorted, array0);

    }

    @Test
    public void testInsertion(){
        Insertion.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        Insertion.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        Insertion.sort(array0);
        assertArrayEquals(array0_sorted, array0);

    }

    @Test
    public void testInsertionComparator(){
        Insertion.sort(array1, Integer::compare);
        assertArrayEquals(array1_sorted, array1);

        Insertion.sort(array2, Integer::compare);
        assertArrayEquals(array2_sorted, array2);

        Insertion.sort(array0, String::compareToIgnoreCase);
        assertArrayEquals(array0_sorted, array0);
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

    @Test
    public void testMergeSort(){
/*        Integer[] auxEven = new Integer[8];
        MergeSort.merge(arrayEven, auxEven, 0, 3, 7);
        assertArrayEquals(arrayEvenMerged,arrayEven);

        Integer[] auxOdd = new Integer[9];
        MergeSort.merge(arrayOdd, auxOdd, 0, 4, 8);
        assertArrayEquals(arrayOddMerged,arrayOdd);*/

        MergeSort.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        MergeSort.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        MergeSort.sort(array2);
        assertArrayEquals(array2_sorted, array2);

    }

    @Test
    public void testMergeSortImproved(){
        MergeSortImproved.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        MergeSortImproved.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        MergeSortImproved.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        MergeSortImproved.sort(array3);
        assertArrayEquals(array3_sorted, array3);
    }

    @Test
    public void testMergeSortBottomUp(){
        MergeSortBottomUp.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        MergeSortBottomUp.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        MergeSortBottomUp.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        MergeSortBottomUp.sort(array3);
        assertArrayEquals(array3_sorted, array3);

    }
}
