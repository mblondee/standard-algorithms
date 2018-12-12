package sorting.sort;

/*
 * implementation of quicksort
 * using median-of-three partitioning
 * not stable
 * */

import java.util.Comparator;

public class QuickSortMedianOfThree {

    // if subarrays are too small use insertion sort
    static final int CUTOFF = 3;

    //compare only three elements: low, mid and high and if necessary swap such that
    // array[low] is smallest
    // array[high] is largest
    // array[mid] is in between
    // then pick array[mid] as the pivot element and put at position high-1
    private static Comparable medianOfThree(Comparable[] array, int low, int high){
        int mid = low + (high-low)/2;

        if(Sort.isStrictLess(array[mid],array[low])){
            Sort.swap(array, mid, low);
        }

        if(Sort.isStrictLess(array[high], array[low])){
            Sort.swap(array, low, high);
        }

        if(Sort.isStrictLess(array[high], array[mid])){
            Sort.swap(array, high, mid);
        }

        //put pivot next to rightmost position
        Sort.swap(array, mid, high-1);// array[high-1] <= array[high]
        return array[high-1];

    }

    //compare only three elements: low, mid and high and if necessary swap such that
    // array[low] is smallest
    // array[high] is largest
    // array[mid] is in between
    // then pick array[mid] as the pivot element and put at position high-1
    private static <T> T medianOfThree(T[] array, int low, int high, Comparator<T> comparator){
        int mid = low + (high-low)/2;

        if(Sort.isStrictLess(array[mid],array[low], comparator)){
            Sort.swap(array, mid, low);
        }

        if(Sort.isStrictLess(array[high], array[low], comparator)){
            Sort.swap(array, low, high);
        }

        if(Sort.isStrictLess(array[high], array[mid], comparator)){
            Sort.swap(array, high, mid);
        }

        //put pivot next to rightmost position
        Sort.swap(array, mid, high-1);// array[high-1] <= array[high]
        return array[high-1];

    }

    private static void quicksort(Comparable[] array, int low, int high){

        if(high <= low){}

        // check whether subarray has at least CUTOFF elements
        if(high-low + 1 < CUTOFF){
            Insertion.sort(array, low, high);
        }

        else {
            Comparable pivot = medianOfThree(array, low, high);


            //do the partitioning
            int left = low-1; // first to check from left is low
            int right = high+1; // first to check from right is high

            while (true) {
                while (Sort.isStrictLess(array[++left], pivot)) {
                    // no stop condition necessary: Sort.isStrictLess(array[high], pivot) will never evaluate to true
                }
                while (Sort.isStrictLess(pivot, array[--right])) {
                    // no stop condition necessary: Sort.isStrictLess(pivot, array[low]) will never evaluate to true
                }
                if(right <= left){break;}
                else {
                    // swap elements because they violate the property we want
                    Sort.swap(array, left, right);
                }



            }

            //quicksort on left and right subarray
            quicksort(array, low, left - 1);
            quicksort(array, left, high);
        }
    }

    private static <T> void quicksort(T[] array, int low, int high, Comparator<T> comparator){

        if(high <= low){}

        // check whether subarray has at least CUTOFF elements
        if(high-low + 1 < CUTOFF){
            Insertion.sort(array, low, high, comparator );
        }

        else {
            T pivot = medianOfThree(array, low, high, comparator);


            //do the partitioning
            int left = low-1; // first to check from left is low
            int right = high+1; // first to check from right is high

            while (true) {
                while (Sort.isStrictLess(array[++left], pivot, comparator)) {
                    // no stop condition necessary: Sort.isStrictLess(array[high], pivot) will never evaluate to true
                }
                while (Sort.isStrictLess(pivot, array[--right], comparator)) {
                    // no stop condition necessary: Sort.isStrictLess(pivot, array[low]) will never evaluate to true
                }
                if(right <= left){break;}
                else {
                    // swap elements because they violate the property we want
                    Sort.swap(array, left, right);
                }



            }

            //quicksort on left and right subarray
            quicksort(array, low, left - 1, comparator);
            quicksort(array, left, high, comparator);
        }
    }

    public static void sort(Comparable[] array){
        quicksort(array, 0, array.length-1);
    }
    public static <T> void sort(T[] array, Comparator<T> comparator){
        quicksort(array, 0, array.length-1, comparator);
    }

/*    private static void insertionSort(Comparable[] array, int low, int high){
        for(int i = low+1; i<=high; i++){
            for(int j = i; j>low; j--){
                if (Sort.isStrictLess(array[j], array[j-1])){
                    Sort.swap(array, j, j-1);
                }
                else{break;}
            }


        }

    }*/

/*    private static <T> void insertionSort(T[] array, int low, int high, Comparator<T> comparator){
        for(int i = low+1; i<=high; i++){
            System.out.println(i);
            System.out.println(array[i]);
            for(int j = i; j>low; j--){
                System.out.println(array[j]);
                if (Sort.isStrictLess(array[j], array[j-1], comparator)){
                    Sort.swap(array, j, j-1);
                    for (T el: array){System.out.println(el);}
                }
                //else{break;}
            }


        }

    }*/

}
