package problems.leetcode;

import org.junit.Test;
import problems.leetcode.leetcode21_40.*;

import java.util.List;

import static org.junit.Assert.*;

public class LeetCode2 {

    @Test
    public void testMergeSortedLists(){
        ListNode lst1 = new ListNode(1);
        lst1.next = new ListNode(2);
        lst1.next.next = new ListNode(4);

        ListNode lst2 = new ListNode(1);
        lst2.next = new ListNode(3);
        lst2.next.next = new ListNode(4);

        ListNode lst = MergeSortedLists.mergeTwoLists(lst1, lst2);
        ListNode pointer = lst;
        assertEquals(1, pointer.val);
        pointer = pointer.next;
        assertEquals(1, pointer.val);
        pointer = pointer.next;
        assertEquals(2, pointer.val);
        pointer = pointer.next;
        assertEquals(3, pointer.val);
        pointer = pointer.next;
        assertEquals(4, pointer.val);
        pointer = pointer.next;
        assertEquals(4, pointer.val);

        ListNode lst3 = new ListNode(3);
        ListNode lst4 = new ListNode(1);
        lst4.next = new ListNode(2);
        ListNode lstM = MergeSortedLists.mergeTwoLists(lst3, lst4);
        assertEquals(1,lstM.val);
        assertEquals(2, lstM.next.val);
        assertEquals(3, lstM.next.next.val);

    }

    @Test
    public void testGenerateParentheses(){
        assertEquals(2, GenerateParentheses.generateParenthesis(2).size());
        assertEquals(5, GenerateParentheses.generateParenthesis(3).size());
    }

/*    @Test
    public void testHeap(){
        MinHeapNode node1 = new MinHeapNode(41,0,0);
        MinHeapNode node2 = new MinHeapNode(51,0,0);
        MinHeapNode node3 = new MinHeapNode(16,0,0);
        MinHeapNode node4 = new MinHeapNode(31,0,0);
        MinHeapNode node5 = new MinHeapNode(13,0,0);
        MinHeapNode node6 = new MinHeapNode(41,0,0);
        MinHeapNode node7 = new MinHeapNode(100,0,0);

        MinHeap heap = new MinHeap(new MinHeapNode[]{node1, node2, node3, node4, node5, node6, node7});

        int[] res = {13,31,16,41,51,41,100};
        for(int i = 0; i < res.length; i++){
            assertEquals(res[i], heap.getHeap()[i].value);
        }*/


    //}

    @Test
    public void testMergeKSort(){
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        ListNode[] input = new ListNode[]{node1, node2, node3};
        ListNode output = MergeKSortedLists.mergeKLists(input);

        int[] result = {1,1,2,3,4,4,5,6};
        ListNode pointer = output;
        for(int i = 0; i < result.length; i++){
            assertEquals(result[i], pointer.val);
            pointer = pointer.next;
        }

        ListNode node4 = null;
        ListNode node5 = null;

        ListNode[] input1 = new ListNode[]{node4, node5};
        ListNode output1 = MergeKSortedLists.mergeKLists(input1);
        assertEquals(null, output1);

        ListNode node6 = null;
        ListNode node7 = new ListNode(8);

        ListNode[] input2 = new ListNode[]{node6, node7};
        ListNode output2 = MergeKSortedLists.mergeKLists(input2);
        int[] result2 = {8};
        pointer = output2;
        for(int i = 0; i < result2.length; i++){
            assertEquals(result2[i], pointer.val);
            pointer = pointer.next;
        }
    }

