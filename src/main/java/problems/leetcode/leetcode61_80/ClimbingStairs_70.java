package problems.leetcode.leetcode61_80;

/*You are climbing a stair case. It takes n steps to reach to the top.

        Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

        Note: Given n will be a positive integer.*/

public class ClimbingStairs_70 {
    // dynamic programming without array
    public static int climbStairs(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int count = 2;
        int prev = 1;
        int next = 2;
        // n will not be 0!
        while(count < n){
            int temp = next;
            next = next + prev;
            prev = temp;
            count++;
        }

        return next;


    }

    // dynamic programming with array
    public static int climbStairsDP(int n) {
        int[] reachable = new int[n+1];// reachable[i] = in how many distinct ways can we reach step i
        reachable[1] = 1; // one step from 0 to 1
        reachable[2] = 2; // one step from 0 to 2 or 1 step from 0 to 1 and 1 step from 1 to 2

        // reachable[i] = reachable[i-2] + reachable[i-1] (either you take 1 step from step i-1 or 2 steps from i-2)
        for(int i = 3; i <= n; i++){
            reachable[i] = reachable[i-1] + reachable[i-2];
        }

        return reachable[n];
    }
}
