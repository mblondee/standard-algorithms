package sorting.sort;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;

public class TestSorting {

    private static Integer[] array1_sorted = {1,1,2,3,5,7,9,10,17};
    private static Integer[] array2_sorted = {};
    private static String[] array0_sorted = {"a", "e", "e", "e", "h", "l","l", "l", "m", "o", "p", "r", "s", "s", "t", "x"};

    private static Integer[] arrayEven = {1,5,7,9, 3,6,7,10};
    private static Integer[] arrayOdd = {1,2,4,5,7,7, 9,10, 13};
    private static Integer[] arrayEvenMerged = {1,3,5,6, 7, 7,9,10};
    private static Integer[] arrayOddMerged = {1,2,4,5,7,7,9,10,13};

    private static Integer[] array3_sorted = {1,3,7,9,10,20,100,170};
    private static Integer[] array4_sorted = {1,1,1,1,1};
    private static Integer[] array5_sorted = {1,2,3,4,5};
    private static Integer[] array6_sorted = {2,3,4,5,7};

    private static Person person1 = new Person("Anna", 50, "Brussels");
    private static Person person2 = new Person("Tom", 38, "Rome");
    private static Person person3 = new Person("Louis", 38, "Brussels");
    //private static Person[] arrayOfPersons = {person1, person2, person3};
    private static Person[] arrayOfPersonsByAge = {person2, person3, person1};
    private static Person[] arrayOfPersonsByName = {person1, person3, person2};

    private static Person person1A = new Person("Anna", 5, "Brussels");
    private static Person person2A = new Person("Bob", 5, "Rome");
    private static Person person3A = new Person("Chris", 8, "Brussels");
    private static Person person4A = new Person("David", 3, "Brussels");
    private static Person person5A = new Person("Erik", 8, "Brussels");
    //private static Person[] arrayOfPersonsA = {person5A, person2A, person1A, person3A, person4A};
    private static Person[] arrayOfPersonsAlfabet = {person1A, person2A, person3A, person4A, person5A};
    private static Person[] arrayOfPersonsByAlfabetAge = {person4A, person1A, person2A, person3A, person5A};




    @Test
    public void testSelection(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Integer[] array3 = {9, 3, 7, 1, 100, 170, 20, 10};
        Integer[] array4 = {1,1,1,1,1};
        Integer[] array5 = {1,2,3,4,5};
        Integer[] array6 = {7,2,3,4,5};


        Selection.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        Selection.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        Selection.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        Selection.sort(array3);
        assertArrayEquals(array3_sorted, array3);

        Selection.sort(array4);
        assertArrayEquals(array4_sorted, array4);

        Selection.sort(array5);
        assertArrayEquals(array5_sorted, array5);

        Selection.sort(array6);
        assertArrayEquals(array6_sorted, array6);

    }

