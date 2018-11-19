package help.libraries;

//Stopwatch computes running time

public class Stopwatch {

    private final long start;

    // initialize
    public Stopwatch() {
        start = System.currentTimeMillis();
    }


    //returns elapsed CPU time in seconds
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

}
