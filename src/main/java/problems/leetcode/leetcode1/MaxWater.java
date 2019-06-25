package problems.leetcode.leetcode1;

/*
* Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
* n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
* Find two lines, which together with x-axis forms a container, such that the container contains the most water.

* Note: You may not slant the container and n is at least 2.
* */

//time complexity O(n)
// space complexity O(1)

public class MaxWater {

    public static int maxArea(int[] height) {

        int base = height.length -1;
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while(left <right){
            int current = base * Math.min(height[left], height[right]);
            if(current > maxArea)
            maxArea = current;

            // suppose there does exist a greater area
            // since we now have the largest possible base, the larger area should have a larger height than the
            // current one
            // the larger area cannot have as side the smallest of left and right
            if(height[left] < height[right]){
                left++;
            }
            else{
                right--;
            }
            base--;
        }

        return maxArea;

    }
}
