package problems.leetcode.leetcode2;

/*The count-and-say sequence is the sequence of integers with the first five terms as following:

        1.     1
        2.     11
        3.     21
        4.     1211
        5.     111221
        1 is read off as "one 1" or 11.
        11 is read off as "two 1s" or 21.
        21 is read off as "one 2, then one 1" or 1211.

        Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

        Note: Each term of the sequence of integers will be represented as a string.*/

// read off the digits of the previous member, counting the number of digits in groups of the same digit.

public class CountAndSay {

    public static String countAndSay(int n) {

        if(n == 1){
            return "1";
        }

        String previous = "1";
        StringBuffer next;

        while(n > 1){
            next = new StringBuffer();
            int i = 0;
            char current = previous.charAt(i);
            int currentCount = 1;
            while(i < previous.length()){
                if(i == previous.length() -1){
                    next.append(currentCount);
                    next.append(current);
                    i++;
                }

                else if(current == previous.charAt(i+1)){
                    currentCount++;
                    i++;
                }
                else{
                    next.append(currentCount);
                    next.append(current);
                    current = previous.charAt(i+1);
                    currentCount = 1;
                    i++;
                }
            }
            previous = next.toString();
            n--;
        }
        return previous;

    }
}
