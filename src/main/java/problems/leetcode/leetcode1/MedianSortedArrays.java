package problems.leetcode.leetcode1;


/*
* There are two sorted arrays nums1 and nums2 of size m and n respectively.
* Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
* You may assume nums1 and nums2 cannot be both empty.
* */

public class MedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        double med;

        int k = (n+m)%2;

        if((n+m) % 2 != 0){
            med = findkthElement((n+m + 1)/2, nums1, nums2, 0,0);
        }
        else{
            double med1 = findkthElement((n+m)/2, nums1, nums2, 0,0);
            double med2 = findkthElement((n+m+2)/2, nums1, nums2, 0,0);
            med =  (med1 + med2)/2;
        }

        return med;
    }

    public static double findkthElement(int k, int[] A, int[] B, int startA, int startB){
        int n = A.length - startA;
        int m = B.length - startB;

        if(n+m < k){
            throw new IllegalArgumentException();
        }

        if(n == 0){
            return B[startB + k-1];
        }
        if(m == 0){
            return A[startA + k-1];
        }
        if(k == 1){
            return Math.min(A[startA], B[startB]);
        }

        // split A and B by k/2
        // A -> A[0 .... k/2 -1] A[k/2 .... n-1]
        // B -> B[0 .... k/2-1] B[k/2 .... m-1]

        // suppose A too small to split
        if(n <= k/2 - 1){
            // k th element must be in second part of B -> discard first half of B (must be k/2 elements)
            // look for k - k/2 element
            return findkthElement(k - k/2, A, B, startA, startB + k/2 );
        }
        if(m <= k/2 -1){
            return findkthElement(k-k/2, A, B, startA + k/2, startB);
        }

        if(A[startA + k/2 -1] <= B[startB + k/2 -1]){
            // the number of elements in the two first halves summed is k,
            // so if A[startA + k/2 -1] < B[startB + k/2 -1]
            // then there are max k-1 elements smaller or equal to A[startA + k/2 -1]
            // we can discard first half of A, the smallest k cannot be there
            return findkthElement(k - k/2, A,B, startA + k/2, startB);
        }
        else{
            return findkthElement(k-k/2, A, B, startA, startB + k/2);
        }

    }





}
