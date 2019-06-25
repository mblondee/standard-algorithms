package problems.sorthash;

import com.sun.prism.shader.Solid_RadialGradient_REPEAT_AlphaTest_Loader;
import org.junit.Test;
import sorting.sort.Sort;

import java.util.*;

import static org.junit.Assert.*;

public class SortHashTest {

    @Test
    public void test1(){

        int[] array1 = new int[]{3,0,5,0,3,4,0,1,2,3,1,4,5};
        assertTrue(
                SortHash.mostFrequent(array1) == 3 || SortHash.mostFrequent(array1) == 0
        );

        int[] array2 = new int[]{};
        assertTrue( SortHash.mostFrequent(array2) == null);
    }

    @Test
    public void test2(){
        int[] array1 = new int[]{};
        assertFalse(SortHash.sumTo(5, array1));

        int[] array2 = new int[]{5};
        assertFalse(SortHash.sumTo(5, array2));

        int[] array3 = new int[]{2,3};
        assertTrue(SortHash.sumTo(5, array3));
        assertFalse(SortHash.sumTo(6, array3));

        int[] array4 = new int[]{2,2,2,2,2};
        assertTrue(SortHash.sumTo(4, array4));
        assertFalse(SortHash.sumTo(6, array4));

        int[] array5 = new int[]{-2,-2,2,2,2};
        assertTrue(SortHash.sumTo(-4, array5));
        assertTrue(SortHash.sumTo(0, array5));

    }

    @Test
    public void test3(){
        Interval int1 = new Interval(1,4);
        Interval int2 = new Interval(2,6);
        assertTrue(Interval.compareByStart.compare(int1, int2) < 0);
        assertTrue(Interval.compareByStart.compare(int2, int1) > 0);
        assertTrue(Interval.compareByStart.compare(int1, int1) == 0);

        List<Interval> setOfIntervals1 = new ArrayList();
        setOfIntervals1.add(int2);
        setOfIntervals1.add(int1);
        assertEquals(0, SortHash.smallestNotBelonging(setOfIntervals1));

        Interval int3 = new Interval(0,4);
        Interval int4 = new Interval(4,6);
        Interval int5 = new Interval(6,9);
        List<Interval> setOfIntervals2 = new ArrayList();
        setOfIntervals2.add(int5);
        setOfIntervals2.add(int4);
        setOfIntervals2.add(int3);
        assertEquals(10, SortHash.smallestNotBelonging(setOfIntervals2));


        Interval int6 = new Interval(0,9);
        Interval int7 = new Interval(0,5);
        Interval int8 = new Interval(0,4);
        List<Interval> setOfIntervals3 = new ArrayList();
        setOfIntervals3.add(int6);
        setOfIntervals3.add(int7);
        setOfIntervals3.add(int8);
        assertEquals(10, SortHash.smallestNotBelonging(setOfIntervals3));

    }

    @Test
    public void test4(){
        int[] input = new int[]{4,1,9,0,11,3,10,2};
        Interval interval = SortHash.getLargestInterval(input);
        assertEquals(0, interval.getStart());
        assertEquals(4, interval.getEnd());


        int[] input1 = new int[]{4,1,9,0,11,3,10,2,4,1,9,0,11,3,10,2};
        Interval interval1 = SortHash.getLargestInterval(input);
        assertEquals(0, interval1.getStart());
        assertEquals(4, interval1.getEnd());
    }

    @Test
    public void test5(){
        List<Set<Integer>> example = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        example.add(set1);

        Set<Integer> set2 = new HashSet<>();
        set2.add(1);
        set2.add(4);
        example.add(set2);

        Set<Integer> set3 = new HashSet<>();
        set3.add(3);
        set3.add(5);
        example.add(set3);

        assertTrue(SortHash.disjoint(example));


        List<Set<Integer>> example1 = new ArrayList<>();
        Set<Integer> set11 = new HashSet<>();
        set11.add(1);
        set11.add(0);
        example1.add(set11);

        Set<Integer> set21 = new HashSet<>();
        set21.add(1);
        set21.add(2);
        example1.add(set21);

        Set<Integer> set31 = new HashSet<>();
        set31.add(4);
        set31.add(0);
        example1.add(set31);


        assertTrue(SortHash.disjoint(example1));

        List<Set<Integer>> example2 = new ArrayList<>();
        Set<Integer> set12 = new HashSet<>();
        set12.add(1);
        set12.add(0);
        example1.add(set12);

        Set<Integer> set22 = new HashSet<>();
        set22.add(1);
        set22.add(2);
        example1.add(set22);

        Set<Integer> set13 = new HashSet<>();
        set13.add(0);
        set13.add(2);
        example1.add(set13);


        assertFalse(SortHash.disjoint(example2));

    }

    @Test
    public void test6(){
//        System.out.println(10012%10);
//
//        System.out.println(10012/10%10);
//
//        System.out.println(10012/100%10);
//       System.out.println(10012/1000%10);
//        System.out.println(10012/10000%10);
//        System.out.println(10012/100000%10);

        int[] input = new int[]{42, 1334, 34, 4, 12, 2, 1333};
        HashMap<Integer, Integer> ranks = SortHash.rank(input);
        assertEquals(new Integer(0), ranks.get(2));
        assertEquals(new Integer(1), ranks.get(4));
        assertEquals(new Integer(2), ranks.get(12));
        assertEquals(new Integer(3), ranks.get(34));
        assertEquals(new Integer(4), ranks.get(42));
        assertEquals(new Integer(5), ranks.get(1333));
        assertEquals(new Integer(6), ranks.get(1334));







    }

    @Test
    public void test7(){
        Interval int1 = new Interval(0,2);
        Interval int2 = new Interval(3,7);
        Interval int3 = new Interval(4,8);
        Interval int4 = new Interval(7,8);
        Interval int5 = new Interval(1,5);
        List<Interval> intervalList = new ArrayList<>();
        intervalList.add(int1);
        intervalList.add(int2);
        intervalList.add(int3);
        intervalList.add(int4);
        intervalList.add(int5);

        assertEquals(3, SortHash.maxOverlap(intervalList));


        List<Interval> intervalList1 = new ArrayList<>();
        intervalList1.add(int1);
        intervalList1.add(int2);
        intervalList1.add(int4);
        assertEquals(1, SortHash.maxOverlap(intervalList1));
    }

    @Test
    public void test8(){
        int[] array1 = new int[]{4,8,1,2};
        assertEquals(16, SortHash.smallestNoSum(array1));

        int[] array2 = new int[]{1,2,5,8};
        assertEquals(4, SortHash.smallestNoSum(array2));

        int[] array3 = new int[]{};
        assertEquals(1, SortHash.smallestNoSum(array3));


        int[] array4 = new int[]{1,1,1,1,1,1};
        assertEquals(7, SortHash.smallestNoSum(array4));
    }

    @Test
    public void test9(){
        int[] array1 = {987, 988, 21, 20, 377, 1, 3, 4};
        boolean[] res1 = SortHash.isFibonacci(array1);

        boolean[] correct1 = {true, false, true, false, true, true, true, false};

        assertArrayEquals(correct1, res1);



        int[] array2 = {1,1,1,20, 21, 21, 1,4,4,4};
        boolean[] res2 = SortHash.isFibonacci(array2);
        boolean[] correct2 = {true, true, true, false, true, true, true, false, false, false};
        assertArrayEquals(correct2, res2);



    }
}
