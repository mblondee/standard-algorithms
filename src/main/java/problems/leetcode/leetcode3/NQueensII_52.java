package problems.leetcode.leetcode3;

/*The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
        Given an integer n, return the number of distinct solutions to the n-queens puzzle.*/

import java.util.ArrayList;
import java.util.List;

public class NQueensII_52 {

    public static int totalNQueens(int n) {


        Character[][] temp = new Character[n][n];
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < n; j++){
                temp[i][j] = '.';
            }
        }

        int result = solve(temp,  n, 0);

        return result;

    }


    public static int solve(Character[][] temp, int total, int column){
        if(column == total){
            return 1;
        }

        int count = 0;

        for(int row = 0; row < total; row++){
            if(isValid(temp, row, column)){
                temp[row][column] = 'Q';
                count += solve(temp, total, column+1);
                temp[row][column] = '.';
            }
        }

        return count;


    }



    // check board to left from current (row, column)
    // the method will only be called when right part is still empty
    public static boolean isValid(Character[][] board, int row, int column){

        // check same row
        for(int j = 0; j <= column; j++){
            if(board[row][j] == 'Q'){
                return false;
            }
        }

        // check same column
        for(int i = 0; i <= row; i++){
            if(board[i][column] == 'Q'){
                return false;
            }
        }

        // check lower diagonal
        for(int i = row, j = column ; i < board.length && j >=0; i++, j--){
            if(board[i][j] == 'Q'){
                return false;
            }
        }


        // check upper diagonal
        for(int i = row, j = column; i >= 0 && j >=0; i--,j--){
            if(board[i][j] == 'Q'){
                return false;
            }
        }

        return true;

    }
}
