package datacompression;

/*
* static methods
* compress and expand a string using run-length encoding
* lossless data compression in which runs of chars (sequences in which the same char value occurs)
* are stored in the same data value (count + value)
* */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLength {

    public static String compress(String input){
        StringBuilder compressed = new StringBuilder();


        int count;

        for(int i = 0; i < input.length(); i++){
            // count occurrences of char at index i
            count = 1;

            // check how many of the next chars are the same
            while(i+1<input.length() && input.charAt(i) == input.charAt(i+1)){
                count ++;
                i++;
            }

            compressed.append(count);
            compressed.append(input.charAt(i));
        }
        return compressed.toString();

    }

    public static String decompress(String compressed){
        StringBuilder output = new StringBuilder();
        //looking for either at least one number in [0-9]
        //or a string [a-zA-Z]
        Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");
        //find patterns in compressed
        Matcher matcher = pattern.matcher(compressed);

        while(matcher.find()){
            int number = Integer.parseInt(matcher.group());
            // find char
            matcher.find();
            while(number != 0){
                output.append(matcher.group());
                number --;
            }
        }
        return output.toString();

    }
}
