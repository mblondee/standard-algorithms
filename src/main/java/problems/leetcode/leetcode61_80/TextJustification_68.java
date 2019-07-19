package problems.leetcode.leetcode61_80;

/*Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters
 and is fully (left and right) justified.

        You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
        Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

        Extra spaces between words should be distributed as evenly as possible.
        If the number of spaces on a line do not divide evenly between words,
        the empty slots on the left will be assigned more spaces than the slots on the right.

        For the last line of text, it should be left justified and no extra space is inserted between words.

        Note:

        A word is defined as a character sequence consisting of non-space characters only.
        Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
        The input array words contains at least one word.*/

import java.util.ArrayList;
import java.util.List;

public class TextJustification_68 {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int current = 0;
        while(current < words.length){

            // step 1: find word that have to be put on one line starting from word in index current
            int currentLength = words[current].length();
            // can we add more words to this line?
            int next = current+1;
            while(next < words.length){
                if(currentLength + 1 + words[next].length() > maxWidth){
                    // current + space + next word is too long
                    break;
                }
                currentLength += 1 + words[next].length();
                next++;
            }

            // step 2: check special cases and add spaces
            StringBuffer str = new StringBuffer();
            // next is not a valid index -> we are in the last line
            // also if we have only one word: line is left justified
            if(next == words.length || next == current +1){
                // line is left justified
                for(int i = current; i < next; i++){
                    str.append(words[i] + " ");
                }
                // maybe we have added an extra " "!
                if(str.length() > maxWidth){
                    str.deleteCharAt(str.length() -1);
                }
                // if not maxWidth, add " " until the end of the line
                for(int i = str.length(); i < maxWidth; i++){
                    str.append(" ");
                }
            }
            // suppose we have only one word:

            else{
                // line is middle justified
                // number of spaces needed : spaces
                int totalSpaces = maxWidth - currentLength;
                // number of spaces between the words
                int spaces = totalSpaces/(next - current -1); // we know this cannot be 0 because
                // we have already dealt with the case next = current+1
                int rest = totalSpaces % (next - current -1); // rest of division to obtain the spaces
                // (if rest not 0 more spaces assigned to the left: the first rest will have an extra space)
                for(int i = current; i < next; i++){
                    str.append(words[i]);
                    // how many spaces should be added?
                    // no need to add after last word
                    if(i < next -1){
                        str.append(" "); //
                        // first rest get an extra space
                        if(i - current < rest){
                            str.append(" ");
                        }
                        // spaces to pad
                        for(int j = 0; j < spaces; j++){
                            str.append(" ");
                        }
                    }
                }

            }



            // set current to next
            current = next;
            result.add(str.toString());

        }



        return result;
    }
}
