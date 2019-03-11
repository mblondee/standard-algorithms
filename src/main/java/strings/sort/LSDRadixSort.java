package strings.sort;

/*
* algorithm to sort strings: LSD (least-significant-digit string sort)
* method of choice for string-sorting applications where all the keys are the same length
*
* based on key-indexed counting
*
* repeat for all characters (from right to left):
* - compute frequency counts
* - transform counts to start indices by computing cumulates
* - distribute data (use auxiliary array)
* - copy back into original array
*
* sort a String[] array of n extended ASCII strings (R=256) of same length
* sort an int[] array of n 32-bit integers, treating each integer as a sequence of 4 bytes (R=256)
*
* uses ~ 7 mn + 3 mr array accesses and extra space n + r
* to sort n items of length m in an r-character alphabet
* */

public class LSDRadixSort {


    /*
    * sort an array of fixed length string in ascending order
    * */

    public static void sort(String[] array, int length){
        int arrayLength = array.length;
        int radix = 256; // extended ASCII alphabet size
        String[] aux = new String[arrayLength];

        // sort by key-indexed counting from right to left
        for(int digit = length -1; digit >= 0; digit--){
            // compute frequency counts
            int[] count = new int[radix+1]; // offset by 1
            for(int i = 0; i<arrayLength; i++){
                // in array[i] get character at digit and increment count in corresponding index (offset 1) in count
                count[array[i].charAt(digit) + 1]++;
            }

            // compute cumulates
            for(int r = 0; r<radix; r++){
                count[r+1] += count[r];
            }

            // move data to aux
            for(int i = 0; i<arrayLength; i++){
                // in array[i] get character at digit and find in count where to put it
                // then increment in count such that it contains next index
                aux[count[array[i].charAt(digit)]++] = array[i];

            }

            // copy back into array
            for(int i = 0; i<arrayLength; i++){
                array[i] = aux[i];
            }
        }


    }

    /*
     * sort an array of 32-bit integers in ascending order
     * radix = 256, sorting 8 bits at a time from right to left
     * */

    public static void sort(int[] array){
        int bit = 32; //length in bits of each integer in array
        int bitsabyte = 8;
        // each integer is treated as 4 bytes, there are 8 bits per byte, so the alphabet of possible bytes has radix 2^8 = 256
        int radix =  1 << bitsabyte;// bitwise shift to the left of 1 by 8 -> 2^8 = 256
        int length = bit/bitsabyte; // length of all integers in terms of bytes

        int arrayLength = array.length;
        int aux[] = new int[arrayLength];


        for(int jump = 0; jump <length; jump++){
            // compute frequency counts
            int[] count = new int[radix+1];
            for(int i = 0; i< arrayLength; i++){
                // shift array[i] to the right to get the current byte + get rid of leading non zeros
                // * array[i] >> bitsabyte*jump does bitewise right shift until the byte we're interested in is in the first 8 bits
                // from the right
                // * radix - 1 = 255 = (1111 1111)_2
                // logical and (&) will make sure that all first 24 digits are zero and only the byte we're interested in remains
                int byteCurrent = (array[i] >> bitsabyte*jump) & (radix-1);
                count[byteCurrent + 1]++; // offset by one to compute the cumulative counts
            }
            // compute cumulative counts
            for(int r = 0; r< radix; r++){
                count[r+1] += count[r];
            }

            // most significant byte has the sign: starting with 1 negative, starting with 0 positive
            // negative ranges from byte 1000 0000 (128) to 1111 1111 (255)
            // positive ranges from byte 0000 0000 (0) to 0111 1111 (127)
            // if we would move the data back to aux we would get the positives sorted, the negatives as well
            // but the positives before the negatives hence we have to shift the positives + 127 and the negatives -127
            if(jump == length -1){
                int shiftpositives = count[radix] - count[radix/2];
                int shiftnegatives = count[radix/2];
                for(int r = 0; r<radix/2; r++){
                    count[r] += shiftpositives;
                }
                for(int r = radix/2; r<radix; r++){
                    count[r] -= shiftnegatives;
                }

            }


            //move data to aux
            for(int i = 0; i<arrayLength; i++){
                // get current byte as before
                int byteCurrent = (array[i] >> bitsabyte*jump) & (radix-1);
                aux[count[byteCurrent]++] = array[i];
            }



            //copy back to array
            for(int i =0;i<arrayLength; i++){
                array[i] = aux[i];
            }
        }


    }


}
