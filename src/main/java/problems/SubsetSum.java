package problems;

/*
* Is there a subset in an array of non negative integers that sum up to some non negative number S?
*
* dynamic programming approach
* table of dimensions n+1 x S+1
* if i==0: empty set has subset that sums to k?: table[i][k] = false for k!=0 and table[i][k] = true for k==0
* if i>0
* table[i][k] = true if {array[0], ... , array[i-1]} has subset with sum k
*             = false else
*
*
* if table[i-1][k] == true =>
*      table[i][k] == true  (adding an element will not change anything if the sum is already obtained)
* if table[i-1][k] == false =>
*      table[i][k] == true iff table[i-1][k-array[i-1]] == true
* proof of "iff"
* (<=) there are elements a_1, ..., a_j in {array[0], ..., array[i-2]} such that a_1 + ... + a_j = k - array[i-1]
* hence a_1 + ... + a_j + array[i-1] = k - array[i-1] + array[i-1] = k
* hence there are elements in {array[0], ..., array[i-1]} such that the sum equals k: table[i][k] == true
*
* (=>) there are elements a_1, ..., a_j in {array[0], ..., array[i-1]} such that a_1 + ... + a_j = k
* case 1: one of them is array[i-1], hence array[i-1] = a_m for some m in 1...j
* then a_1 + .. + a_{m-1} + a_{m+1} + .. + a_j = k - array[i-1]
* hence there are elements in {array[0], ..., array[i-1]} such that the sum equals k-array[i-1]
* case 2: none of then is equal to array[i-1]
* then there are elements in {array[0], ..., array[i-2]} such that the sum equals k but
* this is a contradiction with table[i-1][k] == false
*
* Hence:
* table[i][k]
* = table[i-1][k] if table[i-1][k] == true
* = table[i-1][k-array[i]] if table[i-1][k] == false
*
* time complexity O(sum * n)
* psuedopolynomial
*
* */

public class SubsetSum {
    

    public static boolean isSubset(int[] array, int sum){
        int n = array.length;
        boolean[][] table = new boolean[n+1][sum+1];

        //if sum = 0, then table[i][0] = true
        for(int i = 0; i < n+1; i++){
            table[i][0] = true;
        }

        //if i=0 and sum>0 then false -> but this is default

        for(int i = 1; i< n+1; i++){
            for(int s = 1; s<sum+1; s++){
                if(table[i-1][s]){
                    table[i][s] = table[i-1][s];
                }
                else if(s-array[i-1] >=0){
                    table[i][s] = table[i-1][s-array[i-1]];
                }

            }
        }



        return table[n][sum];
    }


    public static void main(String[] args){
        System.out.println(isSubset(new int[]{1,2,3}, 4));
        System.out.println(isSubset(new int[]{1,2,5}, 4));
        System.out.println(isSubset(new int[]{1,1,1,1,1,1}, 4));
/*        System.out.println(isSubset(new int[]{-1,-1}, 4));
        System.out.println(isSubset(new int[]{-11,10,-5,9}, 4));*/
    }
}
