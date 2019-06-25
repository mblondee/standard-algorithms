package problems.leetcode;

import org.junit.Test;
import problems.leetcode.leetcode1.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class LeetCode1 {

    @Test
    public void testTwoSum(){
        int[] nums = {2,7,11,15};
        int target = 9;

        int[] res = TwoSum.twoSum(nums, target);
        assertEquals(2, res.length);

        Arrays.sort(res);
        assertArrayEquals(new int[]{0,1}, res);



    }

    @Test
    public void testAddTwoNumbers(){
        ListNode l1 = new ListNode(8);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(2);

        ListNode l2 = new ListNode(7);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(1);

        ListNode res = AddTwoNumbers.addTwoNumbers(l1,l2);
        assertEquals(5, res.val);
        assertEquals(9, res.next.val);
        assertEquals(3, res.next.next.val);
        assertEquals(null, res.next.next.next);

        ListNode l3 = new ListNode(9);
        l3.next = new ListNode(2);
        l3.next.next = new ListNode(1);
        l3.next.next.next = new ListNode(4);


        ListNode l4 = new ListNode(2);
        l4.next = new ListNode(8);

        ListNode res3 = AddTwoNumbers.addTwoNumbers(l3,l4);
        assertEquals(1, res3.val);
        assertEquals(1, res3.next.val);
        assertEquals(2, res3.next.next.val);
        assertEquals(4, res3.next.next.next.val);
        assertEquals(null, res3.next.next.next.next);


        ListNode l5 = new ListNode(0);


        ListNode l6 = new ListNode(2);
        l6.next = new ListNode(8);

        ListNode res5 = AddTwoNumbers.addTwoNumbers(l5,l6);
        assertEquals(2, res5.val);
        assertEquals(8, res5.next.val);
        assertEquals(null, res5.next.next);


        ListNode l7 = new ListNode(1);
        ListNode l8 = new ListNode(9);
        l8.next = new ListNode(9);
        ListNode res7 = AddTwoNumbers.addTwoNumbers(l7, l8);
        assertEquals(0, res7.val);
        assertEquals(0, res7.next.val);
        assertEquals(1, res7.next.next.val);
        assertEquals(null, res7.next.next.next);


    }


    @Test
    public void testLongestSubstring(){
        String s1 = "";
        assertEquals(0,LongestSubstring.lengthOfLongestSubstring(s1));

        String s2 = "hahaha";
        assertEquals(2, LongestSubstring.lengthOfLongestSubstring(s2));

        String s3 = "abcabcbb";
        assertEquals(3, LongestSubstring.lengthOfLongestSubstring(s3));

        String s4 = "bbbbb";
        assertEquals(1, LongestSubstring.lengthOfLongestSubstring(s4));

        String s5 = "pwwkew";
        assertEquals(3, LongestSubstring.lengthOfLongestSubstring(s5));

        String s6 = "dvdf";
        assertEquals(3, LongestSubstring.lengthOfLongestSubstring(s6));

    }

    @Test
    public void testkthEl(){
        int[] A = new int[]{0,1,2,3,4};
        int[] B = new int[]{4,5,6,7,8};
        assertEquals(0.0, MedianSortedArrays.findkthElement(1, A,B, 0, 0), 0.0001);
        assertEquals(1.0, MedianSortedArrays.findkthElement(2, A,B, 0, 0), 0.0001);
        assertEquals(2.0, MedianSortedArrays.findkthElement(3, A,B, 0, 0), 0.0001);
        assertEquals(3.0, MedianSortedArrays.findkthElement(4, A,B, 0, 0), 0.0001);
        assertEquals(4.0, MedianSortedArrays.findkthElement(5, A,B, 0, 0), 0.0001);
        assertEquals(4.0, MedianSortedArrays.findkthElement(6, A,B, 0, 0), 0.0001);
        assertEquals(5.0, MedianSortedArrays.findkthElement(7, A,B, 0, 0), 0.0001);
        assertEquals(6.0, MedianSortedArrays.findkthElement(8, A,B, 0, 0), 0.0001);
        assertEquals(7.0, MedianSortedArrays.findkthElement(9, A,B, 0, 0), 0.0001);
        assertEquals(8.0, MedianSortedArrays.findkthElement(10, A,B, 0, 0), 0.0001);

        int[] A1 = new int[]{1,4};
        int[] B1 = new int[]{2,2,3,3,4,4,5,5,6};

        assertEquals(1.0, MedianSortedArrays.findkthElement(1, A1,B1, 0, 0), 0.0001);
        assertEquals(2.0, MedianSortedArrays.findkthElement(2, A1,B1, 0, 0), 0.0001);
        assertEquals(2.0, MedianSortedArrays.findkthElement(3, A1,B1, 0, 0), 0.0001);
        assertEquals(3.0, MedianSortedArrays.findkthElement(4, A1,B1, 0, 0), 0.0001);
        assertEquals(3.0, MedianSortedArrays.findkthElement(5, A1,B1, 0, 0), 0.0001);
        assertEquals(4.0, MedianSortedArrays.findkthElement(6, A1,B1, 0, 0), 0.0001);
        assertEquals(4.0, MedianSortedArrays.findkthElement(7, A1,B1, 0, 0), 0.0001);
        assertEquals(4.0, MedianSortedArrays.findkthElement(8, A1,B1, 0, 0), 0.0001);
        assertEquals(5.0, MedianSortedArrays.findkthElement(9, A1,B1, 0, 0), 0.0001);
        assertEquals(5.0, MedianSortedArrays.findkthElement(10, A1,B1, 0, 0), 0.0001);
        assertEquals(6.0, MedianSortedArrays.findkthElement(11, A1,B1, 0, 0), 0.0001);


        assertEquals(1.0, MedianSortedArrays.findkthElement(1, B1,A1, 0, 0), 0.0001);
        assertEquals(2.0, MedianSortedArrays.findkthElement(2, B1,A1, 0, 0), 0.0001);
        assertEquals(2.0, MedianSortedArrays.findkthElement(3, B1,A1, 0, 0), 0.0001);
        assertEquals(3.0, MedianSortedArrays.findkthElement(4, B1,A1, 0, 0), 0.0001);
        assertEquals(3.0, MedianSortedArrays.findkthElement(5, B1,A1, 0, 0), 0.0001);
        assertEquals(4.0, MedianSortedArrays.findkthElement(6, B1,A1, 0, 0), 0.0001);
        assertEquals(4.0, MedianSortedArrays.findkthElement(7, B1, A1,0, 0), 0.0001);
        assertEquals(4.0, MedianSortedArrays.findkthElement(8, B1, A1,0, 0), 0.0001);
        assertEquals(5.0, MedianSortedArrays.findkthElement(9, B1, A1, 0, 0), 0.0001);
        assertEquals(5.0, MedianSortedArrays.findkthElement(10, B1, A1, 0, 0), 0.0001);
        assertEquals(6.0, MedianSortedArrays.findkthElement(11, B1,A1, 0, 0), 0.0001);

        int[] A3 = new int[]{1};
        int[] B3 = new int[]{2};
        assertEquals(1, MedianSortedArrays.findkthElement(1, A3,B3, 0, 0), 0.0001);
        assertEquals(2, MedianSortedArrays.findkthElement(2, A3,B3, 0, 0), 0.0001);


    }

    @Test
    public void testMedian(){
        int[] A = new int[]{0,1,2,3,4};
        int[] B = new int[]{4,5,6,7,8};
        assertEquals(4.0, MedianSortedArrays.findMedianSortedArrays(A,B), 0.001);


        int[] A1 = new int[]{1,4};
        int[] B1 = new int[]{2,2,3,3,4,4,5,5,6};
        assertEquals(4.0, MedianSortedArrays.findMedianSortedArrays(A1,B1), 0.001);

        int[] A2 = new int[]{};
        int[] B2 = new int[]{2,2,3,3,4,4,5,5,6};
        assertEquals(4.0, MedianSortedArrays.findMedianSortedArrays(A2,B2), 0.001);

        int[] A3 = new int[]{1};
        int[] B3 = new int[]{2};
        assertEquals(1.5, MedianSortedArrays.findMedianSortedArrays(A3,B3), 0.001);
    }

    @Test
    public void testPalindormicSubstring(){
        String s1 = "babad";
        assertTrue(LongestPalindromicSubstring.longestPalindrome(s1).equals("aba") ||
                LongestPalindromicSubstring.longestPalindrome(s1).equals("bab"));

        String s2 = "cbbd";
        assertEquals("bb", LongestPalindromicSubstring.longestPalindrome(s2));

        String s3 = "";
        assertEquals("", LongestPalindromicSubstring.longestPalindrome(s3));

        String s4 = "bbbbbbbbbb";
        assertEquals("bbbbbbbbbb", LongestPalindromicSubstring.longestPalindrome(s4));

        String s5 = "bbaaabb";
        assertEquals(s5, LongestPalindromicSubstring.longestPalindrome(s5));

        String s6 = "a";
        assertEquals(s6, LongestPalindromicSubstring.longestPalindrome(s6));
    }

    @Test
    public void testZigZagConversion(){
        String s1 = "PAYPALISHIRING";
        assertEquals("PAHNAPLSIIGYIR", ZigZagConversion.convert(s1, 3));
        assertEquals("PINALSIGYAHRPI", ZigZagConversion.convert(s1, 4));

        String s2 = "";
        assertEquals("", ZigZagConversion.convert(s2,1));

        String s3 = "ab";
        assertEquals("ab", ZigZagConversion.convert(s3, 1));
    }

    @Test
    public void testReverseInteger(){
        assertEquals(321, ReverseInteger.reverse(123));
        assertEquals(-321, ReverseInteger.reverse(-123));
        assertEquals(0, ReverseInteger.reverse(1534236469));
        assertEquals(-2143847412, ReverseInteger.reverse(-2147483412));
    }

    @Test
    public void testStringToInteger(){
        assertEquals(42, StringToInteger.myAtoi("42"));
        assertEquals(-42, StringToInteger.myAtoi("-42"));
        assertEquals(42, StringToInteger.myAtoi("     42"));
        assertEquals(-42, StringToInteger.myAtoi("    -42"));
        assertEquals(0, StringToInteger.myAtoi("aba"));
        assertEquals(4193, StringToInteger.myAtoi("4193 with words"));
        assertEquals(0, StringToInteger.myAtoi("words and 987"));
        assertEquals(Integer.MIN_VALUE, StringToInteger.myAtoi("-91283472332"));
        assertEquals(Integer.MAX_VALUE, StringToInteger.myAtoi("91283472332"));
        assertEquals(3, StringToInteger.myAtoi("3.14159"));
        assertEquals(0, StringToInteger.myAtoi("+-2"));
        assertEquals(-12, StringToInteger.myAtoi("  -0012a42"));
        assertEquals(0, StringToInteger.myAtoi(" b11228552307"));


    }

    @Test
    public void testPalindromeNumber(){
        assertTrue(PalindromeNumber.isPalindrome(64346));
        assertTrue(PalindromeNumber.isPalindrome(6));
        assertTrue(PalindromeNumber.isPalindrome(66));
        assertFalse(PalindromeNumber.isPalindrome(-121));
        assertFalse(PalindromeNumber.isPalindrome(10));

    }

    @Test
    public void testRegExMatching(){
        assertFalse(RegExMatching.helper("aa", "a", 0,0,2,1));
        assertTrue(RegExMatching.helper("aa", "..", 0,0,2,2));
        assertFalse(RegExMatching.helper("aa", ".", 0,0,2,1));
        assertFalse(RegExMatching.helper("", ".", 0,0,0,1));

        assertTrue(RegExMatching.helper("aa", "a*", 0,0,2,2));
        assertTrue(RegExMatching.helper("aaaa", "a*a*", 0,0,4,4));
        assertFalse(RegExMatching.helper("baaa", "a*a*", 0,0,4,4));
        assertFalse(RegExMatching.helper("aaba", "a*a*", 0,0,4,4));

        assertTrue(RegExMatching.helper("aab", "c*a*b", 0,0,3,5));
        assertTrue(RegExMatching.helper("aab", ".*", 0,0,3,2));

        assertTrue(RegExMatching.helper("aabc", ".*c", 0,0,4,3));
        assertFalse(RegExMatching.helper("aabd", ".*c", 0,0,4,3));

        assertTrue(RegExMatching.isMatch("aaa", "ab*ac*a"));
    }

    @Test
    public void testMaxArea(){
        int[] input = new int[]{1,8,6,2,5,4,8,3,7};
        assertEquals(49, MaxWater.maxArea(input));
    }

    @Test
    public void testIntegerToRoman(){
        assertEquals("I", IntegerToRoman.intToRoman(1));
        assertEquals("II", IntegerToRoman.intToRoman(2));
        assertEquals("III", IntegerToRoman.intToRoman(3));
        assertEquals("IV", IntegerToRoman.intToRoman(4));
        assertEquals("V", IntegerToRoman.intToRoman(5));
        assertEquals("VI", IntegerToRoman.intToRoman(6));
        assertEquals("VII", IntegerToRoman.intToRoman(7));
        assertEquals("VIII", IntegerToRoman.intToRoman(8));
        assertEquals("IX", IntegerToRoman.intToRoman(9));
        assertEquals("X", IntegerToRoman.intToRoman(10));
        assertEquals("L", IntegerToRoman.intToRoman(50));
        assertEquals("C", IntegerToRoman.intToRoman(100));
        assertEquals("D", IntegerToRoman.intToRoman(500));
        assertEquals("M", IntegerToRoman.intToRoman(1000));

        assertEquals("MCMXCIV", IntegerToRoman.intToRoman(1994));
    }

    @Test
    public void testRomanToInteger(){
        assertEquals(1, RomanToInteger.romanToInt("I"));
        assertEquals(2, RomanToInteger.romanToInt("II"));
        assertEquals(3, RomanToInteger.romanToInt("III"));
        assertEquals(4, RomanToInteger.romanToInt("IV"));
        assertEquals(5, RomanToInteger.romanToInt("V"));
        assertEquals(6, RomanToInteger.romanToInt("VI"));
        assertEquals(7, RomanToInteger.romanToInt("VII"));
        assertEquals(8, RomanToInteger.romanToInt("VIII"));
        assertEquals(9, RomanToInteger.romanToInt("IX"));
        assertEquals(10, RomanToInteger.romanToInt("X"));

        assertEquals(40, RomanToInteger.romanToInt("XL"));
        assertEquals(50, RomanToInteger.romanToInt("L"));
        assertEquals(90, RomanToInteger.romanToInt("XC"));
        assertEquals(100, RomanToInteger.romanToInt("C"));
        assertEquals(400, RomanToInteger.romanToInt("CD"));
        assertEquals(900, RomanToInteger.romanToInt("CM"));

        assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));

    }

    @Test
    public void testCommonPrefix(){
        String[] empty = new String[]{};
        assertEquals("", LongestCommonPrefix.longestCommonPrefix(empty));

        String[] strs1 = new String[]{"flower","flow","flight"};
        assertEquals("fl", LongestCommonPrefix.longestCommonPrefix(strs1));

        String[] strs2 = new String[]{"dog","racecar","car"};
        assertEquals("", LongestCommonPrefix.longestCommonPrefix(strs2));
    }

    @Test
    public void testThreeSum(){
        int[] example1 = new int[]{-1, 0, 1, 2, -1,-4};
        assertEquals(2, ThreeSum.threeSum(example1).size());

        int[] example3 = new int[]{-1, 0,1, 1,-1, 2, -1,-4};
        assertEquals(2, ThreeSum.threeSum(example3).size());

        int[] example2 = new int[]{-1,-1,-1,0,0, 0, 1, 2, -1, -4};
        assertEquals(3, ThreeSum.threeSum(example2).size());

        int[] example4 = new int[]{-2,0,0,2,2};
        assertEquals(1, ThreeSum.threeSum(example4).size());
    }

    @Test
    public void testThreeSumClosest(){
        int[] example1 = new int[]{-1, 2, 1, -4};
        assertEquals(2, ThreeSumClosest.threeSumClosest(example1,1));

        int[] example2 = new int[]{-1, 2,-1,1, 1, 1, -4};
        assertEquals(1, ThreeSumClosest.threeSumClosest(example2,1));
    }

    @Test
    public void testLetterCombinations(){
        String input = "23";
        String[] output = {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"};
        List<String> generated = LetterCombinationsPhone.letterCombinations(input);
        assertEquals(output.length, generated.size());
        for(String str: output){
            assertTrue(generated.contains(str));
        }

        String input1 = "";
        String[] output1 = {};
        List<String> generated1 = LetterCombinationsPhone.letterCombinations(input1);
        assertEquals(output1.length, generated1.size());


    }

    @Test
    public void testFourSum(){
        int[] example1 = {1, 0, -1, 0, -2, 2};
        assertEquals(3, FourSum.fourSum(example1, 0).size());

        int[] example2 = {1, 0,0,0, -1, 0, -2,2,-2, 2};
        assertEquals(5, FourSum.fourSum(example2, 0).size());

        int[] example3 = {0,0,0,0};
        assertEquals(1, FourSum.fourSum(example3,0).size());
    }

    @Test
    public void testRemoveNthNode(){
        ListNode testNode = new ListNode(1);
        testNode.next = new ListNode(2);
        testNode.next.next = new ListNode(3);
        testNode.next.next.next = new ListNode(4);
        testNode.next.next.next.next = new ListNode(5);
        RemoveNthNode.removeNthFromEnd(testNode,2);

        ListNode expected = new ListNode(1);
        expected.next = new ListNode(2);
        expected.next.next = new ListNode(3);
        expected.next.next.next = new ListNode(5);

        ListNode pointer1 = testNode;
        ListNode pointer2 = expected;
        while(pointer2 != null){
            assertEquals(pointer1.val, pointer2.val);
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        ListNode testNode1 = new ListNode(1);
        ListNode result = RemoveNthNode.removeNthFromEnd(testNode1,0);
        assertEquals(null, result);


        RemoveNthNode.removeNthFromEnd(testNode, 1);
        ListNode expected1 = new ListNode(1);
        expected1.next = new ListNode(2);
        expected1.next.next = new ListNode(3);

        ListNode pointer3 = testNode;
        ListNode pointer4 = expected1;
        while(pointer4 != null){
            assertEquals(pointer3.val, pointer4.val);
            pointer3 = pointer3.next;
            pointer4 = pointer4.next;
        }



    }

    @Test
    public void testParentheses(){
        assertTrue(ValidParentheses.isValid(""));
        assertTrue(ValidParentheses.isValid("()"));
        assertTrue(ValidParentheses.isValid("{}"));
        assertTrue(ValidParentheses.isValid("[]"));
        assertTrue(ValidParentheses.isValid("(){}[][]()"));
        assertTrue(ValidParentheses.isValid("({[]})"));

        assertFalse(ValidParentheses.isValid("({)}"));
        assertFalse(ValidParentheses.isValid("[{(}}[]"));
        assertFalse(ValidParentheses.isValid("(((((((("));
    }

}
