package problems.leetcode.leetcode1_20;

/*
* The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
* P   A   H   N
*  A P L S I I  G
*   Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows.
* */

public class ZigZagConversion_6 {

    public static String convert(String s, int numRows){
        if(numRows == 1){
            return s;
        }
        StringBuffer[] rowByRow = new StringBuffer[numRows];
        for(int i = 0; i < numRows ; i++){
            rowByRow[i] = new StringBuffer();
        }

        int row = 0;
        boolean down = true; // going down or up

        char[] chars = s.toCharArray();

        for(char c : chars){
            // add c in correct row in rowByRow
            rowByRow[row].append(c);

            // which is next row?

            if(row == numRows - 1){
                down = false;
            }
            if(row == 0){
                down = true;
            }

            if(down){
                row ++;
            }
            else{
                row --;
            }


        }


        StringBuffer result = new StringBuffer();
        for(StringBuffer str : rowByRow){
            result.append(str);
        }

        return result.toString();
    }
}
