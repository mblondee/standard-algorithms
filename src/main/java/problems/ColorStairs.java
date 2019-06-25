package problems;

/*
* Given stairs with n steps. You have to color the stairs in two different colors (red and blue) but two adjacent steps
* cannot be both red. How many ways are there to color the steps?
*
* suppose P(n) is number of possible colorings
* - number of possibilities with last step blue = P(n-1)
* - number of possibilities with last step red
* the penultimate has to be blue in this case and the number of possibilities for this case is P(n-2)
* hence P(n) = P(n-1) + P(n-2)
* P(1) = 2
* P(2) = 3
*
* dynamic programming approach
* */

public class ColorStairs {
    private int[] possibleColorings;
    private int n;

    public ColorStairs(int n){
        possibleColorings = new int[n+1];
        this.n = n;
        createTable();
    }

    private void createTable(){
        possibleColorings[1] = 2;
        possibleColorings[2] = 3;

        for(int i = 3; i <= n; i++){
            possibleColorings[i] = possibleColorings[i-1] + possibleColorings[i-2];
        }
    }

    public int getPossibilities(){
        return possibleColorings[n];
    }

    public static void main(String[] args){
        ColorStairs stairs = new ColorStairs(3);
        System.out.println(stairs.getPossibilities());


    }


}
