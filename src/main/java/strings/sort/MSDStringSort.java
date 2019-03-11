package strings.sort;

/*
* sort strings that not necessarily have the same length
* using most-significant-digit-fist (MSD) string sort
*
* - strings are sorted by their first character
* - subarrays are recursively sorted by their first character
*
* end of string character -1: goes before any char value
* */

public class MSDStringSort {

    public static void sort(String[] array){
        int arrayLength = array.length;
        int radix = 256; // extended ASCII alphabet size
        String[] aux = new String[arrayLength];
        sort(array, aux, 0, arrayLength-1, 0, radix);
    }

    /*
    * sort array from array[low] to array[high] starting at array[digit]
    * */
    private static void sort(String[] array, String[] aux, int low, int high, int digit, int alphabet){
        if(high <= low){
            return;
        }

        // compute frequency counts
        int[] count = new int[alphabet+2]; // offset by 1 and extra char -1 (end of string)
        for(int i = low; i<=high; i++){
            // in array[i] get character at digit and increment count in corresponding index (offset 1 + extra char) in count
            count[charAt(array[i], digit) + 2]++;
        }

        // compute cumulates
        for(int r = 0; r<=alphabet; r++){
            count[r+1] += count[r];
        }

        // move data to aux
        for(int i = low; i<=high; i++){
            // in array[i] get character at digit and find in count where to put it
            // then increment in count such that it contains next index
            int c = charAt(array[i], digit);
            aux[low + count[c+1]] = array[i];
            count[c+1]++;

        }

        // copy back into array
        for(int i = low; i<=high; i++){
            array[i] = aux[i];
        }

        // recursively sort for each character
        for(int r = 0; r<alphabet; r++){
            // character r can be found in array
            // from low + count[r]
            // to low + count[r+1] - 1
            sort(array, aux, low + count[r], low + count[r+1]-1, digit+1, alphabet);
        }
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
