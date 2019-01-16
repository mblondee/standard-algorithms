package sorting.application.eightpuzzle;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestBoard {

    private static int[][] blocks1 = new int[][]{{1,2,3}, {4,5,6}, {7,8,0}};
    private static int[][] blocks2 = new int[][]{{0,1,3}, {4,2,5}, {7,8,6}};
    private static int[][] blocks3 = new int[][]{{8,1,3}, {4,0,2}, {7,6,5}};



    @Test
    public void testBoard(){
        Board board1 = new Board(blocks1);
        Board board2 = new Board(blocks2);
        Board board3 = new Board(blocks3);
        assertEquals(0, board1.hamming());
        assertEquals(4, board2.hamming());
        assertEquals(5, board3.hamming());
        assertEquals(0, board1.manhatten());
        assertEquals(4, board2.manhatten());
        assertEquals(10, board3.manhatten());
        assertTrue(board1.isGoal());
        assertFalse(board2.isGoal());
        assertFalse(board3.isGoal());


    }

/*    @Test
    public void testDistance(){
        Board board1 = new Board(blocks1);
        assertEquals(0, board1.manhatten(0,0));
        assertEquals(0, board1.manhatten(8,8));
        assertEquals(1, board1.manhatten(0,1));
        assertEquals(2, board1.manhatten(0,2));
        assertEquals(1, board1.manhatten(0,3));
        assertEquals(2, board1.manhatten(0,4));
        assertEquals(3, board1.manhatten(0,5));
        assertEquals(2, board1.manhatten(0,6));
        assertEquals(3, board1.manhatten(0,7));
        assertEquals(4, board1.manhatten(0,8));
        assertEquals(1, board1.manhatten(4,5));
    }*/

/*    @Test
    public void testExchange(){
        Board board1 = new Board(blocks1);
        board1.exchange(0,1);
        Board board1a = new Board(new int[][]{{2,1,3}, {4,5,6}, {7,8,0}});
        assertTrue(board1.isEqual(board1a));
        Board board1b = new Board(new int[][]{{2,1,3}, {4,5,7}, {6,8,0}});
        board1.exchange(5,6);
        assertTrue(board1.isEqual(board1b));
    }*/
}
