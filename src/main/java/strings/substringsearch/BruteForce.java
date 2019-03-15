package strings.substringsearch;

/*
* brute force implementation of substring search: search for pattern in text
* */


public class BruteForce {

    /*
    * return offset of first match
    * return length of text if no match exists
    * */
    public static int search(String pattern, String text){
        int patternLength = pattern.length();
        int textLength = text.length();

        // i checks all possible starting points in text
        for(int i = 0; i <= textLength-patternLength; i++){
            //j will check if pattern starts at i
            int j;
            for(j = 0; j< patternLength; j++){
                if(pattern.charAt(j) != text.charAt(i+j)){
                    break; // go to next i
                }
            }
            // check if pattern has been found
            if (j == patternLength){
                return i;
            }
        }
        return textLength;
    }
}
