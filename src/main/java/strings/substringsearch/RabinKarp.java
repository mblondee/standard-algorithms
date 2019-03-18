package strings.substringsearch;

/*
 * Rabin Karp implementation of substring search: search for pattern in text
 * monte carlo version
 * */

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
    private int RADIX;
    private int patternLength;
    private long q; //a large prime
    private long patternHash; // pattern hashvalue
    private long RM; // radix^(patternLength-1) % q
    // used when shifting to the right h_{i+1} = (h_i - char_{i}RM) *radix + char_{i+m}

    public RabinKarp(String pattern){
        RADIX = 256;
        patternLength = pattern.length();
        q = getRandomPrime();

        RM = 1;
        for(int i = 1; i<patternLength; i++){
            RM = (RADIX*RM) %q;
        }
        patternHash = hash(pattern, patternLength);
    }


    public int search(String text){
        int length = text.length();
        if(length < patternLength){
            return length;
        }

        long textHash = hash(text, patternLength);

        if(patternHash == textHash){
            return 0;
        }

        for(int i = patternLength; i<length; i++){
            //remove leading digit + add digit at end, textHash + q to make positive
            textHash = (textHash + q - RM*text.charAt(i-patternLength) % q) %q;
            textHash = (textHash *RADIX + text.charAt(i)) % q;


            int offset = i - patternLength + 1;
            if(patternHash == textHash){
                return offset;
            }
        }

        // no match
        return length;



    }

    private long hash(String string, int length){
        long hash = 0;
        for(int j = 0; j<length; j++){
            hash = (RADIX*hash + string.charAt(j))%q;
        }
        return hash;
    }


    // return a random 31-bit prime
    private static long getRandomPrime(){
        return BigInteger.probablePrime(31, new Random()).longValue();
    }
}
