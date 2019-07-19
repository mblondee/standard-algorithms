package problems.leetcode.leetcode61_80;

/*Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

        Note: You can only move either down or right at any point in time.*/

public class MinimumPathSum_64 {
    public static int minPathSum(int[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] minSum = new int[rows][columns]; // minSum[i][j] minimal sum of all paths from (0,0) to (i,j)

        minSum[0][0] = grid[0][0];

        // first row can only be reached by always going to the right
        for(int i = 1; i < columns; i++){
            minSum[0][i] = grid[0][i] + minSum[0][i-1];
        }

        // first column can only be reached by always going down
        for(int j = 1; j < rows; j++){
            minSum[j][0] = grid[j][0] + minSum[j-1][0];
        }

        for(int i = 1; i < rows; i++){
            for(int j = 1; j < columns; j++){
                minSum[i][j] = Math.min(minSum[i-1][j], minSum[i][j-1]) + grid[i][j];
            }
        }


        return minSum[rows-1][columns-1];

    }
}
