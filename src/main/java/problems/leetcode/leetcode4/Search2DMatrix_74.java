package problems.leetcode.leetcode4;

/*Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

        Integers in each row are sorted from left to right.
        The first integer of each row is greater than the last integer of the previous row.*/

public class Search2DMatrix_74 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        // binary search


        if(matrix.length == 0){
            return false;
        }
        if(matrix[0].length == 0){
            return false;
        }

        int columns = matrix[0].length;

        int low = 0;
        int high = matrix.length-1;

        while(low <= high){
            int mid = low + (high-low)/2;

            if(matrix[mid][0] <= target && target <= matrix[mid][columns-1]){
                // target is in the matrix it will be in row mid
                for(int j = 0; j < columns; j++){
                    if(matrix[mid][j] == target){
                        return true;
                    }
                }
                return false;
            }


            if(target < matrix[mid][0]){
                // target has to be in first half
                high = mid-1;
            }
            else{
                // target has to be in second half
                low = mid+1;

            }
        }
        return false;

    }
}
