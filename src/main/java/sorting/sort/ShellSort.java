package sorting.sort;

// implementation of shellsort
// h-sorting the array using the increment sequence h = 3h+1, starting with 1 (1,4, 13, 40, ...)
// we start from the highest h that is still an index in the array
// for every index i>=h compare array[i] with array[i-h], array[i-2*h], ...
// repeat with next h until h=1


// todo is stable?

import java.util.Comparator;

public class ShellSort {


    //sorting an array in ascending order
    public static void sort(Comparable[] array){
        int length = array.length;
        int h_step = 1;
        // set h_step starting point
        while (h_step < length/3){
            h_step = 3*h_step + 1;
        }

        while(h_step>= 1){
            //h-sort the array
            for(int i = h_step; i<length; i++ ){
                for(int j = i; j>=h_step ; j-=h_step){
                    if (Sort.isStrictLess(array[j], array[j-h_step])){
                        Sort.swap(array, j , j-h_step);
                    }
                    else{
                        break;
                    }
                }
            }
            h_step = h_step/3;
        }
    }


    // sorting an array in ascending order using a comparator
    public static <T> void sort(T[] array, Comparator<T> comparator){
        int length = array.length;
        int h_step = 1;
        // set h_step starting point
        while (h_step < length/3){
            h_step = 3*h_step + 1;
        }

        while(h_step>= 1){
            //h-sort the array
            for(int i = h_step; i<length; i++ ){
                for(int j = i; j>=h_step ; j-=h_step){
                    if (Sort.isStrictLess(array[j], array[j-h_step], comparator)){
                        Sort.swap(array, j , j-h_step);
                    }
                    else{
                        break;
                    }
                }
            }
            h_step = h_step/3;
        }
    }
}