    @Test
    public void testMergKPQ(){
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        ListNode[] input = new ListNode[]{node1, node2, node3};
        ListNode output = MergeKSortedLists.mergeKListsPQ(input);
        int[] result = {1,1,2,3,4,4,5,6};
        ListNode pointer = output;
        for(int i = 0; i < result.length; i++){
            assertEquals(result[i], pointer.val);
            pointer = pointer.next;
        }


        ListNode node4 = null;
        ListNode node5 = null;

        ListNode[] input1 = new ListNode[]{node4, node5};
        ListNode output1 = MergeKSortedLists.mergeKListsPQ(input1);
        assertEquals(null, output1);

        ListNode node6 = null;
        ListNode node7 = new ListNode(8);

        ListNode[] input2 = new ListNode[]{node6, node7};
        ListNode output2 = MergeKSortedLists.mergeKListsPQ(input2);
        int[] result2 = {8};
        pointer = output2;
        for(int i = 0; i < result2.length; i++){
            assertEquals(result2[i], pointer.val);
            pointer = pointer.next;
        }

        ListNode[] input3 = new ListNode[0];
        ListNode output3 = MergeKSortedLists.mergeKListsPQ(input3);


    }

    @Test
    public void testSwap(){
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);

        ListNode node1a = SwapNodes.swapPairs(node1);
        assertEquals(2, node1a.val);
        assertEquals(1, node1a.next.val);
        assertEquals(4, node1a.next.next.val);
        assertEquals(3, node1a.next.next.next.val);
        assertEquals(null, node1a.next.next.next.next);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(2);
        node2.next.next = new ListNode(3);

        ListNode node2a = SwapNodes.swapPairs(node2);
        assertEquals(2, node2a.val);
        assertEquals(1, node2a.next.val);
        assertEquals(3, node2a.next.next.val);


        ListNode node3 = new ListNode(1);
        node3.next = new ListNode(2);


