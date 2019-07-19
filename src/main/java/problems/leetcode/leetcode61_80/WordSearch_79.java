package problems.leetcode.leetcode61_80;

/*Given a 2D board and a word, find if the word exists in the grid.

        The word can be constructed from letters of sequentially adjacent cell,
        where "adjacent" cells are those horizontally or vertically neighboring.
        The same letter cell may not be used more than once.*/

public class WordSearch_79 {

    public static boolean exist(char[][] board, String word) {
        if(board.length == 0 || word.length() == 0){
            return false;
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0) && helper(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean helper(char[][] board, String word, int row, int column, int wordIndex){

        if(board[row][column] != word.charAt(wordIndex)){
            return false;
        }

        // change board[row][column] to '*' in order not to use twice
        board[row][column] = '*';

        // do we have look for more characters?
        // we have found the word
        if(wordIndex+1 == word.length()){
            // change board[row][column] to original char
            board[row][column] = word.charAt(wordIndex);
            return true;
        }


        // look for next chars in board
        // go left
        if(column > 0 && helper(board, word, row, column-1, wordIndex+1)){
            // change board[row][column] to original char
            board[row][column] = word.charAt(wordIndex);
            return true;
        }
        // go right
        if(column < board[0].length-1 && helper(board, word, row, column+1, wordIndex+1)){
            // change board[row][column] to original char
            board[row][column] = word.charAt(wordIndex);
            return true;
        }
        // go up
        if(row > 0 && helper(board, word, row-1, column, wordIndex+1)){
            // change board[row][column] to original char
            board[row][column] = word.charAt(wordIndex);
            return true;
        }
        // go down
        if(row < board.length-1 && helper(board, word, row+1, column, wordIndex +1)){
            // change board[row][column] to original char
            board[row][column] = word.charAt(wordIndex);
            return true;
        }
        // change board[row][column] to original char
        board[row][column] = word.charAt(wordIndex);
        return false;
    }
}