    @Test
    public void testSelectionComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};

        Person[] arrayOfPersons = {person1, person2, person3};

        Selection.sort(array1, Integer::compare);
        assertArrayEquals(array1_sorted, array1);

        Selection.sort(array2, Integer::compare);
        assertArrayEquals(array2_sorted, array2);

        Selection.sort(array0, String::compareToIgnoreCase);
        assertArrayEquals(array0_sorted, array0);


        Selection.sort(arrayOfPersons, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAge, arrayOfPersons);

        Selection.sort(arrayOfPersons, Person.compareByName);
        assertArrayEquals(arrayOfPersonsByName, arrayOfPersons);
    }

    @Test
    public void testInsertion(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Integer[] array3 = {9, 3, 7, 1, 100, 170, 20, 10};
        Integer[] array4 = {1,1,1,1,1};
        Integer[] array5 = {1,2,3,4,5};
        Integer[] array6 = {7,2,3,4,5};

        Insertion.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        Insertion.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        Insertion.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        Insertion.sort(array3);
        assertArrayEquals(array3_sorted, array3);

        Insertion.sort(array4);
        assertArrayEquals(array4_sorted, array4);

        Insertion.sort(array5);
        assertArrayEquals(array5_sorted, array5);

        Insertion.sort(array6);
        assertArrayEquals(array6_sorted, array6);

        String[] array7 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        String[] array7_sorted = {"s", "h", "e", "l","l", "o", "r", "s", "t", "e", "x", "a", "m", "p", "l", "e"};
        Insertion.sort(array7, 4,8);
        assertArrayEquals(array7_sorted, array7);

    }

    @Test
    public void testInsertionComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};

        Insertion.sort(array1, Integer::compare);
        assertArrayEquals(array1_sorted, array1);

        Insertion.sort(array2, Integer::compare);
        assertArrayEquals(array2_sorted, array2);

        Insertion.sort(array0, String::compareToIgnoreCase);
        assertArrayEquals(array0_sorted, array0);

        Insertion.sort(arrayOfPersons, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAge, arrayOfPersons);

        arrayOfPersons = new Person[] {person1, person2, person3};
        Insertion.sort(arrayOfPersons, Person.compareByName);
        assertArrayEquals(arrayOfPersonsByName, arrayOfPersons);

        arrayOfPersons = new Person[] {person1, person2, person3};
        Insertion.sort(arrayOfPersons, 0, 2, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAge, arrayOfPersons);

        // test stability
        Person[] arrayOfPersonsA = {person5A, person2A, person1A, person3A, person4A};
        Insertion.sort(arrayOfPersonsA, Person.compareByName);
        assertArrayEquals(arrayOfPersonsAlfabet, arrayOfPersonsA);
        Insertion.sort(arrayOfPersonsA, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAlfabetAge, arrayOfPersonsA);
    }

    @Test
    public void testInsertionSentinel() {
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};


        InsertionSentinel.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        InsertionSentinel.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        InsertionSentinel.sort(array0);
        assertArrayEquals(array0_sorted, array0);

    }

    @Test
    public void testInsertionSentinelComparator() {
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};

        InsertionSentinel.sort(array1, Integer::compare);
        assertArrayEquals(array1_sorted, array1);

        InsertionSentinel.sort(array2, Integer::compare);
        assertArrayEquals(array2_sorted, array2);

        InsertionSentinel.sort(array0, String::compareToIgnoreCase);
        assertArrayEquals(array0_sorted, array0);

        InsertionSentinel.sort(arrayOfPersons, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAge, arrayOfPersons);

        InsertionSentinel.sort(arrayOfPersons, Person.compareByName);
        assertArrayEquals(arrayOfPersonsByName, arrayOfPersons);

        // test stability
        Person[] arrayOfPersonsA = {person5A, person2A, person1A, person3A, person4A};
        InsertionSentinel.sort(arrayOfPersonsA, Person.compareByName);
        assertArrayEquals(arrayOfPersonsAlfabet, arrayOfPersonsA);
        InsertionSentinel.sort(arrayOfPersonsA, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAlfabetAge, arrayOfPersonsA);

    }

    @Test
    public void testShellSort(){

        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};

        ShellSort.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        ShellSort.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        ShellSort.sort(array2);
        assertArrayEquals(array2_sorted, array2);

    }

    @Test
    public void testShellSortComparator() {
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};
        ShellSort.sort(array1, Integer::compare);
        assertArrayEquals(array1_sorted, array1);

        ShellSort.sort(array2, Integer::compare);
        assertArrayEquals(array2_sorted, array2);

        ShellSort.sort(array0, String::compareToIgnoreCase);
        assertArrayEquals(array0_sorted, array0);

        ShellSort.sort(arrayOfPersons, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAge, arrayOfPersons);

        ShellSort.sort(arrayOfPersons, Person.compareByName);
        assertArrayEquals(arrayOfPersonsByName, arrayOfPersons);

    }

    @Test
    public void testMergeSort(){

        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Integer[] array3 = {9, 3, 7, 1, 100, 170, 20, 10};
        Integer[] array4 = {1,1,1,1,1};
        Integer[] array5 = {1,2,3,4,5};
        Integer[] array6 = {7,2,3,4,5};
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

        MergeSort.sort(array3);
        assertArrayEquals(array3_sorted, array3);

        MergeSort.sort(array4);
        assertArrayEquals(array4_sorted, array4);

        MergeSort.sort(array5);
        assertArrayEquals(array5_sorted, array5);

        MergeSort.sort(array6);
        assertArrayEquals(array6_sorted, array6);



    }

    @Test
    public void testMergeSortComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};
        MergeSort.sort(array1, Integer::compare);
        assertArrayEquals(array1_sorted, array1);

        MergeSort.sort(array2, Integer::compare);
        assertArrayEquals(array2_sorted, array2);

        MergeSort.sort(array0, String::compareToIgnoreCase);
        assertArrayEquals(array0_sorted, array0);

        MergeSort.sort(arrayOfPersons, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAge, arrayOfPersons);

        MergeSort.sort(arrayOfPersons, Person.compareByName);
        assertArrayEquals(arrayOfPersonsByName, arrayOfPersons);

        // test stability
        Person[] arrayOfPersonsA = {person5A, person2A, person1A, person3A, person4A};
        MergeSort.sort(arrayOfPersonsA, Person.compareByName);
        assertArrayEquals(arrayOfPersonsAlfabet, arrayOfPersonsA);
        MergeSort.sort(arrayOfPersonsA, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAlfabetAge, arrayOfPersonsA);

    }

    @Test
    public void testMergeSortImproved(){

        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Integer[] array3 = {9, 3, 7, 1, 100, 170, 20, 10};
        Integer[] array4 = {1,1,1,1,1};
        Integer[] array5 = {1,2,3,4,5};
        Integer[] array6 = {7,2,3,4,5};

        MergeSortImproved.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        MergeSortImproved.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        MergeSortImproved.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        MergeSortImproved.sort(array3);
        assertArrayEquals(array3_sorted, array3);

        MergeSortImproved.sort(array4);
        assertArrayEquals(array4_sorted, array4);

        MergeSortImproved.sort(array5);
        assertArrayEquals(array5_sorted, array5);

        MergeSortImproved.sort(array6);
        assertArrayEquals(array6_sorted, array6);
    }

    @Test
    public void testMergeSortImprovedComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};
        MergeSortImproved.sort(array1, Integer::compare);
        assertArrayEquals(array1_sorted, array1);

        MergeSortImproved.sort(array2, Integer::compare);
        assertArrayEquals(array2_sorted, array2);

        MergeSortImproved.sort(array0, String::compareToIgnoreCase);
        assertArrayEquals(array0_sorted, array0);

        MergeSortImproved.sort(arrayOfPersons, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAge, arrayOfPersons);

        MergeSortImproved.sort(arrayOfPersons, Person.compareByName);
        assertArrayEquals(arrayOfPersonsByName, arrayOfPersons);

        // test stability
        Person[] arrayOfPersonsA = {person5A, person2A, person1A, person3A, person4A};
        MergeSortImproved.sort(arrayOfPersonsA, Person.compareByName);
        assertArrayEquals(arrayOfPersonsAlfabet, arrayOfPersonsA);
        MergeSortImproved.sort(arrayOfPersonsA, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAlfabetAge, arrayOfPersonsA);

    }



    @Test
    public void testMergeSortBottomUp(){

        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Integer[] array3 = {9, 3, 7, 1, 100, 170, 20, 10};
        Integer[] array4 = {1,1,1,1,1};
        Integer[] array5 = {1,2,3,4,5};
        Integer[] array6 = {7,2,3,4,5};

        MergeSortBottomUp.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        MergeSortBottomUp.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        MergeSortBottomUp.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        MergeSortBottomUp.sort(array3);
        assertArrayEquals(array3_sorted, array3);

        MergeSortBottomUp.sort(array4);
        assertArrayEquals(array4_sorted, array4);

        MergeSortBottomUp.sort(array5);
        assertArrayEquals(array5_sorted, array5);

        MergeSortBottomUp.sort(array6);
        assertArrayEquals(array6_sorted, array6);

    }

    @Test
    public void testMergeSortBottomUpComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};
        MergeSortBottomUp.sort(array1, Integer::compare);
        assertArrayEquals(array1_sorted, array1);

        MergeSortBottomUp.sort(array2, Integer::compare);
        assertArrayEquals(array2_sorted, array2);

        MergeSortBottomUp.sort(array0, String::compareToIgnoreCase);
        assertArrayEquals(array0_sorted, array0);

        MergeSortBottomUp.sort(arrayOfPersons, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAge, arrayOfPersons);

        MergeSortBottomUp.sort(arrayOfPersons, Person.compareByName);
        assertArrayEquals(arrayOfPersonsByName, arrayOfPersons);

        // test stability
        Person[] arrayOfPersonsA = {person5A, person2A, person1A, person3A, person4A};
        MergeSortBottomUp.sort(arrayOfPersonsA, Person.compareByName);
        assertArrayEquals(arrayOfPersonsAlfabet, arrayOfPersonsA);
        MergeSortBottomUp.sort(arrayOfPersonsA, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAlfabetAge, arrayOfPersonsA);

    }

    @Test
    public void testQuickSort(){

        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Integer[] array3 = {9, 3, 7, 1, 100, 170, 20, 10};
        Integer[] array4 = {1,1,1,1,1};
        Integer[] array5 = {1,2,3,4,5};
        Integer[] array6 = {7,2,3,4,5};

        QuickSort.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        QuickSort.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        QuickSort.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        QuickSort.sort(array3);
        assertArrayEquals(array3_sorted, array3);

        QuickSort.sort(array4);
        assertArrayEquals(array4_sorted, array4);


        QuickSort.sort(array5);
        assertArrayEquals(array5_sorted, array5);

        QuickSort.sort(array6);
        assertArrayEquals(array6_sorted, array6);



    }

    @Test
    public void testQuickSortComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};
        QuickSort.sort(array1, Integer::compare);
        assertArrayEquals(array1_sorted, array1);

        QuickSort.sort(array2, Integer::compare);
        assertArrayEquals(array2_sorted, array2);

        QuickSort.sort(array0, String::compareToIgnoreCase);
        assertArrayEquals(array0_sorted, array0);

        QuickSort.sort(arrayOfPersons, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAge, arrayOfPersons);

        QuickSort.sort(arrayOfPersons, Person.compareByName);
        assertArrayEquals(arrayOfPersonsByName, arrayOfPersons);


    }

    @Test
    public void testQuickMedianOfThreeSort(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Integer[] array3 = {9, 3, 7, 1, 100, 170, 20, 10};
        Integer[] array4 = {1,1,1,1,1};
        Integer[] array5 = {1,2,3,4,5};
        Integer[] array6 = {7,2,3,4,5};

        QuickSortMedianOfThree.sort(array0);
        assertArrayEquals(array0_sorted, array0);

        QuickSortMedianOfThree.sort(array1);
        assertArrayEquals(array1_sorted, array1);

        QuickSortMedianOfThree.sort(array2);
        assertArrayEquals(array2_sorted, array2);

        QuickSortMedianOfThree.sort(array3);
        assertArrayEquals(array3_sorted, array3);

        QuickSortMedianOfThree.sort(array4);
        assertArrayEquals(array4_sorted, array4);


        QuickSortMedianOfThree.sort(array5);
        assertArrayEquals(array5_sorted, array5);
        QuickSortMedianOfThree.sort(array6);
        assertArrayEquals(array6_sorted, array6);



    }

    @Test
    public void testQuickMedianOfThreeSortComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};

/*        QuickSortMedianOfThree.sort(array1, Integer::compare);
        assertArrayEquals(array1_sorted, array1);

        QuickSortMedianOfThree.sort(array2, Integer::compare);
        assertArrayEquals(array2_sorted, array2);

        QuickSortMedianOfThree.sort(array0, String::compareToIgnoreCase);
        assertArrayEquals(array0_sorted, array0);*/

        QuickSortMedianOfThree.sort(arrayOfPersons, Person.compareByAge);
        assertArrayEquals(arrayOfPersonsByAge, arrayOfPersons);

/*        QuickSortMedianOfThree.sort(arrayOfPersons, Person.compareByName);
        assertArrayEquals(arrayOfPersonsByName, arrayOfPersons);*/


    }
}
