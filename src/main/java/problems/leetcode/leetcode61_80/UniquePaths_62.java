package problems.leetcode.leetcode61_80;

/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

        The robot can only move either down or right at any point in time.
        The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

        How many possible unique paths are there?*/

public class UniquePaths_62 {
    public static int uniquePaths(int m, int n) {
        int[][] reachable = new int[m][n]; // reachable[i][j] = unique paths from (0,0) to (i,j)
        reachable[0][0] = 1;
        // to the right can be reached only by going right
        for(int i = 1; i < n; i++){
            reachable[0][i] = 1;
        }
        // down can be reached only by going down
        for(int j = 1; j < m ; j++){
            reachable[j][0] = 1;
        }

        // reachable[i][j] = reachable[i-1][j] + reachable[i][j-1]
        for(int i = 1; i < m ; i++){
            for(int j = 1; j< n; j++){
                reachable[i][j] = reachable[i-1][j] + reachable[i][j-1];
            }
        }



        return reachable[m-1][n-1];

    }

}
