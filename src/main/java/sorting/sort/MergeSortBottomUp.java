package sorting.sort;

/*
* implementation of bottom up merge sort
* repeatedly merging together small sub arrays on one pass through the array
* using sub arrays of length 1,2,4,8,...
* log N passes through the array
* */



import java.util.Comparator;

public class MergeSortBottomUp {



    // merge array[low .. mid] and array[mid+1 .. high] using aux[low .. high]
    // using the natural order
    private static void merge(Comparable[] array, Comparable[] aux, int low, int mid, int high){

        //copy array into aux
        for(int i = low; i <= high; i++) {
            aux[i] = array[i];
        }

        //merge aux back into array
        int i = low;
        int j = mid +1;
        for(int k = low; k <= high; k++){
            if(i > mid){
                array[k] = aux[j++];
            }
            else if(j > high){
                array[k] = aux[i++];
            }
            else if(Sort.isStrictLess(aux[j], aux[i])){
                array[k] = aux[j++];
            }
            else{
                array[k] = aux[i++];
            }
        }


    }


    // merge array[low .. mid] and array[mid+1 .. high] using aux[low .. high]
    // using a comparator
    private static <T> void merge(T[] array, T[] aux, int low, int mid, int high, Comparator<T> comparator){

        //copy array into aux
        for(int i = low; i <= high; i++) {
            aux[i] = array[i];
        }

        //merge aux back into array
        int i = low;
        int j = mid +1;
        for(int k = low; k <= high; k++){
            if(i > mid){
                array[k] = aux[j++];
            }
            else if(j > high){
                array[k] = aux[i++];
            }
            else if(Sort.isStrictLess(aux[j], aux[i], comparator)){
                array[k] = aux[j++];
            }
            else{
                array[k] = aux[i++];
            }
        }


    }

    // sort an array using the natural order
    public static void sort(Comparable[] array){
        Comparable[] aux = new Comparable[array.length];
        // merging sub arrays with subLength 1,2,4,8,...
        for (int subLength = 1; subLength<array.length; subLength*=2){
            // one pass through array
            for(int low = 0; low < array.length -subLength; low+=2*subLength){
                int high = Math.min(low + 2*subLength - 1, array.length-1); // is possible low + 2*subLength - 1 > array.length -1
                int mid = low + subLength - 1; // mid < (array.length - subLength) + subLength -1 = array.length - 1
                merge(array, aux, low, mid, high);
            }

        }

    }


    // sort an array using a comparator
    public static <T> void sort(T[] array, Comparator<T> comparator){
        T[] aux = (T[]) new Object[array.length];
        // merging sub arrays with subLength 1,2,4,8,...
        for (int subLength = 1; subLength<array.length; subLength*=2){
            // one pass through array
            for(int low = 0; low < array.length -subLength; low+=2*subLength){
                int high = Math.min(low + 2*subLength - 1, array.length-1); // is possible low + 2*subLength - 1 > array.length -1
                int mid = low + subLength - 1; // mid < (array.length - subLength) + subLength -1 = array.length - 1
                merge(array, aux, low, mid, high, comparator);
            }

        }

    }
}
