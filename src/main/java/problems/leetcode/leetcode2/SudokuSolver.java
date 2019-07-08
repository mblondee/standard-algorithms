package problems.leetcode.leetcode2;

/*
* Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.
*
* The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.
* */

public class SudokuSolver {
    public static void solveSudoku(char[][] board) {
        solve(board);
    }

    private static boolean solve(char[][] board){
        //search for an empty cell
        for(int row = 0; row < 9; row++){
            for(int column = 0; column < 9; column++){
                if(board[row][column] == '.'){
                    // find a number that can be put there
                    for(char i = '1'; i <= '9'; i++){
                        if(numberOK(board, row, column, i)){
                            board[row][column] = i;

                            //check if can be solved this way
                            if(solve(board)){
                                return true;
                            }
                            else{
                                // i was not a good guess, we remove it and try next
                                board[row][column] = '.';
                            }
                        }

                    }
                    //a number has not been found for this spot
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean numberOK(char[][] board, int row, int column, char numberChecking){
        // can a number be put in board[row][column]?
        // we will only call this method when board[row][column] == '.'
        return ! isInRow(board, row, numberChecking)  && ! isInColumn(board, column, numberChecking)
                && ! isInBlock(board, row, column, numberChecking);

    }

    private static boolean isInRow(char[][] board, int row, char numberChecking){
        for(int column = 0; column<9; column++){
            if(board[row][column] == numberChecking){
                return true;
            }
        }
        return false;
    }


    private static boolean isInColumn(char[][] board, int column, char numberChecking){
        for(int row = 0; row < 9; row++){
            if(board[row][column] == numberChecking){
                return true;
            }
        }
        return false;
    }

    private static boolean isInBlock(char[][] board, int row, int column, char numberChecking){
        // find corresponding rows and columns for block
        int i = row - row%3;
        int j = column - column%3;
        for(int r = i; r < i+3; r++){
            for(int s = j; s < j+3; s++){
                if(board[r][s] == numberChecking){
                    return true;
                }
            }
        }
        return false;
    }
}
