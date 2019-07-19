package problems.leetcode.leetcode41_60;

/*Given n non-negative integers representing an elevation map where the width of each bar is 1,
        compute how much water it is able to trap after raining.*/


// the amount of water that can be at spot i is as follows:
// x = min(highest-left, highest-right)
// if x - height[i] > 0, then x - height[i] is the amount of water that can be hold index i

public class TrappingRainWater_42 {
    public static int trap(int[] height) {
        int[] left = computeLeft(height);
        int[] right = computeRight(height);
        int result = 0;
        for(int i = 0; i < height.length; i ++){
            int water = Math.min(left[i], right[i]) - height[i];
            if(water>0){
                result += water;
            }
        }
        return result;
    }

    public static int[] computeLeft(int[] height){
        int[] max = new int[height.length];
        int currentMax = 0;
        for(int i = 0; i < height.length; i++){
            max[i] = currentMax;
            if(height[i] > currentMax){
                currentMax = height[i];
            }
        }
        return max;

    }

    public static int[] computeRight(int[] height){
        int[] max = new int[height.length];
        int currentMax = 0;
        for(int i = height.length-1; i >= 0; i--){
            max[i] = currentMax;
            if(height[i] > currentMax){
                currentMax = height[i];
            }
        }
        return max;

    }
}
