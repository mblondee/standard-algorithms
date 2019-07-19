package problems.leetcode;

import org.junit.Test;
import problems.leetcode.leetcode61_80.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LeetCode4 {

    @Test
    public void RotateListTest(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode head1 = new ListNode(5);
        head1.next = new ListNode(1);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(4);

        ListNode result1 = RotateList_61.rotateRight(head,1);
        ListNode p1 = head1;
        ListNode pp1  = result1;
        while(pp1 != null){
            assertEquals(pp1.val, p1.val);
            pp1 = pp1.next;
            p1 = p1.next;
        }

        ListNode head11 = new ListNode(1);
        head11.next = new ListNode(2);
        head11.next.next = new ListNode(3);
        head11.next.next.next = new ListNode(4);
        head11.next.next.next.next = new ListNode(5);
        ListNode result6 = RotateList_61.rotateRight(head11,6);
        ListNode p6 = head1;
        ListNode pp6  = result6;
        while(pp6 != null){
            assertEquals(pp6.val, p6.val);
            pp6 = pp6.next;
            p6 = p6.next;
        }


    }

    @Test
    public void testUniquePaths(){
        assertEquals(3, UniquePaths_62.uniquePaths(3,2));
        assertEquals(28, UniquePaths_62.uniquePaths(7,3));
    }

    @Test
    public void testUniquePathsII(){
        int[][] input1 = {{0,0,0}, {0,1,0}, {0,0,0}};
        assertEquals(2, UniquePathsII_63.uniquePathsWithObstacles(input1));
    }

    @Test
    public void testMinimumPath(){
        int[][] input1 = {{1,3,1}, {1,5,1}, {4,2,1}};
        assertEquals(7, MinimumPathSum_64.minPathSum(input1));
    }

    @Test
    public void testValidNumber(){
        assertTrue(ValidNumber_65.isNumber("0"));
        assertTrue(ValidNumber_65.isNumber(" 0.1"));
        assertFalse(ValidNumber_65.isNumber("abc"));
        assertFalse(ValidNumber_65.isNumber("1 a"));
        assertTrue(ValidNumber_65.isNumber("2e10"));
        assertTrue(ValidNumber_65.isNumber(" -90e3  "));
        assertFalse(ValidNumber_65.isNumber(" 1e"));
        assertTrue(ValidNumber_65.isNumber(" 6e-1 "));
        assertFalse(ValidNumber_65.isNumber(" 99e2.5  "));
        assertTrue(ValidNumber_65.isNumber("53.5e93"));
        assertFalse(ValidNumber_65.isNumber(" --6"));
        assertFalse(ValidNumber_65.isNumber("-+3"));
        assertFalse(ValidNumber_65.isNumber("95a54e53"));
        assertFalse(ValidNumber_65.isNumber(" "));
        assertFalse(ValidNumber_65.isNumber(" ."));
        assertTrue(ValidNumber_65.isNumber("3."));
        assertTrue(ValidNumber_65.isNumber("-1"));
        assertTrue(ValidNumber_65.isNumber("+.8"));
    }

    @Test
    public void testPlusOne(){
        assertArrayEquals(new int[]{1,2,4}, PlusOne_66.plusOne(new int[]{1,2,3}));
        assertArrayEquals(new int[]{1,3,0}, PlusOne_66.plusOne(new int[]{1,2,9}));
        assertArrayEquals(new int[]{1,0,0,0}, PlusOne_66.plusOne(new int[]{9,9,9}));
    }

    @Test
    public void testAddBinary(){
        assertEquals("1010", AddBinary_67.addBinary("111", "11"));
        assertEquals("1", AddBinary_67.addBinary("1", "0"));

    }

    @Test
    public void testTextJustification(){
        List<String> test1 = new ArrayList<>();
        String str1 = "hello ik ben m    ";
        String[] words1 = {"hello", "ik", "ben", "m"};
        assertEquals(18, str1.length());
        test1.add("hello ik ben m    ");
        List<String> result1 = TextJustification_68.fullJustify(words1, 18);
        assertEquals(1, result1.size());
        assertEquals(test1.get(0).length(), result1.get(0).length());
        assertEquals(test1.get(0), result1.get(0));


        List<String> test2 = new ArrayList<>();
        String str21 = "This    is    an";
        assertEquals(16, str21.length());
        String str22 = "example  of text";
        assertEquals(16, str22.length());
        String str23 = "justification.  ";
        assertEquals(16, str23.length());
        test2.add(str21);
        test2.add(str22);
        test2.add(str23);
        String[] words2 = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> result2 = TextJustification_68.fullJustify(words2, 16);
        assertEquals(3, result2.size());
        assertEquals(16, result2.get(0).length());
        assertEquals(16, result2.get(1).length());
        assertEquals(16, result2.get(2).length());
        assertEquals(str21, result2.get(0));
        assertEquals(str22, result2.get(1));
        assertEquals(str23, result2.get(2));

        String[] words3 = {"What","must","be","acknowledgment","shall","be"};
        List<String> result3 = TextJustification_68.fullJustify(words3, 16);
        assertEquals(3, result3.size());
        assertEquals(16, result3.get(0).length());
        assertEquals(16, result3.get(1).length());
        assertEquals(16, result3.get(2).length());
        assertEquals("What   must   be", result3.get(0));
        assertEquals("acknowledgment  ", result3.get(1));
        assertEquals("shall be        ", result3.get(2));

    }

    @Test
    public void testSqrt(){
        assertEquals(2, Sqrt_69.mySqrt(4));
        assertEquals(2, Sqrt_69.mySqrt(8));
    }

    @Test
    public void testClimbingStairs(){
        assertEquals(2, ClimbingStairs_70.climbStairs(2));
        assertEquals(3, ClimbingStairs_70.climbStairs(3));
    }

    @Test
    public void testSimplifyPath(){
        assertEquals("/home",SimplifyPath_71.simplifyPath("/home/"));
        assertEquals("/",SimplifyPath_71.simplifyPath("/../"));
        assertEquals("/home/foo",SimplifyPath_71.simplifyPath("/home//foo/"));
        assertEquals("/c",SimplifyPath_71.simplifyPath("/a/./b/../../c/"));
        assertEquals("/c",SimplifyPath_71.simplifyPath("/a/../../b/../c//.//"));
        assertEquals("/a/b/c",SimplifyPath_71.simplifyPath("/a//b////c/d//././/.."));
    }

    @Test
    public void testHardDistance(){
        assertEquals(3, HardDistance_72.minDistance("horse", "ros"));
        assertEquals(5, HardDistance_72.minDistance("intention", "execution"));
    }

    @Test
    public void testSetMatrixZeroes(){
        int[][] matrix1 = {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        int[][] result1 = {{0,0,0,0}, {0,4,5,0}, {0,3,1,0}};
        SetMatrixZeroes_73.setZeroes(matrix1);
        for(int i = 0; i < matrix1.length; i++){
            assertArrayEquals(result1[i], matrix1[i]);
        }

        int[][] matrix2 = {{1,0}};
        int[][] result2 = {{0,0}};
        SetMatrixZeroes_73.setZeroes(matrix2);
    }

    @Test
    public void testSearch2DMatrix(){
        int[][] matrix = new int[][]{{1,3,5,7}, {10,11,16,20}, {23,30,34,50}};
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 1));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 3));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 5));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 7));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 10));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 11));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 16));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 20));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 23));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 30));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 34));
        assertTrue( Search2DMatrix_74.searchMatrix(matrix, 50));

        assertFalse(Search2DMatrix_74.searchMatrix(matrix, 2));
        assertFalse(Search2DMatrix_74.searchMatrix(matrix, 8));
        assertFalse(Search2DMatrix_74.searchMatrix(matrix, 15));
        assertFalse(Search2DMatrix_74.searchMatrix(matrix, 21));
        assertFalse(Search2DMatrix_74.searchMatrix(matrix, 33));
        assertFalse(Search2DMatrix_74.searchMatrix(matrix, 100));

        int[][] matrix1 = new int[][]{{1,5,8,9}};
        assertTrue(Search2DMatrix_74.searchMatrix(matrix1, 5));
        assertFalse(Search2DMatrix_74.searchMatrix(matrix1, 10));
        assertFalse(Search2DMatrix_74.searchMatrix(matrix1, 7));

        int[][] matrix3 = new int[][]{{1}, {5}, {9}};
        assertTrue(Search2DMatrix_74.searchMatrix(matrix3, 1));
        assertTrue(Search2DMatrix_74.searchMatrix(matrix3, 5));
        assertTrue(Search2DMatrix_74.searchMatrix(matrix3, 9));

        int[][] matrix2 = {{}};
        assertFalse(Search2DMatrix_74.searchMatrix(matrix2, 1));
    }

    @Test
    public void testSortColors(){
        int[] matrix = new int[]{2,0,2,1,1,0};
        SortColors_75.sortColors(matrix);
        assertArrayEquals(new int[]{0,0,1,1,2,2}, matrix);
    }

    @Test
    public void testMinimumWindowSubstring(){
        assertEquals("BANC", MinimumWindowSubstring_76.minWindow("ADOBECODEBANC", "ABC"));
        assertEquals("A", MinimumWindowSubstring_76.minWindow("ABAAA", "A"));
        assertEquals("AA", MinimumWindowSubstring_76.minWindow("AA", "AA"));
        assertEquals("B", MinimumWindowSubstring_76.minWindow("AB", "B"));
        assertEquals("BA", MinimumWindowSubstring_76.minWindow("BBA", "AB"));
    }

    @Test
    public void testCombinations(){
        List<List<Integer>> result1 = Combinations_77.combine(4,2);
        assertEquals(6, result1.size());
    }

    @Test
    public void testSubsets(){
        assertEquals(8, 1<<3);
        assertEquals(1, 1<<0 & 5);

        assertEquals(8, Subsets_78.subsets(new int[]{1,2,3}).size());
        assertEquals(0, Subsets_78.subsets(new int[]{}).size());
        assertEquals(2, Subsets_78.subsets(new int[]{0}).size());
    }

    @Test
    public void testWordSearch(){
        char[][] board1 = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(WordSearch_79.exist(board1,"SEE"));
        assertTrue(WordSearch_79.exist(board1,"ABCCED"));
        assertFalse(WordSearch_79.exist(board1, "ABCB"));
        assertFalse(WordSearch_79.exist(board1, ""));
    }

    @Test
    public void testRemoveDuplicatesII(){
        assertEquals(5, RemoveDuplicatesSortedArrayII_80.removeDuplicates(new int[]{1,1,1,2,2,3}));
        assertEquals(5, RemoveDuplicatesSortedArrayII_80.removeDuplicates(new int[]{1,1,2,2,3}));
        assertEquals(0, RemoveDuplicatesSortedArrayII_80.removeDuplicates(new int[]{}));
        assertEquals(7, RemoveDuplicatesSortedArrayII_80.removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3}));
    }
}
