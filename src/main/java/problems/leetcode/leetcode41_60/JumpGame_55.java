package problems.leetcode.leetcode41_60;

/*Given an array of non-negative integers, you are initially positioned at the first index of the array.

        Each element in the array represents your maximum jump length at that position.

        Determine if you are able to reach the last index.*/

//dynamic programming approach (canJump1)
//canReachEnd[i] = true if end index can be reached from index i, else false
//we are filling out the array from right to left -> this can be optimized (canJump)
// since we are only using the first one we can find and we do not need the array

//canJump1 is O(n*m) with m largest number in array
// others are O(n) and constant space

public class JumpGame_55 {

    public static boolean canJump(int[] nums){
        // left most index from which the last index can be reached
        int mostLeftCanReachIndex = nums.length-1;
        for(int i = nums.length-2; i >= 0; i--){
            // can we reach mostLeftCanReachIndex from current index i.e. are there enough jumps available
            if(i + nums[i] >= mostLeftCanReachIndex){
                mostLeftCanReachIndex = i;
            }
        }
        return mostLeftCanReachIndex==0;
    }

    public static boolean canJump2(int[] nums){
        if(nums.length == 0){
            return false;
        }
        int furthestReached = 0;
        for(int i = 0; i <= furthestReached; i++){
            if(i + nums[i] > furthestReached){
                furthestReached = i+ nums[i];
            }
            if(furthestReached >= nums.length-1){
                return true;
            }
        }
        return false;

    }

    public static boolean canJump1(int[] nums) {
        if(nums.length == 0){
            return false;
        }
        boolean[] canReachEnd = new boolean[nums.length];
        // last index can reach itself
        canReachEnd[nums.length-1] = true;

        for(int i = nums.length-2; i >=0; i--){
            // can we get directly from i to last?
            if(nums.length-1 - i <= nums[i]){
                canReachEnd[i] = true;
            }
            else{
                // can we reach an index (starting from i) that can reach to last?
                for(int jump = 1; jump <= nums[i]; jump++){
                    if(canReachEnd[i + jump]){
                        canReachEnd[i] = true;
                        break;
                    }
                }
            }
        }
        return canReachEnd[0];

    }
}
