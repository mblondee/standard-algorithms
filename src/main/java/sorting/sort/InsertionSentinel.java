package sorting.sort;

// implementation of insertion sort with a sentinel
// first put smallest element in first position
// for every index i compare with array[i] with array[j] with j=i-1 to j=0, swap if smaller
// break if not smaller
// eliminates the j>0 test in the inner loop of insertion sort
// also eliminating all swaps to move a smaller element to the beginning of the array

//TODO add comparator version

import java.util.Comparator;

public class InsertionSentinel {

    public static void sort(Comparable[] array){
        int length = array.length;
        int swapsMade = 0;

        //traverse array from end to beginning in order to put smallest element in first position
        //count swaps made, if this is 0, then array is already sorted
        for(int i = length -1 ; i>0; i--){
            if(Sort.isStrictLess(array[i],array[i-1])){
                Sort.swap(array, i, i-1);
                swapsMade ++;
            }
        }


        // array is already ordered
        if (swapsMade == 0){return;}

        for(int i = 2; i<length; i++){
            // checking whether array[i] has to be moved
            Comparable toSwap = array[i];
            int j = i;
            while (Sort.isStrictLess(toSwap, array[j-1])){
                array[j] = array[j-1];
                j--;
            }
            array[j] = toSwap;
        }
    }

    public static <T> void sort(T[] array, Comparator<T> comparator){
        int length = array.length;
        int swapsMade = 0;

        //traverse array from end to beginning in order to put smallest element in first position
        //count swaps made, if this is 0, then array is already sorted
        for(int i = length -1 ; i>0; i--){
            if(Sort.isStrictLess(array[i],array[i-1], comparator)){
                Sort.swap(array, i, i-1);
                swapsMade ++;
            }
        }


        // array is already ordered
        if (swapsMade == 0){return;}

        for(int i = 2; i<length; i++){
            // checking whether array[i] has to be moved
            T toSwap = array[i];
            int j = i;
            while (Sort.isStrictLess(toSwap, array[j-1], comparator)){
                array[j] = array[j-1];
                j--;
            }
            array[j] = toSwap;
        }
    }
}
