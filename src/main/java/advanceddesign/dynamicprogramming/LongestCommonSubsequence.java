package advanceddesign.dynamicprogramming;

/*
* Given two strings S of length m and T of length n, not necessarily of the same length,
*  what is a longest common subsequence (LCS):
* the longest sequence of characters that appear left to right, not necessarily in a continuous block
*
* solution using dynamic programming
* suppose LCS[i,j] is the length of the LCS of S[0...i-1] and S[0...j-1]
* if S[i-1] != T[j-1]
* LCS[i,j] = max(LCS[i-1, j], LCS[i, j-1])
*
* if S[i-1] = T[j-1]
* LCS[i,j] = 1 + LCS[i-1, j-1]
*
* running time of computing the LCS table takes O(mn) time
* computing a sequence given the LCS table takes O(m+n) time
*
* */



public class LongestCommonSubsequence {

    private int[][] LCS;
    private int m; // rows in LCS
    private int n; // columns in LCS
    private String S;
    private String T;

    public LongestCommonSubsequence(String S, String T){
        m = S.length()+1;
        n = T.length()+1;
        this.S = S;
        this.T = T;
        LCS = new int[m][n];
        fillLCS();
    }

    private void fillLCS(){
        // if i == 0 or j == 0 LCS[i][j] = 0

        for(int i = 1; i < m ; i++){
            for(int j = 1; j<n; j++){
                if(S.charAt(i-1) == T.charAt(j-1)){
                    LCS[i][j] = 1 + LCS[i-1][j-1];
                }
                else{
                    LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
                }
            }
        }
    }

    public int[][] getLCS(){
        return LCS;
    }

    public String getLongestCommonSubsequence(){
        int length = LCS[m-1][n-1];
        char[] arrayOfChar = new char[length];

        int i = m-1;
        int j = n-1;
        int currentIndex = length - 1;

        while(i>0 && j>0){
            // if same character, the char is part of the longest common subsequence
            if(S.charAt(i-1) == T.charAt(j-1)){
                arrayOfChar[currentIndex--] = S.charAt(i-1);
                i--;
                j--;
            }
            // else pick the max
            else if(LCS[i-1][j] > LCS[i][j-1]){
                i--;
            }
            else{
                j--;
            }
        }
        return new String(arrayOfChar);
    }

    public static void main(String[] args){
        String S = "ABCBDAB";
        String T = "BDCABA";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(S,T);
/*        for(int[] row: lcs.getLCS()){
            for(int el: row){
                System.out.print(el + " ");
            }
            System.out.print("\n");
        }*/
        System.out.println(lcs.getLongestCommonSubsequence());
    }

}
