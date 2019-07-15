package problems.leetcode;

import org.junit.Test;
import problems.leetcode.leetcode3.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LeetCode3 {

    @Test
    public void testFirstMissingPositive() {
        int[] nums1 = {4, 3, 5, 1, 0};
        assertEquals(2, FirstMissingPositive_43.firstMissingPositive(nums1));


        int[] nums2 = {4, -3, -5, 1, 0};
        assertEquals(2, FirstMissingPositive_43.firstMissingPositive(nums2));

        int[] nums3 = {-4, -3, -5, -1, 0};
        assertEquals(1, FirstMissingPositive_43.firstMissingPositive(nums3));

        int[] nums4 = {3, 2, 1};
        assertEquals(4, FirstMissingPositive_43.firstMissingPositive(nums4));

        int[] nums5 = {1, 1, 1};
        assertEquals(2, FirstMissingPositive_43.firstMissingPositive(nums5));
    }

    @Test
    public void testTrappingWater() {
        int[] test1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] result1Left = {0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        int[] result1Right = {3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1, 0};
        assertArrayEquals(result1Left, TrappingRainWater_42.computeLeft(test1));
        assertArrayEquals(result1Right, TrappingRainWater_42.computeRight(test1));
        assertEquals(6, TrappingRainWater_42.trap(test1));

        assertEquals(0, TrappingRainWater_42.trap(new int[]{}));
        assertEquals(0, TrappingRainWater_42.trap(new int[]{1}));
        assertEquals(0, TrappingRainWater_42.trap(new int[]{2, 3}));
    }

    @Test
    public void testMultiplyStrings() {
        String num1 = "345";
        String num2 = "976";


        assertEquals("336720", MultiplyStrings_43.multiply(num1, num2));
        assertEquals("0", MultiplyStrings_43.multiply(num1, "0"));
        assertEquals("0", MultiplyStrings_43.multiply("0", num2));
        assertEquals("345", MultiplyStrings_43.multiply(num1, "1"));
        assertEquals("3450", MultiplyStrings_43.multiply(num1, "10"));

        assertEquals("121932631112635269", MultiplyStrings_43.multiply("123456789", "987654321"));

        assertEquals("56088", MultiplyStrings_43.multiply("123", "456"));

        assertEquals("1512", MultiplyStrings_43.add("72", "1440"));
        assertEquals("10512", MultiplyStrings_43.multiply("584", "18"));

        assertEquals("419254329864656431168468", MultiplyStrings_43.multiply("498828660196", "840477629533"));

        assertEquals("1321", MultiplyStrings_43.add(num1, num2));
        assertEquals(num1, MultiplyStrings_43.add(num1, "0"));
        assertEquals("356", MultiplyStrings_43.add(num1, "11"));
        assertEquals("444", MultiplyStrings_43.add(num1, "99"));
        assertEquals("1075", MultiplyStrings_43.add(num2, "99"));
    }

    @Test
    public void testWildCardMatching(){
        assertFalse(WildCardMatching_44.isMatch("aa", "a"));
        assertTrue(WildCardMatching_44.isMatch("aa", "*"));
        assertTrue(WildCardMatching_44.isMatch("aa", "a?"));
        assertTrue(WildCardMatching_44.isMatch("aa", "?a"));
        assertFalse(WildCardMatching_44.isMatch("aa", "?a?"));
        assertFalse(WildCardMatching_44.isMatch("cb", "?a"));
        assertTrue(WildCardMatching_44.isMatch("", "*"));
        assertFalse(WildCardMatching_44.isMatch("acdcb", "a*c?b"));
        assertTrue(WildCardMatching_44.isMatch("adceb", "*a*b"));
        assertFalse(WildCardMatching_44.isMatch("mississippi","m??*ss*?i*pi"));

        assertTrue(WildCardMatching_44.isMatch("ababab", "*a*"));
    }

    @Test
    public void testJumpGameII(){
        assertEquals(2, JumpGameII_45.jump(new int[]{2,3,1,1,4}));
        assertEquals(0, JumpGameII_45.jump(new int[]{2}));
        //assertEquals(Integer.MAX_VALUE, JumpGameII_45.jump(new int[]{0,3,1,1,4}));
    }

    @Test
    public void testPermutations(){
        List<List<Integer>> perm1 = Permutations_46.permute(new int[]{0,1,2});
        assertEquals(6, perm1.size());

        List<List<Integer>> perm2 = Permutations_46.permute(new int[]{});
        assertEquals(0, perm2.size());
    }

    @Test
    public void testPermutationsII(){
        List<List<Integer>> perm1 = PermutationsII_47.permuteUnique(new int[]{1,1,2});
        assertEquals(3, perm1.size());
    }

    @Test
    public void testRotateImage(){
        int[][] matrix1 = {{1,2,3}, {4,5,6}, {7,8,9}};
        RotateImage_48.rotate(matrix1);

        int[][] matrix2 = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        RotateImage_48.rotate(matrix2);
    }

    @Test
    public void testGroupAnagrams(){
        String[] test = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = GroupAnagrams_49.groupAnagrams(test);
        assertEquals(3, result.size());
    }

    @Test
    public void testPow(){
        assertEquals(4*4*4*4*4.0, Pow_50.myPow(4,5), 0.0001);

        assertEquals(1024.00000, Pow_50.myPow(2.00000, 10), 0.0001);
        assertEquals(9.26100, Pow_50.myPow(2.10000, 3), 0.0001);
        assertEquals(0.25000, Pow_50.myPow(2.00000, -2), 0.0001);

        assertEquals(0.0, Pow_50.myPow(2.000, Integer.MIN_VALUE), 0.0001);
    }

    @Test
    public void testNQueens(){
        Character[][] board1 = new Character[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                board1[i][j] = '.';
            }
        }
        board1[0][0] = 'Q';
        board1[1][2] = 'Q';



        assertFalse(NQueens_51.isValid(board1, 0, 0));
        assertFalse(NQueens_51.isValid(board1, 1, 0));
        assertFalse(NQueens_51.isValid(board1, 2, 0));
        assertFalse(NQueens_51.isValid(board1, 3, 0));

        assertFalse(NQueens_51.isValid(board1, 0, 1));
        assertFalse(NQueens_51.isValid(board1, 1, 1));
        assertTrue(NQueens_51.isValid(board1, 2, 1));
        assertTrue(NQueens_51.isValid(board1, 3, 1));

        assertFalse(NQueens_51.isValid(board1, 0, 2));
        assertFalse(NQueens_51.isValid(board1, 1, 2));
        assertFalse(NQueens_51.isValid(board1, 2, 2));
        assertFalse(NQueens_51.isValid(board1, 3, 2));

        assertFalse(NQueens_51.isValid(board1, 0, 3));
        assertFalse(NQueens_51.isValid(board1, 1, 3));
        assertFalse(NQueens_51.isValid(board1, 2, 3));
        assertFalse(NQueens_51.isValid(board1, 3, 3));


        assertEquals(2, NQueens_51.solveNQueens(4).size());
        assertEquals(1, NQueens_51.solveNQueens(1).size());
        assertEquals(0, NQueens_51.solveNQueens(2).size());

    }

    @Test
    public void testNQueensII(){
        assertEquals(2, NQueensII_52.totalNQueens(4));
        assertEquals(1, NQueensII_52.totalNQueens(1));
        assertEquals(0, NQueensII_52.totalNQueens(2));
    }

    @Test
    public void testMaximumSubarray(){
        assertEquals(6, MaximumSubarray_53.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    @Test
    public void testSpiralMatrix(){
        int[][] matrix1 = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
        Integer[] result1 = {1,2,3,4,8,12,11,10,9,5,6,7};
        List<Integer> test1 = SpiralMatrix_54.spiralOrder(matrix1);
        for(int i = 0; i < test1.size(); i++){
            assertEquals(result1[i], test1.get(i));
        }

        int[][] matrix3 = {{1,2,3,4}};
        Integer[] result3 = {1,2,3,4};
        List<Integer> test3 = SpiralMatrix_54.spiralOrder(matrix3);
        for(int i = 0; i < test3.size(); i++){
            assertEquals(result3[i], test3.get(i));
        }

        int[][] matrix4 = {{1}, {2}, {3}, {4}};
        Integer[] result4 = {1,2,3,4};
        List<Integer> test4 = SpiralMatrix_54.spiralOrder(matrix4);
        for(int i = 0; i < test4.size(); i++){
            assertEquals(result4[i], test4.get(i));
        }

        int[][] matrix2 = {};
        assertEquals(0, SpiralMatrix_54.spiralOrder(matrix2).size());
    }

    @Test
    public void testJumpGame(){
        int[] test1 = {2,3,1,1,4};
        assertTrue(JumpGame_55.canJump(test1));
        int[] test2 = {3,2,1,0,4};
        assertFalse(JumpGame_55.canJump(test2));
        int[] test3 = {};
        assertFalse(JumpGame_55.canJump(test3));
        int[] test4 = {1};
        assertTrue(JumpGame_55.canJump(test4));


        assertTrue(JumpGame_55.canJump2(test1));

        assertFalse(JumpGame_55.canJump2(test2));

        assertFalse(JumpGame_55.canJump2(test3));

        assertTrue(JumpGame_55.canJump2(test4));
    }

    @Test
    public void MergeIntervalsTest(){
        int[][] intervals1 = {{15, 18}, {8,10}, {2,6}, {1,3}};
        int[][] merged1 = MergeIntervals_56.merge(intervals1);
        int[][] result1 = {{1,6}, {8,10}, {15,18}};
        for(int i = 0; i < result1.length; i++){
            assertArrayEquals(result1[i], merged1[i]);
        }

        int[][] intervals2 = {{1,4}, {4,5}};
        int[][] merged2 = MergeIntervals_56.merge(intervals2);
        int[][] result2 = {{1,5}};
        for(int i = 0; i < result2.length; i++){
            assertArrayEquals(result2[i], merged2[i]);
        }

        int[][] intervals3 = {{1,4}};
        int[][] merged3 = MergeIntervals_56.merge(intervals3);
        int[][] result3 = {{1,4}};
        for(int i = 0; i < result3.length; i++){
            assertArrayEquals(result3[i], merged3[i]);
        }
    }

    @Test
    public void testInsertInterval() {
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[][] inserted1 = InsertInterval_57.insert(intervals1, new int[]{2, 5});
        int[][] result1 = {{1, 5}, {6, 9}};
        for (int i = 0; i < result1.length; i++) {
            assertArrayEquals(result1[i], inserted1[i]);
        }


        int[][] intervals2 = {{1, 3}, {6, 9}, {10, 11}};
        int[][] inserted2 = InsertInterval_57.insert(intervals2, new int[]{2, 5});
        int[][] result2 = {{1, 5}, {6, 9}, {10, 11}};
        for (int i = 0; i < result2.length; i++) {
            assertArrayEquals(result2[i], inserted2[i]);
        }

        int[][] intervals3 = {{1, 3}, {6, 9}};
        int[][] inserted3 = InsertInterval_57.insert(intervals3, new int[]{8, 10});
        int[][] result3 = {{1, 3}, {6, 10}};
        for (int i = 0; i < result3.length; i++) {
            assertArrayEquals(result3[i], inserted3[i]);
        }
    }

        @Test
        public void testLengthOfLastWord(){
            assertEquals(5, LengthOfLastWord_58.lengthOfLastWord("Hello World"));
            assertEquals(0, LengthOfLastWord_58.lengthOfLastWord(""));
            assertEquals(0, LengthOfLastWord_58.lengthOfLastWord("   "));
            assertEquals(2, LengthOfLastWord_58.lengthOfLastWord("ha"));
            assertEquals(2, LengthOfLastWord_58.lengthOfLastWord("ha "));
        }

}

