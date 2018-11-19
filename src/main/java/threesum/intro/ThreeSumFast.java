package threesum.intro;

import misc.BinarySearch;
import help.libraries.Stopwatch;

import static help.libraries.ReadFileHelper.readFileInts;

import java.util.Arrays;

public class ThreeSumFast {

    // count triples that sum to 0
    public static int count(int[] a){
        Arrays.sort(a);
        int n = a.length;
        int count = 0;
        for (int i=0; i<n; i++){
            for (int j = i+1; j<n; j++){
                // to avoid doublecounting we check for >j
                if (BinarySearch.rank(-a[i] - a[j],a) > j){
                    count++;
                }
            }
        }
        return count;

    }

    public static void main(String[] args){
        String fileName = "/Users/marjonblondeel/Documents/algo-data/src/main/resources/1Kints.txt";
        //String fileName = "/Users/marjonblondeel/Documents/algo-data/src/main/resources/2Kints.txt";
        int[] intsToCheck = readFileInts(fileName);

        Stopwatch timer = new Stopwatch();
        int count = count(intsToCheck);
        System.out.println("elapsed time = " + timer.elapsedTime());
        System.out.println("count= " + count);


    }
}
