package strings.applications.Boggle;

/*
 * a Boggle solver that finds all valid words in a given Boggle board, using a given dictionary
 *
 *
 * based on assigment 4 from Coursera's Algorithm II course
 *
 * the characters in a Boggle board are uppercase letters A through Z (with QU represented as Q)
 * */

import strings.search.TernarySearchTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {

    private TernarySearchTable<Integer> dictionary;

    // for searching in the board
    private boolean[][] visited;
    private BoggleBoard board;
    private Set<String> validWords;

    /*
     * initialize
     * each word in the dictionary contains upper case letters A through Z
     * */

    public BoggleSolver(String[] dictionary){
        this.dictionary = new TernarySearchTable<>();
        for(String word : dictionary){
            this.dictionary.put(word, getScore(word));
        }

    }

    /*
     * returns all valid word in a board
     * */
    public Iterable<String> getAllValidWords(BoggleBoard board){
        this.board = board;
        visited = new boolean[board.rows()][board.cols()];
        validWords = new HashSet<>();

        for(int i = 0; i< board.rows(); i++){
            for(int j = 0; j < board.cols(); j++){
                dfs("", i, j);
            }
        }
        return validWords;

    }

    /*
     * if {@code word} is in the dictionary its score is returned
     * */
    public int scoreOf(String word){
        if(dictionary.contains(word)){
            return getScore(word);
        }
        return 0;

    }

    /*
     * current prefix of at least one word in the dictionary
     * count is current number of characters
     * */
    private void dfs(String prefix, int i, int j){
        char c = board.getLetter(i,j);
        if(c == 'Q'){
            prefix += "QU";
        }
        else{
            prefix += c;
        }

        visited[i][j] = true;

        // prefix is word in dictionary and length is at least 3
        if(dictionary.contains(prefix) && prefix.length()>=3){
            validWords.add(prefix);
        }

        // possibilities for next tile
        // if not visited yet and leads to a prefix

        // go one right
        if(j + 1 < board.cols() && !visited[i][j+1] && dictionary.isPrefix(prefix + board.getLetter(i, j+1))){
            dfs(prefix, i,j+1);
        }

        // go one left
        if(j -1 >= 0 && !visited[i][j-1] && dictionary.isPrefix(prefix + board.getLetter(i, j-1))){
            dfs(prefix, i, j-1);
        }

        // go one up
        if(i -1 >= 0 && !visited[i-1][j] && dictionary.isPrefix(prefix + board.getLetter(i-1, j))){
            dfs(prefix, i-1,j);
        }

        // go one down
        if(i + 1 < board.rows() && ! visited[i+1][j] && dictionary.isPrefix(prefix + board.getLetter(i+1, j))){
            dfs(prefix, i+1, j);
        }

        // go one diagonally up left
        if( i -1 >= 0 && j -1 >= 0 && ! visited[i-1][j-1] && dictionary.isPrefix(prefix + board.getLetter(i-1, j-1))){
            dfs(prefix, i-1,j-1);
        }

        // go one diagonally up right
        if(i -1 >= 0 && j+1<board.cols() && ! visited[i-1][j+1] && dictionary.isPrefix(prefix + board.getLetter(i-1, j+1))){
            dfs(prefix, i-1, j+1);
        }

        //go one diagonally down left
        if(i+1 < board.rows() && j - 1>= 0 && ! visited[i+1][j-1] && dictionary.isPrefix(prefix + board.getLetter(i+1, j-1)) ){
            dfs(prefix, i+1, j-1);
        }

        // go one diagonally down right
        if(i+1 <board.rows() && j+1 < board.cols() && ! visited[i+1][j+1]){
            dfs(prefix, i+1, j+1);
        }


        visited[i][j] = false;
    }


    private int getScore(String word){
        int wordLength = word.length();
        if(wordLength >= 8){
            return 11;
        }
        if(wordLength == 7){
            return 5;
        }
        if(wordLength == 6){
            return 3;
        }
        if(wordLength == 5){
            return 2;
        }
        if(wordLength >= 3){
            return 1;
        }
        return 0;
    }
}
