package sorting.sort;

/*implementation of merge sort
*
* without copying into an auxiliary array every time merge() is called
* this is achieved by switching roles of the original array and the auxiliary array
*
* also before merging testing whether array is already in order and skip call to merge()
* */



import java.util.Comparator;

public class MergeSortImproved {

    // merge original[low .. mid] and original[mid+1 .. high]
    // result is in aux, original remains the same
    // using the natural order
    private static void merge(Comparable[] original, Comparable[] aux, int low, int mid, int high){

        // debug
/*        assert Sort.isSorted(original, low, mid);
        assert Sort.isSorted(original, mid+1, high);*/

        //merge original and put result in aux
        int i = low;
        int j = mid +1;
        for(int k = low; k <= high; k++){
            if(i > mid){
                aux[k] = original[j++];
            }
            else if(j > high){
                aux[k] = original[i++];
            }
            else if(Sort.isStrictLess(original[j], original[i])){
                aux[k] = original[j++];
            }
            else{
                aux[k] = original[i++];
            }
        }

        //debug
/*        assert Sort.isSorted(aux, low, high);*/


    }


    // merge original[low .. mid] and original[mid+1 .. high]
    // result is in aux, original remains the same
    // using a comparator
    private static <T> void merge(T[] original, T[] aux, int low, int mid, int high, Comparator<T> comparator){

        // debug
/*        assert Sort.isSorted(original, low, mid);
        assert Sort.isSorted(original, mid+1, high);*/

        //merge original and put result in aux
        int i = low;
        int j = mid +1;
        for(int k = low; k <= high; k++){
            if(i > mid){
                aux[k] = original[j++];
            }
            else if(j > high){
                aux[k] = original[i++];
            }
            else if(Sort.isStrictLess(original[j], original[i], comparator)){
                aux[k] = original[j++];
            }
            else{
                aux[k] = original[i++];
            }
        }

        //debug
        /*        assert Sort.isSorted(aux, low, high);*/


    }

    // sort arrayToSort using natural order
    private static void sort(Comparable[] auxArray, Comparable[] arrayToSort, int low, int high){
        if (high <= low){return;}

        int mid = low + (high-low)/2;
        sort(arrayToSort, auxArray, low, mid); // switch role of array and auxArray -> auxArray is partially sorted low-mid
        sort(arrayToSort, auxArray, mid + 1, high); // switch role of array and auxArray -> auxArray is partially sorted mid+1-high

        // checking if auxArray is already completely sorted low-high
        // if so copy auxArray low-high into arrayToSort and return
        if (!Sort.isStrictLarger(auxArray[mid], auxArray[mid +1])){
            for (int i = low; i <= high; i++){
                arrayToSort[i] = auxArray[i];

            }
            return;
        }


        merge(auxArray, arrayToSort, low, mid, high); // merge auxArray and put result in arrayToSort
    }

    // sort arrayToSort using a comparator
    private static <T> void sort(T[] auxArray, T[] arrayToSort, int low, int high, Comparator<T> comparator){
        if (high <= low){return;}

        int mid = low + (high-low)/2;
        sort(arrayToSort, auxArray, low, mid, comparator); // switch role of array and auxArray -> auxArray is partially sorted low-mid
        sort(arrayToSort, auxArray, mid + 1, high, comparator); // switch role of array and auxArray -> auxArray is partially sorted mid+1-high

        // checking if auxArray is already completely sorted low-high
        // if so copy auxArray low-high into arrayToSort and return
        if (!Sort.isStrictLarger(auxArray[mid], auxArray[mid +1], comparator)){
            for (int i = low; i <= high; i++){
                arrayToSort[i] = auxArray[i];

            }
            return;
        }


        merge(auxArray, arrayToSort, low, mid, high, comparator); // merge auxArray and put result in arrayToSort
    }

    // sort an array using the natural order
    public static void sort(Comparable[] array){
        Comparable[] aux = array.clone();
        sort(aux, array, 0, array.length -1);
    }

    // sort an array using a comparator
    public static <T> void sort(T[] array, Comparator<T> comparator){
        T[] aux = array.clone();
        sort(aux, array, 0, array.length -1, comparator);
    }
}
