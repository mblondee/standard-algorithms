package advanceddesign.dynamicprogramming;

/*
* 0-1 Knapsack problem: A thief robbing a store finds n items. The ith item has a value vi and weight wi (integers). He can
* carry at most W weight (integer) in his knapsack. Which items should he take.
*
* Dynamic programming solution:
* We make a table maxValue of dimensions n+1 x W+1
* maxValue[i][w] is the max possible value if you can choose from items 1 until i and have a knapsack of weight w
*
* if you do not include item i then:
* maxValue[i][w] = maxValue[i-1][w]
* if you do include item i then:
* maxValue[i][w] = maxValue[i-1][w-wi] + vi
*
* Hence:
* maxValue[i][w] = max(maxValue[i-1][w], maxValue[i-1][w-wi] + vi) if wi < w
* maxValue[i][w] = maxValue[i-1][w] else
*
* running time of computing the maxValue table takes O(nW) time
* computing the set of items in the knapsack given the maxValue table takes O(W+n) time
*
* */


import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private int[][] maxValue;
    private int totalWeight; // weight of the knapsack
    private int[] values; // values of items
    private int[] weights; // weights of items

    private int numberOfItems;


    public Knapsack(int[] values, int[] weights, int totalWeight){
        this.values = values;
        this.weights = weights;
        this.totalWeight = totalWeight;
        this.numberOfItems = values.length;
        maxValue = new int[numberOfItems + 1][totalWeight + 1];
        makeTable();
    }

    private void makeTable(){
        for(int i = 1; i <= numberOfItems; i++){
            for(int w = 1; w<= totalWeight; w++){
                // value and weight of item is in resp. values[i-1] and weights[i-1]
                if(weights[i-1] > w){
                    // may not be included
                    maxValue[i][w] = maxValue[i-1][w];
                }
                else{
                    maxValue[i][w] = Math.max(maxValue[i-1][w], values[i-1] + maxValue[i-1][w-weights[i-1]]);
                }
            }
        }
    }

    public int[][] getMaxValue(){
        return maxValue;
    }

    public int getTotalValue(){
        return maxValue[numberOfItems][totalWeight];
    }

    public List<Integer> getItems(){
        List<Integer> listOfItems = new ArrayList<>();

        int item = numberOfItems;
        int weight = totalWeight;

        // check which items have been included
        // if maxValue[item][weight] == maxValue[item-1][weight], it has not been included
        while(item > 0){
            if(maxValue[item][weight] == maxValue[item-1][weight]){
                item --;
            }
            else{
                listOfItems.add(item);
                weight -= weights[item-1]; // weight of item is in weights[item-1]
                item--;
            }
        }

        return listOfItems;
    }



    public static void main(String[] args){
        int weight = 5;
        int[] values = {100, 20, 60, 40};
        int[] weights = {3,2,4,1};
        Knapsack knapsack = new Knapsack(values, weights, weight);
        for(int[] row: knapsack.getMaxValue()){
            for (int el : row){
                System.out.printf("%4d ", el);

            }
            System.out.print("\n");
        }

        System.out.println("max value: " + knapsack.getTotalValue());
        System.out.println("items:");
        for(int el : knapsack.getItems()){
            System.out.printf("item: %1d value: %4d weight: %2d \n", el, values[el-1], weights[el-1]);
        }
    }

}
