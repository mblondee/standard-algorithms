package problems.leetcode.leetcode4;

/*Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

        You have the following 3 operations permitted on a word:

        Insert a character
        Delete a character
        Replace a character*/

// dynamic programming
// minDist[i][j] = min number of operations needed to convert word1[0...i) into word [0...j) (i and j not included)
// hence minDist[0][j] = j (inserting i chars)
// hence minDist[i][0] = i (deleting i chars)
// computing minDistance[i][j] for i >0 and j>0: (lookup in words at indices i-1 and j-1)
// 1) word1.charAt(i-1) == word2.charAt(j-1): no extra changes needed => minDistance[i][j] = minDistance[i-1][j-1]
// 2) word1.charAt(i-1) != word2.charAt(j-1): word1.charAt(i-1) has to be converted to word2.charAt(j-1)
//   a) delete word1.charAt(i-1) =>  minDistance[i-1][j] + 1 (+1 for removing and then look up in table what to do)
//   b) delete word1.charAt(j) =>  minDistance[i][j-1] + 1 (+1 for removing and then look up in table what to do)
//   c) replace word1.charAt(i-1) by word1.charAt(j-1) =>  minDistance[i-1][j-1] + 1
// minDistance[i][j] is minimum of a), b) and c)


public class HardDistance_72 {
    public static int minDistance(String word1, String word2) {
        int[][] minDistance = new int[word1.length()+1][word2.length()+1];
        minDistance[0][0] = 0;
        for(int i = 0; i <= word1.length(); i++){
            minDistance[i][0] = i;
        }
        for(int j = 0; j <= word2.length(); j++){
            minDistance[0][j] = j;
        }

        for(int i = 1; i <= word1.length(); i++){
            for(int j = 1; j <= word2.length(); j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    minDistance[i][j] = minDistance[i-1][j-1];
                }
                else{
                    minDistance[i][j] = Math.min(Math.min(minDistance[i-1][j] +1, minDistance[i][j-1] + 1), minDistance[i-1][j-1] + 1);
                }
            }
        }

        return minDistance[word1.length()][word2.length()];

    }
}
