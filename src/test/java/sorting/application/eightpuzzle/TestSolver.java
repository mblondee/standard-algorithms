package sorting.application.eightpuzzle;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSolver {

    private static int[][] blocks1 = new int[][]{{1,2,3}, {4,5,6}, {7,8,0}};
    private static int[][] blocks2 = new int[][]{{0,1,3}, {4,2,5}, {7,8,6}};
    private static int[][] blocks3 = new int[][]{{8,1,3}, {4,0,2}, {7,6,5}};

    private static int[][] blocks4 = new int[][]{{1,3,0}, {4,2,5}, {7,8,6}};

    private static int[][] blocks5 = new int[][]{{3,2,4,8}, {1,6,0,12}, {5,10,7,11}, {9,13,14,15}}; //unsolvable

    private static int[][] blocks6 = new int[][]{{1,2,3}, {4,6,5}, {7,8,0}}; //unsolvable
    private static int[][] blocks7 = new int[][]{{2,1}, {3,0}}; // unsolvable

    @Test
    public void testSolver2(){
        Board board = new Board(blocks2);
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());

    }

    @Test
    public void testSolver3(){
        Board board = new Board(blocks3);
        Solver solver = new Solver(board);

    }

    @Test
    public void testSolver5(){
        Board board = new Board(blocks5);
        Board otherBoard = board.exchangeTwoBlocks();
        Solver solver = new Solver(otherBoard);

    }

    @Test
    public void testSolver6(){
        Board board = new Board(blocks6);
        Solver solver = new Solver(board);

    }

    @Test
    public void testSolver7(){
        Board board = new Board(blocks7);
        Board otherBoard = board.exchangeTwoBlocks();
        Solver solver = new Solver(board);

    }
}
