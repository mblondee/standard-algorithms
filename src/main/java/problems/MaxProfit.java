package problems;

/*
* Given an array of stock prices. If you can only buy and sell once what is the maximum profit you can make?
*
* dynamic programming solution O(n)
* */

public class MaxProfit {

    private int numberOfPrices;
    private int[] stockPrices;

    private int[] profit; // profit[i] = max profit when considering subarray stockPrices[0] ... stockPrices[i]

    public MaxProfit(int[] prices){
        stockPrices = prices;
        numberOfPrices = prices.length;
        profit = new int[numberOfPrices];
        createTable();
    }

    private void createTable(){
        int currentMin = stockPrices[0]; // current min price
        for(int i = 1; i < numberOfPrices; i++){
            if(stockPrices[i] < currentMin){
                currentMin = stockPrices[i];
            }
            profit[i] = Math.max(stockPrices[i] - currentMin, profit[i-1]);
        }
    }

    public int getMaxProfit(){
        return profit[numberOfPrices -1];
    }

    public static void main(String[] args){
        int[] prices = {100, 180, 260, 310, 40, 535, 695};
        MaxProfit max = new MaxProfit(prices);
        System.out.println(max.getMaxProfit());
    }
}
