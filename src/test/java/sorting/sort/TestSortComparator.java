package sorting.sort;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestSortComparator {

    private static Comparator StringLength = new Comparator<String>(){
        @Override
        public int compare(String a, String b){
            if (a.length() == b.length()){return 0;}
            else if (a.length() < b.length()){return -1;}
            else {return 1;}
        }
    };

    @Test
    public void testCompare(){
        int first = -5;
        int second = 10;

        assertTrue(Sort.isStrictLess(first, second, Integer::compare));
        assertFalse(Sort.isStrictLess(second, first, Integer::compare));


        String firstString = "";
        String secondString = "aka";
        String thirdString = "haha";

        assertTrue(Sort.isStrictLess(firstString, secondString, StringLength));
        assertTrue(Sort.isStrictLess(firstString, thirdString, StringLength));
        assertTrue(Sort.isStrictLess(secondString, thirdString, StringLength));
        assertFalse(Sort.isStrictLess(secondString, secondString, StringLength));
        assertFalse(Sort.isStrictLess(thirdString, secondString, StringLength));
        assertFalse(Sort.isStrictLess(thirdString, firstString, StringLength));


        assertFalse(Sort.isStrictLess(thirdString, secondString, String::compareToIgnoreCase));
        assertTrue(Sort.isStrictLess(secondString, thirdString, String::compareToIgnoreCase));
    }



    @Test
    public void testIsSorted(){
        Integer[] array1 = {9, 3, 7, 1, 10, 17, 2, 1};
        assertFalse(Sort.isSorted(array1, 0, 7, Integer::compare));

        Integer[] array2 = {1,1,2,3,7,7,10,17};
        assertTrue(Sort.isSorted(array2, 0, 7, Integer::compare));

        String[] arrayS1 = {"", "haha", "akaka"};
        String[] arrayS2 = {"haha", "", "akaka"};
        String[] arrayS3 = {};

        assertTrue(Sort.isSorted(arrayS1, 0,2, StringLength));
        assertTrue(Sort.isSorted(arrayS1, 1,2, StringLength));
        assertTrue(Sort.isSorted(arrayS2, 1,2, StringLength));
        assertFalse(Sort.isSorted(arrayS2, 0,2, StringLength));
    }

    @Test
    public void testPerson(){
        Person person1 = new Person("Anna", 50, "Brussels");
        Person person2 = new Person("Tom", 38, "Rome");
        Person person3 = new Person("Louis", 38, "Brussels");

        assertFalse(Sort.isStrictLess(person1, person2, Person.compareByAge ));
        assertFalse(Sort.isStrictLess(person2, person3, Person.compareByAge ));
        assertTrue(Sort.isStrictLess(person2, person1, Person.compareByAge ));

        assertTrue(Sort.isStrictLess(person1, person2, Person.compareByName));
        assertFalse(Sort.isStrictLess(person2, person1, Person.compareByName));

        assertTrue(Sort.isStrictLess(person1, person2, Person.compareByAddress));
        assertFalse(Sort.isStrictLess(person2, person1, Person.compareByAddress));
    }
}
