package sorting.sort;

/*implementation of merge sort
* to sort an array it is divided into two halves which are each sorted recursively and then merged
* between NlogN/2 and NlogN compares
* at most 6NlogN array accesses
* for arrays of length N
* using an auxiliary array to merge -> requires extra space proportional to N
* */

public class MergeSort {

    // merge array[low .. mid] and array[mid+1 .. high] using aux[low .. high]
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
            else if(Sort.isStrictLess(aux[i], aux[j])){
                array[k] = aux[i++];
            }
            else{
                array[k] = aux[j++];
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

    public static void sort(Comparable[] array){
        Comparable[] aux = new Comparable[array.length];
        sort(array, aux, 0, array.length-1);
    }

}
