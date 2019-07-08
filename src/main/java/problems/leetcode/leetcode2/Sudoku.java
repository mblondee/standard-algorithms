package problems.leetcode.leetcode2;

/*Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated
according to the following rules:

        Each row must contain the digits 1-9 without repetition.
        Each column must contain the digits 1-9 without repetition.
        Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

        The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
        */

import java.util.HashSet;
import java.util.Set;

public class Sudoku {
    public static boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++){
            if(! rowOk(board, i) || ! columnOK(board,i)){
                return false;
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(! blockOK(board,i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean rowOk(char[][] board, int row){
        Set<Character> seen = new HashSet();
        for(int j = 0; j < 9; j++){
            if(board[row][j] == '.'){
                continue;
            }
            if(seen.contains(board[row][j])){
                return false;
            }
            seen.add(board[row][j]);
        }
        return true;
    }

    private static boolean columnOK(char[][] board, int column){
        Set<Character> seen = new HashSet<>();
        for(int i = 0; i < 9; i++){
            if(board[i][column] == '.'){
                continue;
            }
            if(seen.contains(board[i][column])){
                return false;
            }
            seen.add(board[i][column]);
        }
        return true;
    }

    private static boolean blockOK(char[][] board, int row, int column){
        Set<Character> seen = new HashSet<>();
        // row 0-1-2, column 0-1-2
        for(int i = row*3; i<row*3 + 3; i++){
            for(int j = column*3; j <column*3+3; j++){
                if(board[i][j] == '.'){
                    continue;
                }
                if(seen.contains(board[i][j])){
                    return false;
                }
                seen.add(board[i][j]);
            }
        }
        return true;
    }
}
