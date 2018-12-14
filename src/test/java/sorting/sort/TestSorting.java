package sorting.sort;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class TestSorting {


    private static Person person1 = new Person("Anna", 50, "Brussels");
    private static Person person2 = new Person("Tom", 38, "Rome");
    private static Person person3 = new Person("Louis", 38, "Brussels");


    private static Person person1A = new Person("Anna", 5, "Brussels");
    private static Person person2A = new Person("Bob", 5, "Rome");
    private static Person person3A = new Person("Chris", 8, "Brussels");
    private static Person person4A = new Person("David", 3, "Brussels");
    private static Person person5A = new Person("Erik", 8, "Brussels");
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
        assertTrue(Sort.isSorted(array1));

        Selection.sort(array2);
        assertTrue(Sort.isSorted(array2));

        Selection.sort(array0);
        assertTrue(Sort.isSorted(array0));

        Selection.sort(array3);
        assertTrue(Sort.isSorted(array3));

        Selection.sort(array4);
        assertTrue(Sort.isSorted(array4));

        Selection.sort(array5);
        assertTrue(Sort.isSorted(array5));

        Selection.sort(array6);
        assertTrue(Sort.isSorted(array6));

    }

    @Test
    public void testSelectionComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};

        Person[] arrayOfPersons = {person1, person2, person3};

        Selection.sort(array1, Integer::compare);
        assertTrue(Sort.isSorted(array1, Integer::compareTo));

        Selection.sort(array2, Integer::compare);
        assertTrue(Sort.isSorted(array2, Integer::compareTo));

        Selection.sort(array0, String::compareToIgnoreCase);
        assertTrue(Sort.isSorted(array0, String::compareToIgnoreCase));


        Selection.sort(arrayOfPersons, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

        Selection.sort(arrayOfPersons, Person.compareByName);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByName));
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
        assertTrue(Sort.isSorted(array1));

        Insertion.sort(array2);
        assertTrue(Sort.isSorted(array2));

        Insertion.sort(array0);
        assertTrue(Sort.isSorted(array0));

        Insertion.sort(array3);
        assertTrue(Sort.isSorted(array3));

        Insertion.sort(array4);
        assertTrue(Sort.isSorted(array4));

        Insertion.sort(array5);
        assertTrue(Sort.isSorted(array5));

        Insertion.sort(array6);
        assertTrue(Sort.isSorted(array6));

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
        assertTrue(Sort.isSorted(array1, Integer::compareTo));

        Insertion.sort(array2, Integer::compare);
        assertTrue(Sort.isSorted(array2, Integer::compareTo));

        Insertion.sort(array0, String::compareToIgnoreCase);
        assertTrue(Sort.isSorted(array0, String::compareToIgnoreCase));

        Insertion.sort(arrayOfPersons, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

        arrayOfPersons = new Person[] {person1, person2, person3};
        Insertion.sort(arrayOfPersons, Person.compareByName);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByName));

        arrayOfPersons = new Person[] {person1, person2, person3};
        Insertion.sort(arrayOfPersons, 0, 2, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

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
        assertTrue(Sort.isSorted(array1));

        InsertionSentinel.sort(array2);
        assertTrue(Sort.isSorted(array2));

        InsertionSentinel.sort(array0);
        assertTrue(Sort.isSorted(array0));

    }

    @Test
    public void testInsertionSentinelComparator() {
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};

        InsertionSentinel.sort(array1, Integer::compare);
        assertTrue(Sort.isSorted(array1, Integer::compareTo));

        InsertionSentinel.sort(array2, Integer::compare);
        assertTrue(Sort.isSorted(array2, Integer::compareTo));

        InsertionSentinel.sort(array0, String::compareToIgnoreCase);
        assertTrue(Sort.isSorted(array0, String::compareToIgnoreCase));

        InsertionSentinel.sort(arrayOfPersons, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

        InsertionSentinel.sort(arrayOfPersons, Person.compareByName);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByName));

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
        assertTrue(Sort.isSorted(array0));

        ShellSort.sort(array1);
        assertTrue(Sort.isSorted(array1));

        ShellSort.sort(array2);
        assertTrue(Sort.isSorted(array2));

    }

    @Test
    public void testShellSortComparator() {
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};
        ShellSort.sort(array1, Integer::compare);
        assertTrue(Sort.isSorted(array1, Integer::compareTo));

        ShellSort.sort(array2, Integer::compare);
        assertTrue(Sort.isSorted(array2, Integer::compareTo));

        ShellSort.sort(array0, String::compareToIgnoreCase);
        assertTrue(Sort.isSorted(array0, String::compareToIgnoreCase));

        ShellSort.sort(arrayOfPersons, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

        ShellSort.sort(arrayOfPersons, Person.compareByName);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByName));

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

        MergeSort.sort(array0);
        assertTrue(Sort.isSorted(array0));

        MergeSort.sort(array1);
        assertTrue(Sort.isSorted(array1));

        MergeSort.sort(array2);
        assertTrue(Sort.isSorted(array2));

        MergeSort.sort(array3);
        assertTrue(Sort.isSorted(array3));

        MergeSort.sort(array4);
        assertTrue(Sort.isSorted(array4));

        MergeSort.sort(array5);
        assertTrue(Sort.isSorted(array5));

        MergeSort.sort(array6);
        assertTrue(Sort.isSorted(array6));



    }

    @Test
    public void testMergeSortComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};
        MergeSort.sort(array1, Integer::compare);
        assertTrue(Sort.isSorted(array1, Integer::compareTo));

        MergeSort.sort(array2, Integer::compare);
        assertTrue(Sort.isSorted(array2, Integer::compareTo));

        MergeSort.sort(array0, String::compareToIgnoreCase);
        assertTrue(Sort.isSorted(array0, String::compareToIgnoreCase));

        MergeSort.sort(arrayOfPersons, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

        MergeSort.sort(arrayOfPersons, Person.compareByName);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByName));

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
        assertTrue(Sort.isSorted(array0));

        MergeSortImproved.sort(array1);
        assertTrue(Sort.isSorted(array1));

        MergeSortImproved.sort(array2);
        assertTrue(Sort.isSorted(array2));

        MergeSortImproved.sort(array3);
        assertTrue(Sort.isSorted(array3));

        MergeSortImproved.sort(array4);
        assertTrue(Sort.isSorted(array4));

        MergeSortImproved.sort(array5);
        assertTrue(Sort.isSorted(array5));

        MergeSortImproved.sort(array6);
        assertTrue(Sort.isSorted(array6));
    }

    @Test
    public void testMergeSortImprovedComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};
        MergeSortImproved.sort(array1, Integer::compare);
        assertTrue(Sort.isSorted(array1, Integer::compareTo));

        MergeSortImproved.sort(array2, Integer::compare);
        assertTrue(Sort.isSorted(array2, Integer::compareTo));

        MergeSortImproved.sort(array0, String::compareToIgnoreCase);
        assertTrue(Sort.isSorted(array0, String::compareToIgnoreCase));

        MergeSortImproved.sort(arrayOfPersons, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

        MergeSortImproved.sort(arrayOfPersons, Person.compareByName);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByName));

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
        assertTrue(Sort.isSorted(array0));

        MergeSortBottomUp.sort(array1);
        assertTrue(Sort.isSorted(array1));

        MergeSortBottomUp.sort(array2);
        assertTrue(Sort.isSorted(array2));

        MergeSortBottomUp.sort(array3);
        assertTrue(Sort.isSorted(array3));

        MergeSortBottomUp.sort(array4);
        assertTrue(Sort.isSorted(array4));

        MergeSortBottomUp.sort(array5);
        assertTrue(Sort.isSorted(array5));

        MergeSortBottomUp.sort(array6);
        assertTrue(Sort.isSorted(array6));

    }

    @Test
    public void testMergeSortBottomUpComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};
        MergeSortBottomUp.sort(array1, Integer::compare);
        assertTrue(Sort.isSorted(array1, Integer::compareTo));

        MergeSortBottomUp.sort(array2, Integer::compare);
        assertTrue(Sort.isSorted(array2, Integer::compareTo));

        MergeSortBottomUp.sort(array0, String::compareToIgnoreCase);
        assertTrue(Sort.isSorted(array0, String::compareToIgnoreCase));

        MergeSortBottomUp.sort(arrayOfPersons, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

        MergeSortBottomUp.sort(arrayOfPersons, Person.compareByName);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByName));

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
        assertTrue(Sort.isSorted(array0));

        QuickSort.sort(array1);
        assertTrue(Sort.isSorted(array1));

        QuickSort.sort(array2);
        assertTrue(Sort.isSorted(array2));

        QuickSort.sort(array3);
        assertTrue(Sort.isSorted(array3));

        QuickSort.sort(array4);
        assertTrue(Sort.isSorted(array4));


        QuickSort.sort(array5);
        assertTrue(Sort.isSorted(array5));

        QuickSort.sort(array6);
        assertTrue(Sort.isSorted(array6));



    }

    @Test
    public void testQuickSortComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};
        QuickSort.sort(array1, Integer::compare);
        assertTrue(Sort.isSorted(array1, Integer::compareTo));

        QuickSort.sort(array2, Integer::compare);
        assertTrue(Sort.isSorted(array2, Integer::compareTo));

        QuickSort.sort(array0, String::compareToIgnoreCase);
        assertTrue(Sort.isSorted(array0, String::compareToIgnoreCase));

        QuickSort.sort(arrayOfPersons, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

        QuickSort.sort(arrayOfPersons, Person.compareByName);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByName));


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
        assertTrue(Sort.isSorted(array0));


        QuickSortMedianOfThree.sort(array1);
        assertTrue(Sort.isSorted(array1));

        QuickSortMedianOfThree.sort(array2);
        assertTrue(Sort.isSorted(array2));

        QuickSortMedianOfThree.sort(array3);
        assertTrue(Sort.isSorted(array3));

        QuickSortMedianOfThree.sort(array4);
        assertTrue(Sort.isSorted(array4));


        QuickSortMedianOfThree.sort(array5);
        assertTrue(Sort.isSorted(array5));
        QuickSortMedianOfThree.sort(array6);
        assertTrue(Sort.isSorted(array6));



    }

    @Test
    public void testQuickMedianOfThreeSortComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};

        QuickSortMedianOfThree.sort(array1, Integer::compare);
        assertTrue(Sort.isSorted(array1, Integer::compareTo));


        QuickSortMedianOfThree.sort(array2, Integer::compare);
        assertTrue(Sort.isSorted(array2, Integer::compareTo));

        QuickSortMedianOfThree.sort(array0, String::compareToIgnoreCase);
        assertTrue(Sort.isSorted(array0, String::compareToIgnoreCase));

        QuickSortMedianOfThree.sort(arrayOfPersons, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

        QuickSortMedianOfThree.sort(arrayOfPersons, Person.compareByName);
        assertTrue(Sort.isSorted(arrayOfPersons, Person.compareByName));


    }

    @Test
    public void testQuickThreeWay(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Integer[] array3 = {9, 3, 7, 1, 100, 170, 20, 10};
        Integer[] array4 = {1,1,1,1,1};
        Integer[] array5 = {1,2,3,4,5};
        Integer[] array6 = {7,2,3,4,5};

        QuickSortThreeWay.sort(array0);
        assertTrue(Sort.isSorted(array0));


        QuickSortThreeWay.sort(array1);
        assertTrue(Sort.isSorted(array1));

        QuickSortThreeWay.sort(array2);
        assertTrue(Sort.isSorted(array2));

        QuickSortThreeWay.sort(array3);
        assertTrue(Sort.isSorted(array3));

        QuickSortThreeWay.sort(array4);
        assertTrue(Sort.isSorted(array4));


        QuickSortThreeWay.sort(array5);
        assertTrue(Sort.isSorted(array5));
        QuickSortThreeWay.sort(array6);
        assertTrue(Sort.isSorted(array6));



    }

    @Test
    public void testQuickThreeWayComparator(){
        String[] array0 = {"s", "h", "e", "l", "l", "s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1, 5};
        Integer[] array2 = {};
        Person[] arrayOfPersons = {person1, person2, person3};

        QuickSortThreeWay.sort(array1, Integer::compare);
        assertTrue(Sort.isSorted(array1, Integer::compareTo));


        QuickSortThreeWay.sort(array2, Integer::compare);
        assertTrue(Sort.isSorted(array2, Integer::compareTo));

        QuickSortThreeWay.sort(array0, String::compareToIgnoreCase);
        assertTrue(Sort.isSorted(array0, String::compareToIgnoreCase));

        QuickSortThreeWay.sort(arrayOfPersons, Person.compareByAge);
        assertTrue(Sort.isSorted(arrayOfPersons,  Person.compareByAge));

        QuickSortThreeWay.sort(arrayOfPersons, Person.compareByName);
        assertTrue(Sort.isSorted(arrayOfPersons, Person.compareByName));


    }
}
