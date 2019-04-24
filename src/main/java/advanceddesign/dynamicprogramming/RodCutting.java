package advanceddesign.dynamicprogramming;

/*
* use of dynamic programming to solve a problem in deciding where to cut steel rods
* Given a rod of length and p_i the price charged for a rod of length i, determine the maximum revenue r_n obtained
* by cutting up the rod and selling the pieces.
*
* if revenue[i] corresponds to the maximum revenue when cutting a rod of length i and prices[n] the price for a rod of length n
* then
* revenue[n] = max(prices[1] + revenue[n-1], prices[2] + revenue[n-2] , ... , prices[n] + revenue[0])
* */

public class RodCutting {

    private int[] prices; // array of prices
    private int rodLength; // length of rod

    private int[] revenue; //revenue for optimal decomposition given index length
    private int[] size; //optimal size of first piece that is cut off (given index length)

    private static int MINVALUE = Integer.MIN_VALUE;


    public RodCutting(int[] prices, int rodLength){
        this.prices = prices;
        this.rodLength = rodLength;
        revenue = new int[rodLength + 1];
        size = new int[rodLength + 1];
        computeOptimal();
    }

    private void computeOptimal(){
        for(int i = 1; i<=rodLength; i++){
            int currentRevenue = MINVALUE;
            for(int j = 1; j <= i; j++){
                if (currentRevenue < prices[j] + revenue[i-j]){
                    currentRevenue = prices[j] + revenue[i-j];
                    // update choice
                    size[i] = j;
                }
            }
            revenue[i] = currentRevenue;
        }
    }

    public int[] getRevenue(){
        return revenue;
    }

    public int[] getSize(){
        return size;
    }

    public int getOptimalRevenue(){
        return revenue[rodLength];
    }



    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append("Optimal revenue: ");
        string.append(getOptimalRevenue());
        string.append("\n");
        string.append("lengths of pieces: ");
        int n = rodLength;
        while(n>0){
            string.append(size[n]); // length of first piece cut off
            string.append(" ");
            n = n - size[n];
        }

        return string.toString();
    }

    public static void main(String[] args){
        int[] prices = new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        //int rodLength = prices.length-1;
        int rodLength = 7;
        RodCutting rodCutting = new RodCutting(prices, rodLength);
        System.out.println(rodCutting);
    }

}
