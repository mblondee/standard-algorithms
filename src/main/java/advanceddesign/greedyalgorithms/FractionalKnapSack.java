package advanceddesign.greedyalgorithms;

/*
* fractional Knapsack problem: A thief robbing a store finds n items. The ith item has a value vi and weight wi (integers). He can
* carry at most W weight (integer) in his knapsack. Which items should he take in order to take as valuable a load as possible?
* He is allowed to take fractions of items.
*
* Solution using a greedy approach.
* We first compute the value per weight for each item.
* We take as much as possible of the item with the greatest value per weight.
* This is repeated with the item with the next greatest value per weight. Until the maximum weight has been reached.
*
* uses sort, worstcase complexity Omega(n log n)
* remainder of algorithm is O(n)
* * */

import java.util.Arrays;
import java.util.Comparator;


public class FractionalKnapSack {

    private Item[] items;
    private int totalWeight; // weight of the knapsack
    private double totalValue; // total value in knapsack


    private class Item{
        private int value;
        private int weight;
        private Double ratio;

        public Item(int value, int weight){
            this.value = value;
            this.weight = weight;
            this.ratio = new Double(value/weight);
        }

    }


    public FractionalKnapSack(int[] values, int[] weights, int totalWeight){

        // array of items
        items = new Item[values.length];
        for(int i = 0; i < values.length; i++){
            items[i] = new Item(values[i], weights[i]);
        }

        this.totalWeight = totalWeight;

        fillKnapsack();

    }

    public double getTotalValue(){
        return totalValue;
    }

    private void fillKnapsack(){
        // sort items  by ratio from high to low
        Comparator<Item> compareByRatio = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o2.ratio.compareTo(o1.ratio);
            }
        };
        Arrays.sort(items, compareByRatio);

        double currentWeight = 0.0;


        for(Item item : items){
            // there is room for 100% of the item
            if(currentWeight + item.weight <= totalWeight){
                totalValue += item.value;
                currentWeight += item.weight;
            }
            // there might be room left for a %
            else{
                // fraction to be taken is (weight left in knapsack)/(weight of item)
                double fraction = (totalWeight - currentWeight)/item.weight;
                totalValue += fraction*item.value;
                currentWeight += fraction*item.weight;
                break; // nothing more can be added
            }
        }

    }

    public static void main(String[] args){
        int weight = 50;
        int[] values = {60,40,100,120};
        int[] weights = {10,40,20,30};
        FractionalKnapSack knapSack = new FractionalKnapSack(values, weights, weight);
        System.out.println(knapSack.getTotalValue());

    }
}
