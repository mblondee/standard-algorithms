package strings.sort;

import sorting.sort.Sort;

public class Quick3string {

    public static void sort(String[] array){
        sort(array, 0, array.length-1, 0);
    }

    private static void sort(String[] array, int low, int high, int digit) {
        if (high <= low) {
            return;
        }

        // pivot will be put in place
        int pivot = charAt(array[low], digit);
        int strictLess = low; // array[i] < pivot when i <strictLower and array[strictLess] equals pivot
        int strictGreater = high; // array[i] > pivot when i > strictLower
        int index = low + 1; // index traversing the array once

        while(index <= strictGreater){
            int current = charAt(array[index], digit);
            if(current < pivot){
                Sort.swap(array, strictLess, index);
                strictLess++;
                index++;
            }
            else if(current > pivot){
                Sort.swap(array, strictGreater, index);
                strictGreater--;
            }
            else{
                index++;
            }
        }

        // first batch has to be sorted on same digit
        sort(array, low, strictLess-1, digit);
        // if pivot>=0  middle batch has to be sorted on next digit
        if(pivot >= 0){
            sort(array, strictLess, strictGreater, digit+1);
        }
        // third batch has to be sorted on same digit
        sort(array, strictGreater+1, high, digit);
    }

    /*
     * return d-th char in s, -1 if d is length of the string
     * */
    private static int charAt(String s, int digit){
        if(digit == s.length()){
            return -1;
        }
        return s.charAt(digit);
    }
}
