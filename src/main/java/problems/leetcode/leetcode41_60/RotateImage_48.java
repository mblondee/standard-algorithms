package problems.leetcode.leetcode41_60;

/*
You are given an n x n 2D matrix representing an image.

        Rotate the image by 90 degrees (clockwise).

        Note:

        You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
        DO NOT allocate another 2D matrix and do the rotation.
*/


// combination of reverse rows + transpose matrix
/*
1  2  3  4
5  6  7  8
9  10 11 12
13 14 15 16

reverse lines:
13 14 15 16
9  10 11 12
5  6  7  8
1  2  3  4

transpose
13 9  5 1
14 10 6 2
15 11 7 3
16 12 8 4
*/


public class RotateImage_48 {
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // reverse rows
        // swap row i with row n-1-i
        for(int row = 0; row < n/2 ; row++){
            for(int column = 0; column < n; column++){
                int toSwap = matrix[row][column];
                matrix[row][column] = matrix[n-1-row][column];
                matrix[n-1-row][column] = toSwap;
            }
        }

        // transpose
        for(int row = 0; row < n; row++){
            for(int column = row+1; column < n; column++){
                int toSwap = matrix[row][column];
                matrix[row][column] = matrix[column][row];
                matrix[column][row] = toSwap;
            }
        }





    }
}