        ListNode node3a = SwapNodes.swapPairs(node3);
        assertEquals(2, node3a.val);
        assertEquals(1, node3a.next.val);


    }

    @Test
    public void testReverseKNodes(){
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);

        node1 = ReverseKNodes.reverseKGroup(node1, 2);

        ListNode res1 = new ListNode(2);
        res1.next = new ListNode(1);
        res1.next.next = new ListNode(4);
        res1.next.next.next = new ListNode(3);
        res1.next.next.next.next = new ListNode(5);

        ListNode pointer1 = node1;
        ListNode pointerres1 = res1;

        while(pointerres1 != null){
            assertEquals(pointerres1.val, pointer1.val);
            pointer1 = pointer1.next;
            pointerres1 = pointerres1.next;
        }



        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(2);
        node2.next.next = new ListNode(3);
        node2.next.next.next = new ListNode(4);
        node2.next.next.next.next = new ListNode(5);
        node2.next.next.next.next.next = new ListNode(6);
        node2.next.next.next.next.next.next = new ListNode(7);
        node2.next.next.next.next.next.next.next = new ListNode(8);

        node2 = ReverseKNodes.reverseKGroup(node2, 3);

        ListNode res2 = new ListNode(2);
        res2.next = new ListNode(1);
        res2.next.next = new ListNode(4);
        res2.next.next.next = new ListNode(3);
        res2.next.next.next.next = new ListNode(6);
        res2.next.next.next.next.next = new ListNode(5);
        res2.next.next.next.next.next.next = new ListNode(7);
        res2.next.next.next.next.next.next.next = new ListNode(8);

        ListNode pointer2 = node2;
        ListNode pointerres2 = res2;

        while(pointerres1 != null){
            assertEquals(pointerres2.val, pointer2.val);
            pointer2 = pointer2.next;
            pointerres2 = pointerres2.next;
        }


        ListNode node3 = null;
        node3 = ReverseKNodes.reverseKGroup(node3, 2);
        assertEquals(null, node3);

    }

    @Test
    public void testRemoveDup(){
        int[] input1 = new int[]{1,1,2};
        int res = RemoveDuplicatesSortedArray.removeDuplicates(input1);
        assertEquals(2, res);
        int[] result = new int[]{1,2};
        for(int i = 0; i < result.length; i++){
            assertEquals(result[i], input1[i]);
        }

        int[] input2 = new int[]{0,0,1,1,1,2,2,3,3,4};
        int res2 = RemoveDuplicatesSortedArray.removeDuplicates(input2);
        assertEquals(5, res2);
        int[] result2 = {0,1,2,3,4};
        for(int i = 0; i < result2.length; i++){
            assertEquals(result2[i], input2[i]);
        }

        int[] input3 = new int[0];
        int res3 = RemoveDuplicatesSortedArray.removeDuplicates(input3);
        assertEquals(0, res3);

    }

    @Test
    public void testRemoveElement(){
        int[] input1 = {3,2,2,3};
        int res1 = RemoveElement.removeElement(input1, 3);
        assertEquals(2, res1);
        int[] result1 = {2,2};
        for(int i = 0; i < result1.length; i++){
            assertEquals(result1[i], input1[i]);
        }

        int[] input2 = {0,1,2,2,3,0,4,2};
        int res2 = RemoveElement.removeElement(input2, 2);
        assertEquals(5,res2);
        int[] result2 = {0,1,3,0,4};
        for(int i = 0; i < result2.length; i++){
            assertEquals(result2[i], input2[i]);
        }

        int[] input3 = {2};
        int res3 = RemoveElement.removeElement(input3, 3);
        assertEquals(1, res3);
        assertEquals(2, input3[0]);

        res3 = RemoveElement.removeElement(input3,2);
        assertEquals(0, res3);
    }

    @Test
    public void testImplementstrStr(){
        assertEquals(2, ImplementstrStr.strStr("hello", "ll"));
        assertEquals(2, ImplementstrStr.strStr("hellohellohello", "ll"));
        assertEquals(2, ImplementstrStr.strStr("hellllllo", "ll"));
        assertEquals(-1, ImplementstrStr.strStr("hello", "le"));
        assertEquals(0, ImplementstrStr.strStr("hello", ""));
        assertEquals(3, ImplementstrStr.strStr("hello", "lo"));
    }

    @Test
    public void testKMPArray(){
        int[] result1 = {0,0,1,2,3,4,0,1};
        String input1 = "abababca";
        assertArrayEquals(result1, ImplementstrStr.computeArray(input1));

        String checking = "bacbababaabcbab";
        int res = ImplementstrStr.strStrKMP(checking, input1);
        assertEquals(-1, res);

        int res1 = ImplementstrStr.strStrKMP(checking, "");
        assertEquals(0, res1);



        assertEquals(2, ImplementstrStr.strStrKMP("hello", "ll"));
        assertEquals(2, ImplementstrStr.strStrKMP("hellohellohello", "ll"));
        assertEquals(2, ImplementstrStr.strStrKMP("hellllllo", "ll"));
        assertEquals(-1, ImplementstrStr.strStrKMP("hello", "le"));
        assertEquals(0, ImplementstrStr.strStrKMP("hello", ""));
        assertEquals(3, ImplementstrStr.strStrKMP("hello", "lo"));

    }

    @Test
    public void testBitWise(){
        int n = 9; // 1001
        int d = 3; // 11

        assertEquals(9, n << 0);
        assertEquals(18, n << 1); // 10010 shift everything to the left (multiply by 2)
        assertEquals(4, n >> 1); // 100 shift everything to the right (divide by 2)
    }

    @Test
    public void testDivision(){
        int n = 90;
        int d = 30;
        assertEquals(3, DivideTwoIntegers.divide(n,d));

        assertEquals(3, DivideTwoIntegers.divide(91,30));
        assertEquals(0, DivideTwoIntegers.divide(9,30));

        assertEquals(-15, DivideTwoIntegers.divide(45,-3));
        assertEquals(32, DivideTwoIntegers.divide(64,2));

        assertEquals(Integer.MAX_VALUE,DivideTwoIntegers.divide(Integer.MAX_VALUE, 1));

        assertEquals(Integer.MAX_VALUE,DivideTwoIntegers.divide(Integer.MIN_VALUE, -1));
        assertEquals(Integer.MIN_VALUE,DivideTwoIntegers.divide(Integer.MIN_VALUE, 1));
        assertEquals(Integer.MIN_VALUE,DivideTwoIntegers.divide(Integer.MAX_VALUE, -1));

        assertEquals((Integer.MAX_VALUE)/2,DivideTwoIntegers.divide(Integer.MAX_VALUE, 2));
        assertEquals((Integer.MIN_VALUE)/2,DivideTwoIntegers.divide(Integer.MIN_VALUE, 2));
        assertEquals((Integer.MIN_VALUE)/(-2),DivideTwoIntegers.divide(Integer.MIN_VALUE, -2));

        assertEquals(1, DivideTwoIntegers.divide(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(0, DivideTwoIntegers.divide(Integer.MAX_VALUE, Integer.MIN_VALUE));
        assertEquals(-1, DivideTwoIntegers.divide(Integer.MIN_VALUE, Integer.MAX_VALUE));
        assertEquals(1, DivideTwoIntegers.divide(Integer.MIN_VALUE, Integer.MIN_VALUE));

    }

    @Test
    public void testSubstringConcatenation(){
        String s1 = "barfoothefoobarman";
        String[] w1 = {"foo","bar"};
        List<Integer> r1 = SubstringConcatenation.findSubstring(s1,w1);
        assertEquals(2, r1.size());
        int[] ex1 = {0,9};
        for(int i: ex1){
            assertTrue(r1.contains(i));
        }

        String s2 = "wordgoodgoodgoodbestword";
        String[] w2 = {"word","good","best","word"};
        List<Integer> r2 = SubstringConcatenation.findSubstring(s2,w2);
        assertEquals(0, r2.size());

        assertEquals(0, SubstringConcatenation.findSubstring("", w2).size());

        String s3 = "barfoofoobarthefoobarman";
        String[] w3 = {"bar","foo","the"};
        List<Integer> r3 = SubstringConcatenation.findSubstring(s3,w3);



    }

    @Test
    public void testNextPermutation(){
        int[] test1= {1,2,3,6,5,3};
        NextPermutation.nextPermutation(test1);
        assertArrayEquals(new int[]{1,2,5,3,3,6}, test1 );

        int[] test2 = {6,5,3,3,2,1};
        NextPermutation.nextPermutation(test2);
        assertArrayEquals(new int[]{1,2,3,3,5,6}, test2);

        int[] test3 = {};
        NextPermutation.nextPermutation(test3);
        assertArrayEquals(new int[]{}, test3);

        int[] test4 = {2,3,1};
        NextPermutation.nextPermutation(test4);
        assertArrayEquals(new int[]{3,1,2}, test4);
    }

    @Test
    public void testLongestValidParentheses(){
        assertEquals(2, LongestValidParentheses.longestValidParentheses("()"));
        assertEquals(4, LongestValidParentheses.longestValidParentheses("()()"));
        assertEquals(6, LongestValidParentheses.longestValidParentheses("(()())"));

        assertEquals(0, LongestValidParentheses.longestValidParentheses(")))((("));
        assertEquals(6, LongestValidParentheses.longestValidParentheses(")))()()()((("));
        assertEquals(6, LongestValidParentheses.longestValidParentheses("()())))((()))((((())"));

        assertEquals(2, LongestValidParentheses.longestValidParentheses("(()"));
    }

    @Test
    public void testSearchRotatedSortedArray(){
        int[] test1 = {6,7,0,1,2,4,5};
        assertEquals(-1, SearchRotatedSortedArray_33.search(test1, -1));
        assertEquals(2, SearchRotatedSortedArray_33.search(test1, 0));
        assertEquals(3, SearchRotatedSortedArray_33.search(test1, 1));
        assertEquals(4, SearchRotatedSortedArray_33.search(test1, 2));
        assertEquals(-1, SearchRotatedSortedArray_33.search(test1, 3));
        assertEquals(5, SearchRotatedSortedArray_33.search(test1, 4));
        assertEquals(6, SearchRotatedSortedArray_33.search(test1, 5));
        assertEquals(0, SearchRotatedSortedArray_33.search(test1, 6));
        assertEquals(1, SearchRotatedSortedArray_33.search(test1, 7));
        assertEquals(-1, SearchRotatedSortedArray_33.search(test1, 8));

        int[] test2 = {8,7};
        assertEquals(0, SearchRotatedSortedArray_33.search(test2, 8));
        assertEquals(1, SearchRotatedSortedArray_33.search(test2, 7));
        assertEquals(-1, SearchRotatedSortedArray_33.search(test2, 0));

        int[] test3 = {8};
        assertEquals(0, SearchRotatedSortedArray_33.search(test3, 8));
        assertEquals(-1, SearchRotatedSortedArray_33.search(test3, 7));

        int[] test4 = {};
        assertEquals(-1, SearchRotatedSortedArray_33.search(test4, 7));
    }

    @Test
    public void testFirstLastPosition(){
        int[] nums1 = {5,7,7,8,8,10};

        assertArrayEquals(new int[]{3,4}, FirstLastPosition.searchRange(nums1,8));
        assertArrayEquals(new int[]{0,0}, FirstLastPosition.searchRange(nums1,5));
        assertArrayEquals(new int[]{1,2}, FirstLastPosition.searchRange(nums1,7));
        assertArrayEquals(new int[]{-1,-1}, FirstLastPosition.searchRange(nums1,300));

        assertArrayEquals(new int[]{-1,-1}, FirstLastPosition.searchRange(new int[]{},300));
        assertArrayEquals(new int[]{0,0}, FirstLastPosition.searchRange(new int[]{1},1 ));

    }

    @Test
    public void testSearchInsert(){
        int[] nums1 = {5,7,8,10,12};

        assertEquals(1, SearchInsertPosition.searchInsert(nums1, 7));
        assertEquals(1, SearchInsertPosition.searchInsert(nums1, 6));
        assertEquals(0, SearchInsertPosition.searchInsert(nums1, 4));
        assertEquals(3, SearchInsertPosition.searchInsert(nums1, 9));
        assertEquals(4, SearchInsertPosition.searchInsert(nums1, 11));
        assertEquals(5, SearchInsertPosition.searchInsert(nums1, 13));
    }

    @Test
    public void testSudoku(){
        char[][] sudoku1 = {
            {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        assertTrue(Sudoku.isValidSudoku(sudoku1));

        char[][] sudoku2 = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','8','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        assertFalse(Sudoku.isValidSudoku(sudoku2));
    }

    @Test
    public void testSudokuSolver(){
        char[][] sudoku1 = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
/*        assertTrue(SudokuSolver.numberOK(sudoku1,0,2,'4'));
        assertFalse(SudokuSolver.numberOK(sudoku1,8,5,'4'));
        assertFalse(SudokuSolver.numberOK(sudoku1,8,5,'7'));*/

SudokuSolver.solveSudoku(sudoku1);
    }

    @Test
    public void testCountAndSay(){
        assertEquals("1", CountAndSay.countAndSay(1));
        assertEquals("11", CountAndSay.countAndSay(2));
        assertEquals("21", CountAndSay.countAndSay(3));
        assertEquals("1211", CountAndSay.countAndSay(4));
        assertEquals("111221", CountAndSay.countAndSay(5));
    }

    @Test
    public void testCombinationSum(){
        int[] nums1 = {1,2};
        List<List<Integer>> result1 = CombinationSum.combinationSum(nums1, 5);
        assertEquals(3, result1.size());

        System.out.println("--------");

        int[] nums2 = {2,1};
        List<List<Integer>> result2 = CombinationSum.combinationSum(nums2, 5);
        assertEquals(3, result2.size());

        int[] nums3 = {};
        List<List<Integer>> result3 = CombinationSum.combinationSum(nums3, 5);
        assertEquals(0, result3.size());

    }

    @Test
    public void testCombinationSumII(){
        int[] nums1 = {1,2};
        List<List<Integer>> result1 = CombinationSumII.combinationSum2(nums1, 5);
        assertEquals(0, result1.size());

        int[] nums2 = {1,2,3,4,5};
        List<List<Integer>> result2 = CombinationSumII.combinationSum2(nums2, 5);
        assertEquals(3, result2.size());

        int[] nums3 = {10,1,2,7,6,1,5};
        List<List<Integer>> result3 = CombinationSumII.combinationSum2(nums3, 8);
        assertEquals(4, result3.size());


    }


}
