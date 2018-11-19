package percolation.simulation;

/*
* Class RunPercolationExperiment will run several PercolationSimulation
* */

import percolation.unionfind.impl.WeightedQuickUnionCompression;

import java.util.Arrays;
import java.util.OptionalDouble;

public class RunPercolationExperiment {

    private static final int SIZE = 100; // size grid
    private static final int TIMES = 50; // number of simulations
    private static double[] probabilities; // array to save results from each simulation


    public static void main(String[] args) {
        OptionalDouble probability = run();
        System.out.println("average probability is " + probability.getAsDouble());
    }

    // runs TIMES simulations and returns average of the probabilities
    private static OptionalDouble run(){
        probabilities = new double[TIMES];
        int iteration = 0;
        while(iteration < TIMES) {
            System.out.println("Simulation number " + iteration + 1);
            RunSimulation simulation = new RunSimulation(SIZE, new WeightedQuickUnionCompression(SIZE*SIZE + 2) );
            double result = simulation.run();
            probabilities[iteration] = result;
            iteration ++;
        }
        //calculate average of probabilites
        OptionalDouble average = Arrays.stream(probabilities).average();
        return average;
    }
}
