package sorting.sort;

/*implementation of merge sort
* to sort an array it is divided into two halves which are each sorted recursively and then merged
* between NlogN/2 and NlogN compares
* at most 6NlogN array accesses
* for arrays of length N
* using an auxiliary array to merge -> requires extra space proportional to N
* */



import java.util.Comparator;

public class MergeSort {

    // merge array[low .. mid] and array[mid+1 .. high] using aux[low .. high]
    // using natural order
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

    private static void sort(Comparable[] array, Comparable[] aux, int low, int high){
        if (high <= low){return;}

        int mid = low + (high - low)/2;
        sort(array, aux, low, mid);
        sort(array, aux, mid+1, high);
        merge(array, aux, low, mid, high);
    }

    private static <T> void sort(T[] array, T[] aux, int low, int high, Comparator<T> comparator){
        if (high <= low){return;}

        int mid = low + (high - low)/2;
        sort(array, aux, low, mid, comparator);
        sort(array, aux, mid+1, high, comparator);
        merge(array, aux, low, mid, high, comparator);
    }

    // sorting an array in ascending order using the natural order
    public static void sort(Comparable[] array){
        Comparable[] aux = new Comparable[array.length];
        sort(array, aux, 0, array.length-1);
    }


    // sorting an array in ascending order using a comparator
    public static <T> void sort(T[] array, Comparator<T> comparator){
        T[] aux = (T[]) new Object[array.length];
        sort(array, aux, 0, array.length-1, comparator);
    }
}
