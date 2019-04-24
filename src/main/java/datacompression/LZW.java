package datacompression;

/*
 * compress and decompress a string using LZW (Lempel-Ziv-Welch) compression
 * lossless datacompression
 * reading sequences of symbols, grouping them into strings and converting the strings into codes
 * */




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW {

    private static int RADIX = 256; // extended ascii
    //private static int CODEWORDS = 4096; // number of table entries (codes 0-255 assigned to single bytes from the input)

    /*
    * compress input string to a list of output integers
    * */
    public static List<Integer> compress(String input){

        List<Integer> output = new ArrayList<>();

        // create dictionary: every char corresponds to its ascii-value
        Map<String, Integer> dictionary = new HashMap<>();
        for(int i = 0; i< RADIX; i++){
            dictionary.put("" + (char) i, i);
        }
        // first unfilled code
        int nextCode = RADIX;

        String lookUp = "";

        for(int i = 0; i < input.length(); i++){
            // find longest prefix
            String lookUpNext = lookUp + input.charAt(i);
            if(dictionary.containsKey(lookUpNext)){
                // we might be able to find a longer prefix
                lookUp = lookUpNext;
            }
            else{
                //
                output.add(dictionary.get(lookUp));
                // add new prefix to dictionary
                dictionary.put(lookUpNext, nextCode++);
                lookUp = "" + input.charAt(i);
            }
        }

        if(! lookUp.equals("")){
            output.add(dictionary.get(lookUp));
        }


        return output;
    }


    /*
     * decompress a list of output integers to a string
     * */
    public static String decompress(List<Integer> compressed){
        // create dictionary: every char corresponds to its ascii-value
        Map<Integer, String> dictionary = new HashMap<>();
        for(int i = 0; i< RADIX; i++){
            dictionary.put(i, "" + (char) i);
        }
        int nextCode = RADIX;


        String lookup = "" + (char)(int) compressed.remove(0);
        StringBuffer result = new StringBuffer(lookup);

        for(int k: compressed){
            String entry;
            // k is already in list
            if(dictionary.containsKey(k)){
                entry = dictionary.get(k);
            }
            // k is not yet in list
            else if(k == nextCode){
                entry = lookup + lookup.charAt(0);
            }
            else{
                throw new IllegalArgumentException("badly compressed input");
            }
            result.append(entry);

            dictionary.put(nextCode++, lookup + entry.charAt(0));

            lookup = entry;
        }

        return result.toString();



    }
}
