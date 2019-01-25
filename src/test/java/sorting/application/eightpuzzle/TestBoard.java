package sorting.application.eightpuzzle;

import org.junit.Test;


import static org.junit.Assert.*;

public class TestBoard {

    private static int[][] blocks1 = new int[][]{{1,2,3}, {4,5,6}, {7,8,0}};
    private static int[][] blocks2 = new int[][]{{0,1,3}, {4,2,5}, {7,8,6}};
    private static int[][] blocks3 = new int[][]{{8,1,3}, {4,0,2}, {7,6,5}};

    private static int[][] blocks4 = new int[][]{{1,3,0}, {4,2,5}, {7,8,6}};

    @Test
    public void testBoard(){
        Board board1 = new Board(blocks1);
        Board board2 = new Board(blocks2);
        Board board3 = new Board(blocks3);
        assertEquals(0, board1.hamming());
        assertEquals(4, board2.hamming());
        assertEquals(5, board3.hamming());
        assertEquals(0, board1.manhattan());
        assertEquals(4, board2.manhattan());
        assertEquals(10, board3.manhattan());
        assertTrue(board1.isGoal());
        assertFalse(board2.isGoal());
        assertFalse(board3.isGoal());




    }

    @Test
    public void testPrint(){
        Board board1 = new Board(blocks1);
        Board board2 = new Board(blocks2);
        Board board3 = new Board(blocks3);
        System.out.println(board1.toString());
        System.out.println(board2.toString());
        System.out.println(board3.toString());
    }

    @Test
    public void testNeighbours(){
/*        Board board1 = new Board(blocks1);
        System.out.println("Neighbours of ");
        System.out.println(board1);
        System.out.println("are");
        Iterable<Board> neighbours1 = board1.neighbours();
        for(Board b: neighbours1){
            System.out.println(b);
        }
        System.out.println("-----");*/


        Board board4 = new Board(blocks4);
        System.out.println("Neighbours of ");
        System.out.println(board4);
        System.out.println("are");
        Iterable<Board> neighbours2 = board4.neighbours();
        for(Board b: neighbours2){
            System.out.println(b);
            System.out.println("manhanttan is " + b.manhattan());
        }
        System.out.println("-----");

/*        Board board3 = new Board(blocks3);
        System.out.println("Neighbours of ");
        System.out.println(board3);
        System.out.println("are");
        Iterable<Board> neighbours3 = board3.neighbours();
        for(Board b: neighbours3){
            System.out.println(b);
        }
        System.out.println("-----");*/

    }



    @Test
    public void testExchange(){
        Board board1 = new Board(blocks1);
        assertTrue(board1.exchangeTwoBlocks().equals(new Board(new int[][]{{8,2,3}, {4,5,6}, {7,1,0}})));
        assertTrue(board1.equals(new Board(new int[][]{{1,2,3}, {4,5,6}, {7,8,0}})));

        Board board4 = new Board(blocks4);
        assertTrue(board4.exchangeTwoBlocks().equals(new Board(new int[][]{{6,3,0}, {4,2,5}, {7,8,1}})));
        assertTrue(board4.equals(new Board (new int[][] {{1,3,0}, {4,2,5}, {7,8,6}})));
    }

    @Test
    public void testEquals(){
        Board board1 = new Board(blocks1);
        assertTrue(!board1.equals(null));
    }

/*    @Test
    public void testDistance(){
        Board board1 = new Board(blocks1);
        assertEquals(0, board1.manhattan(0,0));
        assertEquals(0, board1.manhattan(8,8));
        assertEquals(1, board1.manhattan(0,1));
        assertEquals(2, board1.manhattan(0,2));
        assertEquals(1, board1.manhattan(0,3));
        assertEquals(2, board1.manhattan(0,4));
        assertEquals(3, board1.manhattan(0,5));
        assertEquals(2, board1.manhattan(0,6));
        assertEquals(3, board1.manhattan(0,7));
        assertEquals(4, board1.manhattan(0,8));
        assertEquals(1, board1.manhattan(4,5));
    }*/

/*    @Test
    public void testExchange(){
        Board board1 = new Board(blocks1);
        board1.exchange(0,1);
        Board board1a = new Board(new int[][]{{2,1,3}, {4,5,6}, {7,8,0}});
        assertTrue(board1.equals(board1a));
        Board board1b = new Board(new int[][]{{2,1,3}, {4,5,7}, {6,8,0}});
        board1.exchange(5,6);
        assertTrue(board1.equals(board1b));
    }*/
}
