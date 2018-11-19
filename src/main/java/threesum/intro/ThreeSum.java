package threesum.intro;

import help.libraries.ReadFileHelper;
import help.libraries.Stopwatch;


public class ThreeSum {

    //return number of triples (i,j,k) such that a[i]+a[j]+a[k] == 0
    // a ia array of integers -> get all such triples from array
    public static int count(int[] a){
        int n = a.length;
        int count = 0;
        for (int i=0; i<n; i++){
            for (int j = i+1; j<n; j++){
                for (int k = j+1 ; k<n ; k++){
                    if(a[i] + a[j] + a[k] == 0){
                        count ++;
                    }
                }
            }
        }
        return count;

    }

    public static void main(String[] args){
        String fileName = "/Users/marjonblondeel/Documents/algo-data/src/main/resources/1Kints.txt";
        //String fileName = "/Users/marjonblondeel/Documents/algo-data/src/main/resources/2Kints.txt";
        int[] intsToCheck = ReadFileHelper.readFileInts(fileName);

        Stopwatch timer = new Stopwatch();
        int count = count(intsToCheck);
        System.out.println("elapsed time = " + timer.elapsedTime());
        System.out.println("count= " + count);


    }



}
