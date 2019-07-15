package problems.leetcode.leetcode3;

/*Given two non-negative integers num1 and num2 represented as strings,
        return the product of num1 and num2, also represented as a string.*/

public class MultiplyStrings_43 {

    public static String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();

        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }


        String total = "";

        // keep track of extra zeros
        int keepTrack = 0;


        for(int i = len1-1; i >= 0; i--){
            String result = "";
            int counter = keepTrack;
            while(counter > 0){
                result = result + "0";
                counter--;
            }
            int carry = 0;
            for(int j = len2-1; j>=0; j--){
                int resultPart = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + carry;
                if(j == 0){
                    result = resultPart + result;
                }
                else {
                    carry = resultPart / 10;
                    result = (resultPart % 10) + result;
                }
            }
            total = add(total, result);
            keepTrack++;

        }

        return total;

    }

    public static String add(String num1, String num2){
        int len1 = num1.length();
        int len2 = num2.length();
        int i = len1-1;
        int j = len2-1;

        int carry = 0;
        String added = "";

        while(i >= 0 && j >= 0){
            int result = num1.charAt(i) - '0' + num2.charAt(j) - '0' + carry;
            carry = result/10;
            added = result%10 + added;
            i--;
            j--;
        }

/*        if(i < 0 && j < 0 && carry != 0){
            added = carry + added;
        }*/

        while(i >= 0){
            int result = num1.charAt(i) - '0' + carry;
            carry = result/10;
            added = result%10 + added;
            i--;
        }

        while(j >= 0){
            int result = num2.charAt(j) - '0' + carry;
            carry = result/10;
            added = result%10 + added;
            j--;
        }

        if(carry != 0){
            added = carry + added;
        }





        return added;

    }
}
