package strings;

import org.junit.Test;
import strings.sort.LSDRadixSort;


import static org.junit.Assert.*;

public class LSDRadixTest {

    @Test
    public void test(){
        String[] testArray = {"hallo", "abbal", "ziezo", "ander"};
        LSDRadixSort.sort(testArray, 5);
        assertArrayEquals(new String[]{"abbal", "ander","hallo", "ziezo" }, testArray);
    }

    @Test
    public void test1(){
        int[] testArray = {0xFFFFF,0x6FFFF,0x1111111,0x125,0xABCDEF};
        LSDRadixSort.sort(testArray);
        assertTrue(isSorted(testArray));
        //System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        int[] testArrayneg = {-0xFFFFF,-0x6FFFF,-0x1111111,-0x125,-0xABCDEF};
        LSDRadixSort.sort(testArrayneg);
        assertTrue(isSorted(testArrayneg));
        int[] testArray1 = {0x00-0x7F, 0x80-0xFF};
        LSDRadixSort.sort(testArray1);
        assertTrue(isSorted(testArray1));
    }

    private static boolean isSorted(int[] array){
        for(int i = 0; i<array.length-1; i++){
            if(array[i] > array[i+1]){
                return false;
            }
        }
        return true;
    }
}
