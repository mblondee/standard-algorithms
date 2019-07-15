package problems.leetcode.leetcode3;

/*The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

        Given an integer n, return all distinct solutions to the n-queens puzzle.

        Each solution contains a distinct board configuration of the n-queens' placement,
        where 'Q' and '.' both indicate a queen and an empty space respectively.*/


// if we have to put n queens in a nxn board, this means that each column should have at least one queen
// we will go through all columns and find the first row where we can put the queen, until the board is filled
// then we backtrack and try the next available row

import java.util.ArrayList;
import java.util.List;

public class NQueens_51 {

    public static List<List<String>> solveNQueens(int n) {

        List<List<String>> result = new ArrayList<>();

        Character[][] temp = new Character[n][n];
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < n; j++){
                temp[i][j] = '.';
            }
        }

        solve(temp, result, n, 0);



        return result;

    }

    public static void solve(Character[][] temp, List<List<String>> result, int total, int column){
        if(column == total){
            // board has been filled
            // add board as a list to result
            List<String> toAdd = new ArrayList<>();
            // row per row
            for(int i = 0; i < total; i++){
                StringBuffer str = new StringBuffer();
                for(int j = 0; j < total; j++){
                    str.append(temp[i][j]);
                }
                toAdd.add(str.toString());
            }
            result.add(toAdd);
            return; // backtrack
        }

        for(int row = 0; row < total; row++){
            if(isValid(temp, row, column)){
                temp[row][column] = 'Q';
                solve(temp, result, total, column+1);
                temp[row][column] = '.';
            }
        }


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
