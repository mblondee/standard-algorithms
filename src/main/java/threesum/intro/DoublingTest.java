package threesum.intro;

import help.libraries.StdRandom;
import help.libraries.Stopwatch;

public class DoublingTest {

    private static final int MAXIMUM = 1000000;


    // return array of size n with random 6-digit integers
    // return time to call ThreeSum.count();
    public static double timeTrial(int n){
        int[] a = new int[n];
        for (int i = 0; i<n; i++){
            a[i] = StdRandom.uniform(-MAXIMUM, MAXIMUM);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    // generate sequence of random input arrays
    // print size array + running time ThreeSum for each array
    // doubling array size at each repetition
    public static void main(String[] args){
        for (int n = 250; true; n+=n){
            double time = timeTrial(n);
            System.out.printf("%7d %7.1f\n", n, time);
        }
    }
}
