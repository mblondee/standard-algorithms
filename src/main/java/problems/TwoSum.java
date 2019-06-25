package problems;

/*
* Given an array of n integers and an integer k. Is there a pair of elements in the array that sums exactly to k?
* */

import java.util.Arrays;
import java.util.HashSet;

public class TwoSum {
    /*
    * option 1: hashing
    * keep a hash table
    * check if k-a[i] is in the hash table, if so return true else add a[i] to hash table
    *
    * hashset contains and add: constant time
    * we go through array at most once -> O(n)
    * space complexity: O(n) (copy into hashset)
    * */
    public static boolean sumsTo1(int[] array, int k){
        HashSet<Integer> values = new HashSet<>();
        for(int i = 0; i<array.length; i++){
            if(values.contains(k-array[i])){
                return true;
            }
            values.add(array[i]);
        }
        return false;
    }


    /*
    * option2: sorting and binary search
    * array must be sorted before doing binary search
    *
    * sorting (quicksort) the array takes O(n log n) time
    * binary search O(log n) for each call -> O(n log n)
    *
    * recursive in place quicksort uses O(log n) space on average for the call stack
    * */
    public static boolean sumsTo2(int[] array, int k){
        Arrays.sort(array);
        for(int i = 0; i < array.length; i++){
            int candidateIndex = Arrays.binarySearch(array, k-array[i]);
            if(candidateIndex >= 0){
                // binary search gives no guarantee about which index will be returned when there are two candidates
                // however the array is sorted, if there is another index with key k-array[i] it has to be one index lower or higher
                if(candidateIndex == i){
                    return i>0 && array[i-1] == array[i] ||  i< array.length -1 && array[i+1] == array[i];
                }
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args){
        System.out.println(sumsTo1(new int[]{}, 4));
        System.out.println(sumsTo1(new int[]{4}, 4));
        System.out.println(sumsTo1(new int[]{2,2}, 4));
        System.out.println(sumsTo1(new int[]{-1, 2,5,2,-10}, 4));
        System.out.println(sumsTo1(new int[]{1,3}, 4));

        System.out.println("----");

        System.out.println(sumsTo2(new int[]{}, 4));
        System.out.println(sumsTo2(new int[]{4}, 4));
        System.out.println(sumsTo2(new int[]{2,2}, 4));
        System.out.println(sumsTo2(new int[]{-1, 2,5,2,-10}, 4));
        System.out.println(sumsTo2(new int[]{1,3}, 4));
    }

}
