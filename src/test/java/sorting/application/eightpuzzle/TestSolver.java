package sorting.application.eightpuzzle;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSolver {

    private static int[][] solvable1 = new int[][]{{1,2,3}, {4,5,6}, {7,8,0}};
    private static int[][] solvable2 = new int[][]{{0,1,3}, {4,2,5}, {7,8,6}};
    private static int[][] solvable3 = new int[][]{{8,1,3}, {4,0,2}, {7,6,5}};

    private static int[][] solvable4 = new int[][]{{1,3,0}, {4,2,5}, {7,8,6}};
    private static int[][] solvable5 = new int[][]{{2,3}, {1,0}};
    private static int[][] solvable6 = new int[][]{{5,1,3,4}, {13,2,7,8}, {6,10,11,12}, {14,9,0,15}};
    private static int[][] solvable7 = new int[][]{{2,5,1,3}, {9,6,12,4}, {10,14,8,0}, {13,11,15,7}};

    private static int[][] unsolvable1 = new int[][]{{3,2,4,8}, {1,6,0,12}, {5,10,7,11}, {9,13,14,15}}; //unsolvable

    private static int[][] unsolvable2 = new int[][]{{1,2,3}, {4,6,5}, {7,8,0}}; //unsolvable
    private static int[][] unsolvable3 = new int[][]{{2,1}, {3,0}}; // unsolvable


    public void testSolverSolvable(Board board){
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
        solver.printPath();
    }

    public void testSolverNotSolvable(Board board){
        Solver solver = new Solver(board);
        assertFalse(solver.isSolvable());
    }


    @Test
    public void testSolver1(){
        testSolverSolvable(new Board(solvable1));
    }

    @Test
    public void testSolver2(){
        testSolverSolvable(new Board(solvable2));
    }

    @Test
    public void testSolver3(){
        testSolverSolvable(new Board(solvable3));
    }

    @Test
    public void testSolver4(){
        testSolverSolvable(new Board(solvable4));
    }

    @Test
    public void testSolver5(){
        testSolverSolvable(new Board(solvable5));
    }

    @Test
    public void testSolver6(){
        testSolverSolvable(new Board(solvable6));
    }

    @Test
    public void testSolver7(){
        testSolverSolvable(new Board(solvable7));
    }

    @Test
    public void testSolverS1(){
        testSolverNotSolvable(new Board(unsolvable1));
    }

    @Test
    public void testSolverS2(){
        testSolverNotSolvable(new Board(unsolvable2));
    }

    @Test
    public void testSolverS3(){
        testSolverNotSolvable(new Board(unsolvable3));
    }


}
