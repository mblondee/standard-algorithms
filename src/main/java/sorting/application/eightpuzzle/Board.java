package sorting.application.eightpuzzle;

import sorting.priorityqueue.MinPQ;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

//TODO: hamming needed?

public class Board {

    private int dimension; //dimension of the board
    private int[] board; //array representing the board
    private int boardlength; // dimension*dimemsion


    /*
    * constructs a board from an n-by-n array of blocks
    * */
    public Board(int[][] blocks){
        dimension = blocks[0].length;
        boardlength = dimension*dimension;
        board = new int[boardlength];

        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                board[i*dimension + j] = blocks[i][j]; // block in row i and column j
            }
        }

    }

    /*
     * constructs a board from an array of blocks
     * used in neighbours()
     * */
    private Board(int[] blocks){
        boardlength = blocks.length;
        dimension = (int) Math.sqrt(boardlength);
        board = blocks;
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
        for(int i = 0; i < boardlength; i++){
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
        for(int i = 0; i<boardlength; i++){
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


    @Override
    public boolean equals(Object other){
        if (other == null){return false;}
        if(other.getClass() != this.getClass()){return false;}

        Board thatBoard = (Board) other;
        return Arrays.equals(this.board, thatBoard.board);
    }

    /*
    * is this board the goal board?
    * */

    public boolean isGoal(){
        for(int i = 0; i<boardlength; i++){
            if(board[i] != i + 1 && board[i] != 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        int index = 0;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                s.append(String.format("%2d ", board[index]));
                index++;
            }
            s.append("\n");
        }
        return s.toString();

    }


    /*
    * return all neighbours of the board: all boards that differ in one movement (up-down-left-right)
    * */
    public Iterable<Board> neighbours(){
        Stack<Board> neighbours = new Stack<>();
        // find index which contains the 0 (empty block)
        int index0 = 0;
        for (int i = 0; i < boardlength; i++){
            if(board[i] == 0){
                index0 = i;
                break;
            }
        }

        // move empty block up (only possible if index0 >= dimension)
        if(index0 >= dimension){
            Board above = new Board(Arrays.copyOf(board, boardlength));
            above.exchange(index0, index0-dimension);
            neighbours.push(above);
        }

        // move empty block down (only possible if index0 < boardlength - dimension)
        if (index0 < boardlength - dimension){
            Board under = new Board(Arrays.copyOf(board, boardlength));
            under.exchange(index0, index0+dimension);
            neighbours.push(under);
        }


        // move empty block left (only possible if index0 % dimension != 0)
        if (index0 % dimension != 0){
            Board left = new Board(Arrays.copyOf(board, boardlength));
            left.exchange(index0, index0-1);
            neighbours.push(left);
        }

        // move empty block right (only possible if index0 % dimension != dimension -1)
        if (index0 % dimension != dimension -1 ){
            Board right = new Board(Arrays.copyOf(board, boardlength));
            right.exchange(index0, index0+1);
            neighbours.push(right);
        }

        return neighbours;
    }


    /*
    * return a board that is obtained by exchanging 2 randoms blocks
    * */

    public Board exchangeTwoBlocks(){
        // find 2 blocks that are not 0
        int firstIndex = 0;
        int lastIndex = boardlength - 1;

        while (board[firstIndex] == 0){
            firstIndex++;
        }
        while(board[lastIndex] == 0){
            lastIndex--;

        }
        Board newBoard = new Board(Arrays.copyOf(board, boardlength));
        newBoard.exchange(firstIndex, lastIndex);
        return newBoard;
    }


}
