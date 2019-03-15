package strings.substringsearch;

/*
* KMP (Knuth-Morris-Pratt) implementation of substring search: search for (first occurence of) pattern in text
* */

public class KMP {

    private int RADIX;
    private int[][] dfa; //when in state j and examining char in text, dfa[char][j]  contains next state
    // there are patternLength + 1 states (last one is final acceptance state)
    private String pattern;
    private int patternLength;

    /*
    * pattern has to be preprocessed
    * deterministic finite automaton (dfa) is build from pattern
    * */
    public KMP(String pattern){
        this.pattern = pattern;
        this.RADIX = 256;

        patternLength = pattern.length();
        dfa = new int[RADIX][patternLength];

        // if there is a mismatch, the idea is to check where would dfa be if we would have backed up?

        // example: looking for pattern "aacaaab"
        // a mismatch at j = 3 "aacb", in brute force we would back up "acb" rescanning subpattern[1] to subpattern[j-1]
        // however we have already scanned this before -> this should be reflected in dfa

        // a mismatch "aacab" -> where would "aca" take us? this is (state 0) =  (state 1) + char a (correct char)
        // a mismatch "aacb" -> where would "ac" take us? (state 1) = (state 2) + char c (correct char)
        // a mismatch "aab" -> where would "a" take us? (state 2) = (state 3) + char a (correct char)
        // a mismatch "ab" -> where would "a" take us? this is the first state

        // this state is variable x, it is x = 0 at the beginning

        // if j = 0 dfa[c][j] = 0 by default, have to set match case
        dfa[pattern.charAt(0)][0] = 1;
        // set dfa for j>=1
        for(int x = 0, j = 1; j < patternLength; j++){
            for(int c = 0; c < RADIX; c++){
                // suppose all mismatch cases
                dfa[c][j] = dfa[c][x];
            }
            // set match case
            dfa[pattern.charAt(j)][j] = j+1;
            //update x

            x = dfa[pattern.charAt(j)][x];
        }


    }

    /*
     * return offset of first match
     * return length of text if no match exists
     * */
    public int search(String text){
        int textLength = text.length();
        int i,j;
        for(i = 0, j=1; i<textLength && j <patternLength; i++){
            j = dfa[text.charAt(i)][j];
        }
        if(j == patternLength){
            return i-patternLength; // pattern found
        }
        return textLength;
    }
}
