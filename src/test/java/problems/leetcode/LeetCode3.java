package problems.leetcode;

import org.junit.Test;
import problems.leetcode.leetcode3.*;

import java.util.List;

import static org.junit.Assert.*;

public class LeetCode3 {

    @Test
    public void testFirstMissingPositive() {
        int[] nums1 = {4, 3, 5, 1, 0};
        assertEquals(2, FirstMissingPositive.firstMissingPositive(nums1));


        int[] nums2 = {4, -3, -5, 1, 0};
        assertEquals(2, FirstMissingPositive.firstMissingPositive(nums2));

        int[] nums3 = {-4, -3, -5, -1, 0};
        assertEquals(1, FirstMissingPositive.firstMissingPositive(nums3));

        int[] nums4 = {3, 2, 1};
        assertEquals(4, FirstMissingPositive.firstMissingPositive(nums4));

        int[] nums5 = {1, 1, 1};
        assertEquals(2, FirstMissingPositive.firstMissingPositive(nums5));
    }

    @Test
    public void testTrappingWater() {
        int[] test1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] result1Left = {0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        int[] result1Right = {3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1, 0};
        assertArrayEquals(result1Left, TrappingRainWater.computeLeft(test1));
        assertArrayEquals(result1Right, TrappingRainWater.computeRight(test1));
        assertEquals(6, TrappingRainWater.trap(test1));

        assertEquals(0, TrappingRainWater.trap(new int[]{}));
        assertEquals(0, TrappingRainWater.trap(new int[]{1}));
        assertEquals(0, TrappingRainWater.trap(new int[]{2, 3}));
    }

    @Test
    public void testMultiplyStrings() {
        String num1 = "345";
        String num2 = "976";


        assertEquals("336720", MultiplyStrings.multiply(num1, num2));
        assertEquals("0", MultiplyStrings.multiply(num1, "0"));
        assertEquals("0", MultiplyStrings.multiply("0", num2));
        assertEquals("345", MultiplyStrings.multiply(num1, "1"));
        assertEquals("3450", MultiplyStrings.multiply(num1, "10"));

        assertEquals("121932631112635269", MultiplyStrings.multiply("123456789", "987654321"));

        assertEquals("56088", MultiplyStrings.multiply("123", "456"));

        assertEquals("1512", MultiplyStrings.add("72", "1440"));
        assertEquals("10512", MultiplyStrings.multiply("584", "18"));

        assertEquals("419254329864656431168468", MultiplyStrings.multiply("498828660196", "840477629533"));

        assertEquals("1321", MultiplyStrings.add(num1, num2));
        assertEquals(num1, MultiplyStrings.add(num1, "0"));
        assertEquals("356", MultiplyStrings.add(num1, "11"));
        assertEquals("444", MultiplyStrings.add(num1, "99"));
        assertEquals("1075", MultiplyStrings.add(num2, "99"));
    }

    @Test
    public void testWildCardMatching(){
        assertFalse(WildCardMatching.isMatch("aa", "a"));
        assertTrue(WildCardMatching.isMatch("aa", "*"));
        assertTrue(WildCardMatching.isMatch("aa", "a?"));
        assertTrue(WildCardMatching.isMatch("aa", "?a"));
        assertFalse(WildCardMatching.isMatch("aa", "?a?"));
        assertFalse(WildCardMatching.isMatch("cb", "?a"));
        assertTrue(WildCardMatching.isMatch("", "*"));
        assertFalse(WildCardMatching.isMatch("acdcb", "a*c?b"));
        assertTrue(WildCardMatching.isMatch("adceb", "*a*b"));
        assertFalse(WildCardMatching.isMatch("mississippi","m??*ss*?i*pi"));

        assertTrue(WildCardMatching.isMatch("ababab", "*a*"));
    }

    @Test
    public void testJumpGameII(){
        assertEquals(2, JumpGameII.jump(new int[]{2,3,1,1,4}));
        assertEquals(0, JumpGameII.jump(new int[]{2}));
        //assertEquals(Integer.MAX_VALUE, JumpGameII.jump(new int[]{0,3,1,1,4}));
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
}

