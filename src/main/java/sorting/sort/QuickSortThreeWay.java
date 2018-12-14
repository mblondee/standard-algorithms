package sorting.sort;


/*
 * implementation of quicksort with 3-way partitioning
 * not stable
 * */

import java.util.Comparator;

public class QuickSortThreeWay {

    private static void sort(Comparable[] array, int low, int high){
        if (high <= low){return;}


        Comparable pivot = array[low];
        int strictLess = low; // array[i] < pivot when i <strictLower and array[strictLess] equals pivot
        int strictGreater = high; // array[i] > pivot when i > strictLower
        int index = low + 1; // index traversing the array once

        // partition array in 3 subarrays
        // array[0] to array[strictLess -1]: array[i] < pivot
        // array[strictLess] to array[strictGreater]: array[i] = pivot
        // array[strictGreater+1] to array[high]: array[i] > pivot
        while(index <= strictGreater){
            if (Sort.isStrictLess(pivot, array[index])){
                // array[index] needs to go to the end of the array (index strictGreater)
                // decrement strictGreater
                Sort.swap(array, index, strictGreater--);
            }
            else if (Sort.isStrictLess(array[index], pivot)){
                // array[index] needs to go to beginning of the array (index strictLess)
                // increment strictLess
                // increment index
                Sort.swap(array, index++, strictLess++);

            }
            else{
                index++;
            }
        }

        sort(array, low, strictLess-1);
        sort(array, strictGreater+1, high);



    }

    private static <T> void sort(T[] array, int low, int high, Comparator<T> comparator){
        if (high <= low){return;}


        T pivot = array[low];
        int strictLess = low; // array[i] < pivot when i <strictLower and array[strictLess] equals pivot
        int strictGreater = high; // array[i] > pivot when i > strictLower
        int index = low + 1; // index traversing the array once

        // partition array in 3 subarrays
        // array[0] to array[strictLess -1]: array[i] < pivot
        // array[strictLess] to array[strictGreater]: array[i] = pivot
        // array[strictGreater+1] to array[high]: array[i] > pivot
        while(index <= strictGreater){
            if (Sort.isStrictLess(pivot, array[index], comparator)){
                // array[index] needs to go to the end of the array (index strictGreater)
                // decrement strictGreater
                Sort.swap(array, index, strictGreater--);
            }
            else if (Sort.isStrictLess(array[index], pivot, comparator)){
                // array[index] needs to go to beginning of the array (index strictLess)
                // increment strictLess
                // increment index
                Sort.swap(array, index++, strictLess++);

            }
            else{
                index++;
            }
        }

        sort(array, low, strictLess-1, comparator);
        sort(array, strictGreater+1, high, comparator);



    }

    // sorting an array in ascending order using the natural order
    public static void sort(Comparable[] array){
        sort(array, 0, array.length-1);
    }

    // sorting an array in ascending order using a comparator
    public static <T> void sort(T[] array, Comparator<T> comparator){
        sort(array, 0, array.length-1, comparator);
    }
}

