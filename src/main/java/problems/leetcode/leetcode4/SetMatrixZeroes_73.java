package problems.leetcode.leetcode4;

/*Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.*/



public class SetMatrixZeroes_73 {
    public static void setZeroes(int[][] matrix) {
        if(matrix.length == 0){
            return;
        }

        int rowZero = -1;
        int columnZero = -1;

        // find first element that is zero -> corresponding row and column are saved (rowZero and columnZero)
        // we will not yet put this row and column to 0 however we will continue to go through the array
        // if a zero is found in [i,j]
        // we set matrix[i][columnZero] = 0 and matrix[rowZero][j] = 0

        // when having passed through the matrix we use columnZero and rowZero to put zeroes where necessary
        // finally we update columnZero and rowZero and put all zeroes
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    // check is rowZero and columnZero already exist
                    if(rowZero == -1){
                        // set rowZero and columnZero to current
                        rowZero = i;
                        columnZero = j;
                    }
                    else{
                        // rowZero and columnZero already exist
                        matrix[rowZero][j] = 0;
                        matrix[i][columnZero] = 0;
                    }
                }
            }
        }

        // no zeroes
        if(rowZero == -1){
            return;
        }

        // which rows are zero rows?
        // skip rowZero because we still need to know the columns to be set to zero
        for(int i = 0; i < matrix.length; i++){
            if(i == rowZero){
                continue;
            }
            if(matrix[i][columnZero] == 0){
                for(int j = 0 ; j < matrix[0].length; j++){
                    matrix[i][j] = 0;
                }
            }
        }

        // which columns are zero rows?
        for(int j = 0; j < matrix[0].length; j++){
            if(matrix[rowZero][j] == 0){
                for(int i = 0; i < matrix.length; i++){
                    matrix[i][j] = 0;
                }
            }
        }

        // set rowsZero to zeroes
        for(int j = 0; j < matrix[0].length; j++){
            matrix[rowZero][j] = 0;
        }


    }
}
