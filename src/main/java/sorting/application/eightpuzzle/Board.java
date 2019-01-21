package sorting.application.eightpuzzle;

import sorting.priorityqueue.MinPQ;

import java.util.Arrays;
import java.util.Comparator;

//TODO: hamming needed?

public class Board {

    private int dimension; //dimension of the board
    private int[] board; //array representing the board


    /*
    * constructs a board from an n-by-n array of blocks
    * */
    public Board(int[][] blocks){
        dimension = blocks[0].length;
        board = new int[dimension*dimension];

        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                board[i*dimension + j] = blocks[i][j]; // block in row i and column j
            }
        }

    }


    /*
    returns dimension of board
    */
    public int dimension(){
        return dimension;
    }

    /*
    returns number of blocks out of place
    if board[i-1] != i for i = 1 .... length-1
    */
    public int hamming(){
        int sum = 0;
        for(int i = 0; i < board.length; i++){
            if (board[i] != i + 1 && board[i] != 0){
                sum++;
            }
        }
        return sum;
    }

    /*
    * returns sum of distances between blocks and their goal index
    * */
    public int manhattan(){
        int sum = 0;
        for(int i = 0; i<board.length; i++){
            if(board[i] != i + 1 && board[i] != 0){
                // board[i] is not in place and should be in board[i]-1
                // find distance between i and board[i] - 1
                int distance = manhattan(i, board[i] -1);
                sum += distance;
            }
        }
        return sum;
    }

    /*returns manhattan distance between
    * two indices in board
    * */
    private int manhattan(int index1, int index2){
        int row1 = index1 / 3;
        int column1 = index1 % 3;
        int row2 = index2 / 3;
        int column2 = index2 % 3;
        int distanceRows = Math.abs(row1 - row2);
        int distanceColumns = Math.abs(column1 - column2);
        return distanceColumns + distanceRows;
    }

    /*
    * exchange two elements in the board
    * */
    private void exchange(int i, int j){
        int temp = board[i];
        board[i] = board[j];
        board[j] = temp;
    }

    public boolean isEqual(Object other){
        if (other == this){return true;}
        if (other == null){return false;}
        if(other.getClass() != this.getClass()){return false;}

        Board thatBoard = (Board) other;
        return Arrays.equals(this.board, thatBoard.board);
    }

    /*
    * is this board the goal board?
    * */

    public boolean isGoal(){
        for(int i = 0; i<board.length; i++){
            if(board[i] != i + 1 && board[i] != 0){
                return false;
            }
        }
        return true;
    }



}
