package problems.leetcode.leetcode3;
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

        Each element in the array represents your maximum jump length at that position.

        Your goal is to reach the last index in the minimum number of jumps.

        You can assume that you can always reach the last index.*/

import java.util.LinkedList;

public class JumpGameII {

    public static int jump1(int[] nums) {

        // end can never be reached
        if(nums.length > 1 && nums[0] == 0){
            return Integer.MAX_VALUE;
        }

        int[] minimumJumpsToReachIndex = new int[nums.length];

        minimumJumpsToReachIndex[0] = 0;
        for(int i = 1; i < nums.length; i++){
            minimumJumpsToReachIndex[i] = Integer.MAX_VALUE;
        }


        for(int i = 0; i < nums.length-1; i++) {
            // where can we get from here -> steps nums[i]
            for (int step = 1; step <= nums[i]; step++) {
                if (i + step < nums.length) {
                    minimumJumpsToReachIndex[i + step] = Math.min(minimumJumpsToReachIndex[i + step], minimumJumpsToReachIndex[i] + 1);
                }
            }
        }

        return minimumJumpsToReachIndex[nums.length -1];

    }

    //dijkstra solution
    public static int jump2(int[] nums) {
        if(nums.length <= 1){
            return 0;
        }

        // keep track of where to go next
        LinkedList<Integer> queue = new LinkedList<>();

        int[] minimumJumpsToReachIndex = new int[nums.length];

        minimumJumpsToReachIndex[0] = 0;
        for(int i = 1; i < nums.length; i++){
            minimumJumpsToReachIndex[i] = Integer.MAX_VALUE;
        }

        queue.add(0);
        int visitedUntil = 0;

        while(! queue.isEmpty()){
            int start = queue.pop();
            int distance = minimumJumpsToReachIndex[start] + 1; // distance from 0 to neighbours through start
            for(int step = 1; step <= nums[start]; step++){
                if(start + step > visitedUntil && start + step < nums.length){
                    // update shortest distance
                    if(distance < minimumJumpsToReachIndex[start+step]){
                        minimumJumpsToReachIndex[start + step] = distance;
                    }
                    // add to queue
                    queue.add(start+step);
                }
            }
            visitedUntil = Math.max(visitedUntil, start+nums[start]);
        }
        return minimumJumpsToReachIndex[nums.length -1];
    }


    // we begin from 0, i.e. range [0,0]
    // which range can be reached from this range
    // update steps with every iteration
    // stop when last index is in range
    public static int jump(int[] nums) {
        int startRange = 0;
        int endRange = 0;
        int step = 0;

        while(nums.length-1 > endRange){
            int min = startRange;
            int max = endRange;
            for(int i = min; i <= max; i++){
                // we can get from i to which range?
                // from i we can go nums[i] further => max(endRange, i+ nums[i])
                endRange = Math.max(endRange, i+ nums[i]);
                startRange = min + 1;
            }
            step++;
        }


        return step;

    }
}
