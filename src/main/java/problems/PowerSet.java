package problems;

/*
* print all subsets of a given set
*
* suppose we have an array of n (distinct) elements
* number of elements in power set: 2^n this corresponds one-to-one with binary numbers for 0 to 2^n-1
* every binary number corresponds to a subset
* eg array = {1,2,3,4}
* 0000 -> {}
* 0110 -> {2,3}
* 1111 -> {1,2,3,4}
* */

public class PowerSet {

    public static <T> void printSubSets(T[] array){
        int n = array.length;

        // go through every subset
        // 1<<n shift 1 n bits to the left: (number with nth bit from the left 1)
        // 1 -> 10000..0 = 2^n
        // every left shit is multiplying by two
        for(int i = 0; i < (1 << n); i++){

            System.out.print("{");

            for(int j = 0; j < n; j++){

                // 1 << j is number with jth bit from the left 1
                // bitwise and between 1 << j and i will be
                // 0 when jth bit in i is 0 --> array[j] has to be included
                // !=0 when jth bit in i 1 --> array[j] is not to be included
                if( ((1<<j) & i) > 0 ){
                    System.out.print(array[j] + " ");
                }


            }


            System.out.println("}");


        }
    }

    public static void main(String[] args){

        printSubSets(new Integer[]{1,2,3,4});

    }
}
