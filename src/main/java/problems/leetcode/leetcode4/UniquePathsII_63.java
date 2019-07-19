package problems.leetcode.leetcode4;

/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

        The robot can only move either down or right at any point in time.
        The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

        Now consider if some obstacles are added to the grids. How many unique paths would there be?*/

public class UniquePathsII_63 {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length == 0){
            return 0;
        }
        int rows = obstacleGrid.length;
        int columns = obstacleGrid[0].length;
        int[][] reachable = new int[rows][columns];
        if(obstacleGrid[0][0] != 1){
            reachable[0][0] = 1;
        }

        // first row only reachable from the left
        for(int i = 0; i < columns ; i++){
            if(obstacleGrid[0][i] == 1){
                // not reachable (default is 0, already filled in) and all others more the right will not be reachable
                break;
            }
            else{
                reachable[0][i] = 1;
            }
        }

        // first columns only reachable from above
        for(int j = 0; j < rows; j++){
            if(obstacleGrid[j][0] == 1){
                break;
            }
            else{
                reachable[j][0] = 1;
            }
        }

        // if there is no obstacle: reachable[i][j] = reachable[i-1][j] + reachable[i][j-1]
        for(int i = 1; i < rows; i++){
            for(int j = 1; j < columns; j++){
                if(obstacleGrid[i][j] == 0){
                    reachable[i][j] = reachable[i-1][j] + reachable[i][j-1];
                }
            }
        }



        return reachable[rows-1][columns-1];
    }


}
