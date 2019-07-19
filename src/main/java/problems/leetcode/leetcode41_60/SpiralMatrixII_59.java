package problems.leetcode.leetcode41_60;

/*Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.*/

public class SpiralMatrixII_59 {
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        addBorder(result, 0,n-1, 0, n-1, 1, n*n);
        return result;

    }

    public static void addBorder(int[][] result, int rowStart, int rowEnd, int columnStart, int columEnd,  int count, int total){
        if(count > total ){
            return;
        }
        // add first row
        for(int j = columnStart; j <= columEnd; j++){
            result[rowStart][j] = count;
            count++;
        }
        // add last column
        for(int i = rowStart + 1; i <= rowEnd; i++){
            result[i][columEnd] = count;
            count++;
        }
        // add last row (if different from first)
        if(rowStart != rowEnd){
            for(int j = columEnd-1; j >= columnStart; j--){
                result[rowEnd][j] = count;
                count++;
            }
        }
        // add first column (if different from last)
        if(columnStart != columEnd){
            for(int i = rowEnd -1; i>rowStart; i--){
                result[i][columnStart] = count;
                count++;
            }
        }

        addBorder(result, rowStart+1, rowEnd-1, columnStart+1, columEnd-1, count, total);


    }

}
