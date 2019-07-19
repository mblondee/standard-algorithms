package problems.leetcode.leetcode1_20;

/*
* Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
* Find all unique triplets in the array which gives the sum of zero.

Note:
*The solution set must not contain duplicate triplets.
* */


/*
explanation of algorithm used below

1) sort array
2) take el fixed in array
2a) check in rest of array if there is a pair (b,c) such that el + a + b = 0
3) repeat 2) for all el in array

* to find a pair in an ordered array that sums to a number (for simplicity 0):
* pointer i at index 0 and pointer j at last index
* if i+j < 0 => i++
* if i+j > 0 => j--
*
* why not i+j >0 => j++? (or i+j < 0 => i--)
* suppose start index for i was i_0 and for j j_0
* for l = 0 to k-1 we have i_l + j_1 < 0 (every step we had to move pointer i to the left)
* and then i_k + j_1 > 0
* it would not make sense to try i_k + j_0 because
* i_k + j_0 > i_k + j_1 > 0
* i_k + j_0 != 0
* */


/*
* time complexity O(n^2)
* */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> listResult = new ArrayList<>();

        for(int index = 0; index < nums.length; index++){
            if(index >0 && nums[index] == nums[index-1]){
                continue;
            }
            int a = nums[index];
            if(a > 0){
                // following numbers all positive
                break;
            }

            // looking for index i and j such that a + nums[i] + nums[j] = 0
            int i = index +1;
            int j = nums.length -1;
            while(i < j){
                // if this i is the same as the previous one we will not find new triples and we might
                // encounter duplicate triples
                if(a + nums[i] + nums[j] == 0){
                    List<Integer> triple = new ArrayList<>();
                    triple.add(a);
                    triple.add(nums[i]);
                    triple.add(nums[j]);
                    listResult.add(triple);
                    while(i<j && nums[i] == nums[i+1]) {
                        i++;
                    }
                    i++;
                    while( i < j && nums[j] == nums[j-1]) {
                        j--;
                    }
                    j--;
                }
                else if(a + nums[i] + nums[j] > 0){
                    while( i < j && nums[j] == nums[j-1]) {
                        j--;
                    }
                    j--;
                }
                else{
                    while(i<j && nums[i] == nums[i+1]) {
                        i++;
                    }
                    i++;
                }
            }

        }


        return listResult;

    }


}
