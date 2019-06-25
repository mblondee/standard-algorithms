package problems;


/*
* suppose you have a staircase of n steps and at each point you can 1 or 2 steps
* how many different ways are there to reach the top?
*
* this is actually fibonacci
* n = 1: one way
* n = 2: two ways
*
* n>=3: how can you get to step n?
* reach step n-1 + one step more (only one possibility)
* reach step n-2 + two steps or two individual steps but the latter is already counted in previous situation
*
* waysToReach(n) = waysToReach(n-1) + waysToReach(n-2)
*
* solution using dynamic programming: O(n)
* */

public class CountingSteps {

    private int[] waysToReach; // waysToReach[n] = number of ways to reach step n
    private int steps;

    public CountingSteps(int steps){
        if(steps < 1){
            throw new IllegalArgumentException();
        }
        this.steps = steps;
        this.waysToReach = new int[steps+1];
        createTable();
    }

    private void createTable(){
        waysToReach[0] = 0;
        waysToReach[1] = 1;
        waysToReach[2] = 2;
        for(int i = 3; i<steps+1; i++){
            waysToReach[i] = waysToReach[i-1] + waysToReach[i-2];
        }
    }

    public int getSteps(int n){
        return waysToReach[n];
    }

    public static void main(String[] args){
        CountingSteps steps = new CountingSteps(10);
        for(int i = 1; i <= 10; i++){
            System.out.println("number of steps to reach " + i + ": " + steps.getSteps(i));
        }
    }

}
