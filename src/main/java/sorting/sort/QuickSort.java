package sorting.sort;

/*
* implementation of quicksort
*
* */

import java.util.Comparator;

public class QuickSort {

    // puts array[low] into its final index (partitionIndex)
    // for all i, i < partitionIndex: array[i] <= array[partitionIndex]
    // for all i, i > partitionIndex: array[i] >= array[partitionIndex]
    // returns partitionIndex
    // using natural order
    private static int partition(Comparable[] array, int low, int high){
        Comparable toPutInPlace = array[low];

        int left = low; // pointer moving from left to right
        int right = high + 1; // pointer moving from right to left, at end will be index to swap with low

        while(true){
            // move from left to right to find first element greater or equal to toPutInPlace
            while(Sort.isStrictLess(array[++left], toPutInPlace)){
                // all elements are strict less than toPutInPlace
                if(left == high){break;}
            }
            // move from right to left to find first element less or equal to toPutInPlace
            while(Sort.isStrictLess(toPutInPlace, array[--right])){
                // all elements are strict greater than toPutInPlace
                // no need to check right == low because if this is the case than Sort.isStrictLess(toPutInPlace, array[--right])
                // would have been false anyway -> would never be checked
            }

            //we check whether we have checked all entries
            //if so we are done
            if(right <= left){
                break;
            }
            else{
                // swap elements because there violate the property we want
                Sort.swap(array, left, right);
            }

        }

        Sort.swap(array, low, right);
        return right;
    }


    // puts array[low] into its final index (partitionIndex)
    // for all i, i < partitionIndex: array[i] <= array[partitionIndex]
    // for all i, i > partitionIndex: array[i] >= array[partitionIndex]
    // returns partitionIndex
    // using a comparator
    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator){
        T toPutInPlace = array[low];

        int left = low; // pointer moving from left to right
        int right = high + 1; // pointer moving from right to left, at end will be index to swap with low

        while(true){
            // move from left to right to find first element greater or equal to toPutInPlace
            while(Sort.isStrictLess(array[++left], toPutInPlace, comparator)){
                // all elements are strict less than toPutInPlace
                if(left == high){break;}
            }
            // move from right to left to find first element less or equal to toPutInPlace
            while(Sort.isStrictLess(toPutInPlace, array[--right], comparator)){
                // all elements are strict greater than toPutInPlace
                // no need to check right == low because if this is the case than Sort.isStrictLess(toPutInPlace, array[--right])
                // would have been false anyway -> would never be checked
            }

            //we check whether we have checked all entries
            //if so we are done
            if(right <= left){
                break;
            }
            else{
                // swap elements because there violate the property we want
                Sort.swap(array, left, right);
            }

        }

        Sort.swap(array, low, right);
        return right;
    }


    private static void sort(Comparable[] array, int low, int high){
        if (high <= low){return;}
        int partitionIndex = partition(array, low, high); // index put in place by partition()
        sort(array, low, partitionIndex - 1); // sort left from partitionIndex array[low, ..., partitionIndex-1]
        sort(array, partitionIndex + 1, high); // sort right from partitionIndex array[partitionIndex + 1... high]
    }

    // sorting an array in ascending order using the natural order
    public static void sort(Comparable[] array){
        sort(array, 0, array.length-1);
    }



    private static <T> void sort(T[] array, int low, int high, Comparator<T> comparator) {
        if (high <= low) {
            return;
        }
        int partitionIndex = partition(array, low, high, comparator); // index put in place by partition()
        sort(array, low, partitionIndex - 1, comparator); // sort left from partitionIndex array[low, ..., partitionIndex-1]
        sort(array, partitionIndex + 1, high, comparator); // sort right from partitionIndex array[partitionIndex + 1... high]
    }

    // sorting an array in ascending order using a comparator
    public static <T> void sort(T[] array, Comparator<T> comparator){
        sort(array,0, array.length-1, comparator);
    }

}
