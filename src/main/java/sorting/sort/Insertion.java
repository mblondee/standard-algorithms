package sorting.sort;

// implementation of insertion sort
// for every index i compare array[i] with array[j] with j=i-1 to j=0, swap if smaller
// break if not smaller
// worst case ~N^2/2 compares and  ~N^2/2 swaps
// best case N-1 compares and 0 swaps
// average case ~N^2/4 compares and ~N^2/4 swaps

public class Insertion {

    //sorting an array in ascending order
    public static void sort(Comparable[] array){
        int length = array.length;
        for(int i = 0; i<length; i++){
            for(int j = i; j>0; j--){
                if (Sort.isStrictLess(array[j], array[j-1])){
                    Sort.swap(array, j, j-1);
                    }
                else{break;}
                }


        }
    }
}

