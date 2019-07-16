package threesum.intro;

import help.libraries.StdRandom;
import help.libraries.Stopwatch;

public class DoublingRatio {

    private static final int MAXIMUM = 1000000;

    // do not instantiate
    private DoublingRatio(){}

    // return array of size n with random 6-digit integers
    // return time to call ThreeSum_15.count();
    public static double timeTrial(int n){
        int[] a = new int[n];
        for (int i = 0; i<n; i++){
            a[i] = StdRandom.uniform(-MAXIMUM, MAXIMUM);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    // print table of running times to call ThreeSum_15.count()
    // for arrays of size 250, 500, 1000, 2000 and so on
    // as well as ratio of running times between successive array sizes
    public static void main(String[] args){
        // start with array of size 125
        double prev = timeTrial(125);
        for (int n = 250; true; n +=n){
            double time = timeTrial(n);
            System.out.printf("%7d %7.1f %5.1f\n", n, time, time/prev);
            prev = time;
        }
    }


}
