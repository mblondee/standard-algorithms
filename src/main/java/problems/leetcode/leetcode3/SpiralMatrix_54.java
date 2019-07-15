package problems.leetcode.leetcode3;

/*Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.*/

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix_54 {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        if(rows == 0){
            return result;
        }
        int columns = matrix[0].length;
        addBoundary(matrix,result, 0,rows-1, 0, columns-1);
        return result;
    }

    public static void addBoundary(int[][] matrix, List<Integer> result, int startRow, int endRow, int startColumn, int endColumn){
        if(startRow > endRow || startColumn > endColumn){
            return;
        }
        // add first row
        for(int i = startColumn; i <= endColumn; i++){
            result.add(matrix[startRow][i]);
        }
        // add last column
            for (int i = startRow+1; i <= endRow; i++) {
                result.add(matrix[i][endColumn]);
            }
        // add last row if different from first row
        if(startRow != endRow) {
            for (int i = endColumn-1; i >= startColumn; i--) {
                result.add(matrix[endRow][i]);
            }
        }
        // add first column if different from last column
        if(startColumn != endColumn) {
            for (int i = endRow-1; i > startRow; i--) {
                result.add(matrix[i][startColumn]);
            }
        }
        addBoundary(matrix, result, startRow+1, endRow-1, startColumn+1, endColumn-1);
    }
}
